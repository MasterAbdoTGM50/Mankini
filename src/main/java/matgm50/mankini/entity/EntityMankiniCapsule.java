package matgm50.mankini.entity;

import matgm50.mankini.item.IMankini;
import matgm50.mankini.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class EntityMankiniCapsule extends EntityThrowable {


    public EntityMankiniCapsule(World par1World) {

        super(par1World);

    }

    public EntityMankiniCapsule(World par1World, EntityLivingBase par2EntityLivingBase) {

        super(par1World, par2EntityLivingBase);

    }

    public EntityMankiniCapsule(World par1World, double par2, double par4, double par6) {

        super(par1World, par2, par4, par6);

    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {

        if(mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {

            Entity hit = mop.entityHit;

            if(hit instanceof EntityPlayer) {

                EntityPlayer hitPlayer = (EntityPlayer)hit;

                ItemStack toPlace = getFirstFoundMankini(getThrower());
                ItemStack toReplace = hitPlayer.getCurrentArmor(2);

                EntityItem replaced = new EntityItem(this.worldObj, hitPlayer.posX, hitPlayer.posY, hitPlayer.posZ, toReplace);

                if(!this.worldObj.isRemote) {

                    hitPlayer.setCurrentItemOrArmor(2, toPlace);

                    worldObj.spawnEntityInWorld(replaced);

                    setDead();

                }

            }

        }

    }

    public ItemStack getFirstFoundMankini(EntityLivingBase thrower) {

        if(thrower instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer)thrower;

            for(int i = 0; i < player.inventory.getSizeInventory(); i++) {

                if(player.inventory.getStackInSlot(i).getItem() instanceof IMankini) {

                    ItemStack foundMankini =  player.inventory.getStackInSlot(i).copy();
                    player.inventory.getStackInSlot(i).stackSize = 0;
                    return foundMankini;

                }

            }

        }

        return new ItemStack(ModItems.itemDyeableMankini);

    }
}
