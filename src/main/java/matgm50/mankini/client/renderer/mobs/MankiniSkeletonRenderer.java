package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.model.ModelMankiniSkeleton;
import matgm50.mankini.entity.hostile.MankiniSkeletonEntity;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MankiniSkeletonRenderer extends HumanoidMobRenderer<MankiniSkeletonEntity, ModelMankiniSkeleton<MankiniSkeletonEntity>> {
	private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public MankiniSkeletonRenderer(EntityRendererProvider.Context context) {
		this(context, ClientHandler.MANKINI_SKELETON, ClientHandler.MANKINI_SKELETON_INNER_ARMOR, ClientHandler.MANKINI_SKELETON_OUTER_ARMOR);
	}

	public MankiniSkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation layerLocation, ModelLayerLocation layerLocation1, ModelLayerLocation layerLocation2) {
		super(context, new ModelMankiniSkeleton<>(context.bakeLayer(layerLocation)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel(context.bakeLayer(layerLocation1)), new SkeletonModel(context.bakeLayer(layerLocation2)), context.getModelManager()));
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	public ResourceLocation getTextureLocation(MankiniSkeletonEntity entity) {
		return SKELETON_TEXTURES;
	}

	protected boolean isShaking(AbstractSkeleton p_174389_) {
		return p_174389_.isShaking();
	}
}