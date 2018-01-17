package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class TickHandler {

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent event) {

        EntityPlayer player = event.player;
        boolean allowFlying = false;

        ItemStack armor = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

        if(armor != null) {
        	if (armor.getItem().equals(ModItems.bat_mankini)) {
                player.fallDistance = 0F;
            }
        	
            if(armor.getItem().equals(ModItems.aetheric_mankini)) {
            	allowFlying = true;
            }
            else
            {
            	allowFlying = false;
            }
            
            if (allowFlying && !player.isCreative()) { 
				player.capabilities.allowFlying = true;
			}
            else 
            {
            	if (player.capabilities.allowFlying && !player.isCreative()) { 
		        	player.capabilities.isFlying = false;
		        	player.capabilities.allowFlying = false;
            	}
            }
        }

    }
}