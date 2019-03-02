package matgm50.mankini.client.renderer.mobs;

import matgm50.mankini.entity.hostile.EntityMankiniEvoker;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.model.ModelIllager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMankiniEvoker extends RenderLiving<EntityMankiniEvoker> {
	private static final ResourceLocation MANKINI_EVOKER_ILLAGER = new ResourceLocation("mankini:textures/entity/mankini_evoker.png");

	public RenderMankiniEvoker(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelIllager(0.0F, 0.0F, 64, 64), 0.5F);
		this.addLayer(new LayerHeldItem(this) {
			public void render(EntityLivingBase livingBase, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
				if (((EntitySpellcasterIllager)livingBase).isSpellcasting()) {
					super.render(livingBase, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
				}
			}

			protected void translateToHand(EnumHandSide p_191361_1_) {
				((ModelIllager)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
			}
		});
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityMankiniEvoker entity) {
		return MANKINI_EVOKER_ILLAGER;
	}

	/**
	 * Allows the render to do state modifications necessary before the model is rendered.
	 */
	protected void preRenderCallback(EntityMankiniEvoker entitylivingbaseIn, float partialTickTime) {
		float f = 0.9375F;
		GlStateManager.scalef(0.9375F, 0.9375F, 0.9375F);
	}
}