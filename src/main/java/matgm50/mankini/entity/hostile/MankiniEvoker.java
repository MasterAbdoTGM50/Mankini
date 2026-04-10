package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.IMankini;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.illager.SpellcasterIllager;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class MankiniEvoker extends SpellcasterIllager {
	private Player wololoTarget;

	public MankiniEvoker(EntityType<? extends MankiniEvoker> type, Level level) {
		super(type, level);
		this.xpReward = 6;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MankiniEvoker.AICastingSpell());
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
		this.goalSelector.addGoal(5, new MankiniEvoker.SpellcasterCastingSpellGoal());
		this.goalSelector.addGoal(6, new MankiniEvoker.AIWololoSpell());
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, (new EntityAIMankiniTarget<>(this, Player.class, true)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
	}

	@Override
	public void applyRaidBuffs(ServerLevel level, int wave, boolean unused) {

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
			if (MankiniEvoker.this.getTarget() != null) {
				MankiniEvoker.this.getLookControl().setLookAt(MankiniEvoker.this.getTarget(), (float) MankiniEvoker.this.getMaxHeadYRot(), (float) MankiniEvoker.this.getMaxHeadXRot());
			} else if (MankiniEvoker.this.getWololoTarget() != null) {
				MankiniEvoker.this.getLookControl().setLookAt(MankiniEvoker.this.getWololoTarget(), (float) MankiniEvoker.this.getMaxHeadYRot(), (float) MankiniEvoker.this.getMaxHeadXRot());
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
		@Override
		public boolean canUse() {
			if (MankiniEvoker.this.getTarget() != null) {
				return false;
			} else if (MankiniEvoker.this.isCastingSpell()) {
				return false;
			} else if (MankiniEvoker.this.tickCount < this.nextAttackTickCount) {
				return false;
			} else if (!EventHooks.canEntityGrief(getServerLevel(MankiniEvoker.this), MankiniEvoker.this)) {
				return false;
			} else {
				List<Player> list = MankiniEvoker.this.level().getEntitiesOfClass(Player.class, MankiniEvoker.this.getBoundingBox().inflate(16.0D, 4.0D, 16.0D), this.wololoSelector);
				if (list.isEmpty()) {
					return false;
				} else {
					MankiniEvoker.this.setWololoTarget(list.get(MankiniEvoker.this.random.nextInt(list.size())));
					return true;
				}
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		@Override
		public boolean canContinueToUse() {
			return MankiniEvoker.this.getWololoTarget() != null && this.attackWarmupDelay > 0;
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		@Override
		public void stop() {
			super.stop();
			MankiniEvoker.this.setWololoTarget((Player) null);
		}

		@Override
		protected void performSpellCasting() {
			Player targetPlayer = MankiniEvoker.this.getWololoTarget();
			if (targetPlayer != null && targetPlayer.isAlive()) {
				Inventory playerInv = targetPlayer.getInventory();
				ItemStack wornStack = targetPlayer.getItemBySlot(EquipmentSlot.CHEST);
				boolean flag = !(wornStack.getItem() instanceof IMankini);

				if (flag) {
					ItemStack kiniStack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

					if (wornStack.isEmpty()) {
						playerInv.setItem(38, kiniStack);
					} else if (!wornStack.isEmpty() && !(wornStack.getItem() instanceof IMankini)) {
						playerInv.setItem(38, kiniStack);
						if (playerInv.getFreeSlot() != -1) {
							targetPlayer.spawnAtLocation(getServerLevel(level()), wornStack, 0.5F);
						} else {
							playerInv.setItem(playerInv.getFreeSlot(), wornStack);
						}
					}
				}
			}

		}

		@Override
		protected int getCastWarmupTime() {
			return 40;
		}

		@Override
		protected int getCastingTime() {
			return 60;
		}

		@Override
		protected int getCastingInterval() {
			return 140;
		}

		@Override
		protected SoundEvent getSpellPrepareSound() {
			return SoundEvents.EVOKER_PREPARE_WOLOLO;
		}

		@Override
		protected SpellcasterIllager.IllagerSpell getSpell() {
			return SpellcasterIllager.IllagerSpell.WOLOLO;
		}
	}
}