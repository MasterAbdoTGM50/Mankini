package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.init.ModLootTableList;
import matgm50.mankini.item.IMankini;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class EntityMankiniEvoker extends EntitySpellcasterIllager {
    private EntityPlayer wololoTarget;

    public EntityMankiniEvoker(World worldIn) {
        super(ModEntities.MANKINI_EVOKER, worldIn);
        this.setSize(0.6F, 1.95F);
        this.experienceValue = 6;
    }

    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityMankiniEvoker.AICastingSpell());
        this.tasks.addTask(2, new EntityAIAvoidEntity<>(this, EntityMankiniWither.class, 8.0F, 0.6D, 1.0D));
        this.tasks.addTask(6, new EntityMankiniEvoker.AIWololoSpell());
        this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityMankiniEvoker.class));
        this.targetTasks.addTask(2, (new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, (new EntityAIMankiniTarget(this, EntityVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, false));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(12.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
    }

    protected void registerData() {
        super.registerData();
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(NBTTagCompound compound) {
        super.readAdditional(compound);
    }

    /**
     * Writes the extra NBT data specific to this type of entity. Should <em>not</em> be called from outside this class;
     * use {@link #writeUnlessPassenger} or {@link #writeWithoutTypeId} instead.
     */
    public void writeAdditional(NBTTagCompound compound) {
        super.writeAdditional(compound);
    }

    protected ResourceLocation getLootTable() {
        return ModLootTableList.ENTITIES_MANKINI_EVOKER;
    }

    protected void updateAITasks() {
        super.updateAITasks();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
    }

    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isOnSameTeam(Entity entityIn) {
        if (entityIn == null) {
            return false;
        } else if (entityIn == this) {
            return true;
        } else if (super.isOnSameTeam(entityIn)) {
            return true;
        } else if (entityIn instanceof EntityVex) {
            return this.isOnSameTeam(((EntityVex) entityIn).getOwner());
        } else if (entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getCreatureAttribute() == CreatureAttribute.ILLAGER) {
            return this.getTeam() == null && entityIn.getTeam() == null;
        } else {
            return false;
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_EVOKER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_EVOKER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_EVOKER_HURT;
    }

    private void setWololoTarget(@Nullable EntityPlayer wololoTargetIn) {
        this.wololoTarget = wololoTargetIn;
    }

    @Nullable
    private EntityPlayer getWololoTarget() {
        return this.wololoTarget;
    }

    protected SoundEvent getSpellSound() {
        return SoundEvents.ENTITY_EVOKER_CAST_SPELL;
    }

    class AICastingSpell extends EntitySpellcasterIllager.AICastingApell {
        private AICastingSpell() {
            super();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (EntityMankiniEvoker.this.getAttackTarget() != null) {
                EntityMankiniEvoker.this.getLookHelper().setLookPositionWithEntity(EntityMankiniEvoker.this.getAttackTarget(), (float) EntityMankiniEvoker.this.getHorizontalFaceSpeed(), (float) EntityMankiniEvoker.this.getVerticalFaceSpeed());
            } else if (EntityMankiniEvoker.this.getWololoTarget() != null) {
                EntityMankiniEvoker.this.getLookHelper().setLookPositionWithEntity(EntityMankiniEvoker.this.getWololoTarget(), (float) EntityMankiniEvoker.this.getHorizontalFaceSpeed(), (float) EntityMankiniEvoker.this.getVerticalFaceSpeed());
            }

        }
    }

    public class AIWololoSpell extends EntitySpellcasterIllager.AIUseSpell {
        private final Predicate<EntityPlayer> wololoSelector = (playerIn) -> {
            return !(playerIn.inventory.armorInventory.get(2).getItem() instanceof IMankini);
        };

        public AIWololoSpell() {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (EntityMankiniEvoker.this.getAttackTarget() != null) {
                return false;
            } else if (EntityMankiniEvoker.this.isSpellcasting()) {
                return false;
            } else if (EntityMankiniEvoker.this.ticksExisted < this.spellCooldown) {
                return false;
            } else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(EntityMankiniEvoker.this.world, EntityMankiniEvoker.this)) {
                return false;
            } else {
                List<EntityPlayer> list = EntityMankiniEvoker.this.world.getEntitiesWithinAABB(EntityPlayer.class, EntityMankiniEvoker.this.getBoundingBox().grow(16.0D, 4.0D, 16.0D), this.wololoSelector);
                if (list.isEmpty()) {
                    return false;
                } else {
                    EntityMankiniEvoker.this.setWololoTarget(list.get(EntityMankiniEvoker.this.rand.nextInt(list.size())));
                    return true;
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return EntityMankiniEvoker.this.getWololoTarget() != null && this.spellWarmup > 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            super.resetTask();
            EntityMankiniEvoker.this.setWololoTarget((EntityPlayer) null);
        }

        protected void castSpell() {
            EntityPlayer targetPlayer = EntityMankiniEvoker.this.getWololoTarget();
            if (targetPlayer != null && targetPlayer.isAlive()) {
                InventoryPlayer playerInv = targetPlayer.inventory;
                ItemStack wornStack = playerInv.armorInventory.get(2);
                boolean flag = wornStack.getItem() instanceof IMankini;
                boolean flag2 = false;

                for(int i = 0; i < targetPlayer.inventory.getSizeInventory(); i++) {
                    if(targetPlayer.inventory.getStackInSlot(i).getItem() instanceof IMankini) {
                        flag2 = true;
                        break;
                    }
                }

                if(!flag && !flag2) {
                    ItemStack kiniStack = new ItemStack(ModItems.dyeable_mankini);

                    if(wornStack.getItem() == null){
                        playerInv.setInventorySlotContents(38, kiniStack);
                    } else if(wornStack.getItem() != null && !(wornStack.getItem() instanceof IMankini)){
                        playerInv.setInventorySlotContents(38, kiniStack);
                        if(playerInv.getFirstEmptyStack() != -1) {
                            targetPlayer.entityDropItem(wornStack, 0.5F);
                        } else {
                            playerInv.setInventorySlotContents(playerInv.getFirstEmptyStack(), wornStack);
                        }
                    }
                }
            }

        }

        protected int getCastWarmupTime() {
            return 40;
        }

        protected int getCastingTime() {
            return 60;
        }

        protected int getCastingInterval() {
            return 140;
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_WOLOLO;
        }

        protected EntitySpellcasterIllager.SpellType getSpellType() {
            return EntitySpellcasterIllager.SpellType.WOLOLO;
        }
    }
}