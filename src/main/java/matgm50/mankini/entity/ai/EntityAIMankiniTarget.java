package matgm50.mankini.entity.ai;

import matgm50.mankini.item.IMankini;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;

public class EntityAIMankiniTarget<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public EntityAIMankiniTarget(CreatureEntity attacker, Class<T> target, boolean checkSight) {
        super(attacker, target, checkSight);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return super.shouldExecute() && !isFabulous();
    }

    public boolean isFabulous() {
        if(this.target != null && this.target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)this.target;
            return player.inventory.armorInventory.get(2).getItem() instanceof IMankini;
        }
        else {
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return this.targetEntitySelector != null ? this.targetEntitySelector.canTarget(this.goalOwner, this.target) : super.shouldContinueExecuting();
    }
}