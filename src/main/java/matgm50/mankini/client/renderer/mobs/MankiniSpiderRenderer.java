package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.MankiniSpiderEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;

public class MankiniSpiderRenderer extends SpiderRenderer<MankiniSpiderEntity> {
	private static final ResourceLocation MANKINI_SPIDER = ModLib.modLoc("textures/entity/mankini_spider.png");

	public MankiniSpiderRenderer(Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(MankiniSpiderEntity entity) {
		return MANKINI_SPIDER;
	}
}