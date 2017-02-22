package matgm50.mankini.client.renderer;

import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMankiniSpider extends RenderLiving<EntityMankiniSpider>{

	public static final Factory FACTORY = new Factory();
	
    private static final ResourceLocation MANKINI_SPIDER = new ResourceLocation("mankini:textures/entity/mankinispider.png");

	public RenderMankiniSpider(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSpider(), 0.5F);
	}	

	protected ResourceLocation getEntityTexture(EntityMankiniSpider entity) {
		return MANKINI_SPIDER;
	}
	public static class Factory implements IRenderFactory<EntityMankiniSpider> {

	@Override
	public Render<? super EntityMankiniSpider> createRenderFor(RenderManager manager) {
	return new RenderMankiniSpider(manager);
	}

	}
}