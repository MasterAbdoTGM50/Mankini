package matgm50.mankini.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class EntityMankiniCapsule extends EntityThrowable {

    ItemStack foundMankini;

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

                EntityPlayer hitPlayer = (EntityPlayer)hit;
                Boolean full;
                
                    for (int i=0; i<=hitPlayer.inventory.getSizeInventory()-5; i++) {
                         if (hitPlayer.inventory.mainInventory[i] != null) {
                             full = false;
                         }
                    }
                    full = true;
                

                if(!this.worldObj.isRemote) {

                    if(hitPlayer.inventory.armorItemInSlot(2) != null) {
                    	
                    	 if(full = false) {

                        ItemStack currentChest = hitPlayer.inventory.armorItemInSlot(3);
                        hitPlayer.inventory.addItemStackToInventory(currentChest);
                        

                    	 }
                    	 else if(full = true){
                    		 ItemStack toSpawn = foundMankini;
                             EntityItem spawned = new EntityItem(this.worldObj, hitPlayer.posX, hitPlayer.posY, hitPlayer.posZ, toSpawn);
                             worldObj.spawnEntityInWorld(spawned);
                    	 }
                    }

                    hitPlayer.inventory.setInventorySlotContents(3, foundMankini);

                    setDead();

                }
                

            }

        }

    }


    

}
