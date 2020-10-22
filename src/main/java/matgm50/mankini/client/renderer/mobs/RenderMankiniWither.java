package matgm50.mankini.client.renderer.mobs;

import com.mojang.blaze3d.matrix.MatrixStack;
import matgm50.mankini.client.layers.LayerMankiniWitherAura;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMankiniWither extends MobRenderer<EntityMankiniWither, ModelMankiniWither<EntityMankiniWither>> {
	private static final ResourceLocation MANKINI_WITHER_TEXTURES = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_wither_invulnerable.png");
	private static final ResourceLocation MANKINI_WITHER = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_wither.png");

	public RenderMankiniWither(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ModelMankiniWither(0.0F), 1.0F);
		this.addLayer(new LayerMankiniWitherAura(this));
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(EntityMankiniWither entity) {
		int i = entity.getInvulTime();
		return i > 0 && (i > 80 || i / 5 % 2 != 1) ? MANKINI_WITHER_TEXTURES : MANKINI_WITHER;
	}

	@Override
	protected void preRenderCallback(EntityMankiniWither entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		float f = 2.0F;
		int i = entitylivingbaseIn.getInvulTime();
		if (i > 0) {
			f -= ((float)i - partialTickTime) / 220.0F * 0.5F;
		}

		matrixStackIn.scale(f, f, f);
	}
}