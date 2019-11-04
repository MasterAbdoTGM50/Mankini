package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.client.model.ModelMankinikSkeleton;
import matgm50.mankini.entity.hostile.EntityMankiniSkeleton;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMankiniSkeleton extends BipedRenderer<EntityMankiniSkeleton, ModelMankinikSkeleton<EntityMankiniSkeleton>> {
	private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public RenderMankiniSkeleton(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ModelMankinikSkeleton<>(), 0.5F);
		this.addLayer(new HeldItemLayer<>(this));
		this.addLayer(new BipedArmorLayer(this, new ModelMankinikSkeleton(0.5F, true), new ModelMankinikSkeleton(1.0F, true)));
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityMankiniSkeleton entity) {
		return SKELETON_TEXTURES;
	}
}