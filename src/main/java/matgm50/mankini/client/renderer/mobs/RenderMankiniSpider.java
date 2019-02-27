package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelSpider;
import net.minecraft.util.ResourceLocation;

public class RenderMankiniSpider extends RenderLiving<EntityMankiniSpider>{

    private static final ResourceLocation MANKINI_SPIDER = new ResourceLocation("mankini:textures/entity/mankini_spider.png");

	public RenderMankiniSpider(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSpider(), 0.5F);
	}	

	protected ResourceLocation getEntityTexture(EntityMankiniSpider entity) {
		return MANKINI_SPIDER;
	}
}