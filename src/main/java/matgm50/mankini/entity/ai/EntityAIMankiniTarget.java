package matgm50.mankini.entity.ai;

import matgm50.mankini.item.IMankini;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.function.Predicate;

public class EntityAIMankiniTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
    private final EntityCreature attacker;

    public EntityAIMankiniTarget(EntityCreature attacker, Class<T> target, boolean checkSight) {
        super(attacker, target, checkSight);
        this.attacker = attacker;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if(isFabulous())
            return false;
        else
            return super.shouldExecute();
    }

    public boolean isFabulous() {
        if(this.target instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)this.target;
            boolean flag = player.inventory.armorInventory.get(2).getItem() instanceof IMankini;

            if(!flag) {
                return true;
            }
            else {
                return false;
            }
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