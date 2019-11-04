package matgm50.mankini.client;

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
import matgm50.mankini.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.DyeableArmorItem;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientHandler {

    public static void registerRenders(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class, renderManager -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCreeper.class, RenderMankiniCreeper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEnderman.class, RenderMankiniEnderman::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEndermite.class, RenderMankiniEndermite::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniSpider.class, RenderMankiniSpider::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniSkeleton.class, RenderMankiniSkeleton::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWither.class, RenderMankiniWither::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWitherCapsule.class, renderManager -> new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEvoker.class, RenderMankiniEvoker::new);
    }


    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        itemColors.register((p_getColor_1_, p_getColor_2_) -> {
            return p_getColor_2_ > 0 ? -1 : ((DyeableArmorItem)p_getColor_1_.getItem()).getColor(p_getColor_1_);
        }, ModItems.dyeable_mankini);
    }
}
