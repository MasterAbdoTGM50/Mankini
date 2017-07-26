package matgm50.mankini.entity;

import matgm50.mankini.init.ModConfigGen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


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
	
	@SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, -10.0F, 0.0D, 0.0D);
            }
        }
    }
	@Override
    protected void onImpact(RayTraceResult result) {
	   
	   if (result.entityHit != null) {

            Entity hit = result.entityHit;
           
            if(hit instanceof EntityPlayer) {
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
                    	playerInv.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), foundMankini);
                    	//hitPlayer.inventory.setInventorySlotContents(hitPlayer.inventory.getFirstEmptyStack(), MankiniHelper.getFirstFoundMankini(hitPlayer));
                    		//hitPlayer.inventory.addItemStackToInventory(MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
                }
            }
            if (ModConfigGen.ShootMankinisOntoMobs)
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
		       }
		        
		       if (hit instanceof EntitySkeleton)
		       {
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
		       }
		        
		       if (hit instanceof EntityPigZombie)
		       {
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
            if (hit instanceof EntityMob || hit instanceof EntityLiving)
            {
            	if (hit instanceof EntityPlayer || hit instanceof EntityZombie || hit instanceof EntitySkeleton || hit instanceof EntityPigZombie)
            	{
            		
            	}
            	else
            	{
            		this.entityDropItem(foundMankini, 1);
            	}
            }
            /*
            else if (hit instanceof EntityCreeper && !(hit instanceof EntityMankiniCreeper))
            {
                hit.setDead();
                
                EntityMankiniCreeper mankinicreeper = new EntityMankiniCreeper(worldObj); 
                mankinicreeper.setLocationAndAngles(hit.posX, hit.posY, hit.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinicreeper);
            }
			
            else if (hit instanceof EntityEnderman && !(hit instanceof EntityMankiniEnderman))
            {
            	EntityEnderman hitEnderman = (EntityEnderman)hit;
            	hitEnderman.setDead();
            	worldObj.removeEntity(hitEnderman);
            	
                EntityMankiniEnderman mankinienderman = new EntityMankiniEnderman(worldObj); 
                mankinienderman.setLocationAndAngles(hitEnderman.posX, hitEnderman.posY, hitEnderman.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinienderman);
            }
			
            else if (hit instanceof EntitySpider && !(hit instanceof EntityMankiniSpider))
            {
            	EntitySpider hitSpider = (EntitySpider)hit;
            	hitSpider.setDead();
            	worldObj.removeEntity(hitSpider);
            	
            	EntityMankiniSpider mankinispider = new EntityMankiniSpider(worldObj); 
            	mankinispider.setLocationAndAngles(hitSpider.posX, hitSpider.posY, hitSpider.posZ, 0,0); 
        		worldObj.spawnEntityInWorld(mankinispider);
            }
           */
        }
		if (!this.world.isRemote){
			if (result.typeOfHit != null && result.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				this.entityDropItem(foundMankini, 1);
				this.world.setEntityState(this, (byte)3);
				this.setDead();
			}
		}
			else 
			this.world.setEntityState(this, (byte)3);
			this.setDead();
		}
}
