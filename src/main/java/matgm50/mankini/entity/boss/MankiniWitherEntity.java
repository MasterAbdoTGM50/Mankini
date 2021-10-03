package matgm50.mankini.entity.boss;

import com.google.common.collect.ImmutableList;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PowerableMob;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class MankiniWitherEntity extends Monster implements PowerableMob, RangedAttackMob {
    private static final EntityDataAccessor<Integer> FIRST_HEAD_TARGET = SynchedEntityData.defineId(MankiniWitherEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SECOND_HEAD_TARGET = SynchedEntityData.defineId(MankiniWitherEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> THIRD_HEAD_TARGET = SynchedEntityData.defineId(MankiniWitherEntity.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> HEAD_TARGETS = ImmutableList.of(FIRST_HEAD_TARGET, SECOND_HEAD_TARGET, THIRD_HEAD_TARGET);
    private static final EntityDataAccessor<Integer> INVULNERABILITY_TIME = SynchedEntityData.defineId(MankiniWitherEntity.class, EntityDataSerializers.INT);
    private final float[] xRotationHeads = new float[2];
    private final float[] yRotationHeads = new float[2];
    private final float[] xRotOHeads = new float[2];
    private final float[] yRotOHeads = new float[2];
    private final int[] nextHeadUpdate = new int[2];
    private final int[] idleHeadUpdates = new int[2];
    private int blockBreakCounter;
    private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private static final Predicate<LivingEntity> NOT_UNDEAD = (p_213797_0_) -> {
        return p_213797_0_.getMobType() != MobType.UNDEAD && p_213797_0_.attackable();
    };
    private static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(NOT_UNDEAD);

    public MankiniWitherEntity(EntityType<? extends MankiniWitherEntity> type, Level worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
        this.xpReward = 50;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MankiniWitherEntity.DoNothingGoal());
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 0, false, false, NOT_UNDEAD));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FIRST_HEAD_TARGET, 0);
        this.entityData.define(SECOND_HEAD_TARGET, 0);
        this.entityData.define(THIRD_HEAD_TARGET, 0);
        this.entityData.define(INVULNERABILITY_TIME, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Invul", this.getInvulTime());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setInvulTime(compound.getInt("Invul"));
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }

    }

    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.WITHER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void aiStep() {
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
        if (!this.level.isClientSide && this.getWatchedTargetId(0) > 0) {
            Entity entity = this.level.getEntity(this.getWatchedTargetId(0));
            if (entity != null) {
                double d0 = vec3.y;
                if (this.getY() < entity.getY() || !this.isArmored() && this.getY() < entity.getY() + 5.0D) {
                    d0 = Math.max(0.0D, d0);
                    d0 = d0 + (0.3D - d0 * (double)0.6F);
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
            this.setYRot((float)Mth.atan2(vec3.z, vec3.x) * (180F / (float)Math.PI) - 90.0F);
        }

        super.aiStep();

        for(int i = 0; i < 2; ++i) {
            this.yRotOHeads[i] = this.yRotationHeads[i];
            this.xRotOHeads[i] = this.xRotationHeads[i];
        }

        for(int j = 0; j < 2; ++j) {
            int k = this.getWatchedTargetId(j + 1);
            Entity entity1 = null;
            if (k > 0) {
                entity1 = this.level.getEntity(k);
            }

            if (entity1 != null) {
                double d9 = this.getHeadX(j + 1);
                double d1 = this.getHeadY(j + 1);
                double d3 = this.getHeadZ(j + 1);
                double d4 = entity1.getX() - d9;
                double d5 = entity1.getY() + (double)entity1.getEyeHeight() - d1;
                double d6 = entity1.getZ() - d3;
                double d7 = Math.sqrt(d4 * d4 + d6 * d6);
                float f = (float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)) - 90.0F;
                float f1 = (float)(-(Mth.atan2(d5, d7) * (double)(180F / (float)Math.PI)));
                this.xRotationHeads[j] = this.rotlerp(this.xRotationHeads[j], f1, 40.0F);
                this.yRotationHeads[j] = this.rotlerp(this.yRotationHeads[j], f, 10.0F);
            } else {
                this.yRotationHeads[j] = this.rotlerp(this.yRotationHeads[j], this.yBodyRot, 10.0F);
            }
        }

        boolean flag = this.isArmored();

        for(int l = 0; l < 3; ++l) {
            double d8 = this.getHeadX(l);
            double d10 = this.getHeadY(l);
            double d2 = this.getHeadZ(l);
            this.level.addParticle(ParticleTypes.SMOKE, d8 + this.random.nextGaussian() * (double)0.3F, d10 + this.random.nextGaussian() * (double)0.3F, d2 + this.random.nextGaussian() * (double)0.3F, 0.0D, 0.0D, 0.0D);
            if (flag && this.level.random.nextInt(4) == 0) {
                this.level.addParticle(ParticleTypes.ENTITY_EFFECT, d8 + this.random.nextGaussian() * (double)0.3F, d10 + this.random.nextGaussian() * (double)0.3F, d2 + this.random.nextGaussian() * (double)0.3F, (double)0.7F, (double)0.7F, 0.5D);
            }
        }

        if (this.getInvulTime() > 0) {
            for(int i1 = 0; i1 < 3; ++i1) {
                this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + this.random.nextGaussian(), this.getY() + (double)(this.random.nextFloat() * 3.3F), this.getZ() + this.random.nextGaussian(), (double)0.7F, (double)0.7F, (double)0.9F);
            }
        }

    }

    protected void customServerAiStep() {
        if (this.getInvulTime() > 0) {
            int j1 = this.getInvulTime() - 1;
            if (j1 <= 0) {
                Explosion.BlockInteraction explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
                this.level.explode(this, this.getX(), this.getEyeY(), this.getZ(), 7.0F, false, explosion$mode);
                this.level.globalLevelEvent(1023, blockPosition(), 0);
            }

            this.setInvulTime(j1);
            if (this.tickCount % 10 == 0) {
                this.heal(10.0F);
            }

        } else {
            super.customServerAiStep();

            for(int i = 1; i < 3; ++i) {
                if (this.tickCount >= this.nextHeadUpdate[i - 1]) {
                    this.nextHeadUpdate[i - 1] = this.tickCount + 10 + this.random.nextInt(10);
                    if (this.level.getDifficulty() == Difficulty.NORMAL || this.level.getDifficulty() == Difficulty.HARD) {
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
                        Entity entity = this.level.getEntity(k1);
                        if (entity != null && entity.isAlive() && !(this.distanceToSqr(entity) > 900.0D) && this.hasLineOfSight(entity)) {
                            if (entity instanceof Player && ((Player)entity).getAbilities().invulnerable) {
                                this.updateWatchedTargetId(i, 0);
                            } else {
                                this.launchWitherSkullToEntity(i + 1, (LivingEntity)entity);
                                this.nextHeadUpdate[i - 1] = this.tickCount + 40 + this.random.nextInt(20);
                                this.idleHeadUpdates[i - 1] = 0;
                            }
                        } else {
                            this.updateWatchedTargetId(i, 0);
                        }
                    } else {
                        List<LivingEntity> list = this.level.getNearbyEntities(LivingEntity.class, TARGETING_CONDITIONS, this, this.getBoundingBox().inflate(20.0D, 8.0D, 20.0D));

                        for(int j2 = 0; j2 < 10 && !list.isEmpty(); ++j2) {
                            LivingEntity livingentity = list.get(this.random.nextInt(list.size()));
                            if (livingentity != this && livingentity.isAlive() && this.hasLineOfSight(livingentity)) {
                                if (livingentity instanceof Player) {
                                    if (!((Player)livingentity).getAbilities().invulnerable) {
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
                if (this.blockBreakCounter == 0 && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                    int i1 = Mth.floor(this.getY());
                    int l1 = Mth.floor(this.getX());
                    int i2 = Mth.floor(this.getZ());
                    boolean flag = false;

                    for(int k2 = -1; k2 <= 1; ++k2) {
                        for(int l2 = -1; l2 <= 1; ++l2) {
                            for(int j = 0; j <= 3; ++j) {
                                int i3 = l1 + k2;
                                int k = i1 + j;
                                int l = i2 + l2;
                                BlockPos blockpos = new BlockPos(i3, k, l);
                                BlockState blockstate = this.level.getBlockState(blockpos);
                                if (blockstate.canEntityDestroy(this.level, blockpos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                                    flag = this.level.destroyBlock(blockpos, true) || flag;
                                }
                            }
                        }
                    }

                    if (flag) {
                        this.level.levelEvent((Player)null, 1022, blockPosition(), 0);
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
        return !blockIn.isAir() && !BlockTags.WITHER_IMMUNE.contains(blockIn.getBlock());
    }

    public void ignite() {
        this.setInvulTime(220);
        this.setHealth(this.getMaxHealth() / 3.0F);
    }

    public void makeStuckInBlock(BlockState p_213295_1_, Vec3 p_213295_2_) {
    }

    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private double getHeadX(int p_82214_1_) {
        if (p_82214_1_ <= 0) {
            return this.getX();
        } else {
            float f = (this.yBodyRot + (float)(180 * (p_82214_1_ - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);
            return this.getX() + (double)f1 * 1.3D;
        }
    }

    private double getHeadY(int p_82208_1_) {
        return p_82208_1_ <= 0 ? this.getY() + 3.0D : this.getY() + 2.2D;
    }

    private double getHeadZ(int p_82213_1_) {
        if (p_82213_1_ <= 0) {
            return this.getZ();
        } else {
            float f = (this.yBodyRot + (float)(180 * (p_82213_1_ - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);
            return this.getZ() + (double)f1 * 1.3D;
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
        this.launchWitherSkullToCoords(p_82216_1_, p_82216_2_.getX(), p_82216_2_.getY() + (double)p_82216_2_.getEyeHeight() * 0.5D, p_82216_2_.getZ(), p_82216_1_ == 0 && this.random.nextFloat() < 0.001F);
    }

    /**
     * Launches a Wither skull toward (par2, par4, par6)
     */
    private void launchWitherSkullToCoords(int p_82209_1_, double x, double y, double z, boolean invulnerable) {
        this.level.levelEvent((Player)null, 1024, blockPosition(), 0);
        double d0 = this.getHeadX(p_82209_1_);
        double d1 = this.getHeadY(p_82209_1_);
        double d2 = this.getHeadZ(p_82209_1_);
        double d3 = x - d0;
        double d4 = y - d1;
        double d5 = z - d2;
        MankiniWitherCapsuleEntity entityMankiniWitherCapsule = new MankiniWitherCapsuleEntity(this.level, this, d3, d4, d5);
        if (invulnerable) {
            entityMankiniWitherCapsule.setMankiniInvulnerable(true);
        }

        entityMankiniWitherCapsule.setPosRaw(d0, d1, d2);
        this.level.addFreshEntity(entityMankiniWitherCapsule);
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        this.launchWitherSkullToEntity(0, target);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source != DamageSource.DROWN && !(source.getEntity() instanceof MankiniWitherEntity)) {
            if (this.getInvulTime() > 0 && source != DamageSource.OUT_OF_WORLD) {
                return false;
            } else {
                if (this.isArmored()) {
                    Entity entity = source.getDirectEntity();
                    if (entity instanceof AbstractArrow) {
                        return false;
                    }
                }

                Entity entity1 = source.getEntity();
                if (entity1 != null && !(entity1 instanceof Player) && entity1 instanceof LivingEntity && ((LivingEntity)entity1).getMobType() == this.getMobType()) {
                    return false;
                } else {
                    if (this.blockBreakCounter <= 0) {
                        this.blockBreakCounter = 20;
                    }

                    for(int i = 0; i < this.idleHeadUpdates.length; ++i) {
                        this.idleHeadUpdates[i] += 3;
                    }

                    return super.hurt(source, amount);
                }
            }
        } else {
            return false;
        }
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        ItemEntity itementity = this.spawnAtLocation(Items.NETHER_STAR);
        if (itementity != null) {
            itementity.setExtendedLifetime();
        }

    }

    /**
     * Makes the entity despawn if requirements are reached
     */
    public void checkDespawn() {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            this.discard();
        } else {
            this.noActionTime = 0;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getBrightnessForRender() {
        return 15728880;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    public boolean addEffect(MobEffectInstance effectInstance, @Nullable Entity entity) {
        return false;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.6F)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 4.0D);
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadYRot(int p_82207_1_) {
        return this.yRotationHeads[p_82207_1_];
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadXRot(int p_82210_1_) {
        return this.xRotationHeads[p_82210_1_];
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

    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    protected boolean canRide(Entity entityIn) {
        return false;
    }

    /**
     * Returns false if this Entity is a boss, true otherwise.
     */
    public boolean canChangeDimensions() {
        return false;
    }

    public boolean canBeAffected(MobEffectInstance potioneffectIn) {
        return potioneffectIn.getEffect() == ModRegistry.MANKINI_WITHER_EFFECT.get() ? false : super.canBeAffected(potioneffectIn);
    }

    @Override
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
        public boolean canUse() {
            return MankiniWitherEntity.this.getInvulTime() > 0;
        }
    }
}