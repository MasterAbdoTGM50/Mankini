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
	public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
		if (livingEntity instanceof Player player) {
			ItemStack mankiniStack = MankiniHelper.findMankini(player);

			int i = this.getUseDuration(stack) - timeLeft;
			if (i < 0) return;

			if (!mankiniStack.isEmpty() || player.getAbilities().instabuild) {
				if (mankiniStack.isEmpty()) {
					mankiniStack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());
				}

				float f = getMankiniVelocity(i);
				if (!((double) f < 0.1D)) {
					if (!level.isClientSide) {
						MankiniCapsuleEntity entityCapsule = createMankini(level, mankiniStack.copy(), livingEntity);
						entityCapsule.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
						entityCapsule.setOwner(player);

						level.addFreshEntity(entityCapsule);
					}

					level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if (MankiniHelper.isMankini(mankiniStack) && !player.getAbilities().instabuild) {
						mankiniStack.shrink(1);
						if (mankiniStack.isEmpty()) {
							player.getInventory().removeItem(mankiniStack);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
		ItemStack itemstack = player.getItemInHand(handIn);
		boolean flag = !MankiniHelper.findMankini(player).isEmpty();

		InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, player, handIn, flag);
		if (ret != null) return ret;

		if (!player.getAbilities().instabuild && !flag) {
			return flag ? InteractionResultHolder.pass(itemstack) : InteractionResultHolder.fail(itemstack);
		} else {
			player.startUsingItem(handIn);
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
		float f = (float) charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	public MankiniCapsuleEntity createMankini(Level level, ItemStack stack, LivingEntity livingBase) {
		MankiniCapsuleEntity capsule = new MankiniCapsuleEntity(level, livingBase, stack);
		if (livingBase instanceof MankiniSkeletonEntity) {
			stack.setDamageValue(level.random.nextInt(stack.getMaxDamage()));
			capsule = new MankiniCapsuleEntity(level, livingBase, stack, false);
		}
		return capsule;
	}
}

    