package matgm50.mankini.client.model;

import matgm50.mankini.entity.boss.EntityMankiniWither;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class ModelMankiniWither<T extends EntityMankiniWither> extends EntityModel<T> {
    private final RendererModel[] upperBodyParts;
    private final RendererModel[] heads;

    public ModelMankiniWither(float p_i46302_1_) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.upperBodyParts = new RendererModel[3];
        this.upperBodyParts[0] = new RendererModel(this, 0, 16);
        this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, p_i46302_1_);
        this.upperBodyParts[1] = (new RendererModel(this)).setTextureSize(this.textureWidth, this.textureHeight);
        this.upperBodyParts[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
        this.upperBodyParts[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, p_i46302_1_);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, p_i46302_1_);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, p_i46302_1_);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, p_i46302_1_);
        this.upperBodyParts[2] = new RendererModel(this, 12, 22);
        this.upperBodyParts[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, p_i46302_1_);
        this.heads = new RendererModel[3];
        this.heads[0] = new RendererModel(this, 0, 0);
        this.heads[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, p_i46302_1_);
        this.heads[1] = new RendererModel(this, 32, 0);
        this.heads[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
        this.heads[1].rotationPointX = -8.0F;
        this.heads[1].rotationPointY = 4.0F;
        this.heads[2] = new RendererModel(this, 32, 0);
        this.heads[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
        this.heads[2].rotationPointX = 10.0F;
        this.heads[2].rotationPointY = 4.0F;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        for(RendererModel RendererModel : this.heads) {
            RendererModel.render(scale);
        }

        for(RendererModel RendererModel1 : this.upperBodyParts) {
            RendererModel1.render(scale);
        }

    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far"
     * arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        float f = MathHelper.cos(ageInTicks * 0.1F);
        this.upperBodyParts[1].rotateAngleX = (0.065F + 0.05F * f) * (float)Math.PI;
        this.upperBodyParts[2].setRotationPoint(-2.0F, 6.9F + MathHelper.cos(this.upperBodyParts[1].rotateAngleX) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyParts[1].rotateAngleX) * 10.0F);
        this.upperBodyParts[2].rotateAngleX = (0.265F + 0.1F * f) * (float)Math.PI;
        this.heads[0].rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.heads[0].rotateAngleX = headPitch * ((float)Math.PI / 180F);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second and
     * third as in the setRotationAngles method.
     */
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        EntityMankiniWither entitywither = (EntityMankiniWither)entitylivingbaseIn;

        for(int i = 1; i < 3; ++i) {
            this.heads[i].rotateAngleY = (entitywither.getHeadYRotation(i - 1) - entitylivingbaseIn.renderYawOffset) * ((float)Math.PI / 180F);
            this.heads[i].rotateAngleX = entitywither.getHeadXRotation(i - 1) * ((float)Math.PI / 180F);
        }

    }
}