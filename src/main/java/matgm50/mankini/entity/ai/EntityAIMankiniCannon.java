package matgm50.mankini.entity.ai;

import matgm50.mankini.init.ModItems;
import matgm50.mankini.item.IMankini;
import matgm50.mankini.item.ItemMankiniCannon;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

import java.util.EnumSet;

public class EntityAIMankiniCannon<T extends MonsterEntity & IRangedAttackMob> extends Goal {
    private final T entity;
    private final double moveSpeedAmp;
    private int attackCooldown;
    private final float maxAttackDistance;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public EntityAIMankiniCannon(T mob, double moveSpeedAmpIn, int attackCooldownIn, float maxAttackDistanceIn) {
        this.entity = mob;
        this.moveSpeedAmp = moveSpeedAmpIn;
        this.attackCooldown = attackCooldownIn;
        this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setAttackCooldown(int p_189428_1_) {
        this.attackCooldown = p_189428_1_;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return this.entity.getAttackTarget() == null ? false : this.isCannonInMainHand();
    }

    protected boolean isCannonInMainHand() {
        return !this.entity.getHeldItemMainhand().isEmpty() && this.entity.getHeldItemMainhand().getItem() == ModItems.mankini_cannon;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return (this.shouldExecute() || !this.entity.getNavigator().noPath()) && this.isCannonInMainHand();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
        this.entity.setAggroed(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
        this.entity.setAggroed(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.entity.resetActiveHand();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        LivingEntity livingBase = this.entity.getAttackTarget();
        if (livingBase != null) {
            if(livingBase instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)livingBase;
                boolean flag = player.inventory.armorInventory.get(2).getItem() instanceof IMankini;
                boolean flag2 = false;

                for(int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    if(player.inventory.getStackInSlot(i).getItem() instanceof IMankini) {
                        flag2 = true;
                        break;
                    }
                }

                if(!flag && !flag2) {
                    doMankiniShooting(livingBase);
                }
            } else {
                doMankiniShooting(livingBase);
            }
        }
    }

    public void doMankiniShooting(LivingEntity entitylivingbase) {
        double d0 = this.entity.getDistanceSq(entitylivingbase.posX, entitylivingbase.getBoundingBox().minY, entitylivingbase.posZ);
        boolean flag = this.entity.getEntitySenses().canSee(entitylivingbase);
        boolean flag1 = this.seeTime > 0;
        if (flag != flag1) {
            this.seeTime = 0;
        }

        if (flag) {
            ++this.seeTime;
        } else {
            --this.seeTime;
        }

        if (!(d0 > (double) this.maxAttackDistance) && this.seeTime >= 20) {
            this.entity.getNavigator().clearPath();
            ++this.strafingTime;
        } else {
            this.entity.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.moveSpeedAmp);
            this.strafingTime = -1;
        }

        if (this.strafingTime >= 20) {
            if ((double) this.entity.getRNG().nextFloat() < 0.3D) {
                this.strafingClockwise = !this.strafingClockwise;
            }

            if ((double) this.entity.getRNG().nextFloat() < 0.3D) {
                this.strafingBackwards = !this.strafingBackwards;
            }

            this.strafingTime = 0;
        }

        if (this.strafingTime > -1) {
            if (d0 > (double) (this.maxAttackDistance * 0.75F)) {
                this.strafingBackwards = false;
            } else if (d0 < (double) (this.maxAttackDistance * 0.25F)) {
                this.strafingBackwards = true;
            }

            this.entity.getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
            this.entity.faceEntity(entitylivingbase, 30.0F, 30.0F);
        } else {
            this.entity.getLookController().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
        }

        if (this.entity.isHandActive()) {
            if (!flag && this.seeTime < -60) {
                this.entity.resetActiveHand();
            } else if (flag) {
                int i = this.entity.getItemInUseMaxCount();
                if (i >= 20) {
                    this.entity.resetActiveHand();
                    ((IRangedAttackMob) this.entity).attackEntityWithRangedAttack(entitylivingbase, ItemMankiniCannon.getMankiniVelocity(i));
                    this.attackTime = this.attackCooldown;
                }
            }
        } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
            this.entity.setActiveHand(Hand.MAIN_HAND);
        }
    }
}