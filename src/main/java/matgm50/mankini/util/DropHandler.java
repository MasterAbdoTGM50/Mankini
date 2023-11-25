package matgm50.mankini.util;

import matgm50.mankini.lib.ModLib;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DropHandler {

	@SubscribeEvent
	public static void onLivingDrop(LivingDropsEvent event) {

	}
}
