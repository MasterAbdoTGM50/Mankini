package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelCreeper;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMankiniCreeper extends RenderLiving<EntityMankiniCreeper> {

    private static final ResourceLocation MANKINI_CREEPER = new ResourceLocation("mankini:textures/entity/mankini_creeper.png");

	public RenderMankiniCreeper(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelCreeper(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMankiniCreeper entity) {
		return MANKINI_CREEPER;
	}
}