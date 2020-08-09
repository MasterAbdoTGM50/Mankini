package matgm50.mankini.client.layers;

import com.mojang.blaze3d.platform.GlStateManager;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMankiniWitherAura extends LayerRenderer<EntityMankiniWither, ModelMankiniWither<EntityMankiniWither>> {
    private static final ResourceLocation WITHER_ARMOR = new ResourceLocation("textures/entity/wither/wither_armor.png");
    private final ModelMankiniWither<EntityMankiniWither> witherModel = new ModelMankiniWither(0.5F);

    public LayerMankiniWitherAura(IEntityRenderer<EntityMankiniWither, ModelMankiniWither<EntityMankiniWither>> p_i50915_1_) {
        super(p_i50915_1_);
    }

    public void render(EntityMankiniWither p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (p_212842_1_.isArmored()) {
            GlStateManager.depthMask(!p_212842_1_.isInvisible());
            this.bindTexture(WITHER_ARMOR);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float lvt_9_1_ = (float)p_212842_1_.ticksExisted + p_212842_4_;
            float lvt_10_1_ = MathHelper.cos(lvt_9_1_ * 0.02F) * 3.0F;
            float lvt_11_1_ = lvt_9_1_ * 0.01F;
            GlStateManager.translatef(lvt_10_1_, lvt_11_1_, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float lvt_12_1_ = 0.5F;
            GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            this.witherModel.setLivingAnimations(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_4_);
            ((ModelMankiniWither)this.getEntityModel()).setModelAttributes(this.witherModel);
            GameRenderer lvt_13_1_ = Minecraft.getInstance().gameRenderer;
            lvt_13_1_.setupFogColor(true);
            this.witherModel.render(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
            lvt_13_1_.setupFogColor(false);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}