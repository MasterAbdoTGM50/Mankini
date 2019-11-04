package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityMankiniSkeleton extends AbstractMankiniSkeleton {

    public EntityMankiniSkeleton(EntityType<? extends EntityMankiniSkeleton> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityMankiniSkeleton(World worldIn) {
        super(ModEntities.MANKINI_SKELETON, worldIn);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason reason) {
        if (MankiniConfig.COMMON.MankiniCreeperSpawn.get())
            return super.canSpawn(worldIn, reason);
        else
            return false;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }
}