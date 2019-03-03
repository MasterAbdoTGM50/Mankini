package matgm50.mankini.entity.ai;

import matgm50.mankini.Mankini;
import matgm50.mankini.item.IMankini;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIMankiniTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {

    public EntityAIMankiniTarget(EntityCreature attacker, Class<T> target, boolean checkSight) {
        super(attacker, target, checkSight);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return super.shouldExecute() && !isFabulous();
    }

    public boolean isFabulous() {
        if(this.targetEntity != null && this.targetEntity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)this.targetEntity;
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
        return this.targetEntitySelector != null ? this.targetEntitySelector.test(this.targetEntity) : super.shouldContinueExecuting();
    }
}