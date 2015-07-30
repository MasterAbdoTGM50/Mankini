package matgm50.mankini.util;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import matgm50.mankini.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

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

        ItemStack armor = player.getCurrentArmor(2);

        if(armor != null) {

            if(armor.getItem() == ModItems.itemAethericMankini) {

                player.capabilities.allowFlying = true;

            }

        } else {

            if(!player.capabilities.isCreativeMode) {

                player.capabilities.allowFlying = false;

            }

        }

    }
    
}
