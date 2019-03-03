package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.client.layers.LayerMankiniEndermaneyes;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.model.ModelEnderman;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class RenderMankiniEnderman extends RenderLiving<EntityMankiniEnderman>{
	
    private static final ResourceLocation MANKINI_ENDERMAN = new ResourceLocation("mankini:textures/entity/mankini_enderman.png");
	private final Random rnd = new Random();
	
	public RenderMankiniEnderman(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelEnderman(0.0F), 0.5F);
		this.addLayer(new LayerMankiniEndermaneyes(this));
		this.addLayer(new LayerHeldItem(this));
	}

	public ModelEnderman getMainModel() {
		return (ModelEnderman)super.getMainModel();
	}
	
	protected ResourceLocation getEntityTexture(EntityMankiniEnderman entity) {
		return MANKINI_ENDERMAN;
	}
}