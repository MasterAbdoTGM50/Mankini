package matgm50.mankini.client.renderer;

import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMankiniEnderman extends RenderLiving<EntityMankiniEnderman>{

	public static final Factory FACTORY = new Factory();
	
    private static final ResourceLocation MANKINI_ENDERMAN = new ResourceLocation("mankini:textures/entity/mankinienderman.png");
	
	public RenderMankiniEnderman(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelEnderman(0.0F), 0.5F);
	}	
	
	protected ResourceLocation getEntityTexture(EntityMankiniEnderman entity) {
		return MANKINI_ENDERMAN;
	}
	public static class Factory implements IRenderFactory<EntityMankiniEnderman> {

	@Override
	public Render<? super EntityMankiniEnderman> createRenderFor(RenderManager manager) {
	return new RenderMankiniEnderman(manager);
	}

	}
}