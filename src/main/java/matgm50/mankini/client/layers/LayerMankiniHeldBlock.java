package matgm50.mankini.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import matgm50.mankini.entity.hostile.MankiniEndermanEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMankiniHeldBlock extends RenderLayer<MankiniEndermanEntity, EndermanModel<MankiniEndermanEntity>> {
	public LayerMankiniHeldBlock(RenderLayerParent<MankiniEndermanEntity, EndermanModel<MankiniEndermanEntity>> p_i50949_1_) {
		super(p_i50949_1_);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, MankiniEndermanEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		BlockState blockstate = entitylivingbaseIn.getCarriedBlock();
		if (blockstate != null) {
			matrixStackIn.pushPose();
			matrixStackIn.translate(0.0D, 0.6875D, -0.75D);
			matrixStackIn.mulPose(Axis.XP.rotationDegrees(20.0F));
			matrixStackIn.mulPose(Axis.YP.rotationDegrees(45.0F));
			matrixStackIn.translate(0.25D, 0.1875D, 0.25D);
			float f = 0.5F;
			matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
			matrixStackIn.mulPose(Axis.YP.rotationDegrees(90.0F));
			Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
			matrixStackIn.popPose();
		}
	}
}