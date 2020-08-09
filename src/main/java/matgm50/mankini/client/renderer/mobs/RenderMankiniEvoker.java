package matgm50.mankini.client.renderer.mobs;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import matgm50.mankini.entity.hostile.EntityMankiniEvoker;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMankiniEvoker<T extends EntityMankiniEvoker> extends IllagerRenderer<T> {
	private static final ResourceLocation MANKINI_EVOKER_ILLAGER = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_evoker.png");

	public RenderMankiniEvoker(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
		this.addLayer(new HeldItemLayer<T, IllagerModel<T>>(this) {
			public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				if (entitylivingbaseIn.isSpellcasting()) {
					super.render(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
				}
			}
		});
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	public ResourceLocation getEntityTexture(T entity) {
		return MANKINI_EVOKER_ILLAGER;
	}

	@Override
	protected void preRenderCallback(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		float f = 0.9375F;
		matrixStackIn.scale(0.9375F, 0.9375F, 0.9375F);
	}
}