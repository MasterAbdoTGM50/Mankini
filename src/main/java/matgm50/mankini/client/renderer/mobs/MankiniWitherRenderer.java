package matgm50.mankini.client.renderer.mobs;

import com.mojang.blaze3d.vertex.PoseStack;
import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.layers.LayerMankiniWitherAura;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.entity.boss.MankiniWitherEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class MankiniWitherRenderer extends MobRenderer<MankiniWitherEntity, ModelMankiniWither<MankiniWitherEntity>> {
	private static final ResourceLocation MANKINI_WITHER_TEXTURES = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_wither_invulnerable.png");
	private static final ResourceLocation MANKINI_WITHER = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_wither.png");

	public MankiniWitherRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelMankiniWither(context.bakeLayer(ClientHandler.MANKINI_WITHER)), 1.0F);
		this.addLayer(new LayerMankiniWitherAura(this, context.getModelSet()));
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(MankiniWitherEntity entity) {
		int i = entity.getInvulTime();
		return i > 0 && (i > 80 || i / 5 % 2 != 1) ? MANKINI_WITHER_TEXTURES : MANKINI_WITHER;
	}

	@Override
	protected void scale(MankiniWitherEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
		float f = 2.0F;
		int i = entitylivingbaseIn.getInvulTime();
		if (i > 0) {
			f -= ((float)i - partialTickTime) / 220.0F * 0.5F;
		}

		matrixStackIn.scale(f, f, f);
	}
}