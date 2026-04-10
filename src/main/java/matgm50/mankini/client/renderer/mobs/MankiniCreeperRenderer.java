package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;

public class MankiniCreeperRenderer extends CreeperRenderer {
	private static final Identifier MANKINI_CREEPER = ModLib.modLoc("textures/entity/mankini_creeper.png");

	public MankiniCreeperRenderer(Context context) {
		super(context);
	}

	@Override
	public Identifier getTextureLocation(CreeperRenderState state) {
		return MANKINI_CREEPER;
	}
}