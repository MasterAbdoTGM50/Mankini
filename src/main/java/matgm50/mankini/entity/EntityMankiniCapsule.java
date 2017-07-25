package matgm50.mankini.entity;

import matgm50.mankini.init.ModConfigGen;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class EntityMankiniCapsule extends EntityThrowable {

    ItemStack foundMankini;

    public EntityMankiniCapsule(World worldIn) {

        super(worldIn);

    }

    public EntityMankiniCapsule(World worldIn, EntityLivingBase throwerIn, ItemStack foundMankini) {

        super(worldIn, throwerIn);
        this.foundMankini = foundMankini;

    }

    public EntityMankiniCapsule(World worldIn, double x, double y, double z) {

        super(worldIn, x, y, z);

    }

    public static void registerFixesMankiniCapsule(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "MankiniCapsule");
    }

   @Override
    protected void onImpact(RayTraceResult result) {
	   
        if(result.typeOfHit != null && result.typeOfHit == RayTraceResult.Type.ENTITY) {

            Entity hit = result.entityHit;
           
            if(hit instanceof EntityPlayer) {
                this.setDead();
                EntityPlayer hitPlayer = (EntityPlayer)hit;
                Boolean full = true;
                
                ItemStack itemstack = hitPlayer.inventory.armorInventory.get(2);
                           
                if(!this.world.isRemote) {
                	
                	for (int i=0; i<=3; i++) {
                    	
                        if (hitPlayer.inventory.getStackInSlot(i) == null) {
                            full = false;
                        }
                   } 
                	
                    if(itemstack.getItem() == null){
                    	hitPlayer.inventory.setInventorySlotContents(38, foundMankini);
                    	//hitPlayer.inventory.setInventorySlotContents(38, MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
                    
                    else if(itemstack.getItem() != null && full == false){
                    	hitPlayer.inventory.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), foundMankini);
                    	//hitPlayer.inventory.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), MankiniHelper.getFirstFoundMankini(hitPlayer));
                    		//hitPlayer.inventory.addItemStackToInventory(MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
                }
            }
            
            if (ModConfigGen.ShootMankinisOntoMobs)
			{
		       if (hit instanceof EntityZombie)
		       {
		        	setDead();
		        	EntityZombie hitZombie = (EntityZombie)hit;
		        	
		        	hitZombie.setItemStackToSlot(EntityEquipmentSlot.CHEST, foundMankini);
		        	hitZombie.setDropChance(EntityEquipmentSlot.CHEST, 1f);
		       }
		        
		       if (hit instanceof EntitySkeleton)
		       {
		        	setDead();
		        	EntitySkeleton hitSkeleton = (EntitySkeleton)hit;
		        	
		        	hitSkeleton.setItemStackToSlot(EntityEquipmentSlot.CHEST, foundMankini);
		        	hitSkeleton.setDropChance(EntityEquipmentSlot.CHEST, 1f);
		       }
		        
		       if (hit instanceof EntityPigZombie)
		       {
		        	setDead();
		        	EntityPigZombie hitPigman = (EntityPigZombie)hit;
		        	
		        	hitPigman.setItemStackToSlot(EntityEquipmentSlot.CHEST, foundMankini);
		        	hitPigman.setDropChance(EntityEquipmentSlot.CHEST, 1f);
		       }
			}
            /*
            else if (hit instanceof EntityCreeper && !(hit instanceof EntityMankiniCreeper))
            {
                hit.setDead();
                
                EntityMankiniCreeper mankinicreeper = new EntityMankiniCreeper(worldObj); 
                mankinicreeper.setLocationAndAngles(hit.posX, hit.posY, hit.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinicreeper);
        		
        		
        		this.setDead();
            }
			
            else if (hit instanceof EntityEnderman && !(hit instanceof EntityMankiniEnderman))
            {
            	EntityEnderman hitEnderman = (EntityEnderman)hit;
            	hitEnderman.setDead();
            	worldObj.removeEntity(hitEnderman);
            	
                EntityMankiniEnderman mankinienderman = new EntityMankiniEnderman(worldObj); 
                mankinienderman.setLocationAndAngles(hitEnderman.posX, hitEnderman.posY, hitEnderman.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinienderman);
        		setDead();
            }
			
            else if (hit instanceof EntitySpider && !(hit instanceof EntityMankiniSpider))
            {
            	setDead();
            	EntitySpider hitSpider = (EntitySpider)hit;
            	hitSpider.setDead();
            	worldObj.removeEntity(hitSpider);
            	
            	EntityMankiniSpider mankinispider = new EntityMankiniSpider(worldObj); 
            	mankinispider.setLocationAndAngles(hitSpider.posX, hitSpider.posY, hitSpider.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinispider);
            }
           */
           this.world.setEntityState(this, (byte)3);
           this.setDead();
        }
        if(!this.world.isRemote) {
      	  if (result.typeOfHit != null && result.typeOfHit == RayTraceResult.Type.BLOCK)
	      {
	  		this.world.setEntityState(this, (byte)3);
			this.setDead();
			this.entityDropItem(foundMankini, 1);
	      }
	      }
        	else
	        this.world.setEntityState(this, (byte)3);
	        this.setDead();
	   }
}
