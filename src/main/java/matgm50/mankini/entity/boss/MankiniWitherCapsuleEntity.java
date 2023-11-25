package matgm50.mankini.entity.boss;

import matgm50.mankini.init.MankiniDamageTypes;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.network.NetworkHooks;
import net.neoforged.neoforge.network.PlayMessages.SpawnEntity;

public class MankiniWitherCapsuleEntity extends AbstractHurtingProjectile implements ItemSupplier {
	private static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(MankiniWitherCapsuleEntity.class, EntityDataSerializers.BOOLEAN);

	public MankiniWitherCapsuleEntity(EntityType<? extends MankiniWitherCapsuleEntity> type, Level level) {
		super(type, level);
	}

	public MankiniWitherCapsuleEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(ModRegistry.MANKINI_WITHER_PROJECTILE.get(), shooter, accelX, accelY, accelZ, level);
	}

	public MankiniWitherCapsuleEntity(SpawnEntity spawnEntity, Level level) {
		this(ModRegistry.MANKINI_WITHER_PROJECTILE.get(), level);
	}

	@OnlyIn(Dist.CLIENT)
	public MankiniWitherCapsuleEntity(Level level, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModRegistry.MANKINI_WITHER_PROJECTILE.get(), x, y, z, accelX, accelY, accelZ, level);
	}

	/**
	 * Return the motion factor for this projectile. The factor is multiplied by the original motion.
	 */
	protected float getInertia() {
		return this.isMankiniInvulnerable() ? 0.73F : super.getInertia();
	}

	/**
	 * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
	 */
	public boolean isOnFire() {
		return false;
	}

	/**
	 * Explosion resistance of a block relative to this entity
	 */
	public float getBlockExplosionResistance(Explosion explosionIn, BlockGetter level, BlockPos pos, BlockState blockStateIn, FluidState p_180428_5_, float p_180428_6_) {
		return this.isMankiniInvulnerable() && blockStateIn.canEntityDestroy(level, pos, this) ? Math.min(0.8F, p_180428_6_) : p_180428_6_;
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	protected void onHit(HitResult result) {
		if (!this.level().isClientSide) {
			if (result.getType() == HitResult.Type.ENTITY) {
				Entity entity = ((EntityHitResult) result).getEntity();
				Entity shooter = getOwner();
				if (shooter instanceof LivingEntity shootingEntity) {
					if (entity.hurt(shootingEntity.damageSources().source(MankiniDamageTypes.MANKINI_WITHER, this), 4.0F)) {
						if (entity.isAlive()) {
							this.doEnchantDamageEffects(shootingEntity, entity);
						} else {
							shootingEntity.heal(5.0F);
						}
					}
				} else {
					entity.hurt(entity.damageSources().magic(), 3.0F);
				}

				if (entity instanceof LivingEntity) {
					int i = 0;
					if (this.level().getDifficulty() == Difficulty.NORMAL) {
						i = 10;
					} else if (this.level().getDifficulty() == Difficulty.HARD) {
						i = 40;
					}

					if (i > 0) {
						((LivingEntity) entity).addEffect(new MobEffectInstance(ModRegistry.MANKINI_WITHER_EFFECT.get(), 20 * i, 1));
					}

					if (!this.level().isClientSide) {
						if (entity instanceof Player hitPlayer) {
							Inventory playerInv = hitPlayer.getInventory();

							ItemStack itemstack = hitPlayer.getInventory().armor.get(2);
							ItemStack dyeableKini = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());
							dyeableKini.setDamageValue(dyeableKini.getMaxDamage() / this.level().random.nextInt(10));

							if (this.level().random.nextInt(100) < 8) {
								if (!itemstack.isEmpty()) {
									playerInv.removeItemNoUpdate(38);
								}
								playerInv.setItem(38, dyeableKini);
							}
						}
					}
				}
			}
			Level.ExplosionInteraction explosion$mode = EventHooks.getMobGriefingEvent(this.level(), this) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE;
			this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, false, explosion$mode);
			this.discard();
		}
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	public boolean isPickable() {
		return false;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean hurt(DamageSource source, float amount) {
		return false;
	}

	protected void defineSynchedData() {
		this.entityData.define(INVULNERABLE, false);
	}

	/**
	 * Return whether this skull comes from an invulnerable (aura) wither boss.
	 */
	public boolean isMankiniInvulnerable() {
		return this.entityData.get(INVULNERABLE);
	}

	/**
	 * Set whether this skull comes from an invulnerable (aura) wither boss.
	 */
	public void setMankiniInvulnerable(boolean invulnerable) {
		this.entityData.set(INVULNERABLE, invulnerable);
	}

	protected boolean shouldBurn() {
		return false;
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ModRegistry.MANKINI_CAPSULE_ITEM.get());
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return (Packet<ClientGamePacketListener>) NetworkHooks.getEntitySpawningPacket(this);
	}
}