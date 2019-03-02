package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.hostile.EntityMankiniSkeleton;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCannon extends Item {

    public ItemMankiniCannon(Item.Properties builder) {
        super(builder.group(Mankini.tabMankini).maxStackSize(1));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            boolean flag = entityplayer.abilities.isCreativeMode;
            ItemStack itemstack = MankiniHelper.findMankini(entityplayer);

            int i = this.getUseDuration(stack) - timeLeft;
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(ModItems.dyeable_mankini);
                }

                float f = getMankiniVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = entityplayer.abilities.isCreativeMode || (itemstack.getItem() instanceof IMankini);
                    if (!worldIn.isRemote) {
                        EntityMankiniCapsule entityCapsule = createMankini(worldIn, itemstack, entityLiving);
                        entityCapsule.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        stack.damageItem(1, entityplayer);
                        worldIn.spawnEntity(entityCapsule);
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !entityplayer.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                    entityplayer.addStat(StatList.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !MankiniHelper.findMankini(playerIn).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !flag) {
            return flag ? new ActionResult<>(EnumActionResult.PASS, itemstack) : new ActionResult<>(EnumActionResult.FAIL, itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 24000;
    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.BOW;
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

    public EntityMankiniCapsule createMankini(World worldIn, ItemStack stack, EntityLivingBase livingBase) {
        EntityMankiniCapsule mankiniCapsule = new EntityMankiniCapsule(worldIn, livingBase, stack);
        if(livingBase instanceof EntityMankiniSkeleton) {
            stack.setDamage(random.nextInt(stack.getMaxDamage()));
            mankiniCapsule = new EntityMankiniCapsule(worldIn, livingBase, stack, false);
        }
        return mankiniCapsule;
    }
}

    