package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
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
@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TickHandler {

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        boolean allowFlying;

        ItemStack armor = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

        if(armor != null) {
            if (armor.getItem().equals(ModRegistry.WITHER_MANKINI.get())) {
                if(player.getActivePotionEffects().contains(Effects.WITHER) || player.getActivePotionEffects().contains(ModRegistry.MANKINI_WITHER_EFFECT.get())) {
                    player.removePotionEffect(Effects.WITHER);
                    player.removePotionEffect(ModRegistry.MANKINI_WITHER_EFFECT.get());
                }
            }
            if (armor.getItem().equals(ModRegistry.BAT_MANKINI.get())) {
                player.fallDistance = 0F;
            }

            allowFlying = armor.getItem().equals(ModRegistry.AETHERIC_MANKINI.get());

            if (allowFlying && !player.isCreative()) {
				player.abilities.allowFlying = true;
			} else {
            	if (player.abilities.allowFlying && !player.isCreative()) {
		        	player.abilities.isFlying = false;
		        	player.abilities.allowFlying = false;
            	}
            }
        }
    }
}