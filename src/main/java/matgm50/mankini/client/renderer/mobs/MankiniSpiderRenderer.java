package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.MankiniSpiderEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;

public class MankiniSpiderRenderer extends SpiderRenderer<MankiniSpiderEntity> {
	private static final ResourceLocation MANKINI_SPIDER = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_spider.png");

	public MankiniSpiderRenderer(Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(MankiniSpiderEntity entity) {
		return MANKINI_SPIDER;
	}
}