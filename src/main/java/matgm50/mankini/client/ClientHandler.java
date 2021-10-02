package matgm50.mankini.client;

import matgm50.mankini.client.renderer.mobs.RenderMankiniCreeper;
import matgm50.mankini.client.renderer.mobs.RenderMankiniEnderman;
import matgm50.mankini.client.renderer.mobs.RenderMankiniEndermite;
import matgm50.mankini.client.renderer.mobs.RenderMankiniEvoker;
import matgm50.mankini.client.renderer.mobs.RenderMankiniSkeleton;
import matgm50.mankini.client.renderer.mobs.RenderMankiniSpider;
import matgm50.mankini.client.renderer.mobs.RenderMankiniWither;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.CustomSpawnEggItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.DyeableArmorItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {

    public static void registerRenders(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_CAPSULE.get(), renderManager -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_CREEPER.get(), RenderMankiniCreeper::new);
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_ENDERMAN.get(), RenderMankiniEnderman::new);
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_ENDERMITE.get(), RenderMankiniEndermite::new);
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_SPIDER.get(), RenderMankiniSpider::new);
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_SKELETON.get(), RenderMankiniSkeleton::new);
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_WITHER.get(), RenderMankiniWither::new);
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_WITHER_PROJECTILE.get(), renderManager -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.MANKINI_EVOKER.get(), RenderMankiniEvoker::new);
    }

    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        itemColors.register((p_getColor_1_, p_getColor_2_) -> {
            return p_getColor_2_ > 0 ? -1 : ((DyeableArmorItem)p_getColor_1_.getItem()).getColor(p_getColor_1_);
        }, ModRegistry.DYEABLE_MANKINI.get());

        for(CustomSpawnEggItem spawneggitem : CustomSpawnEggItem.getEggs()) {
            itemColors.register((p_198141_1_, p_198141_2_) -> {
                return spawneggitem.getColor(p_198141_2_);
            }, spawneggitem);
        }
    }
}
