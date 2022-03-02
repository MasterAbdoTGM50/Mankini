package matgm50.mankini.entity.ai;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.IMankini;
import matgm50.mankini.item.ItemMankiniCannon;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class EntityAIMankiniCannon<T extends Monster & RangedAttackMob> extends Goal {
	private final T entity;
	private final double moveSpeedAmp;
	private int attackCooldown;
	private final float maxAttackDistance;
	private int attackTime = -1;
	private int seeTime;
	private boolean strafingClockwise;
	private boolean strafingBackwards;
	private int strafingTime = -1;

	public EntityAIMankiniCannon(T mob, double moveSpeedAmpIn, int attackCooldownIn, float maxAttackDistanceIn) {
		this.entity = mob;
		this.moveSpeedAmp = moveSpeedAmpIn;
		this.attackCooldown = attackCooldownIn;
		this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public void setAttackCooldown(int p_189428_1_) {
		this.attackCooldown = p_189428_1_;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean canUse() {
		return this.entity.getTarget() != null && this.isCannonInMainHand();
	}

	protected boolean isCannonInMainHand() {
		return !this.entity.getMainHandItem().isEmpty() && this.entity.getMainHandItem().getItem() == ModRegistry.MANKINI_CANNON.get();
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean canContinueToUse() {
		return (this.canUse() || !this.entity.getNavigation().isDone()) && this.isCannonInMainHand();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void start() {
		super.start();
		this.entity.setAggressive(true);
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by another one
	 */
	public void stop() {
		super.stop();
		this.entity.setAggressive(false);
		this.seeTime = 0;
		this.attackTime = -1;
		this.entity.stopUsingItem();
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		LivingEntity livingBase = this.entity.getTarget();
		if (livingBase != null) {
			if (livingBase instanceof Player player) {
				boolean flag = player.getInventory().armor.get(2).getItem() instanceof IMankini;
				boolean flag2 = false;

				for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
					if (player.getInventory().getItem(i).getItem() instanceof IMankini) {
						flag2 = true;
						break;
					}
				}

				if (!flag && !flag2) {
					doMankiniShooting(livingBase);
				}
			} else {
				doMankiniShooting(livingBase);
			}
		}
	}

	public void doMankiniShooting(LivingEntity entitylivingbase) {
		double d0 = this.entity.distanceToSqr(entitylivingbase.getX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getZ());
		boolean flag = this.entity.getSensing().hasLineOfSight(entitylivingbase);
		boolean flag1 = this.seeTime > 0;
		if (flag != flag1) {
			this.seeTime = 0;
		}

		if (flag) {
			++this.seeTime;
		} else {
			--this.seeTime;
		}

		if (!(d0 > (double) this.maxAttackDistance) && this.seeTime >= 20) {
			this.entity.getNavigation().stop();
			++this.strafingTime;
		} else {
			this.entity.getNavigation().moveTo(entitylivingbase, this.moveSpeedAmp);
			this.strafingTime = -1;
		}

		if (this.strafingTime >= 20) {
			if ((double) this.entity.getRandom().nextFloat() < 0.3D) {
				this.strafingClockwise = !this.strafingClockwise;
			}

			if ((double) this.entity.getRandom().nextFloat() < 0.3D) {
				this.strafingBackwards = !this.strafingBackwards;
			}

			this.strafingTime = 0;
		}

		if (this.strafingTime > -1) {
			if (d0 > (double) (this.maxAttackDistance * 0.75F)) {
				this.strafingBackwards = false;
			} else if (d0 < (double) (this.maxAttackDistance * 0.25F)) {
				this.strafingBackwards = true;
			}

			this.entity.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
			this.entity.lookAt(entitylivingbase, 30.0F, 30.0F);
		} else {
			this.entity.getLookControl().setLookAt(entitylivingbase, 30.0F, 30.0F);
		}

		if (this.entity.isUsingItem()) {
			if (!flag && this.seeTime < -60) {
				this.entity.stopUsingItem();
			} else if (flag) {
				int i = this.entity.getTicksUsingItem();
				if (i >= 20) {
					this.entity.stopUsingItem();
					((RangedAttackMob) this.entity).performRangedAttack(entitylivingbase, ItemMankiniCannon.getMankiniVelocity(i));
					this.attackTime = this.attackCooldown;
				}
			}
		} else if (--this.attackTime <= 0 && this.seeTime >= -60) {
			this.entity.startUsingItem(InteractionHand.MAIN_HAND);
		}
	}
}