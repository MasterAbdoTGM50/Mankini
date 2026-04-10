package matgm50.mankini.entity.boss;

import com.google.common.collect.ImmutableList;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class MankiniWither extends Monster implements RangedAttackMob {
	private static final EntityDataAccessor<Integer> FIRST_HEAD_TARGET = SynchedEntityData.defineId(MankiniWither.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> SECOND_HEAD_TARGET = SynchedEntityData.defineId(MankiniWither.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> THIRD_HEAD_TARGET = SynchedEntityData.defineId(MankiniWither.class, EntityDataSerializers.INT);
	private static final List<EntityDataAccessor<Integer>> HEAD_TARGETS = ImmutableList.of(FIRST_HEAD_TARGET, SECOND_HEAD_TARGET, THIRD_HEAD_TARGET);
	private static final EntityDataAccessor<Integer> INVULNERABILITY_TIME = SynchedEntityData.defineId(MankiniWither.class, EntityDataSerializers.INT);
	private final float[] xRotationHeads = new float[2];
	private final float[] yRotationHeads = new float[2];
	private final float[] xRotOHeads = new float[2];
	private final float[] yRotOHeads = new float[2];
	private final int[] nextHeadUpdate = new int[2];
	private final int[] idleHeadUpdates = new int[2];
	private int blockBreakCounter;
	private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(
			this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
	private static final TargetingConditions.Selector LIVING_ENTITY_SELECTOR = (livingEntity, serverLevel) -> !livingEntity.getType().is(EntityTypeTags.WITHER_FRIENDS)
			&& livingEntity.attackable();
	private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat()
			.range(20.0D).selector(LIVING_ENTITY_SELECTOR);

	public MankiniWither(EntityType<? extends MankiniWither> type, Level level) {
		super(type, level);
		this.setHealth(this.getMaxHealth());
		this.getNavigation().setCanFloat(true);
		this.xpReward = 50;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MankiniWither.DoNothingGoal());
		this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 0, false, false, LIVING_ENTITY_SELECTOR));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(FIRST_HEAD_TARGET, 0);
		builder.define(SECOND_HEAD_TARGET, 0);
		builder.define(THIRD_HEAD_TARGET, 0);
		builder.define(INVULNERABILITY_TIME, 0);
	}

	@Override
	public void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.putInt("Invul", this.getInvulTime());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		this.setInvulTime(input.getIntOr("Invul", 0));
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}

	}

	@Override
	public void setCustomName(@Nullable Component name) {
		super.setCustomName(name);
		this.bossInfo.setName(this.getDisplayName());
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.WITHER_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.WITHER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.WITHER_DEATH;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void aiStep() {
		Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
		if (!this.level().isClientSide && this.getWatchedTargetId(0) > 0) {
			Entity entity = this.level().getEntity(this.getWatchedTargetId(0));
			if (entity != null) {
				double d0 = vec3.y;
				if (this.getY() < entity.getY() || !this.isArmored() && this.getY() < entity.getY() + 5.0D) {
					d0 = Math.max(0.0D, d0);
					d0 = d0 + (0.3D - d0 * (double) 0.6F);
				}

				vec3 = new Vec3(vec3.x, d0, vec3.z);
				Vec3 vec31 = new Vec3(entity.getX() - this.getX(), 0.0D, entity.getZ() - this.getZ());
				if (vec31.horizontalDistanceSqr() > 9.0D) {
					Vec3 Vector3d2 = vec31.normalize();
					vec3 = vec3.add(Vector3d2.x * 0.3D - vec3.x * 0.6D, 0.0D, Vector3d2.z * 0.3D - vec3.z * 0.6D);
				}
			}
		}

		this.setDeltaMovement(vec3);
		if (vec3.horizontalDistanceSqr() > 0.05D) {
			this.setYRot((float) Mth.atan2(vec3.z, vec3.x) * (180F / (float) Math.PI) - 90.0F);
		}

		super.aiStep();

		for (int i = 0; i < 2; ++i) {
			this.yRotOHeads[i] = this.yRotationHeads[i];
			this.xRotOHeads[i] = this.xRotationHeads[i];
		}

		for (int j = 0; j < 2; ++j) {
			int k = this.getWatchedTargetId(j + 1);
			Entity entity1 = null;
			if (k > 0) {
				entity1 = this.level().getEntity(k);
			}

			if (entity1 != null) {
				double d9 = this.getHeadX(j + 1);
				double d1 = this.getHeadY(j + 1);
				double d3 = this.getHeadZ(j + 1);
				double d4 = entity1.getX() - d9;
				double d5 = entity1.getY() + (double) entity1.getEyeHeight() - d1;
				double d6 = entity1.getZ() - d3;
				double d7 = Math.sqrt(d4 * d4 + d6 * d6);
				float f = (float) (Mth.atan2(d6, d4) * (double) (180F / (float) Math.PI)) - 90.0F;
				float f1 = (float) (-(Mth.atan2(d5, d7) * (double) (180F / (float) Math.PI)));
				this.xRotationHeads[j] = this.rotlerp(this.xRotationHeads[j], f1, 40.0F);
				this.yRotationHeads[j] = this.rotlerp(this.yRotationHeads[j], f, 10.0F);
			} else {
				this.yRotationHeads[j] = this.rotlerp(this.yRotationHeads[j], this.yBodyRot, 10.0F);
			}
		}

		boolean flag = this.isArmored();

		for (int l = 0; l < 3; ++l) {
			double headX = this.getHeadX(l);
			double headY = this.getHeadY(l);
			double headZ = this.getHeadZ(l);
			float f = 0.3F * this.getScale();
			this.level().addParticle(ParticleTypes.SMOKE,
					headX + this.random.nextGaussian() * (double) 0.3F,
					headY + this.random.nextGaussian() * (double) 0.3F,
					headZ + this.random.nextGaussian() * (double) 0.3F, 0.0D, 0.0D, 0.0D
			);
			if (flag && this.level().random.nextInt(4) == 0) {
				this.level().addParticle(
						ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, 0.7F, 0.7F, 0.5F),
						headX + this.random.nextGaussian() * (double) f,
						headY + this.random.nextGaussian() * (double) f,
						headZ + this.random.nextGaussian() * (double) f,
						0.0,
						0.0,
						0.0
				);
			}
		}

		if (this.getInvulTime() > 0) {
			float f3 = 3.3F * this.getScale();
			for (int i1 = 0; i1 < 3; ++i1) {
				this.level()
						.addParticle(
								ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, 0.7F, 0.7F, 0.9F),
								this.getX() + this.random.nextGaussian(),
								this.getY() + (double) (this.random.nextFloat() * f3),
								this.getZ() + this.random.nextGaussian(),
								0.0,
								0.0,
								0.0
						);
				;
			}
		}

	}

	protected void customServerAiStep(ServerLevel serverLevel) {
		if (this.getInvulTime() > 0) {
			int j1 = this.getInvulTime() - 1;
			if (j1 <= 0) {
				Level.ExplosionInteraction explosion$mode = EventHooks.canEntityGrief(serverLevel, this) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE;
				serverLevel.explode(this, this.getX(), this.getEyeY(), this.getZ(), 7.0F, false, explosion$mode);
				serverLevel.globalLevelEvent(1023, blockPosition(), 0);
			}

			this.setInvulTime(j1);
			if (this.tickCount % 10 == 0) {
				this.heal(10.0F);
			}

		} else {
			super.customServerAiStep(serverLevel);

			for (int i = 1; i < 3; ++i) {
				if (this.tickCount >= this.nextHeadUpdate[i - 1]) {
					this.nextHeadUpdate[i - 1] = this.tickCount + 10 + this.random.nextInt(10);
					if (serverLevel.getDifficulty() == Difficulty.NORMAL || serverLevel.getDifficulty() == Difficulty.HARD) {
						int j3 = i - 1;
						int k3 = this.idleHeadUpdates[i - 1];
						this.idleHeadUpdates[j3] = this.idleHeadUpdates[i - 1] + 1;
						if (k3 > 15) {
							float f = 10.0F;
							float f1 = 5.0F;
							double d0 = Mth.nextDouble(this.random, this.getX() - 10.0D, this.getX() + 10.0D);
							double d1 = Mth.nextDouble(this.random, this.getY() - 5.0D, this.getY() + 5.0D);
							double d2 = Mth.nextDouble(this.random, this.getZ() - 10.0D, this.getZ() + 10.0D);
							this.launchWitherSkullToCoords(i + 1, d0, d1, d2, true);
							this.idleHeadUpdates[i - 1] = 0;
						}
					}

					int k1 = this.getWatchedTargetId(i);
					if (k1 > 0) {
						Entity entity = serverLevel.getEntity(k1);
						if (entity != null && entity.isAlive() && !(this.distanceToSqr(entity) > 900.0D) && this.hasLineOfSight(entity)) {
							if (entity instanceof Player && ((Player) entity).getAbilities().invulnerable) {
								this.updateWatchedTargetId(i, 0);
							} else {
								this.launchWitherSkullToEntity(i + 1, (LivingEntity) entity);
								this.nextHeadUpdate[i - 1] = this.tickCount + 40 + this.random.nextInt(20);
								this.idleHeadUpdates[i - 1] = 0;
							}
						} else {
							this.updateWatchedTargetId(i, 0);
						}
					} else {
						List<LivingEntity> list = serverLevel.getNearbyEntities(
								LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0, 8.0, 20.0)
						);
						for (int j2 = 0; j2 < 10 && !list.isEmpty(); ++j2) {
							LivingEntity livingentity = list.get(this.random.nextInt(list.size()));
							if (livingentity != this && livingentity.isAlive() && this.hasLineOfSight(livingentity)) {
								if (livingentity instanceof Player) {
									if (!((Player) livingentity).getAbilities().invulnerable) {
										this.updateWatchedTargetId(i, livingentity.getId());
									}
								} else {
									this.updateWatchedTargetId(i, livingentity.getId());
								}
								break;
							}

							list.remove(livingentity);
						}
					}
				}
			}

			if (this.getTarget() != null) {
				this.updateWatchedTargetId(0, this.getTarget().getId());
			} else {
				this.updateWatchedTargetId(0, 0);
			}

			if (this.blockBreakCounter > 0) {
				--this.blockBreakCounter;
				if (this.blockBreakCounter == 0 && EventHooks.canEntityGrief((ServerLevel) serverLevel, this)) {
					int i1 = Mth.floor(this.getY());
					int l1 = Mth.floor(this.getX());
					int i2 = Mth.floor(this.getZ());
					boolean flag = false;

					for (int k2 = -1; k2 <= 1; ++k2) {
						for (int l2 = -1; l2 <= 1; ++l2) {
							for (int j = 0; j <= 3; ++j) {
								int i3 = l1 + k2;
								int k = i1 + j;
								int l = i2 + l2;
								BlockPos blockpos = new BlockPos(i3, k, l);
								BlockState blockstate = serverLevel.getBlockState(blockpos);
								if (blockstate.canEntityDestroy(serverLevel, blockpos, this) && EventHooks.onEntityDestroyBlock(this, blockpos, blockstate)) {
									flag = serverLevel.destroyBlock(blockpos, true) || flag;
								}
							}
						}
					}

					if (flag) {
						serverLevel.levelEvent((Player) null, 1022, blockPosition(), 0);
					}
				}
			}

			if (this.tickCount % 20 == 0) {
				this.heal(1.0F);
			}

			this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
		}
	}

	@Deprecated //Forge: DO NOT USE use BlockState.canEntityDestroy
	public static boolean canDestroyBlock(BlockState blockIn) {
		return !blockIn.isAir() && !blockIn.is(BlockTags.WITHER_IMMUNE);
	}

	public void ignite() {
		this.setInvulTime(220);
		this.setHealth(this.getMaxHealth() / 3.0F);
	}

	@Override
	public void makeStuckInBlock(BlockState p_213295_1_, Vec3 p_213295_2_) {
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	private double getHeadX(int p_82214_1_) {
		if (p_82214_1_ <= 0) {
			return this.getX();
		} else {
			float f = (this.yBodyRot + (float) (180 * (p_82214_1_ - 1))) * ((float) Math.PI / 180F);
			float f1 = Mth.cos(f);
			return this.getX() + (double) f1 * 1.3D;
		}
	}

	private double getHeadY(int p_82208_1_) {
		return p_82208_1_ <= 0 ? this.getY() + 3.0D : this.getY() + 2.2D;
	}

	private double getHeadZ(int p_82213_1_) {
		if (p_82213_1_ <= 0) {
			return this.getZ();
		} else {
			float f = (this.yBodyRot + (float) (180 * (p_82213_1_ - 1))) * ((float) Math.PI / 180F);
			float f1 = Mth.sin(f);
			return this.getZ() + (double) f1 * 1.3D;
		}
	}

	private float rotlerp(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
		float f = Mth.wrapDegrees(p_82204_2_ - p_82204_1_);
		if (f > p_82204_3_) {
			f = p_82204_3_;
		}

		if (f < -p_82204_3_) {
			f = -p_82204_3_;
		}

		return p_82204_1_ + f;
	}

	private void launchWitherSkullToEntity(int p_82216_1_, LivingEntity p_82216_2_) {
		this.launchWitherSkullToCoords(p_82216_1_, p_82216_2_.getX(), p_82216_2_.getY() + (double) p_82216_2_.getEyeHeight() * 0.5D, p_82216_2_.getZ(), p_82216_1_ == 0 && this.random.nextFloat() < 0.001F);
	}

	/**
	 * Launches a Wither skull toward (par2, par4, par6)
	 */
	private void launchWitherSkullToCoords(int head, double x, double y, double z, boolean invulnerable) {
		this.level().levelEvent((Player) null, 1024, blockPosition(), 0);
		double d0 = this.getHeadX(head);
		double d1 = this.getHeadY(head);
		double d2 = this.getHeadZ(head);
		double d3 = x - d0;
		double d4 = y - d1;
		double d5 = z - d2;
		Vec3 vec3 = new Vec3(d3, d4, d5);
		MankiniWitherCapsuleEntity witherCapsuleEntity = new MankiniWitherCapsuleEntity(this.level(), this, vec3.normalize());
		if (invulnerable) {
			witherCapsuleEntity.setMankiniInvulnerable(true);
		}

		witherCapsuleEntity.setPosRaw(d0, d1, d2);
		this.level().addFreshEntity(witherCapsuleEntity);
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		this.launchWitherSkullToEntity(0, target);
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount) {
		if (this.isInvulnerableTo(serverLevel, source)) {
			return false;
		} else if (!source.is(DamageTypeTags.WITHER_IMMUNE_TO) && !(source.getEntity() instanceof WitherBoss)) {
			if (this.getInvulTime() > 0 && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
				return false;
			} else {
				if (this.isArmored()) {
					Entity entity = source.getDirectEntity();
					if (entity instanceof AbstractArrow) {
						return false;
					}
				}

				Entity entity1 = source.getEntity();
				if (!(entity1 instanceof Player) && entity1 instanceof LivingEntity &&
						((LivingEntity) entity1).getType().is(EntityTypeTags.UNDEAD)) {
					return false;
				} else {
					if (this.blockBreakCounter <= 0) {
						this.blockBreakCounter = 20;
					}

					for (int i = 0; i < this.idleHeadUpdates.length; ++i) {
						this.idleHeadUpdates[i] += 3;
					}

					return super.hurtServer(serverLevel, source, amount);
				}
			}
		} else {
			return false;
		}
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
		super.dropCustomDeathLoot(level, damageSource, recentlyHit);
		ItemEntity itementity = this.spawnAtLocation(level, Items.NETHER_STAR);
		if (itementity != null) {
			itementity.setExtendedLifetime();
		}
	}

	/**
	 * Makes the entity despawn if requirements are reached
	 */
	@Override
	public void checkDespawn() {
		if (this.level().getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
			this.discard();
		} else {
			this.noActionTime = 0;
		}
	}

	@Override
	protected int calculateFallDamage(double fallDistance, float damageMultiplier) {
		return 0;
	}

	@Override
	public boolean addEffect(MobEffectInstance effectInstance, @Nullable Entity entity) {
		return false;
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 300.0D)
				.add(Attributes.MOVEMENT_SPEED, (double) 0.6F)
				.add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.ARMOR, 4.0D);
	}

	public float[] getHeadYRots() {
		return this.yRotationHeads;
	}

	public float[] getHeadXRots() {
		return this.xRotationHeads;
	}

	public int getInvulTime() {
		return this.entityData.get(INVULNERABILITY_TIME);
	}

	public void setInvulTime(int time) {
		this.entityData.set(INVULNERABILITY_TIME, time);
	}

	/**
	 * Returns the target entity ID if present, or -1 if not @param par1 The target offset, should be from 0-2
	 */
	public int getWatchedTargetId(int head) {
		return this.entityData.get(HEAD_TARGETS.get(head));
	}

	/**
	 * Updates the target entity ID
	 */
	public void updateWatchedTargetId(int targetOffset, int newId) {
		this.entityData.set(HEAD_TARGETS.get(targetOffset), newId);
	}

	/**
	 * Returns whether the wither is armored with its boss armor or not by checking whether its health is below half of
	 * its maximum.
	 */
	public boolean isArmored() {
		return this.getHealth() <= this.getMaxHealth() / 2.0F;
	}

	@Override
	protected boolean canRide(Entity entityIn) {
		return false;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance potioneffectIn) {
		return potioneffectIn.is(ModRegistry.MANKINI_WITHER_EFFECT) && super.canBeAffected(potioneffectIn);
	}

	public boolean isPowered() {
		return this.getHealth() <= this.getMaxHealth() / 2.0F;
	}

	class DoNothingGoal extends Goal {
		public DoNothingGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean canUse() {
			return MankiniWither.this.getInvulTime() > 0;
		}
	}
}