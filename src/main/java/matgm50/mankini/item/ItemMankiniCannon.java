package matgm50.mankini.item;

import matgm50.mankini.entity.hostile.MankiniSkeletonEntity;
import matgm50.mankini.entity.projectiles.MankiniCapsuleEntity;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCannon extends Item {

    public ItemMankiniCannon(Item.Properties builder) {
        super(builder.stacksTo(1));
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player playerEntity) {
            ItemStack itemstack = MankiniHelper.findMankini(playerEntity);

            int i = this.getUseDuration(stack) - timeLeft;
            if (i < 0) return;

            if (!itemstack.isEmpty() || playerEntity.getAbilities().instabuild) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());
                }

                float f = getMankiniVelocity(i);
                if (!((double)f < 0.1D)) {
                    if (!worldIn.isClientSide) {
                        MankiniCapsuleEntity entityCapsule = createMankini(worldIn, itemstack.copy(), entityLiving);
                        entityCapsule.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, f * 3.0F, 1.0F);
                        entityCapsule.setOwner(playerEntity);

                        worldIn.addFreshEntity(entityCapsule);
                    }

                    worldIn.playSound((Player)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (worldIn.random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (MankiniHelper.isMankini(itemstack) && !playerEntity.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerEntity.getInventory().removeItem(itemstack);
                        }
                    }

                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        boolean flag = !MankiniHelper.findMankini(playerIn).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.getAbilities().instabuild && !flag) {
            return flag ? InteractionResultHolder.pass(itemstack) : InteractionResultHolder.fail(itemstack);
        } else {
            playerIn.startUsingItem(handIn);
            return InteractionResultHolder.success(itemstack);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 24000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    //This is for the skeleton
    public static float getMankiniVelocity(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public MankiniCapsuleEntity createMankini(Level worldIn, ItemStack stack, LivingEntity livingBase) {
        MankiniCapsuleEntity mankiniCapsule = new MankiniCapsuleEntity(worldIn, livingBase, stack);
        if(livingBase instanceof MankiniSkeletonEntity) {
            stack.setDamageValue(worldIn.random.nextInt(stack.getMaxDamage()));
            mankiniCapsule = new MankiniCapsuleEntity(worldIn, livingBase, stack, false);
        }
        return mankiniCapsule;
    }
}

    