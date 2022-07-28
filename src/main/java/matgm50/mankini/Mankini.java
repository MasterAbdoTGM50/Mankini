package matgm50.mankini;

import com.mojang.logging.LogUtils;
import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.MankiniModifiers;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.init.ModSpawning;
import matgm50.mankini.lib.ModLib;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ModLib.MOD_ID)
public class Mankini {
	public static final Logger logger = LogUtils.getLogger();

	public Mankini() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MankiniConfig.commonSpec);
		eventBus.register(MankiniConfig.class);

		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.ENTITY_TYPES.register(eventBus);
		ModRegistry.MOB_EFFECTS.register(eventBus);
		MankiniModifiers.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		eventBus.addListener(this::setup);
		eventBus.addListener(ModSpawning::registerEntityAttributes);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		});
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModSpawning.registerSpawnPlacement();
	}
}