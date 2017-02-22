package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class TickHandler {

    public static void init() {

        FMLCommonHandler.instance().bus().register(new TickHandler());

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
