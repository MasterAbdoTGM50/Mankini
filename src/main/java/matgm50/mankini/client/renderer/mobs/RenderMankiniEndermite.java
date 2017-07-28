package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMankiniEndermite extends RenderLiving<EntityMankiniEndermite>{

	public static final Factory FACTORY = new Factory();
	
    private static final ResourceLocation MANKINI_ENDERMITE = new ResourceLocation("mankini:textures/entity/mankiniendermite.png");
	
	public RenderMankiniEndermite(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelEnderMite(), 0.3F);
	}	
	
	protected ResourceLocation getEntityTexture(EntityMankiniEndermite entity) {
		return MANKINI_ENDERMITE;
	}
	public static class Factory implements IRenderFactory<EntityMankiniEndermite> {

	@Override
	public Render<? super EntityMankiniEndermite> createRenderFor(RenderManager manager) {
	return new RenderMankiniEndermite(manager);
	}

	}
}