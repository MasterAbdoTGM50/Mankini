package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityMankiniEndermite extends EntityEndermite {
    public EntityMankiniEndermite(World worldIn) {
        super(worldIn);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.MANKINI_ENDERMITE;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAIMankiniTarget<>(this, EntityPlayer.class, true));
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
