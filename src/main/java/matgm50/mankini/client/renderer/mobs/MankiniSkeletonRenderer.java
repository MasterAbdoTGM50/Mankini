package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.model.ModelMankiniSkeleton;
import matgm50.mankini.client.renderer.state.MankiniSkeletonRenderState;
import matgm50.mankini.entity.hostile.MankiniSkeleton;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class MankiniSkeletonRenderer extends HumanoidMobRenderer<MankiniSkeleton, MankiniSkeletonRenderState, ModelMankiniSkeleton<MankiniSkeletonRenderState>> {
	private static final ResourceLocation SKELETON_TEXTURES = ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png");

	public MankiniSkeletonRenderer(EntityRendererProvider.Context context) {
		this(context, ClientHandler.MANKINI_SKELETON, ClientHandler.MANKINI_SKELETON_INNER_ARMOR);
	}

	public MankiniSkeletonRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, ModelLayerLocation innerArmor) {
		super(context, new ModelMankiniSkeleton<>(context.bakeLayer(layer)), 0.5F);
		this.addLayer(
				new HumanoidArmorLayer<>(
						this,
						new ModelMankiniSkeleton<>(context.bakeLayer(layer)),
						new ModelMankiniSkeleton<>(context.bakeLayer(innerArmor)),
						context.getEquipmentRenderer()
				)
		);
	}

	@Override
	public MankiniSkeletonRenderState createRenderState() {
		return new MankiniSkeletonRenderState();
	}

	public void extractRenderState(MankiniSkeleton skeleton, MankiniSkeletonRenderState state, float partialTick) {
		super.extractRenderState(skeleton, state, partialTick);
		state.isAggressive = skeleton.isAggressive();
		state.isShaking = skeleton.isShaking();
		state.isHoldingBow = skeleton.getMainHandItem().is(Items.BOW);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	public ResourceLocation getTextureLocation(MankiniSkeletonRenderState renderState) {
		return SKELETON_TEXTURES;
	}
}