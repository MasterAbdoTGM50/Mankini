package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */
@EventBusSubscriber(modid = ModLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class TickHandler {

	@SubscribeEvent
	public static void playerTick(PlayerTickEvent.Post event) {
		Player player = event.getEntity();
		boolean allowFlying;

		ItemStack armor = player.getItemBySlot(EquipmentSlot.CHEST);

		if (armor != null && !player.isCreative() && !player.isSpectator()) {
			if (armor.getItem().equals(ModRegistry.WITHER_MANKINI.get())) {
				if (player.getActiveEffects().contains(MobEffects.WITHER) ||
						player.getActiveEffects().stream().anyMatch(effect -> effect.getEffect().
								is(ModRegistry.MANKINI_WITHER_EFFECT.getKey()))) {
					player.removeEffect(MobEffects.WITHER);
					player.removeEffect(BuiltInRegistries.MOB_EFFECT.getHolderOrThrow(ModRegistry.MANKINI_WITHER_EFFECT.getKey()));
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