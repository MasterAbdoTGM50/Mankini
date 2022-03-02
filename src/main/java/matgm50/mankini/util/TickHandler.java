package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
		Player player = event.player;
		boolean allowFlying;

		ItemStack armor = player.getItemBySlot(EquipmentSlot.CHEST);

		if (armor != null && !player.isCreative() && !player.isSpectator()) {
			if (armor.getItem().equals(ModRegistry.WITHER_MANKINI.get())) {
				if (player.getActiveEffects().contains(MobEffects.WITHER) || player.getActiveEffects().contains(ModRegistry.MANKINI_WITHER_EFFECT.get())) {
					player.removeEffect(MobEffects.WITHER);
					player.removeEffect(ModRegistry.MANKINI_WITHER_EFFECT.get());
				}
			}
			if (armor.getItem().equals(ModRegistry.BAT_MANKINI.get())) {
				player.fallDistance = 0F;
			}

			allowFlying = armor.getItem().equals(ModRegistry.AETHERIC_MANKINI.get());

			if (allowFlying) {
				player.getAbilities().mayfly = true;
			} else {
				if (player.getAbilities().mayfly) {
					player.getAbilities().flying = false;
					player.getAbilities().mayfly = false;
				}
			}
		}
	}
}