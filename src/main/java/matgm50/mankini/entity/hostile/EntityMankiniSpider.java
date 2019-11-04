package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityMankiniSpider extends SpiderEntity {

    public EntityMankiniSpider(EntityType<? extends EntityMankiniSpider> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityMankiniSpider(World worldIn)
    {
        super(ModEntities.MANKINI_SPIDER, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new EntityMankiniSpider.AttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new EntityMankiniSpider.TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.addGoal(3, new EntityMankiniSpider.TargetGoal<>(this, IronGolemEntity.class));
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        ItemStack creeperKini = new ItemStack(ModItems.dyeable_mankini);

        if (this.world.rand.nextInt(100) == 0) {
            MobEntity entityskeleton = new SkeletonEntity(EntityType.SKELETON, this.world);

            if(this.world.rand.nextInt(20) < 5) {
                entityskeleton = new EntityMankiniSkeleton(this.world);
            }

            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onInitialSpawn(worldIn, difficultyIn, reason, (ILivingEntityData)null, (CompoundNBT) null);
            entityskeleton.setItemStackToSlot(EquipmentSlotType.CHEST, creeperKini);
            this.world.addEntity(entityskeleton);
            entityskeleton.startRiding(this);
        }
        else if (this.world.rand.nextInt(100) < 10)
        {

            EntityMankiniCreeper mankinicreeper = new EntityMankiniCreeper(this.world);
            mankinicreeper.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            mankinicreeper.onInitialSpawn(worldIn, difficultyIn, reason, (ILivingEntityData)null, (CompoundNBT) null);
            mankinicreeper.setItemStackToSlot(EquipmentSlotType.CHEST, creeperKini);
            this.world.addEntity(mankinicreeper);
            mankinicreeper.startRiding(this);
        }

        if (spawnDataIn == null) {
            spawnDataIn = new SpiderEntity.GroupData();
            if (this.world.getDifficulty() == Difficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficultyIn.getClampedAdditionalDifficulty()) {
                ((SpiderEntity.GroupData)spawnDataIn).setRandomEffect(this.world.rand);
            }
        }

        if (spawnDataIn instanceof SpiderEntity.GroupData) {
            Effect potion = ((SpiderEntity.GroupData)spawnDataIn).effect;
            if (potion != null) {
                this.addPotionEffect(new EffectInstance(potion, Integer.MAX_VALUE));
            }
        }

        return spawnDataIn;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason reason) {
        if(MankiniConfig.COMMON.MankiniSpiderSpawn.get())
            return super.canSpawn(worldIn, reason);
        else
            return false;
    }

    static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(SpiderEntity spider) {
            super(spider, 1.0D, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            return super.shouldExecute() && !this.attacker.isBeingRidden();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            float f = this.attacker.getBrightness();
            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
                this.attacker.setAttackTarget((LivingEntity)null);
                return false;
            } else {
                return super.shouldContinueExecuting();
            }
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(4.0F + attackTarget.getWidth());
        }
    }

    public static class GroupData implements ILivingEntityData {
        public Effect effect;

        public void setRandomEffect(Random rand) {
            int i = rand.nextInt(5);
            if (i <= 1) {
                this.effect = Effects.SPEED;
            } else if (i <= 2) {
                this.effect = Effects.STRENGTH;
            } else if (i <= 3) {
                this.effect = Effects.REGENERATION;
            } else if (i <= 4) {
                this.effect = Effects.INVISIBILITY;
            }

        }
    }

    static class TargetGoal<T extends LivingEntity> extends EntityAIMankiniTarget<T> {
        public TargetGoal(SpiderEntity spider, Class<T> classTarget) {
            super(spider, classTarget, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            float f = this.goalOwner.getBrightness();
            return f >= 0.5F ? false : super.shouldExecute();
        }
    }
}
