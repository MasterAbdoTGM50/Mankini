package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

public class EntityMankiniSkeleton extends AbstractMankiniSkeleton {

    public EntityMankiniSkeleton(World worldIn) {
        super(ModEntities.MANKINI_SKELETON, worldIn);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
        if (MankiniConfig.COMMON.MankiniCreeperSpawn.get()) {
            return super.canSpawn(worldIn, p_205020_2_);
        } else {
            return false;
        }
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_SKELETON;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }
}