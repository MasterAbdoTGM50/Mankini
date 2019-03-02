package matgm50.mankini.entity.ai;

import matgm50.mankini.item.IMankini;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.function.Predicate;

public class EntityAIMankiniTarget<T extends EntityMob> extends EntityAINearestAttackableTarget<T> {
    private final EntityMob attacker;

    public EntityAIMankiniTarget(EntityMob attacker, Class<T> target, boolean p_i48571_3_) {
        super(attacker, target, 10, p_i48571_3_, false, (Predicate<T>)null);
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
            boolean flag2 = false;

            for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
                if(player.inventory.getStackInSlot(i).getItem() instanceof IMankini) {
                    flag2 = true;
                    break;
                }
            }

            if(!flag && !flag2) {
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