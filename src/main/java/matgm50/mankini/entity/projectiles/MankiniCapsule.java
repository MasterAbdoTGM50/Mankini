package matgm50.mankini.entity.projectiles;

import matgm50.mankini.entity.boss.MankiniWither;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.IMankini;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;


public class MankiniCapsule extends ThrowableItemProjectile {

	public boolean dropItem = true;

	public MankiniCapsule(EntityType<? extends MankiniCapsule> type, Level level) {
		super(type, level);
	}

	public MankiniCapsule(Level level, LivingEntity throwerIn, ItemStack foundMankini) {
		super(ModRegistry.MANKINI_CAPSULE.get(), throwerIn, level, foundMankini);
	}

	public MankiniCapsule(Level level, LivingEntity throwerIn, ItemStack foundMankini, boolean drop) {
		this(level, throwerIn, foundMankini);
		this.dropItem = drop;
	}

	private ParticleOptions getParticle() {
		ItemStack itemstack = this.getItem();
		return (ParticleOptions) (itemstack.isEmpty() ? ParticleTypes.CRIT : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
	}

	public void handleEntityEvent(byte id) {
		if (id == 3) {
			ParticleOptions options = this.getParticle();

			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(options, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHit(HitResult result) {
		if (this.level() instanceof ServerLevel serverLevel) {
			boolean flag = false;
			if (result.getType() == HitResult.Type.ENTITY) {
				flag = true;
				Entity hit = ((EntityHitResult) result).getEntity();
				if (hit != null) {
					if (hit instanceof Player hitPlayer) {

						ItemStack wornStack = hitPlayer.getItemBySlot(EquipmentSlot.CHEST);
						Inventory playerInv = hitPlayer.getInventory();

						if (wornStack.isEmpty()) {
							playerInv.setItem(38, getMankini());
						} else if (!wornStack.isEmpty() && !(wornStack.getItem() instanceof IMankini)) {
							playerInv.setItem(38, getMankini());
							if (playerInv.getFreeSlot() == -1) {
								if (dropItem) {
									this.spawnAtLocation(serverLevel, wornStack, 0.5F);
								}
							} else {
								playerInv.setItem(playerInv.getFreeSlot(), wornStack);
							}
						}
					} else if (hit instanceof WitherBoss originalWither && !(hit instanceof MankiniWither)) {

						MankiniWither mankiniWither = new MankiniWither(ModRegistry.MANKINI_WITHER.get(), this.level());
						mankiniWither.snapTo(originalWither.getX(), originalWither.getY(), originalWither.getZ(), originalWither.getYRot(), 0.0F);
						mankiniWither.ignite();
						this.discard();
						this.level().addFreshEntity(mankiniWither);
					} else if (MankiniConfig.COMMON.ShootMankinisOntoMobs.get()) {
						switch (hit) {
							case Zombie hitZombie -> {
								ItemStack chestStack = hitZombie.getItemBySlot(EquipmentSlot.CHEST);

								if (chestStack.isEmpty()) {
									hitZombie.setItemSlot(EquipmentSlot.CHEST, getMankini());
									hitZombie.setDropChance(EquipmentSlot.CHEST, 1F);
								} else {
									if (dropItem) {
										this.spawnAtLocation(serverLevel, getMankini(), 0.5F);
									}
								}
							}
							case Skeleton hitSkeleton -> {
								ItemStack chestStack = hitSkeleton.getItemBySlot(EquipmentSlot.CHEST);

								if (chestStack.isEmpty()) {
									hitSkeleton.setItemSlot(EquipmentSlot.CHEST, getMankini());
									hitSkeleton.setDropChance(EquipmentSlot.CHEST, 1F);
								} else {
									if (dropItem) {
										this.spawnAtLocation(serverLevel, getMankini(), 0.5F);
									}
								}
							}
							case Piglin hitPiglin -> {
								ItemStack chestStack = hitPiglin.getItemBySlot(EquipmentSlot.CHEST);

								if (chestStack.isEmpty()) {
									hitPiglin.setItemSlot(EquipmentSlot.CHEST, getMankini());
									hitPiglin.setDropChance(EquipmentSlot.CHEST, 1F);
								} else {
									if (dropItem) {
										this.spawnAtLocation(serverLevel, getMankini(), 0.5F);
									}
								}
							}
							default -> {
							}
						}
					}
				}
			}

			if (!flag) {
				if (dropItem) {
					this.spawnAtLocation(serverLevel, getMankini(), 1F);
				}
			}
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	public ItemStack getMankini() {
		return this.getItem();
	}

	@Override
	protected Item getDefaultItem() {
		return ModRegistry.MANKINI_CAPSULE_ITEM.get();
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ModRegistry.MANKINI_CAPSULE_ITEM.get());
	}
}
