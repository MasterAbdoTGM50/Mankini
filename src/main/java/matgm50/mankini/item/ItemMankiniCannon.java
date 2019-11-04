package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.hostile.EntityMankiniSkeleton;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCannon extends Item {

    public ItemMankiniCannon(Item.Properties builder) {
        super(builder.group(Mankini.tabMankini).maxStackSize(1));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity PlayerEntity = (PlayerEntity)entityLiving;
            boolean flag = PlayerEntity.abilities.isCreativeMode;
            ItemStack itemstack = MankiniHelper.findMankini(PlayerEntity);

            int i = this.getUseDuration(stack) - timeLeft;
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(ModItems.dyeable_mankini);
                }

                float f = getMankiniVelocity(i);
                if (!((double)f < 0.1D)) {
                    if (!worldIn.isRemote) {
                        EntityMankiniCapsule entityCapsule = createMankini(worldIn, itemstack.copy(), entityLiving);
                        entityCapsule.shoot(PlayerEntity, PlayerEntity.rotationPitch, PlayerEntity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        worldIn.addEntity(entityCapsule);
                    }

                    worldIn.playSound((PlayerEntity)null, PlayerEntity.posX, PlayerEntity.posY, PlayerEntity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (MankiniHelper.isMankini(itemstack) && !PlayerEntity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            PlayerEntity.inventory.deleteStack(itemstack);
                        }
                    }

                    PlayerEntity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !MankiniHelper.findMankini(playerIn).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !flag) {
            return flag ? new ActionResult<>(ActionResultType.PASS, itemstack) : new ActionResult<>(ActionResultType.FAIL, itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 24000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
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

    public EntityMankiniCapsule createMankini(World worldIn, ItemStack stack, LivingEntity livingBase) {
        EntityMankiniCapsule mankiniCapsule = new EntityMankiniCapsule(worldIn, livingBase, stack);
        if(livingBase instanceof EntityMankiniSkeleton) {
            stack.setDamage(random.nextInt(stack.getMaxDamage()));
            mankiniCapsule = new EntityMankiniCapsule(worldIn, livingBase, stack, false);
        }
        return mankiniCapsule;
    }
}

    