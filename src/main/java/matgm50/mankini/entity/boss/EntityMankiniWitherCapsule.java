package matgm50.mankini.entity.boss;

import matgm50.mankini.init.ModEffects;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = IRendersAsItem.class
)
public class EntityMankiniWitherCapsule extends DamagingProjectileEntity implements IRendersAsItem {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityMankiniWitherCapsule.class, DataSerializers.BOOLEAN);

    public EntityMankiniWitherCapsule(EntityType<? extends EntityMankiniWitherCapsule> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityMankiniWitherCapsule(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(ModEntities.MANKINI_WITHER_PROJECTILE, shooter, accelX, accelY, accelZ, worldIn);
    }

    public EntityMankiniWitherCapsule(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
        this(ModEntities.MANKINI_WITHER_PROJECTILE, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public EntityMankiniWitherCapsule(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(ModEntities.MANKINI_WITHER_PROJECTILE, x, y, z, accelX, accelY, accelZ, worldIn);
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor() {
        return this.isMankiniInvulnerable() ? 0.73F : super.getMotionFactor();
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning() {
        return false;
    }

    /**
     * Explosion resistance of a block relative to this entity
     */
    public float getExplosionResistance(Explosion explosionIn, IBlockReader worldIn, BlockPos pos, BlockState blockStateIn, IFluidState p_180428_5_, float p_180428_6_) {
        return this.isMankiniInvulnerable() && blockStateIn.canEntityDestroy(worldIn, pos, this) ? Math.min(0.8F, p_180428_6_) : p_180428_6_;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult)result).getEntity();
                if (this.shootingEntity != null) {
                    if (entity.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 4.0F)) {
                        if (entity.isAlive()) {
                            this.applyEnchantments(this.shootingEntity, entity);
                        } else {
                            this.shootingEntity.heal(5.0F);
                        }
                    }
                } else {
                    entity.attackEntityFrom(DamageSource.MAGIC, 3.0F);
                }

                if (entity instanceof LivingEntity) {
                    int i = 0;
                    if (this.world.getDifficulty() == Difficulty.NORMAL) {
                        i = 10;
                    } else if (this.world.getDifficulty() == Difficulty.HARD) {
                        i = 40;
                    }

                    if (i > 0) {
                        ((LivingEntity) entity).addPotionEffect(new EffectInstance(ModEffects.MANKINI_WITHER, 10 * i, 1));
                    }

                    if (!this.world.isRemote) {
                        if (entity instanceof PlayerEntity) {
                            PlayerEntity hitPlayer = (PlayerEntity) entity;
                            PlayerInventory playerInv = hitPlayer.inventory;

                            ItemStack itemstack = hitPlayer.inventory.armorInventory.get(2);
                            ItemStack dyeableKini = new ItemStack(ModItems.dyeable_mankini);
                            dyeableKini.setDamage(dyeableKini.getMaxDamage()/this.world.rand.nextInt(10));

                            if (this.world.rand.nextInt(100) < 8) {
                                if(!itemstack.isEmpty()) {
                                    playerInv.removeStackFromSlot(38);
                                    playerInv.setInventorySlotContents(38, new ItemStack(ModItems.dyeable_mankini));
                                } else {
                                    playerInv.setInventorySlotContents(38, new ItemStack(ModItems.dyeable_mankini));
                                }
                            }
                        }
                    }
                }
            }
            Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, mode);
            this.remove();
        }

    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    protected void registerData() {
        this.dataManager.register(INVULNERABLE, false);
    }

    /**
     * Return whether this skull comes from an invulnerable (aura) wither boss.
     */
    public boolean isMankiniInvulnerable() {
        return this.dataManager.get(INVULNERABLE);
    }

    /**
     * Set whether this skull comes from an invulnerable (aura) wither boss.
     */
    public void setMankiniInvulnerable(boolean invulnerable) {
        this.dataManager.set(INVULNERABLE, invulnerable);
    }

    protected boolean isFireballFiery() {
        return false;
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