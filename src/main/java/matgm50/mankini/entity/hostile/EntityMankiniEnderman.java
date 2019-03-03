package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityMankiniEnderman extends EntityEnderman {

    public EntityMankiniEnderman(World worldIn) {
        super(worldIn);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.MANKINI_ENDERMAN;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityMankiniEndermite.class, 10, true, false, EntityMankiniEndermite::isSpawnedByPlayer));
    }

    @Override
    public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
        if(MankiniConfig.COMMON.MankiniEndermanSpawn.get())
        {
            return super.canSpawn(worldIn, p_205020_2_);
        }
        else
        {
            return false;
        }
    }
}
