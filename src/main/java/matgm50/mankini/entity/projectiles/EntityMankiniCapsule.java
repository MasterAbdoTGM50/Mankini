package matgm50.mankini.entity.projectiles;

import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.init.Particles;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class EntityMankiniCapsule extends EntityThrowable {

    ItemStack foundMankini;

    public EntityMankiniCapsule(World worldIn) {

        super(ModEntities.MANKINI_CAPSULE, worldIn);

    }

    public EntityMankiniCapsule(World worldIn, EntityLivingBase throwerIn, ItemStack foundMankini) {

        super(ModEntities.MANKINI_CAPSULE, throwerIn.posX, throwerIn.posY + (double)throwerIn.getEyeHeight() - (double)0.1F, throwerIn.posZ, worldIn);
        this.foundMankini = foundMankini;

    }

	@OnlyIn(Dist.CLIENT)
    public void handleStadtusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
				this.world.spawnParticle(Particles.ITEM_SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

	@Override
    protected void onImpact(RayTraceResult result) {
	   
	   if (result.entity != null) {

            Entity hit = result.entity;

            if(hit instanceof EntityWither && !(hit instanceof EntityMankiniWither)) {
            	EntityWither originalWither = (EntityWither)hit;
				originalWither.setDropItemsWhenDead(false);

            	EntityMankiniWither mankiniWiher = new EntityMankiniWither(this.world);
				mankiniWiher.setLocationAndAngles(originalWither.posX, originalWither.posY, originalWither.posZ, originalWither.rotationYaw, 0.0F);

				originalWither.remove();
				this.world.spawnEntity(mankiniWiher);
			} else if(hit instanceof EntityPlayer) {
                EntityPlayer hitPlayer = (EntityPlayer)hit;
                Boolean full = true;
                
                
                ItemStack itemstack = hitPlayer.inventory.armorInventory.get(2);
                InventoryPlayer playerInv = hitPlayer.inventory;
                           
                if(!this.world.isRemote) {
                	for (int i=0; i<=3; i++) {
                    	
                        if (playerInv.getStackInSlot(i) == null) {
                            full = false;
                        }
                	} 

                    if(itemstack.getItem() == null){
                    	playerInv.setInventorySlotContents(38, foundMankini);
                    	//hitPlayer.inventory.setInventorySlotContents(38, MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
                    
                    else if(itemstack.getItem() != null && full == false){
                    	if(hitPlayer.inventory.getFirstEmptyStack() == -1)
                    	{
                    		hitPlayer.entityDropItem(foundMankini, 0.5F);
                    	}
                    	else
                    	{ 
                    		playerInv.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), foundMankini);
                    	}
                    	//hitPlayer.inventory.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), MankiniHelper.getFirstFoundMankini(hitPlayer));
                    		//hitPlayer.inventory.addItemStackToInventory(MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
                }
            } else if (MankiniConfig.COMMON.ShootMankinisOntoMobs.get())
			{
		       if (hit instanceof EntityZombie)
		       {
		        	EntityZombie hitZombie = (EntityZombie)hit;		        	
		        	ItemStack GetChest = hitZombie.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		        	
		        	if(!this.world.isRemote) {
	                    if(GetChest.getItem() == Items.AIR){
	                    	hitZombie.setItemStackToSlot(EntityEquipmentSlot.CHEST, foundMankini);
	    		        	hitZombie.setDropChance(EntityEquipmentSlot.CHEST, 1F);
	                    }
	                    else
	                    {
	                    	this.entityDropItem(foundMankini, 1);
	                    }
	                }
		       } else if (hit instanceof EntitySkeleton) {
		        	EntitySkeleton hitSkeleton = (EntitySkeleton)hit;
		        	ItemStack GetChest = hitSkeleton.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		        	
		        	if(!this.world.isRemote) {
	                	
	                    if(GetChest.getItem() == Items.AIR){
	                    	hitSkeleton.setItemStackToSlot(EntityEquipmentSlot.CHEST, foundMankini);
	                    	hitSkeleton.setDropChance(EntityEquipmentSlot.CHEST, 1F);
	                    }
		        		else
		        		{
		        			this.entityDropItem(foundMankini, 1);
		        		}
	                }
		       } else if (hit instanceof EntityPigZombie) {
		        	EntityPigZombie hitPigman = (EntityPigZombie)hit;
		        	ItemStack GetChest = hitPigman.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		        	
		        	if(!this.world.isRemote) {
	                	
	                    if(GetChest.getItem() == Items.AIR){
	                    	hitPigman.setItemStackToSlot(EntityEquipmentSlot.CHEST, foundMankini);
	                    	hitPigman.setDropChance(EntityEquipmentSlot.CHEST, 1F);
	                    }
		        		else
		        		{
		        			this.entityDropItem(foundMankini, 1);
		        		}
	                }
		       }
			}
            
            //In case other mobs are hit
            //Don't want to delete the mankini
			else if (hit instanceof EntityMob || hit instanceof EntityLiving)
            {
            	if (MankiniConfig.COMMON.ShootMankinisOntoMobs.get())
            	{
	            	if (hit instanceof EntityPlayer || hit instanceof EntityZombie || hit instanceof EntitySkeleton || hit instanceof EntityPigZombie)
	            	{
	            		
	            	}
	            	else
	            	{
	            		this.entityDropItem(foundMankini, 1);
	            	}
            	}
            	else
            	{
            		
            	}
            	
            }
        }
		if (!this.world.isRemote){
			if (result.type != null && result.type == RayTraceResult.Type.BLOCK)
			{
				this.entityDropItem(foundMankini, 0.5F);
				this.world.setEntityState(this, (byte)3);
				this.remove();
			}
		}
			else 
			this.world.setEntityState(this, (byte)3);
			this.remove();
		}
}
