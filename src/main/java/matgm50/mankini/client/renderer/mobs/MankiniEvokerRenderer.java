package matgm50.mankini.client.renderer.mobs;

import com.mojang.blaze3d.vertex.PoseStack;
import matgm50.mankini.entity.hostile.MankiniEvoker;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.client.renderer.entity.state.EvokerRenderState;
import net.minecraft.resources.ResourceLocation;

public class MankiniEvokerRenderer extends EvokerRenderer<MankiniEvoker> {
	private static final ResourceLocation MANKINI_EVOKER_ILLAGER = ModLib.modLoc("textures/entity/mankini_evoker.png");

	public MankiniEvokerRenderer(Context context) {
		super(context);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	public ResourceLocation getTextureLocation(EvokerRenderState state) {
		return MANKINI_EVOKER_ILLAGER;
	}

	@Override
	protected void scale(EvokerRenderState renderState, PoseStack poseStack) {
		float f = 0.9375F;
		poseStack.scale(f, f, f);
	}
}