package matgm50.mankini.entity.ai;

import matgm50.mankini.item.IMankini;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class EntityAIMankiniTarget<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public EntityAIMankiniTarget(PathfinderMob attacker, Class<T> target, boolean checkSight) {
        super(attacker, target, checkSight);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean canUse() {
        return super.canUse() && !isFabulous();
    }

    public boolean isFabulous() {
        if(this.targetMob != null && this.targetMob instanceof Player) {
            Player player = (Player)this.targetMob;
            return player.getInventory().armor.get(2).getItem() instanceof IMankini;
        }
        else {
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.targetConditions != null ? mob != null && targetMob != null && this.targetConditions.test(this.mob, this.targetMob) : super.canContinueToUse();
    }
}