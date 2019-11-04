package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.util.ResourceLocation;

public class RenderMankiniSpider<T extends EntityMankiniSpider> extends MobRenderer<T, SpiderModel<T>> {

    private static final ResourceLocation MANKINI_SPIDER = new ResourceLocation("mankini:textures/entity/mankini_spider.png");

	public RenderMankiniSpider(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SpiderModel(), 0.5F);
	}	

	protected ResourceLocation getEntityTexture(EntityMankiniSpider entity) {
		return MANKINI_SPIDER;
	}
}