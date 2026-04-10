package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.Identifier;

public class MankiniEndermanRenderer extends EndermanRenderer {
	private static final Identifier MANKINI_ENDERMAN = ModLib.modLoc("textures/entity/mankini_enderman.png");

	public MankiniEndermanRenderer(Context context) {
		super(context);
	}

	@Override
	public Identifier getTextureLocation(EndermanRenderState state) {
		return MANKINI_ENDERMAN;
	}
}