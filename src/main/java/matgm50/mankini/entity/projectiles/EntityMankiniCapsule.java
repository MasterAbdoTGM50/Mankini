package matgm50.mankini.entity.projectiles;

import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.item.IMankini;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombiePigmanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;


public class EntityMankiniCapsule extends ProjectileItemEntity {

    public ItemStack foundMankini;
    public boolean dropItem = true;

	public EntityMankiniCapsule(EntityType<? extends EntityMankiniCapsule> type, World worldIn) {
		super(type, worldIn);
	}

    public EntityMankiniCapsule(World worldIn, LivingEntity throwerIn, ItemStack foundMankini) {
        super(ModEntities.MANKINI_CAPSULE, throwerIn.posX, throwerIn.posY + (double)throwerIn.getEyeHeight() - (double)0.1F, throwerIn.posZ, worldIn);
        this.foundMankini = foundMankini;
    }

	public EntityMankiniCapsule(World worldIn, LivingEntity throwerIn, ItemStack foundMankini, boolean drop) {
		super(ModEntities.MANKINI_CAPSULE, throwerIn.posX, throwerIn.posY + (double)throwerIn.getEyeHeight() - (double)0.1F, throwerIn.posZ, worldIn);
		this.foundMankini = foundMankini;
		this.dropItem = drop;
	}

	public EntityMankiniCapsule(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
		this(ModEntities.MANKINI_CAPSULE, worldIn);
	}

	@OnlyIn(Dist.CLIENT)
	private IParticleData func_213887_n() {
		ItemStack itemstack = this.func_213882_k();
		return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
	}

	@OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
			IParticleData iparticledata = this.func_213887_n();

			for(int i = 0; i < 8; ++i) {
				this.world.addParticle(iparticledata, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
        }
    }

	@Override
    protected void onImpact(RayTraceResult result) {
		if (!this.world.isRemote){
			if (result.getType() == RayTraceResult.Type.ENTITY) {
				Entity hit = ((EntityRayTraceResult)result).getEntity();
				if (hit != null) {
					if (hit instanceof PlayerEntity) {
						PlayerEntity hitPlayer = (PlayerEntity) hit;

						ItemStack wornStack = hitPlayer.inventory.armorInventory.get(2);
						PlayerInventory playerInv = hitPlayer.inventory;

						if (wornStack.getItem() == null) {
							playerInv.setInventorySlotContents(38, foundMankini);
						} else if (wornStack.getItem() != null && !(wornStack.getItem() instanceof IMankini)) {
							playerInv.setInventorySlotContents(38, foundMankini);
							if (playerInv.getFirstEmptyStack() == -1) {
								if (dropItem) {
									this.entityDropItem(wornStack, 0.5F);
								}
							} else {
								playerInv.setInventorySlotContents(playerInv.getFirstEmptyStack(), wornStack);
							}
						}
					} else if (hit instanceof WitherEntity && !(hit instanceof EntityMankiniWither)) {
						WitherEntity originalWither = (WitherEntity) hit;
//						originalWither.setDropItemsWhenDead(false);

						EntityMankiniWither mankiniWither = new EntityMankiniWither(ModEntities.MANKINI_WITHER, this.world);
						mankiniWither.setLocationAndAngles(originalWither.posX, originalWither.posY, originalWither.posZ, originalWither.rotationYaw, 0.0F);
						mankiniWither.ignite();
						originalWither.remove();
						this.world.addEntity(mankiniWither);
					} else if (MankiniConfig.COMMON.ShootMankinisOntoMobs.get()) {
						if (hit instanceof ZombieEntity) {
							ZombieEntity hitZombie = (ZombieEntity) hit;
							ItemStack chestStack = hitZombie.getItemStackFromSlot(EquipmentSlotType.CHEST);

							if (chestStack.getItem() == Items.AIR) {
								hitZombie.setItemStackToSlot(EquipmentSlotType.CHEST, foundMankini);
								hitZombie.setDropChance(EquipmentSlotType.CHEST, 1F);
							} else {
								if (dropItem) {
									this.entityDropItem(foundMankini, 0.5F);
								}
							}
						} else if (hit instanceof SkeletonEntity) {
							SkeletonEntity hitSkeleton = (SkeletonEntity) hit;
							ItemStack GetChest = hitSkeleton.getItemStackFromSlot(EquipmentSlotType.CHEST);

							if (GetChest.getItem() == Items.AIR) {
								hitSkeleton.setItemStackToSlot(EquipmentSlotType.CHEST, foundMankini);
								hitSkeleton.setDropChance(EquipmentSlotType.CHEST, 1F);
							} else {
								if (dropItem) {
									this.entityDropItem(foundMankini, 0.5F);
								}
							}
						} else if (hit instanceof ZombiePigmanEntity) {
							ZombiePigmanEntity hitPigman = (ZombiePigmanEntity) hit;
							ItemStack GetChest = hitPigman.getItemStackFromSlot(EquipmentSlotType.CHEST);

							if (GetChest.getItem() == Items.AIR) {
								hitPigman.setItemStackToSlot(EquipmentSlotType.CHEST, foundMankini);
								hitPigman.setDropChance(EquipmentSlotType.CHEST, 1F);
							} else {
								if (dropItem) {
									this.entityDropItem(foundMankini, 0.5F);
								}
							}
						} else if (hit instanceof MobEntity) {
							MobEntity hitMob = (MobEntity) hit;
						}
					}
				}
			} else {
				if(dropItem) {
					this.entityDropItem(foundMankini, 1F);
				}
				this.world.setEntityState(this, (byte)3);
				this.remove();
			}
		}
		else {
			this.world.setEntityState(this, (byte) 3);
			this.remove();
		}
    }

	@Override
	protected Item func_213885_i() {
		return ModItems.mankini_capsule;
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(ModItems.mankini_capsule);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
