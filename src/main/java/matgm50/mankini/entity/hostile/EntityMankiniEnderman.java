package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class EntityMankiniEnderman extends EndermanEntity {

    private static final Predicate<LivingEntity> spawnedByPlayer = (p_213626_0_) -> {
        return p_213626_0_ instanceof EndermiteEntity && ((EndermiteEntity)p_213626_0_).isSpawnedByPlayer();
    };

    public EntityMankiniEnderman(EntityType<? extends EntityMankiniEnderman> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityMankiniEnderman(World worldIn) {
        super(ModRegistry.MANKINI_ENDERMAN.get(), worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return EndermanEntity.func_234287_m_();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityMankiniEndermite.class, 10, true, false, spawnedByPlayer));
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason reason) {
        if(MankiniConfig.COMMON.MankiniEndermanSpawn.get())
            return super.canSpawn(worldIn, reason);
        else
            return false;
    }
}
