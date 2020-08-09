package matgm50.mankini;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.init.ModSpawning;
import matgm50.mankini.lib.ModLib;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModLib.MOD_ID)
public class Mankini {
	public static final Logger logger = LogManager.getLogger(ModLib.MOD_ID);

	public Mankini() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MankiniConfig.commonSpec);
		eventBus.register(MankiniConfig.class);

		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.ENTITIES.register(eventBus);
		ModRegistry.EFFECTS.register(eventBus);

		eventBus.addListener(this::setup);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerRenders);
			eventBus.addListener(ClientHandler::registerItemColors);
		});
	}

	private void setup(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(ModSpawning::register);
    }
}