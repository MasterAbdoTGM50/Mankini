package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.client.model.ModelMankinikSkeleton;
import matgm50.mankini.entity.hostile.AbstractMankiniSkeleton;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMankiniSkeleton extends RenderBiped<AbstractMankiniSkeleton> {
	private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public RenderMankiniSkeleton(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelMankinikSkeleton(), 0.5F);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerBipedArmor(this) {
			protected void initArmor() {
				this.modelLeggings = new ModelMankinikSkeleton(0.5F, true);
				this.modelArmor = new ModelMankinikSkeleton(1.0F, true);
			}
		});
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(AbstractMankiniSkeleton entity) {
		return SKELETON_TEXTURES;
	}
}