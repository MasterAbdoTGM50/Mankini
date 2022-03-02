package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;

public class MankiniEndermanRenderer extends EndermanRenderer {
	private static final ResourceLocation MANKINI_ENDERMAN = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_enderman.png");

	public MankiniEndermanRenderer(Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(EnderMan entity) {
		return MANKINI_ENDERMAN;
	}
}