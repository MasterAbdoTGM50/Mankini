package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.IMankini;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class MankiniEvokerEntity extends SpellcasterIllager {
	private Player wololoTarget;

	public MankiniEvokerEntity(EntityType<? extends MankiniEvokerEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public MankiniEvokerEntity(Level worldIn) {
		super(ModRegistry.MANKINI_EVOKER.get(), worldIn);
		this.xpReward = 6;
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MankiniEvokerEntity.AICastingSpell());
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
		this.goalSelector.addGoal(5, new MankiniEvokerEntity.SpellcasterCastingSpellGoal());
		this.goalSelector.addGoal(6, new MankiniEvokerEntity.AIWololoSpell());
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, (new EntityAIMankiniTarget<>(this, Player.class, true)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
	}

	@Override
	public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.VILLAGER_NO;
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 12.0D)
				.add(Attributes.MAX_HEALTH, 24.0D);
	}

	private void setWololoTarget(@Nullable Player wololoTargetIn) {
		this.wololoTarget = wololoTargetIn;
	}

	@Nullable
	private Player getWololoTarget() {
		return this.wololoTarget;
	}

	@Override
	protected SoundEvent getCastingSoundEvent() {
		return null;
	}

	class AICastingSpell extends SpellcasterIllager.SpellcasterCastingSpellGoal {
		private AICastingSpell() {
			super();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (MankiniEvokerEntity.this.getTarget() != null) {
				MankiniEvokerEntity.this.getLookControl().setLookAt(MankiniEvokerEntity.this.getTarget(), (float) MankiniEvokerEntity.this.getMaxHeadYRot(), (float) MankiniEvokerEntity.this.getMaxHeadXRot());
			} else if (MankiniEvokerEntity.this.getWololoTarget() != null) {
				MankiniEvokerEntity.this.getLookControl().setLookAt(MankiniEvokerEntity.this.getWololoTarget(), (float) MankiniEvokerEntity.this.getMaxHeadYRot(), (float) MankiniEvokerEntity.this.getMaxHeadXRot());
			}

		}
	}

	public class AIWololoSpell extends SpellcasterIllager.SpellcasterUseSpellGoal {
		private final Predicate<Player> wololoSelector = this::hasMankini;

		public boolean hasMankini(Player playerIn) {
			Inventory inventory = playerIn.getInventory();
			boolean hasMankini = false;
			for (int i = 0; i < inventory.getContainerSize(); i++) {
				if (inventory.getItem(i).getItem() instanceof IMankini) {
					hasMankini = true;
					break;
				}
			}
			return !hasMankini;
		}

		public AIWololoSpell() {
			super();
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean canUse() {
			if (MankiniEvokerEntity.this.getTarget() != null) {
				return false;
			} else if (MankiniEvokerEntity.this.isCastingSpell()) {
				return false;
			} else if (MankiniEvokerEntity.this.tickCount < this.nextAttackTickCount) {
				return false;
			} else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(MankiniEvokerEntity.this.level(), MankiniEvokerEntity.this)) {
				return false;
			} else {
				List<Player> list = MankiniEvokerEntity.this.level().getEntitiesOfClass(Player.class, MankiniEvokerEntity.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D), this.wololoSelector);
				if (list.isEmpty()) {
					return false;
				} else {
					MankiniEvokerEntity.this.setWololoTarget(list.get(MankiniEvokerEntity.this.random.nextInt(list.size())));
					return true;
				}
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean canContinueToUse() {
			return MankiniEvokerEntity.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void stop() {
			super.stop();
			MankiniEvokerEntity.this.setWololoTarget((Player) null);
		}

		protected void performSpellCasting() {
			Player targetPlayer = MankiniEvokerEntity.this.getWololoTarget();
			if (targetPlayer != null && targetPlayer.isAlive()) {
				Inventory playerInv = targetPlayer.getInventory();
				ItemStack wornStack = playerInv.armor.get(2);
				boolean flag = !(wornStack.getItem() instanceof IMankini);

				if (flag) {
					ItemStack kiniStack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

					if (wornStack.isEmpty()) {
						playerInv.setItem(38, kiniStack);
					} else if (!wornStack.isEmpty() && !(wornStack.getItem() instanceof IMankini)) {
						playerInv.setItem(38, kiniStack);
						if (playerInv.getFreeSlot() != -1) {
							targetPlayer.spawnAtLocation(wornStack, 0.5F);
						} else {
							playerInv.setItem(playerInv.getFreeSlot(), wornStack);
						}
					}
				}
			}

		}

		protected int getCastWarmupTime() {
			return 40;
		}

		protected int getCastingTime() {
			return 60;
		}

		protected int getCastingInterval() {
			return 140;
		}

		protected SoundEvent getSpellPrepareSound() {
			return SoundEvents.EVOKER_PREPARE_WOLOLO;
		}

		protected SpellcasterIllager.IllagerSpell getSpell() {
			return SpellcasterIllager.IllagerSpell.WOLOLO;
		}
	}
}