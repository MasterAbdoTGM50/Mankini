package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EndermiteModel;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMankiniEndermite extends MobRenderer<EntityMankiniEndermite, EndermiteModel<EntityMankiniEndermite>> {

	private static final ResourceLocation MANKINI_ENDERMITE = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_endermite.png");
	
	public RenderMankiniEndermite(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new EndermiteModel(), 0.3F);
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(EntityMankiniEndermite entity) {
		return MANKINI_ENDERMITE;
	}
}