package matgm50.mankini.client.layers;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMankiniHeldBlock extends LayerRenderer<EntityMankiniEnderman, EndermanModel<EntityMankiniEnderman>> {
    public LayerMankiniHeldBlock(IEntityRenderer<EntityMankiniEnderman, EndermanModel<EntityMankiniEnderman>> p_i50949_1_) {
        super(p_i50949_1_);
    }

    public void render(EntityMankiniEnderman p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        BlockState lvt_9_1_ = p_212842_1_.getHeldBlockState();
        if (lvt_9_1_ != null) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, 0.6875F, -0.75F);
            GlStateManager.rotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(0.25F, 0.1875F, 0.25F);
            float lvt_10_1_ = 0.5F;
            GlStateManager.scalef(-0.5F, -0.5F, 0.5F);
            int lvt_11_1_ = p_212842_1_.getBrightnessForRender();
            int lvt_12_1_ = lvt_11_1_ % 65536;
            int lvt_13_1_ = lvt_11_1_ / 65536;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float)lvt_12_1_, (float)lvt_13_1_);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            Minecraft.getInstance().getBlockRendererDispatcher().renderBlockBrightness(lvt_9_1_, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}