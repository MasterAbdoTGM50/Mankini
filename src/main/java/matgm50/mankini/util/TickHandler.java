package matgm50.mankini.util;

import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class TickHandler {

    @SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		if (event.getEntity() instanceof EntityMankiniCreeper) {
            {
            	ItemStack itemStackToDrop = new ItemStack(ModItems.itemDyeableMankini, 1);
            	event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, 
            		event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
		}
		if (event.getEntity() instanceof EntityMankiniEnderman) {
            {
            	ItemStack itemStackToDrop = new ItemStack(ModItems.itemDyeableMankini, 1);
            	event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, 
            		event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
		}
		if (event.getEntity() instanceof EntityMankiniSpider) {
            {
            	ItemStack itemStackToDrop = new ItemStack(ModItems.itemDyeableMankini, 1);
            	event.getDrops().add(new EntityItem(event.getEntity().worldObj, event.getEntity().posX, 
            		event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
		}
    }
		
    @SubscribeEvent
    public void tick(PlayerTickEvent event) {

        EntityPlayer player = event.player;

        ItemStack armor = player.inventory.armorItemInSlot(2);

        if(armor != null) {

            if(armor.getItem() == ModItems.itemAethericMankini) {

                player.capabilities.allowFlying = true;

            }
            else if (armor.getItem() == ModItems.itemBatMankini) {

               player.fallDistance = 0F;

            }

        } else {

            if(!player.capabilities.isCreativeMode) {

                player.capabilities.allowFlying = false;

            }

        }

    }
    
}
