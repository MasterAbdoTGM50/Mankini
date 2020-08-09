package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityMankiniEndermite extends EndermiteEntity {

    public EntityMankiniEndermite(EntityType<? extends EntityMankiniEndermite> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityMankiniEndermite(World worldIn) {
        super(ModRegistry.MANKINI_ENDERMITE.get(), worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return EndermiteEntity.func_234288_m_();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new EntityAIMankiniTarget<>(this, PlayerEntity.class, true));
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason reason) {
        if(MankiniConfig.COMMON.MankiniEndermiteSpawn.get())
            return super.canSpawn(worldIn, reason);
        else
            return false;
    }
}
