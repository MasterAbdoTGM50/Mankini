package matgm50.mankini.util;

import matgm50.mankini.init.ModEffects;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */
@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TickHandler {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        boolean allowFlying = false;

        ItemStack armor = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

        if(armor != null) {
            if (armor.getItem().equals(ModItems.wither_mankini)) {
                if(player.getActivePotionEffects().contains(Effects.WITHER) || player.getActivePotionEffects().contains(ModEffects.MANKINI_WITHER)) {
                    player.removePotionEffect(Effects.WITHER);
                    player.removePotionEffect(ModEffects.MANKINI_WITHER);
                }
            }
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
				player.abilities.allowFlying = true;
			}
            else 
            {
            	if (player.abilities.allowFlying && !player.isCreative()) {
		        	player.abilities.isFlying = false;
		        	player.abilities.allowFlying = false;
            	}
            }
        }
    }
}