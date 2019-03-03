package matgm50.mankini;

import matgm50.mankini.client.renderer.mobs.RenderMankiniCreeper;
import matgm50.mankini.client.renderer.mobs.RenderMankiniEnderman;
import matgm50.mankini.client.renderer.mobs.RenderMankiniEndermite;
import matgm50.mankini.client.renderer.mobs.RenderMankiniEvoker;
import matgm50.mankini.client.renderer.mobs.RenderMankiniSkeleton;
import matgm50.mankini.client.renderer.mobs.RenderMankiniSpider;
import matgm50.mankini.client.renderer.mobs.RenderMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWitherCapsule;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.entity.hostile.EntityMankiniEvoker;
import matgm50.mankini.entity.hostile.EntityMankiniSkeleton;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModCreativeTab;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.init.ModSpawning;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.item.ItemArmorDyeable;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
			MinecraftForge.EVENT_BUS.addListener(ClientHandler::registerBlockColors);
		});
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		ModSpawning.register();
    }

	public static class ClientHandler {
		public static void registerRenders(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class,
					renderManager -> new RenderSprite<EntityMankiniCapsule>(renderManager, ModItems.mankini_capsule, Minecraft.getInstance().getItemRenderer()));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCreeper.class,
					renderManager -> new RenderMankiniCreeper(renderManager));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEnderman.class,
					renderManager -> new RenderMankiniEnderman(renderManager));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEndermite.class,
					renderManager -> new RenderMankiniEndermite(renderManager));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniSpider.class,
					renderManager -> new RenderMankiniSpider(renderManager));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniSkeleton.class,
					renderManager -> new RenderMankiniSkeleton(renderManager));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWither.class,
					renderManager -> new RenderMankiniWither(renderManager));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWitherCapsule.class,
					renderManager -> new RenderSprite<EntityMankiniWitherCapsule>(renderManager, ModItems.mankini_capsule, Minecraft.getInstance().getItemRenderer()));
			RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEvoker.class,
					renderManager -> new RenderMankiniEvoker(renderManager));
		}

		public static void registerBlockColors(ColorHandlerEvent.Item event) {
			ItemColors itemColors = event.getItemColors();

			itemColors.register((p_getColor_1_, p_getColor_2_) -> {
				return p_getColor_2_ > 0 ? -1 : ((ItemArmorDyeable)p_getColor_1_.getItem()).getColor(p_getColor_1_);
			}, ModItems.dyeable_mankini);
		}
	}
}