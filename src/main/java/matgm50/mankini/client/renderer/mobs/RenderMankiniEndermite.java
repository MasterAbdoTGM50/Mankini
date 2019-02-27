package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelEnderMite;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMankiniEndermite extends RenderLiving<EntityMankiniEndermite>{

	private static final ResourceLocation MANKINI_ENDERMITE = new ResourceLocation("mankini:textures/entity/mankini_endermite.png");
	
	public RenderMankiniEndermite(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelEnderMite(), 0.3F);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMankiniEndermite entity) {
		return MANKINI_ENDERMITE;
	}
}