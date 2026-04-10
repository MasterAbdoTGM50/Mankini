package matgm50.mankini.client.renderer.mobs;

import com.mojang.blaze3d.vertex.PoseStack;
import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.layers.LayerMankiniWitherAura;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.entity.boss.MankiniWither;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

public class MankiniWitherRenderer extends MobRenderer<MankiniWither, WitherRenderState, ModelMankiniWither<WitherRenderState>> {
	private static final ResourceLocation MANKINI_WITHER_TEXTURES = ModLib.modLoc("textures/entity/mankini_wither_invulnerable.png");
	private static final ResourceLocation MANKINI_WITHER = ModLib.modLoc("textures/entity/mankini_wither.png");

	public MankiniWitherRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelMankiniWither<>(context.bakeLayer(ClientHandler.MANKINI_WITHER)), 1.0F);
		this.addLayer(new LayerMankiniWitherAura(this, context.getModelSet()));
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(WitherRenderState state) {
		int i = Mth.floor(state.invulnerableTicks);
		return i > 0 && (i > 80 || i / 5 % 2 != 1) ? MANKINI_WITHER_TEXTURES : MANKINI_WITHER;
	}

	@Override
	protected void scale(WitherRenderState state, PoseStack poseStack) {
		float f = 2.0F;
		int i = Mth.floor(state.invulnerableTicks);
		if (i > 0) {
			f -= ((float) i - state.partialTick) / 220.0F * 0.5F;
		}

		poseStack.scale(f, f, f);
	}

	@Override
	public void render(WitherRenderState state, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
		super.render(state, poseStack, bufferSource, 15728880);
	}

	@Override
	public WitherRenderState createRenderState() {
		return new WitherRenderState();
	}

	@Override
	public void extractRenderState(MankiniWither wither, WitherRenderState state, float partialTick) {
		super.extractRenderState(wither, state, partialTick);
		int i = wither.getInvulTime();
		state.invulnerableTicks = i > 0 ? i - partialTick : 0.0F;
		System.arraycopy(wither.getHeadXRots(), 0, state.xHeadRots, 0, state.xHeadRots.length);
		System.arraycopy(wither.getHeadYRots(), 0, state.yHeadRots, 0, state.yHeadRots.length);
		state.isPowered = wither.isPowered();
	}
}