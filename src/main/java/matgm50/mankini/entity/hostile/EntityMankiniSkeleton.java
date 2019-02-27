package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniCannon;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMankiniSkeleton extends EntitySkeleton {
    private final EntityAIAttackRangedBow<AbstractSkeleton> aiArrowAttack = new EntityAIAttackRangedBow<>(this, 1.0D, 20, 15.0F);
    private final EntityAIMankiniCannon<AbstractSkeleton> aiCannonAttack = new EntityAIMankiniCannon<>(this, 1.0D, 40, 15.0F);
    private final EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2D, false) {
        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            super.resetTask();
            EntityMankiniSkeleton.this.setSwingingArms(false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            super.startExecuting();
            EntityMankiniSkeleton.this.setSwingingArms(true);
        }
    };

    public EntityMankiniSkeleton(World worldIn) {
        super(worldIn);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.MANKINI_SKELETON;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData entityLivingData, @Nullable NBTTagCompound itemNbt) {
        entityLivingData = super.onInitialSpawn(difficulty, entityLivingData, itemNbt);

        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.dyeable_mankini));

        if(this.world.rand.nextInt(100) < 8) {
            this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ModItems.mankini_cannon));
        }

        return entityLivingData;

    }

    @Override
    public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
        if(MankiniConfig.COMMON.MankiniCreeperSpawn.get())
        {
            return super.canSpawn(worldIn, p_205020_2_);
        }
        else
        {
            return false;
        }
    }

    @Override
    protected EntityArrow getArrow(float p_190726_1_) {
        return null;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityMankiniCapsule entityCapsule = this.getCapsule(distanceFactor);
        double d0 = target.posX - this.posX;
        double d1 = target.getBoundingBox().minY + (double)(target.height / 3.0F) - entityCapsule.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityCapsule.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityCapsule);
    }

    protected EntityMankiniCapsule getCapsule(float p_190726_1_) {
        EntityMankiniCapsule mankiniCapsule = new EntityMankiniCapsule(this.world, this, new ItemStack(ModItems.dyeable_mankini));
        return mankiniCapsule;
    }

    @Override
    public void setCombatTask() {
        if (this.world != null && !this.world.isRemote) {
            this.tasks.removeTask(this.aiAttackOnCollide);
            this.tasks.removeTask(this.aiCannonAttack);
            this.tasks.removeTask(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItemMainhand();
            if (itemstack.getItem() == Items.BOW) {
                int i = 20;
                if (this.world.getDifficulty() != EnumDifficulty.HARD) {
                    i = 40;
                }

                this.aiArrowAttack.setAttackCooldown(i);
                this.tasks.addTask(4, this.aiArrowAttack);
            } else if (itemstack.getItem() == ModItems.mankini_cannon) {
                int i = 40;
                if (this.world.getDifficulty() != EnumDifficulty.HARD) {
                    i = 60;
                }

                this.aiCannonAttack.setAttackCooldown(i);
                this.tasks.addTask(4, this.aiCannonAttack);
            }
            else {
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
        }
    }
}
