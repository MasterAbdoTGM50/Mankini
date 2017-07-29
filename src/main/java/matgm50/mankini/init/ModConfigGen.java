package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModLib.MOD_ID, category = "")
@Config.LangKey("mankini.config.title")
public class ModConfigGen {
	
	@Config.Comment({"General settings"})
	public static General general = new General();
	
	@Config.Comment({"Enable mobs."})
	public static ConfigMobs entities = new ConfigMobs();
	
	@Config.Comment({"For configuring changes to mob behavior."})
	public static MobBehavior mobbehavior = new MobBehavior();
	
	public static class General{
		
		@Config.Comment("Ability to shoot Mankini's onto capable mobs (default: true)")
		public boolean ShootMankinisOntoMobs = true;
	}
	
	public static class ConfigMobs {
		
		@Config.RequiresMcRestart
		@Config.Comment("Enables the Mankini Creeper (default: true)")
		public boolean MankiniCreeper = true;
	}
	
	public static class MobBehavior{
		
		@Config.Comment("Mankini creepers overrides armor (default: false)")
		public boolean CreeperOverride = false;
		
		@Config.Comment("Mankini creepers become evil and mankini's default with curse of binding (default: false)")
		public boolean EvilCreepers = false;
	}
	@Mod.EventBusSubscriber(modid = ModLib.MOD_ID)
	private static class EventHandler {
		
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(ModLib.MOD_ID)) {
				ConfigManager.sync(ModLib.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
