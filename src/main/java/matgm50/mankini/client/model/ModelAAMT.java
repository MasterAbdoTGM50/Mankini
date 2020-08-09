package matgm50.mankini.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelAAMT<T extends LivingEntity> extends BipedModel<T> {
    private final ModelRenderer f1p2;
    private final ModelRenderer f1p3;
    private final ModelRenderer f2p1;
    private final ModelRenderer f2p2;
    private final ModelRenderer f2p3;
    private final ModelRenderer sp1;
    private final ModelRenderer sp1d1;
    private final ModelRenderer sp1d2;
    private final ModelRenderer sp2;
    private final ModelRenderer sp2d1;
    private final ModelRenderer sp2d2;
    private final ModelRenderer sf;
    private final ModelRenderer rj;
    private final ModelRenderer rw1;
    private final ModelRenderer rw2;
    private final ModelRenderer lj;
    private final ModelRenderer lw1;
    private final ModelRenderer lw2;
    private final ModelRenderer f1p1;

    public ModelAAMT(float f) {
        super(f, 0, 64, 64);
        textureWidth = 64;
        textureHeight = 64;

        f1p2 = new ModelRenderer(this);
        f1p2.setRotationPoint(0.0F, 0.0F, 0.0F);
        f1p2.setTextureOffset(0, 37).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 1.0F, 1.0F, 0.0F, true);

        f1p3 = new ModelRenderer(this);
        f1p3.setRotationPoint(0.0F, 0.0F, 0.0F);
        f1p3.setTextureOffset(0, 39).addBox(-2.0F, 5.0F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);

        f2p1 = new ModelRenderer(this);
        f2p1.setRotationPoint(0.0F, 0.0F, 0.0F);
        f2p1.setTextureOffset(0, 41).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 3.0F, 1.0F, 0.0F, true);

        f2p2 = new ModelRenderer(this);
        f2p2.setRotationPoint(0.0F, 3.0F, 0.0F);
        f2p2.setTextureOffset(0, 45).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);

        f2p3 = new ModelRenderer(this);
        f2p3.setRotationPoint(0.0F, 0.0F, 0.0F);
        f2p3.setTextureOffset(0, 47).addBox(-1.0F, 4.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        sp1 = new ModelRenderer(this);
        sp1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sp1, 0.0F, 0.0F, -0.6981F);
        sp1.setTextureOffset(18, 32).addBox(-6.0F, -4.0F, -2.5F, 7.0F, 1.0F, 5.0F, 0.0F, true);

        sp1d1 = new ModelRenderer(this);
        sp1d1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sp1d1, 0.0F, 0.0F, -0.6981F);
        sp1d1.setTextureOffset(18, 38).addBox(-7.0F, -4.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        sp1d2 = new ModelRenderer(this);
        sp1d2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sp1d2, 0.0F, 0.0F, -0.6981F);
        sp1d2.setTextureOffset(18, 38).addBox(-7.0F, -4.0F, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        sp2 = new ModelRenderer(this);
        sp2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sp2, 0.0F, 0.0F, -0.6981F);
        sp2.setTextureOffset(18, 40).addBox(0.0F, -6.0F, -2.5F, 1.0F, 2.0F, 5.0F, 0.0F, true);

        sp2d1 = new ModelRenderer(this);
        sp2d1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sp2d1, 0.0F, 0.0F, -0.6981F);
        sp2d1.setTextureOffset(18, 47).addBox(0.0F, -7.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        sp2d2 = new ModelRenderer(this);
        sp2d2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sp2d2, 0.0F, 0.0F, -0.6981F);
        sp2d2.setTextureOffset(18, 47).addBox(0.0F, -7.0F, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        sf = new ModelRenderer(this);
        sf.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(sf, 0.0F, 0.0F, -0.6981F);
        sf.setTextureOffset(18, 49).addBox(-5.0F, -5.0F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, true);

        rj = new ModelRenderer(this);
        rj.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(rj, 0.2618F, 0.0F, 0.2618F);
        rj.setTextureOffset(42, 32).addBox(-5.0F, 2.0F, 3.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

        rw1 = new ModelRenderer(this);
        rw1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(rw1, 0.2618F, 0.0F, 0.2618F);
        rw1.setTextureOffset(42, 44).addBox(-2.0F, 2.0F, 7.0F, 1.0F, 4.0F, 3.0F, 0.0F, true);

        rw2 = new ModelRenderer(this);
        rw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(rw2, 0.2618F, 0.0F, 0.2618F);
        rw2.setTextureOffset(42, 51).addBox(-2.0F, 2.0F, 10.0F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        lj = new ModelRenderer(this);
        lj.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lj, 0.2618F, 0.0F, -0.2618F);
        lj.setTextureOffset(42, 32).addBox(1.0F, 2.0F, 3.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

        lw1 = new ModelRenderer(this);
        lw1.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lw1, 0.2618F, 0.0F, -0.2618F);
        lw1.setTextureOffset(42, 44).addBox(1.0F, 2.0F, 7.0F, 1.0F, 4.0F, 3.0F, 0.0F, true);

        lw2 = new ModelRenderer(this);
        lw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(lw2, 0.2618F, 0.0F, -0.2618F);
        lw2.setTextureOffset(42, 51).addBox(1.0F, 2.0F, 10.0F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        f1p1 = new ModelRenderer(this);
        f1p1.setRotationPoint(0.0F, 0.0F, 0.0F);
        f1p1.setTextureOffset(0, 32).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 4.0F, 1.0F, 0.0F, true);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        f1p2.render(matrixStackIn, buffer, packedLight, packedOverlay);
        f1p3.render(matrixStackIn, buffer, packedLight, packedOverlay);
        f2p1.render(matrixStackIn, buffer, packedLight, packedOverlay);
        f2p2.render(matrixStackIn, buffer, packedLight, packedOverlay);
        f2p3.render(matrixStackIn, buffer, packedLight, packedOverlay);
        sp1.render(matrixStackIn, buffer, packedLight, packedOverlay);
        sp1d1.render(matrixStackIn, buffer, packedLight, packedOverlay);
        sp1d2.render(matrixStackIn, buffer, packedLight, packedOverlay);
        sp2.render(matrixStackIn, buffer, packedLight, packedOverlay);
        sp2d1.render(matrixStackIn, buffer, packedLight, packedOverlay);
        sp2d2.render(matrixStackIn, buffer, packedLight, packedOverlay);
        sf.render(matrixStackIn, buffer, packedLight, packedOverlay);
        rj.render(matrixStackIn, buffer, packedLight, packedOverlay);
        rw1.render(matrixStackIn, buffer, packedLight, packedOverlay);
        rw2.render(matrixStackIn, buffer, packedLight, packedOverlay);
        lj.render(matrixStackIn, buffer, packedLight, packedOverlay);
        lw1.render(matrixStackIn, buffer, packedLight, packedOverlay);
        lw2.render(matrixStackIn, buffer, packedLight, packedOverlay);
        f1p1.render(matrixStackIn, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
