package matgm50.mankini.entity;

import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
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

    ItemStack kini = new ItemStack(ModItems.itemDyeableMankini);
    public EntityMankiniCapsule(World worldIn)
    {
        super(worldIn);
    }

    public EntityMankiniCapsule(World worldIn, EntityLivingBase throwerIn, ItemStack foundMankini)
    {
    	super(worldIn, throwerIn);
    	this.foundMankini = foundMankini;
    }

    public EntityMankiniCapsule(World worldIn, double x, double y, double z)
    {
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
            	setDead();
                EntityPlayer hitPlayer = (EntityPlayer)hit;
                Boolean full = true;
      
                if(!this.worldObj.isRemote) {
                	for (int i=0; i<=3; i++) {
                        if (hitPlayer.inventory.getStackInSlot(i) == null) {
                            full = false;
                        }
                   } 
                    if(hitPlayer.inventory.armorItemInSlot(2) == null){
                    	hitPlayer.inventory.setInventorySlotContents(38, MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
                    
                    else if(hitPlayer.inventory.armorItemInSlot(2) != null && full == false){
                    	hitPlayer.inventory.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), MankiniHelper.getFirstFoundMankini(hitPlayer));
                    		//hitPlayer.inventory.addItemStackToInventory(MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
                }
            }
            
            else if (hit instanceof EntityCreeper)
            {
            	setDead();
            	EntityCreeper hitCreeper = (EntityCreeper)hit;
                hitCreeper.setDead();
                worldObj.removeEntity(hitCreeper);
                
                EntityMankiniCreeper mankinicreeper = new EntityMankiniCreeper(worldObj); 
                mankinicreeper.setLocationAndAngles(hitCreeper.posX, hitCreeper.posY, hitCreeper.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinicreeper);
            }
			
            else if (hit instanceof EntityEnderman)
            {
            	setDead();
            	EntityEnderman hitEnderman = (EntityEnderman)hit;
            	hitEnderman.setDead();
            	worldObj.removeEntity(hitEnderman);
            	
                EntityMankiniEnderman mankinienderman = new EntityMankiniEnderman(worldObj); 
                mankinienderman.setLocationAndAngles(hitEnderman.posX, hitEnderman.posY, hitEnderman.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinienderman);
            }
			
            else if (hit instanceof EntitySpider)
            {
            	setDead();
            	EntitySpider hitSpider = (EntitySpider)hit;
            	hitSpider.setDead();
            	worldObj.removeEntity(hitSpider);
            	
            	EntityMankiniSpider mankinispider = new EntityMankiniSpider(worldObj); 
            	mankinispider.setLocationAndAngles(hitSpider.posX, hitSpider.posY, hitSpider.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinispider);
            }
            
           else if (hit instanceof EntityZombie)
           {
            	setDead();
            	EntityZombie hitZombie = (EntityZombie)hit;
            	ItemStack mankini = new ItemStack(ModItems.itemDyeableMankini);
            	
            	hitZombie.setItemStackToSlot(EntityEquipmentSlot.CHEST, mankini);
            	hitZombie.setDropChance(EntityEquipmentSlot.CHEST, 1f);
           }
            
           else if (hit instanceof EntitySkeleton)
           {
            	setDead();
            	EntitySkeleton hitSkeleton = (EntitySkeleton)hit;
            	ItemStack mankini = new ItemStack(ModItems.itemDyeableMankini);
            	
            	hitSkeleton.setItemStackToSlot(EntityEquipmentSlot.CHEST, mankini);
            	hitSkeleton.setDropChance(EntityEquipmentSlot.CHEST, 1f);
           }
            
           else if (hit instanceof EntityPigZombie)
           {
            	setDead();
            	EntityPigZombie hitPigman = (EntityPigZombie)hit;
            	ItemStack mankini = new ItemStack(ModItems.itemDyeableMankini);
            	
            	hitPigman.setItemStackToSlot(EntityEquipmentSlot.CHEST, mankini);
            	hitPigman.setDropChance(EntityEquipmentSlot.CHEST, 1f);
           }
           setDead();
        }
        if(!this.worldObj.isRemote) {
      	  if (result.typeOfHit != null && result.typeOfHit == RayTraceResult.Type.BLOCK)
      {
         setDead();
         int kiniDrop = Item.getIdFromItem(ModItems.itemDyeableMankini);
         this.dropItem(Item.getItemById(kiniDrop), 1);
      }
      }
        else setDead();
   }
}
