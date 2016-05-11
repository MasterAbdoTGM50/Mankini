package matgm50.mankini.entity;

import matgm50.mankini.item.ModItems;
import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class EntityMankiniCapsule extends EntityThrowable {

    ItemStack foundMankini;

    ItemStack kini = new ItemStack(ModItems.itemDyeableMankini);
    public EntityMankiniCapsule(World par1World) {

        super(par1World);

    }

    public EntityMankiniCapsule(World par1World, EntityLivingBase par2EntityLivingBase, ItemStack foundMankini) {

        super(par1World, par2EntityLivingBase);
        this.foundMankini = foundMankini;

    }

    public EntityMankiniCapsule(World par1World, double par2, double par4, double par6) {

        super(par1World, par2, par4, par6);

    }

   @Override
    protected void onImpact(RayTraceResult mop) {
	   
        if(mop.typeOfHit != null && mop.typeOfHit == RayTraceResult.Type.ENTITY) {

            Entity hit = mop.entityHit;
           
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
            setDead();
        }
        if(!this.worldObj.isRemote) {
      	  if (mop.typeOfHit != null && mop.typeOfHit == RayTraceResult.Type.BLOCK)
      {
         setDead();
         int kiniDrop = Item.getIdFromItem(ModItems.itemDyeableMankini);
         this.dropItem(Item.getItemById(kiniDrop), 1);
      }
      }
        else setDead();
   }
}
