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

        if(mop.typeOfHit != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {

            Entity hit = mop.entityHit;

            if(getThrower() instanceof EntityPlayer && hit instanceof EntityPlayer) {

                EntityPlayer hitPlayer = (EntityPlayer)hit;

                ItemStack toPlace = getFirstFoundMankini((EntityPlayer)getThrower());

                if(!this.worldObj.isRemote) {

                    if(hitPlayer.getCurrentArmor(2) != null) {

                        ItemStack toSpawn = hitPlayer.getCurrentArmor(2).copy();
                        EntityItem spawned = new EntityItem(this.worldObj, hitPlayer.posX, hitPlayer.posY, hitPlayer.posZ, toSpawn);
                        worldObj.spawnEntityInWorld(spawned);

                    }

                    hitPlayer.setCurrentItemOrArmor(2, toPlace);

                    setDead();

                }

            }

        }

    }

    public ItemStack getFirstFoundMankini(EntityPlayer thrower) {

        for(int i = 0; i < thrower.inventory.getSizeInventory(); i++) {

            if(thrower.inventory.getStackInSlot(i).getItem() instanceof IMankini) {

                ItemStack foundMankini =  thrower.inventory.getStackInSlot(i).copy();
                --thrower.inventory.getStackInSlot(i).stackSize;
                return foundMankini;

            }

        }

        return new ItemStack(ModItems.itemDyeableMankini);

    }
}
