package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.init.ModLootTableList;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityMankiniCreeper extends EntityCreeper
{
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 3;

    public EntityMankiniCreeper(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAICreeperSwell(this));
        this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIMankiniTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public EntityType<?> getType() {
        return ModEntities.MANKINI_CREEPER;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick()
    {
        if (this.isAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;
            if (this.hasIgnited()) {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();
            if (i > 0 && this.timeSinceIgnited == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }

        super.tick();
    }

    /**
     * Creates an explosion as determined by this creeper's power and explosion radius.
     */
    private void explode()
    {   		
    	if(this.getAttackTarget() instanceof EntityPlayer)
    	{
    		EntityPlayer hitPlayer = (EntityPlayer) this.getAttackTarget();
    		
    		float f = this.getPowered() ? 2.0F : 1.0F;
    		
        	Boolean full = true;
        	
        	InventoryPlayer playerInv = hitPlayer.inventory;
        	
        	ItemStack itemstack = hitPlayer.inventory.armorInventory.get(2);
            ItemStack creeperKini = new ItemStack(ModItems.dyeable_mankini);

            this.world.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius * f, false);
            
            
        	if (!this.world.isRemote)
            {
        		boolean ArmourOverride = MankiniConfig.COMMON.CreeperOverride.get();
        		boolean EvilCreepers = MankiniConfig.COMMON.EvilCreepers.get();
        		
                    if(hitPlayer.posX == (int) this.posX || hitPlayer.posY == (int) this.posY || hitPlayer.posZ == this.posZ){
                        if(itemstack == ItemStack.EMPTY){
                        	playerInv.setInventorySlotContents(38, creeperKini);
                        	full=true;
                        }
                        
                        else if(itemstack != ItemStack.EMPTY && full == true && itemstack != creeperKini){
                        	if(ArmourOverride == true)
                        	{
                        		if(EvilCreepers)
                        		{
                        			playerInv.removeStackFromSlot(38);
                                	playerInv.setInventorySlotContents(38, creeperKini);
                                	creeperKini.addEnchantment(Enchantments.BINDING_CURSE, 1);
                                	creeperKini.addEnchantment(Enchantments.VANISHING_CURSE, 1);
                        		}
                        		else
                        		{
                        			playerInv.removeStackFromSlot(38);
                                	playerInv.setInventorySlotContents(38, creeperKini);
                        		}
                        	}
                        	else
                        	{
                        		if(EvilCreepers)
                        		{
                        			ItemStack oldArmour = itemstack.copy();
		                        	playerInv.removeStackFromSlot(38);
		                        	playerInv.setInventorySlotContents(38, creeperKini);
		                        	creeperKini.addEnchantment(Enchantments.BINDING_CURSE, 1);         
		                        	creeperKini.addEnchantment(Enchantments.VANISHING_CURSE, 1);         
			                        	if(hitPlayer.inventory.getFirstEmptyStack() == -1)
			                        	{
			                        		hitPlayer.entityDropItem(oldArmour, 0.5F);
			                        	}
			                        	else
			                        	{ 
			                        		playerInv.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), oldArmour);
			                        	}
                        		}
                        		else
                        		{
                        			ItemStack oldArmour = itemstack.copy();
		                        	playerInv.removeStackFromSlot(38);
		                        	playerInv.setInventorySlotContents(38, creeperKini);
			                        	if(hitPlayer.inventory.getFirstEmptyStack() == -1)
			                        	{
			                        		hitPlayer.entityDropItem(oldArmour, 0.5F);
			                        	}
			                        	else
			                        	{ 
			                        		playerInv.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), oldArmour);
			                        	}
                        		}
                        	}
                        }
                        else 
                        {
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
    protected ResourceLocation getLootTable()
    {
    	return ModLootTableList.ENTITIES_MANKINI_CREEPER;
    }

    @Override
    public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
        if(MankiniConfig.COMMON.MankiniCreeperSpawn.get())
        {
            return super.canSpawn(worldIn, p_205020_2_);
        }
        else
        {
            return false;
        }
    }
}