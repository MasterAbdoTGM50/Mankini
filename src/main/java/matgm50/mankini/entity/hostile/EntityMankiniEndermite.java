package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityMankiniEndermite extends EntityEndermite {
    public EntityMankiniEndermite(World worldIn) {
        super(worldIn);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.MANKINI_ENDERMAN;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
        if(MankiniConfig.COMMON.MankiniEndermiteSpawn.get())
        {
            return super.canSpawn(worldIn, p_205020_2_);
        }
        else
        {
            return false;
        }
    }
}
