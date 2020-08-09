package matgm50.mankini.client.renderer.mobs;

import com.mojang.blaze3d.platform.GlStateManager;
import matgm50.mankini.entity.hostile.EntityMankiniEvoker;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMankiniEvoker<T extends EntityMankiniEvoker> extends IllagerRenderer<T> {
	private static final ResourceLocation MANKINI_EVOKER_ILLAGER = new ResourceLocation("mankini:textures/entity/mankini_evoker.png");

	public RenderMankiniEvoker(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
		this.addLayer(new HeldItemLayer<T, IllagerModel<T>>(this) {
			public void render(T p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
				if (p_212842_1_.isSpellcasting()) {
					super.render(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_4_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
				}
			}
		});
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(T entity) {
		return MANKINI_EVOKER_ILLAGER;
	}

	/**
	 * Allows the render to do state modifications necessary before the model is rendered.
	 */
	protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
		float f = 0.9375F;
		GlStateManager.scalef(0.9375F, 0.9375F, 0.9375F);
	}
}