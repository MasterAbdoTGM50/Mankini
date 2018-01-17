package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.ModConfigGen;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

public class EntityMankiniCreeper extends EntityCreeper
{
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 1;
    private int droppedSkulls;

    public EntityMankiniCreeper(World worldIn)
    {
        super(worldIn);
    }
    
    public static void registerFixesMankiniCreeper(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityMankiniCreeper.class);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        if (this.isEntityAlive())
        {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.hasIgnited())
            {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0)
            {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0)
            {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }

        super.onUpdate();
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
        		boolean ArmourOverride = ModConfigGen.mobbehavior.CreeperOverride;
        		boolean EvilCreepers = ModConfigGen.mobbehavior.EvilCreepers;
        		
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
        this.setDead();
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }
    
    @Override
    protected ResourceLocation getLootTable()
    {
    	return new ResourceLocation("mankini:entities/mankini_creeper");
    }
}