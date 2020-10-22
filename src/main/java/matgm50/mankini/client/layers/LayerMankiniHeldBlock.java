package matgm50.mankini.client.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMankiniHeldBlock extends LayerRenderer<EntityMankiniEnderman, EndermanModel<EntityMankiniEnderman>> {
    public LayerMankiniHeldBlock(IEntityRenderer<EntityMankiniEnderman, EndermanModel<EntityMankiniEnderman>> p_i50949_1_) {
        super(p_i50949_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityMankiniEnderman entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        BlockState blockstate = entitylivingbaseIn.getHeldBlockState();
        if (blockstate != null) {
            matrixStackIn.push();
            matrixStackIn.translate(0.0D, 0.6875D, -0.75D);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(20.0F));
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(45.0F));
            matrixStackIn.translate(0.25D, 0.1875D, 0.25D);
            float f = 0.5F;
            matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.0F));
            Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
            matrixStackIn.pop();
        }
    }
}