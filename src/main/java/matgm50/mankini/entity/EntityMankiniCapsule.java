package matgm50.mankini.entity;

import matgm50.mankini.item.ModItems;
import matgm50.mankini.util.MankiniHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
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
        
                
                    for (int i=0; i<=hitPlayer.inventory.getSizeInventory(); i++) {
                    	
                         if (hitPlayer.inventory.getStackInSlot(i) == null) {
                             full = false;
                         }
                    }
                           
                if(!this.worldObj.isRemote) {

                    if(hitPlayer.inventory.armorItemInSlot(2) == null){
                    	hitPlayer.inventory.setInventorySlotContents(38, MankiniHelper.getFirstFoundMankini(hitPlayer));
                    }
        }
        
    }
            setDead();
        }
   }
}
