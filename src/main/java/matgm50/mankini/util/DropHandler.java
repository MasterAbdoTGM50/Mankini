package matgm50.mankini.util;

import matgm50.mankini.lib.ModLib;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

@EventBusSubscriber(modid = ModLib.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class DropHandler {

	@SubscribeEvent
	public static void onLivingDrop(LivingDropsEvent event) {

	}
}
