package matgm50.mankini.client.renderer.mobs;

import com.mojang.blaze3d.vertex.PoseStack;
import matgm50.mankini.entity.hostile.MankiniEvokerEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MankiniEvokerRenderer extends EvokerRenderer<MankiniEvokerEntity> {
	private static final ResourceLocation MANKINI_EVOKER_ILLAGER = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_evoker.png");

	public MankiniEvokerRenderer(Context context) {
		super(context);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	public ResourceLocation getTextureLocation(MankiniEvokerEntity entity) {
		return MANKINI_EVOKER_ILLAGER;
	}

	@Override
	protected void scale(MankiniEvokerEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
		float f = 0.9375F;
		matrixStackIn.scale(f, f, f);
	}
}