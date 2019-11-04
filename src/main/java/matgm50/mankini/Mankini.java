package matgm50.mankini;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModCreativeTab;
import matgm50.mankini.init.ModSpawning;
import matgm50.mankini.lib.ModLib;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
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
    public static Mankini instance;

	public static final Logger logger = LogManager.getLogger(ModLib.MOD_ID);

    public static ItemGroup tabMankini = new ModCreativeTab(ModLib.MOD_ID);

	public Mankini() {
		instance = this;

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MankiniConfig.commonSpec);
		FMLJavaModLoadingContext.get().getModEventBus().register(MankiniConfig.class);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			MinecraftForge.EVENT_BUS.addListener(ClientHandler::registerRenders);
			FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::registerItemColors);
		});
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		ModSpawning.register();
    }
}