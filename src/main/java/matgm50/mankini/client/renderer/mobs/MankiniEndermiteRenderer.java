package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EndermiteRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class MankiniEndermiteRenderer extends EndermiteRenderer {
	private static final ResourceLocation MANKINI_ENDERMITE = ModLib.modLoc("textures/entity/mankini_endermite.png");

	public MankiniEndermiteRenderer(Context context) {
		super(context);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return MANKINI_ENDERMITE;
	}
}