package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EndermiteRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Endermite;

import javax.annotation.Nullable;

public class MankiniEndermiteRenderer extends EndermiteRenderer {
	private static final ResourceLocation MANKINI_ENDERMITE = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_endermite.png");
	
	public MankiniEndermiteRenderer(Context context) {
		super(context);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Endermite entity) {
		return MANKINI_ENDERMITE;
	}
}