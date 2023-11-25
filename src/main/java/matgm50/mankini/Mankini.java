package matgm50.mankini;

import com.mojang.logging.LogUtils;
import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.MankiniModifiers;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.init.ModSpawning;
import matgm50.mankini.lib.ModLib;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(ModLib.MOD_ID)
public class Mankini {
	public static final Logger logger = LogUtils.getLogger();

	public Mankini() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MankiniConfig.commonSpec);
		eventBus.register(MankiniConfig.class);

		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.CREATIVE_MODE_TABS.register(eventBus);
		ModRegistry.ENTITY_TYPES.register(eventBus);
		ModRegistry.MOB_EFFECTS.register(eventBus);
		MankiniModifiers.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		if (FMLEnvironment.dist.isClient()) {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		}
	}
}