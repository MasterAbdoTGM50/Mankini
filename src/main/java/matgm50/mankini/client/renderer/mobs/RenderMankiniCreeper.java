package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMankiniCreeper extends MobRenderer<EntityMankiniCreeper, CreeperModel<EntityMankiniCreeper>> {
    private static final ResourceLocation MANKINI_CREEPER = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_creeper.png");

	public RenderMankiniCreeper(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CreeperModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityMankiniCreeper entity) {
		return MANKINI_CREEPER;
	}
}