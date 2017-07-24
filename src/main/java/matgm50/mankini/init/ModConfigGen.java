package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModLib.MOD_ID)
public class ModConfigGen {

	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(ModLib.MOD_ID)) {
				ConfigManager.load(ModLib.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
