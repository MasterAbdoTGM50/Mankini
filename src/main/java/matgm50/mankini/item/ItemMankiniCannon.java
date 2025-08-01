package matgm50.mankini.item;

import matgm50.mankini.entity.hostile.MankiniSkeleton;
import matgm50.mankini.entity.projectiles.MankiniCapsule;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCannon extends Item {

	public ItemMankiniCannon(Item.Properties builder) {
		super(builder.stacksTo(1));
	}

	@Override
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
		if (livingEntity instanceof Player player) {
			ItemStack mankiniStack = MankiniHelper.findMankini(player);

			int i = this.getUseDuration(stack, livingEntity) - timeLeft;
			if (i < 0) return false;

			if (!mankiniStack.isEmpty() || player.getAbilities().instabuild) {
				if (mankiniStack.isEmpty()) {
					mankiniStack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());
				}

				float f = getMankiniVelocity(i);
				if (!((double) f < 0.1D)) {
					if (!level.isClientSide) {
						MankiniCapsule entityCapsule = createMankini(level, mankiniStack.copy(), livingEntity);
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
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		boolean flag = !MankiniHelper.findMankini(player).isEmpty();

		InteractionResult ret = EventHooks.onArrowNock(itemstack, level, player, hand, flag);
		if (ret != null) return ret;

		if (!player.getAbilities().instabuild && !flag) {
			return flag ? InteractionResult.PASS : InteractionResult.FAIL;
		} else {
			player.startUsingItem(hand);
			return InteractionResult.SUCCESS;
		}
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
		return 24000;
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.BOW;
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

	public MankiniCapsule createMankini(Level level, ItemStack stack, LivingEntity livingBase) {
		MankiniCapsule capsule = new MankiniCapsule(level, livingBase, stack);
		if (livingBase instanceof MankiniSkeleton) {
			stack.setDamageValue(level.random.nextInt(stack.getMaxDamage()));
			capsule = new MankiniCapsule(level, livingBase, stack, false);
		}
		return capsule;
	}
}

    