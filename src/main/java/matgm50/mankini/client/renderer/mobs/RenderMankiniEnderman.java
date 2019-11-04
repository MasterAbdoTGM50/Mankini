package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.client.layers.LayerMankiniHeldBlock;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EndermanEyesLayer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class RenderMankiniEnderman extends MobRenderer<EntityMankiniEnderman, EndermanModel<EntityMankiniEnderman>> {
	
    private static final ResourceLocation MANKINI_ENDERMAN = new ResourceLocation("mankini:textures/entity/mankini_enderman.png");
	private final Random rnd = new Random();
	
	public RenderMankiniEnderman(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new EndermanModel<>(0.0F), 0.5F);
		this.addLayer(new EndermanEyesLayer(this));
		this.addLayer(new LayerMankiniHeldBlock(this));
	}

	@Override
	public EndermanModel<EntityMankiniEnderman> getEntityModel() {
		return super.getEntityModel();
	}

	protected ResourceLocation getEntityTexture(EntityMankiniEnderman entity) {
		return MANKINI_ENDERMAN;
	}
}