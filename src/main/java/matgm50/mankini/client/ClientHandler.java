package matgm50.mankini.client;

import matgm50.mankini.client.extension.AAMTItemExtension;
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
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ClientHandler {
	public static final ModelLayerLocation MANKINI_WITHER = createLocation("wither", "main");
	public static final ModelLayerLocation MANKINI_WITHER_ARMOR = createLocation("wither", "armor");
	public static final ModelLayerLocation MANKINI_SKELETON = new ModelLayerLocation(ModLib.modLoc("main"), "skeleton");
	public static final ArmorModelSet<ModelLayerLocation> MANKINI_SKELETON_ARMOR = createArmorSet("skeleton");
	public static final ModelLayerLocation AAMT = new ModelLayerLocation(ModLib.modLoc("main"), "aamt");

	private static ArmorModelSet<ModelLayerLocation> createArmorSet(String path) {
		return new ArmorModelSet<>(
				createLocation(path, "helmet"), createLocation(path, "chestplate"), createLocation(path, "leggings"), createLocation(path, "boots")
		);
	}

	private static ModelLayerLocation createLocation(String path, String model) {
		return new ModelLayerLocation(ModLib.modLoc(path), model);
	}

	public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new AAMTItemExtension(), ModRegistry.AETHERIC_MANKINI.get());
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
		ArmorModelSet<LayerDefinition> armormodelset = HumanoidModel.createArmorMeshSet(LayerDefinitions.INNER_ARMOR_DEFORMATION, LayerDefinitions.OUTER_ARMOR_DEFORMATION)
				.map(definition -> LayerDefinition.create(definition, 64, 32));
		event.registerLayerDefinition(MANKINI_SKELETON_ARMOR.head(), armormodelset::head);
		event.registerLayerDefinition(MANKINI_SKELETON_ARMOR.chest(), armormodelset::chest);
		event.registerLayerDefinition(MANKINI_SKELETON_ARMOR.legs(), armormodelset::legs);
		event.registerLayerDefinition(MANKINI_SKELETON_ARMOR.feet(), armormodelset::feet);
		event.registerLayerDefinition(AAMT, ModelAAMT::createMesh);
	}
}
