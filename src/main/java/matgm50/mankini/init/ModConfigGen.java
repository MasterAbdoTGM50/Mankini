package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModLib.MOD_ID)
@Config.LangKey("mankini.config.title")
public class ModConfigGen {

	@Config.Comment("Ability to shoot Mankini's onto capable mobs (default: true)")
	public static boolean ShootMankinisOntoMobs = true;
	
	@Mod.EventBusSubscriber(modid = ModLib.MOD_ID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(ModLib.MOD_ID)) {
				ConfigManager.load(ModLib.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
