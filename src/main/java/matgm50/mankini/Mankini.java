package matgm50.mankini;

import com.mojang.logging.LogUtils;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.MankiniModifiers;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(ModLib.MOD_ID)
public class Mankini {
	public static final Logger logger = LogUtils.getLogger();

	public Mankini(IEventBus eventBus, ModContainer container, Dist dist) {
		container.registerConfig(ModConfig.Type.COMMON, MankiniConfig.commonSpec);
		eventBus.register(MankiniConfig.class);

		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.CREATIVE_MODE_TABS.register(eventBus);
		ModRegistry.ENTITY_TYPES.register(eventBus);
		ModRegistry.MOB_EFFECTS.register(eventBus);
		MankiniModifiers.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		}
	}
}