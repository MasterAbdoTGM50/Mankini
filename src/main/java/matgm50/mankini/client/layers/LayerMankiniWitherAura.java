package matgm50.mankini.client.layers;

import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.client.renderer.mobs.RenderMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMankiniWitherAura implements LayerRenderer<EntityMankiniWither> {
    private static final ResourceLocation MANKINI_WITHER_ARMOR = new ResourceLocation("mankini:textures/entity/mankini_wither_armor.png");
    private final RenderMankiniWither witherRenderer;
    private final ModelMankiniWither witherModel = new ModelMankiniWither(0.5F);

    public LayerMankiniWitherAura(RenderMankiniWither witherRendererIn) {
        this.witherRenderer = witherRendererIn;
    }

    public void render(EntityMankiniWither entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entitylivingbaseIn.isArmored()) {
            GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
            this.witherRenderer.bindTexture(MANKINI_WITHER_ARMOR);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = (float)entitylivingbaseIn.ticksExisted + partialTicks;
            float f1 = MathHelper.cos(f * 0.02F) * 3.0F;
            float f2 = f * 0.01F;
            GlStateManager.translatef(f1, f2, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float f3 = 0.5F;
            GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            this.witherModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.witherModel.setModelAttributes(this.witherRenderer.getMainModel());
            Minecraft.getInstance().entityRenderer.setupFogColor(true);
            this.witherModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.getInstance().entityRenderer.setupFogColor(false);
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