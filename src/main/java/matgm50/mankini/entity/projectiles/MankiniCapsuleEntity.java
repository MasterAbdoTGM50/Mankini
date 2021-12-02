package matgm50.mankini.entity.projectiles;

import matgm50.mankini.entity.boss.MankiniWitherEntity;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.IMankini;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;


public class MankiniCapsuleEntity extends ThrowableItemProjectile {

    public ItemStack foundMankini;
    public boolean dropItem = true;

	public MankiniCapsuleEntity(EntityType<? extends MankiniCapsuleEntity> type, Level worldIn) {
		super(type, worldIn);
	}

    public MankiniCapsuleEntity(Level worldIn, LivingEntity throwerIn, ItemStack foundMankini) {
        super(ModRegistry.MANKINI_CAPSULE.get(), throwerIn.getX(), throwerIn.getY() + (double)throwerIn.getEyeHeight() - (double)0.1F, throwerIn.getZ(), worldIn);
        this.foundMankini = foundMankini;
    }

	public MankiniCapsuleEntity(Level worldIn, LivingEntity throwerIn, ItemStack foundMankini, boolean drop) {
		super(ModRegistry.MANKINI_CAPSULE.get(), throwerIn.getX(), throwerIn.getY() + (double)throwerIn.getEyeHeight() - (double)0.1F, throwerIn.getZ(), worldIn);
		this.foundMankini = foundMankini;
		this.dropItem = drop;
	}

	public MankiniCapsuleEntity(SpawnEntity spawnEntity, Level worldIn) {
		this(ModRegistry.MANKINI_CAPSULE.get(), worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	private ParticleOptions getParticle() {
		ItemStack itemstack = this.getItemRaw();
		return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.CRIT : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
	}

	@OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id)
    {
        if (id == 3) {
			ParticleOptions iparticledata = this.getParticle();

			for(int i = 0; i < 8; ++i) {
				this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
        }
    }

	@Override
    protected void onHit(HitResult result) {
		if (!this.level.isClientSide){
			boolean flag = false;
			if (result.getType() == HitResult.Type.ENTITY) {
				flag = true;
				Entity hit = ((EntityHitResult)result).getEntity();
				if (hit != null) {
					if (hit instanceof Player hitPlayer) {

						ItemStack wornStack = hitPlayer.getInventory().armor.get(2);
						Inventory playerInv = hitPlayer.getInventory();

						if (wornStack.isEmpty()) {
							playerInv.setItem(38, foundMankini);
						} else if (!wornStack.isEmpty() && !(wornStack.getItem() instanceof IMankini)) {
							playerInv.setItem(38, foundMankini);
							if (playerInv.getFreeSlot() == -1) {
								if (dropItem) {
									this.spawnAtLocation(wornStack, 0.5F);
								}
							} else {
								playerInv.setItem(playerInv.getFreeSlot(), wornStack);
							}
						}
					} else if (hit instanceof WitherBoss originalWither && !(hit instanceof MankiniWitherEntity)) {

						MankiniWitherEntity mankiniWither = new MankiniWitherEntity(ModRegistry.MANKINI_WITHER.get(), this.level);
						mankiniWither.moveTo(originalWither.getX(), originalWither.getY(), originalWither.getZ(), originalWither.getYRot(), 0.0F);
						mankiniWither.ignite();
						this.discard();
						this.level.addFreshEntity(mankiniWither);
					} else if (MankiniConfig.COMMON.ShootMankinisOntoMobs.get()) {
						if (hit instanceof Zombie hitZombie) {
							ItemStack chestStack = hitZombie.getItemBySlot(EquipmentSlot.CHEST);

							if (chestStack.isEmpty()) {
								hitZombie.setItemSlot(EquipmentSlot.CHEST, foundMankini);
								hitZombie.setDropChance(EquipmentSlot.CHEST, 1F);
							} else {
								if (dropItem) {
									this.spawnAtLocation(foundMankini, 0.5F);
								}
							}
						} else if (hit instanceof Skeleton hitSkeleton) {
							ItemStack chestStack = hitSkeleton.getItemBySlot(EquipmentSlot.CHEST);

							if (chestStack.isEmpty()) {
								hitSkeleton.setItemSlot(EquipmentSlot.CHEST, foundMankini);
								hitSkeleton.setDropChance(EquipmentSlot.CHEST, 1F);
							} else {
								if (dropItem) {
									this.spawnAtLocation(foundMankini, 0.5F);
								}
							}
						} else if (hit instanceof Piglin hitPiglin) {
							ItemStack chestStack = hitPiglin.getItemBySlot(EquipmentSlot.CHEST);

							if (chestStack.isEmpty()) {
								hitPiglin.setItemSlot(EquipmentSlot.CHEST, foundMankini);
								hitPiglin.setDropChance(EquipmentSlot.CHEST, 1F);
							} else {
								if (dropItem) {
									this.spawnAtLocation(foundMankini, 0.5F);
								}
							}
						}
					}
				}
			}

			if(!flag) {
				if(dropItem) {
					this.spawnAtLocation(foundMankini, 1F);
				}
			}
			this.level.broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
    }

	@Override
	protected Item getDefaultItem() {
		return ModRegistry.MANKINI_CAPSULE_ITEM.get();
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ModRegistry.MANKINI_CAPSULE_ITEM.get());
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
