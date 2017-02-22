package matgm50.mankini.client.renderer;

import matgm50.mankini.entity.EntityMankiniCreeper;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMankiniCreeper extends RenderLiving<EntityMankiniCreeper>{

	public static final Factory FACTORY = new Factory();
	
    private static final ResourceLocation MANKINI_CREEPER = new ResourceLocation("mankini:textures/entity/mankinicreeper.png");

	public RenderMankiniCreeper(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelCreeper(), 0.5F);
	}	

	protected ResourceLocation getEntityTexture(EntityMankiniCreeper entity) {
		return MANKINI_CREEPER;
	}
	public static class Factory implements IRenderFactory<EntityMankiniCreeper> {

	@Override
	public Render<? super EntityMankiniCreeper> createRenderFor(RenderManager manager) {
	return new RenderMankiniCreeper(manager);
	}

	}
}