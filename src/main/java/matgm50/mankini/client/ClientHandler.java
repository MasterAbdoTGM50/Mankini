package matgm50.mankini.client;

import matgm50.mankini.client.model.ModelAAMT;
import matgm50.mankini.client.model.ModelMankiniSkeleton;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.client.renderer.mobs.MankiniCreeperRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniEndermanRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniEndermiteRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniEvokerRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniSkeletonRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniSpiderRenderer;
import matgm50.mankini.client.renderer.mobs.MankiniWitherRenderer;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.EquipmentClientInfo.Layer;
import net.minecraft.client.resources.model.EquipmentClientInfo.LayerType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ClientHandler {
	public static final ModelLayerLocation MANKINI_WITHER = new ModelLayerLocation(ModLib.modLoc("main"), "wither");
	public static final ModelLayerLocation MANKINI_WITHER_ARMOR = new ModelLayerLocation(ModLib.modLoc("armor"), "wither");
	public static final ModelLayerLocation MANKINI_SKELETON = new ModelLayerLocation(ModLib.modLoc("main"), "skeleton");
	public static final ModelLayerLocation MANKINI_SKELETON_INNER_ARMOR = new ModelLayerLocation(ModLib.modLoc("inner_armor"), "skeleton");
	public static final ModelLayerLocation MANKINI_SKELETON_OUTER_ARMOR = new ModelLayerLocation(ModLib.modLoc("outer_armor"), "skeleton");
	public static final ModelLayerLocation AAMT = new ModelLayerLocation(ModLib.modLoc("main"), "aamt");

	public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new IClientItemExtensions() {
			private LazyLoadedValue<ModelAAMT<?>> model;

			public HumanoidModel<?> provideArmorModelForSlot() {
				if (model == null) {
					model = new LazyLoadedValue<>(() -> new ModelAAMT<>(Minecraft.getInstance().getEntityModels().bakeLayer(ClientHandler.AAMT)));
				}
				return model.get();
			}

			@Override
			public Model getHumanoidArmorModel(ItemStack itemStack, LayerType layerType, Model original) {
				return provideArmorModelForSlot();
			}

			@Override
			public ResourceLocation getArmorTexture(ItemStack stack, LayerType type, Layer layer, ResourceLocation _default) {
				return ModLib.modLoc("textures/models/aetheric_mankini.png");
			}
		}, ModRegistry.AETHERIC_MANKINI.get());
	}

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
		event.registerLayerDefinition(MANKINI_SKELETON, ModelMankiniSkeleton::createBodyLayer);
		event.registerLayerDefinition(MANKINI_SKELETON_INNER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F), 64, 32));
		event.registerLayerDefinition(MANKINI_SKELETON_OUTER_ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32));
		event.registerLayerDefinition(AAMT, ModelAAMT::createMesh);
	}
}
