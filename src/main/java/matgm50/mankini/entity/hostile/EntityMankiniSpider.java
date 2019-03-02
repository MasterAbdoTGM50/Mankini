package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMankiniSpider extends EntitySpider {
    public EntityMankiniSpider(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.MANKINI_SPIDER;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityMankiniSpider.AISpiderAttack(this));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityMankiniSpider.AISpiderTarget<>(this, EntityPlayer.class));
        this.targetTasks.addTask(3, new EntityMankiniSpider.AISpiderTarget<>(this, EntityIronGolem.class));
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingData, @Nullable NBTTagCompound itemNbt) {
        livingData = super.onInitialSpawn(difficulty, livingData, itemNbt);
        ItemStack creeperKini = new ItemStack(ModItems.dyeable_mankini);

        if (this.world.rand.nextInt(100) == 0) {
            EntityMob entityskeleton = new EntitySkeleton(this.world);

            if(this.world.rand.nextInt(20) < 5) {
                entityskeleton = new EntityMankiniSkeleton(this.world);
            }

            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData)null, (NBTTagCompound)null);
            entityskeleton.setItemStackToSlot(EntityEquipmentSlot.CHEST, creeperKini);
            this.world.spawnEntity(entityskeleton);
            entityskeleton.startRiding(this);
        }
        else if (this.world.rand.nextInt(100) < 10)
        {

            EntityMankiniCreeper mankinicreeper = new EntityMankiniCreeper(this.world);
            mankinicreeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            mankinicreeper.onInitialSpawn(difficulty, (IEntityLivingData)null, null);
            mankinicreeper.setItemStackToSlot(EntityEquipmentSlot.CHEST, creeperKini);
            this.world.spawnEntity(mankinicreeper);
            mankinicreeper.startRiding(this);
        }

        if (livingData == null) {
            livingData = new EntitySpider.GroupData();
            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty()) {
                ((EntitySpider.GroupData)livingData).setRandomEffect(this.world.rand);
            }
        }

        if (livingData instanceof EntitySpider.GroupData) {
            Potion potion = ((EntitySpider.GroupData)livingData).effect;
            if (potion != null) {
                this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
            }
        }

        return livingData;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
        if(MankiniConfig.COMMON.MankiniSpiderSpawn.get())
        {
            return super.canSpawn(worldIn, p_205020_2_);
        }
        else
        {
            return false;
        }
    }

    static class AISpiderAttack extends EntityAIAttackMelee {
        public AISpiderAttack(EntitySpider spider) {
            super(spider, 1.0D, true);
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            float f = this.attacker.getBrightness();
            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
                this.attacker.setAttackTarget((EntityLivingBase)null);
                return false;
            } else {
                return super.shouldContinueExecuting();
            }
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget) {
            return (double)(4.0F + attackTarget.width);
        }
    }

    static class AISpiderTarget<T extends EntityLivingBase> extends EntityAIMankiniTarget<T> {
        public AISpiderTarget(EntitySpider spider, Class<T> classTarget) {
            super(spider, classTarget, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            float f = this.taskOwner.getBrightness();
            return f >= 0.5F ? false : super.shouldExecute();
        }
    }
}
