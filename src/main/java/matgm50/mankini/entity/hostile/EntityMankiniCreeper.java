package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.CreeperSwellGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityMankiniCreeper extends CreeperEntity {

	public EntityMankiniCreeper(EntityType<? extends EntityMankiniCreeper> type, World worldIn) {
		super(type, worldIn);
	}

    public EntityMankiniCreeper(World worldIn) {
        super(ModRegistry.MANKINI_CREEPER.get(), worldIn);
    }

	@Override
	public EntityType<?> getType() {
		return ModRegistry.MANKINI_CREEPER.get();
	}

	@Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new CreeperSwellGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new EntityAIMankiniTarget<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    /**
     * Creates an explosion as determined by this creeper's power and explosion radius.
     */
    private void explode() {
    	if(this.getAttackTarget() instanceof PlayerEntity) {
            PlayerEntity hitPlayer = (PlayerEntity) this.getAttackTarget();
    		
    		float f = this.getPowered() ? 2.0F : 1.0F;
    		
        	Boolean full = true;
        	
        	PlayerInventory playerInv = hitPlayer.inventory;
        	
        	ItemStack itemstack = hitPlayer.inventory.armorInventory.get(2);
            ItemStack creeperKini = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

            this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)3 * f, Explosion.Mode.NONE);
            
            
        	if (!this.world.isRemote) {
				if(hitPlayer.posX == (int) this.posX || hitPlayer.posY == (int) this.posY || hitPlayer.posZ == this.posZ){
					if(itemstack.isEmpty()){
						playerInv.setInventorySlotContents(38, creeperKini);
						full = true;
					}

					if(full && itemstack != creeperKini){
						if(MankiniConfig.COMMON.CreeperOverride.get()) {
							if(MankiniConfig.COMMON.EvilCreepers.get()) {
								playerInv.removeStackFromSlot(38);
								playerInv.setInventorySlotContents(38, creeperKini);
								creeperKini.addEnchantment(Enchantments.BINDING_CURSE, 1);
								creeperKini.addEnchantment(Enchantments.VANISHING_CURSE, 1);
							} else {
								playerInv.removeStackFromSlot(38);
								playerInv.setInventorySlotContents(38, creeperKini);
							}
						} else {
							if(MankiniConfig.COMMON.EvilCreepers.get()) {
								ItemStack oldArmour = itemstack.copy();
								playerInv.removeStackFromSlot(38);
								playerInv.setInventorySlotContents(38, creeperKini);
								creeperKini.addEnchantment(Enchantments.BINDING_CURSE, 1);
								creeperKini.addEnchantment(Enchantments.VANISHING_CURSE, 1);
									if(hitPlayer.inventory.getFirstEmptyStack() == -1) {
										hitPlayer.entityDropItem(oldArmour, 0.5F);
									} else {
										playerInv.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), oldArmour);
									}
							} else {
								ItemStack oldArmour = itemstack.copy();
								playerInv.removeStackFromSlot(38);
								playerInv.setInventorySlotContents(38, creeperKini);
								if(hitPlayer.inventory.getFirstEmptyStack() == -1) {
									hitPlayer.entityDropItem(oldArmour, 0.5F);
								} else {
									playerInv.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), oldArmour);
								}
							}
						}
					} else {
						hitPlayer.entityDropItem(creeperKini, 0.5F);
					}
				}
			}
    	}
        this.remove();
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        if(MankiniConfig.COMMON.MankiniCreeperSpawn.get())
            return super.canSpawn(worldIn, spawnReasonIn);
        else
            return false;
    }
}