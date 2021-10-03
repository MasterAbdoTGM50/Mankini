package matgm50.mankini.client;

import matgm50.mankini.client.model.ModelAAMT;
import matgm50.mankini.client.model.ModelMankiniSkeleton;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.client.renderer.AAMTRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniCreeperRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniEndermanRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniEndermiteRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniEvokerRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniSkeletonRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniSpiderRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniWitherRenderer;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.item.CustomSpawnEggItem;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ClientHandler {
    public static final ModelLayerLocation MANKINI_WITHER = new ModelLayerLocation(new ResourceLocation(ModLib.MOD_ID, "main"), "wither");
    public static final ModelLayerLocation MANKINI_WITHER_ARMOR = new ModelLayerLocation(new ResourceLocation(ModLib.MOD_ID, "armor"), "wither");
    public static final ModelLayerLocation MANKINI_SKELETON = new ModelLayerLocation(new ResourceLocation(ModLib.MOD_ID, "main"), "skeleton");
    public static final ModelLayerLocation MANKINI_SKELETON_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation(ModLib.MOD_ID, "inner_armor"), "skeleton");
    public static final ModelLayerLocation MANKINI_SKELETON_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation(ModLib.MOD_ID, "outer_armor"), "skeleton");
    public static final ModelLayerLocation AAMT = new ModelLayerLocation(new ResourceLocation(ModLib.MOD_ID, "main"), "aamt");

    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModRegistry.MANKINI_CAPSULE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_CREEPER.get(), MankiniCreeperRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_ENDERMAN.get(), MankiniEndermanRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_ENDERMITE.get(), MankiniEndermiteRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_SPIDER.get(), MankiniSpiderRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_SKELETON.get(), MankiniSkeletonRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_WITHER.get(), MankiniWitherRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_WITHER_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModRegistry.MANKINI_EVOKER.get(), MankiniEvokerRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MANKINI_WITHER, () -> ModelMankiniWither.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(MANKINI_WITHER_ARMOR, () -> ModelMankiniWither.createBodyLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION));
        event.registerLayerDefinition(MANKINI_SKELETON, () -> ModelMankiniSkeleton.createBodyLayer());
        event.registerLayerDefinition(MANKINI_SKELETON_INNER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F), 64, 32));
        event.registerLayerDefinition(MANKINI_SKELETON_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32));
        event.registerLayerDefinition(AAMT, () -> ModelAAMT.createMesh());
    }

    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        itemColors.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableArmorItem)stack.getItem()).getColor(stack), ModRegistry.DYEABLE_MANKINI.get());

        for(RegistryObject<Item> itemObject : ModRegistry.ITEMS.getEntries()) {
            if(itemObject.get() instanceof CustomSpawnEggItem spawnEggItem) {
                itemColors.register((stack, tintIndex) -> spawnEggItem.getColor(tintIndex), spawnEggItem);
            }
        }
    }
}
