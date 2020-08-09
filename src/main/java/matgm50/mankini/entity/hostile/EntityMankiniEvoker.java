package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.IMankini;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpellcastingIllagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class EntityMankiniEvoker extends SpellcastingIllagerEntity {
    private PlayerEntity wololoTarget;

    public EntityMankiniEvoker(EntityType<? extends EntityMankiniEvoker> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityMankiniEvoker(World worldIn) {
        super(ModRegistry.MANKINI_EVOKER.get(), worldIn);
        this.experienceValue = 6;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new EntityMankiniEvoker.AICastingSpell());
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.addGoal(5, new EntityMankiniEvoker.CastingASpellGoal());
        this.goalSelector.addGoal(6, new EntityMankiniEvoker.AIWololoSpell());
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, (new EntityAIMankiniTarget<>(this, PlayerEntity.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, false));
    }

    @Override
    public void applyWaveBonus(int p_213660_1_, boolean p_213660_2_) {
    }

    @Override
    public SoundEvent getRaidLossSound() {
        return SoundEvents.ENTITY_VILLAGER_NO;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
            .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D)
            .createMutableAttribute(Attributes.FOLLOW_RANGE, 12.0D)
            .createMutableAttribute(Attributes.MAX_HEALTH, 24.0D);
    }
    
    private void setWololoTarget(@Nullable PlayerEntity wololoTargetIn) {
        this.wololoTarget = wololoTargetIn;
    }

    @Nullable
    private PlayerEntity getWololoTarget() {
        return this.wololoTarget;
    }

    @Override
    protected SoundEvent getSpellSound() {
        return null;
    }

    class AICastingSpell extends SpellcastingIllagerEntity.CastingASpellGoal {
        private AICastingSpell() {
            super();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (EntityMankiniEvoker.this.getAttackTarget() != null) {
                EntityMankiniEvoker.this.getLookController().setLookPositionWithEntity(EntityMankiniEvoker.this.getAttackTarget(), (float) EntityMankiniEvoker.this.getHorizontalFaceSpeed(), (float) EntityMankiniEvoker.this.getVerticalFaceSpeed());
            } else if (EntityMankiniEvoker.this.getWololoTarget() != null) {
                EntityMankiniEvoker.this.getLookController().setLookPositionWithEntity(EntityMankiniEvoker.this.getWololoTarget(), (float) EntityMankiniEvoker.this.getHorizontalFaceSpeed(), (float) EntityMankiniEvoker.this.getVerticalFaceSpeed());
            }

        }
    }

    public class AIWololoSpell extends SpellcastingIllagerEntity.UseSpellGoal {
        private final Predicate<PlayerEntity> wololoSelector = this::hasMankini;

        public boolean hasMankini(PlayerEntity playerIn) {
            PlayerInventory inventory = playerIn.inventory;
            boolean hasMankini = false;
            for(int i = 0; i < inventory.getSizeInventory(); i++) {
                if(inventory.getStackInSlot(i).getItem() instanceof IMankini) {
                    hasMankini = true;
                    break;
                }
            }
            return !hasMankini;
        }
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
                List<PlayerEntity> list = EntityMankiniEvoker.this.world.getEntitiesWithinAABB(PlayerEntity.class, EntityMankiniEvoker.this.getBoundingBox().grow(16.0D, 4.0D, 16.0D), this.wololoSelector);
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
            EntityMankiniEvoker.this.setWololoTarget((PlayerEntity) null);
        }

        protected void castSpell() {
            PlayerEntity targetPlayer = EntityMankiniEvoker.this.getWololoTarget();
            if (targetPlayer != null && targetPlayer.isAlive()) {
                PlayerInventory playerInv = targetPlayer.inventory;
                ItemStack wornStack = playerInv.armorInventory.get(2);
                boolean flag = !(wornStack.getItem() instanceof IMankini);

                if(flag) {
                    ItemStack kiniStack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

                    if(wornStack.isEmpty()) {
                        playerInv.setInventorySlotContents(38, kiniStack);
                    } else if(!wornStack.isEmpty() && !(wornStack.getItem() instanceof IMankini)) {
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

        protected SpellcastingIllagerEntity.SpellType getSpellType() {
            return SpellcastingIllagerEntity.SpellType.WOLOLO;
        }
    }
}