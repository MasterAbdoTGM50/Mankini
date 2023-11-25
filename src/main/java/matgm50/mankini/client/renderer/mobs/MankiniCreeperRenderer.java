package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MankiniCreeperRenderer extends CreeperRenderer {
	private static final ResourceLocation MANKINI_CREEPER = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_creeper.png");

	public MankiniCreeperRenderer(Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(Creeper entity) {
		return MANKINI_CREEPER;
	}
}