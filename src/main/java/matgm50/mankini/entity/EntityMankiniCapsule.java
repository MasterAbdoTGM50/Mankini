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
    protected void onImpact(MovingObjectPosition mop) {

        if(mop.typeOfHit != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {

            Entity hit = mop.entityHit;

            if(hit instanceof EntityPlayer) {

                EntityPlayer hitPlayer = (EntityPlayer)hit;

                if(!this.worldObj.isRemote) {

                    if(hitPlayer.inventory.armorItemInSlot(2) != null) {

                        ItemStack toSpawn = hitPlayer.inventory.armorItemInSlot(2);
                        EntityItem spawned = new EntityItem(this.worldObj, hitPlayer.posX, hitPlayer.posY, hitPlayer.posZ, toSpawn);
                        worldObj.spawnEntityInWorld(spawned);

                    }

                    hitPlayer.setCurrentItemOrArmor(3, foundMankini);

                    setDead();

                }

            }

        }

    }

}
