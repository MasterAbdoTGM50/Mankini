package matgm50.mankini.client.model;

import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAAMT extends ModelBiped {
    ModelRenderer f1p1;
    ModelRenderer f1p2;
    ModelRenderer f1p3;
    ModelRenderer f2p1;
    ModelRenderer f2p2;
    ModelRenderer f2p3;
    ModelRenderer sp1;
    ModelRenderer sp1d1;
    ModelRenderer sp1d2;
    ModelRenderer sp2;
    ModelRenderer sp2d1;
    ModelRenderer sp2d2;
    ModelRenderer sf;
    ModelRenderer rj;
    ModelRenderer rw1;
    ModelRenderer rw2;
    ModelRenderer lj;
    ModelRenderer lw1;
    ModelRenderer lw2;

    public ModelAAMT(float f) {

        super(f, 0, 64, 64);

        textureWidth = 64;
        textureHeight = 64;

        f1p1 = new ModelRenderer(this, 0, 32);
        f1p1.addBox(-4F, 0F, -3F, 8, 4, 1);
        f1p1.setRotationPoint(0F, 0F, 0F);
        f1p1.setTextureSize(64, 32);
        f1p1.mirror = true;
        setRotation(f1p1, 0F, 0F, 0F);
        f1p2 = new ModelRenderer(this, 0, 37);
        f1p2.addBox(-3F, 4F, -3F, 6, 1, 1);
        f1p2.setRotationPoint(0F, 0F, 0F);
        f1p2.setTextureSize(64, 32);
        f1p2.mirror = true;
        setRotation(f1p2, 0F, 0F, 0F);
        f1p3 = new ModelRenderer(this, 0, 39);
        f1p3.addBox(-2F, 5F, -3F, 4, 1, 1);
        f1p3.setRotationPoint(0F, 0F, 0F);
        f1p3.setTextureSize(64, 32);
        f1p3.mirror = true;
        setRotation(f1p3, 0F, 0F, 0F);
        f2p1 = new ModelRenderer(this, 0, 41);
        f2p1.addBox(-3F, 0F, -4F, 6, 3, 1);
        f2p1.setRotationPoint(0F, 0F, 0F);
        f2p1.setTextureSize(64, 32);
        f2p1.mirror = true;
        setRotation(f2p1, 0F, 0F, 0F);
        f2p2 = new ModelRenderer(this, 0, 45);
        f2p2.addBox(-2F, 0F, -4F, 4, 1, 1);
        f2p2.setRotationPoint(0F, 3F, 0F);
        f2p2.setTextureSize(64, 32);
        f2p2.mirror = true;
        setRotation(f2p2, 0F, 0F, 0F);
        f2p3 = new ModelRenderer(this, 0, 47);
        f2p3.addBox(-1F, 4F, -4F, 2, 1, 1);
        f2p3.setRotationPoint(0F, 0F, 0F);
        f2p3.setTextureSize(64, 32);
        f2p3.mirror = true;
        setRotation(f2p3, 0F, 0F, 0F);
        sp1 = new ModelRenderer(this, 18, 32);
        sp1.addBox(-6F, -4F, -2.5F, 7, 1, 5);
        sp1.setRotationPoint(0F, 0F, 0F);
        sp1.setTextureSize(64, 32);
        sp1.mirror = true;
        setRotation(sp1, 0F, 0F, -0.6981317F);
        sp1d1 = new ModelRenderer(this, 18, 38);
        sp1d1.addBox(-7F, -4F, -2.5F, 1, 1, 1);
        sp1d1.setRotationPoint(0F, 0F, 0F);
        sp1d1.setTextureSize(64, 32);
        sp1d1.mirror = true;
        setRotation(sp1d1, 0F, 0F, -0.6981317F);
        sp1d2 = new ModelRenderer(this, 18, 38);
        sp1d2.addBox(-7F, -4F, 1.5F, 1, 1, 1);
        sp1d2.setRotationPoint(0F, 0F, 0F);
        sp1d2.setTextureSize(64, 32);
        sp1d2.mirror = true;
        setRotation(sp1d2, 0F, 0F, -0.6981317F);
        sp2 = new ModelRenderer(this, 18, 40);
        sp2.addBox(0F, -6F, -2.5F, 1, 2, 5);
        sp2.setRotationPoint(0F, 0F, 0F);
        sp2.setTextureSize(64, 32);
        sp2.mirror = true;
        setRotation(sp2, 0F, 0F, -0.6981317F);
        sp2d1 = new ModelRenderer(this, 18, 47);
        sp2d1.addBox(0F, -7F, -2.5F, 1, 1, 1);
        sp2d1.setRotationPoint(0F, 0F, 0F);
        sp2d1.setTextureSize(64, 32);
        sp2d1.mirror = true;
        setRotation(sp2d1, 0F, 0F, -0.6981317F);
        sp2d2 = new ModelRenderer(this, 18, 47);
        sp2d2.addBox(0F, -7F, 1.5F, 1, 1, 1);
        sp2d2.setRotationPoint(0F, 0F, 0F);
        sp2d2.setTextureSize(64, 32);
        sp2d2.mirror = true;
        setRotation(sp2d2, 0F, 0F, -0.6981317F);
        sf = new ModelRenderer(this, 18, 49);
        sf.addBox(-5F, -5F, -1.5F, 5, 1, 3);
        sf.setRotationPoint(0F, 0F, 0F);
        sf.setTextureSize(64, 32);
        sf.mirror = true;
        setRotation(sf, 0F, 0F, -0.6981317F);
        rj = new ModelRenderer(this, 42, 32);
        rj.addBox(-5F, 2F, 3F, 4, 8, 4);
        rj.setRotationPoint(0F, 0F, 0F);
        rj.setTextureSize(64, 32);
        rj.mirror = true;
        setRotation(rj, 0.2617994F, 0F, 0.2617994F);
        rw1 = new ModelRenderer(this, 42, 44);
        rw1.addBox(-2F, 2F, 7F, 1, 4, 3);
        rw1.setRotationPoint(0F, 0F, 0F);
        rw1.setTextureSize(64, 32);
        rw1.mirror = true;
        setRotation(rw1, 0.2617994F, 0F, 0.2617994F);
        rw2 = new ModelRenderer(this, 42, 51);
        rw2.addBox(-2F, 2F, 10F, 1, 3, 1);
        rw2.setRotationPoint(0F, 0F, 0F);
        rw2.setTextureSize(64, 32);
        rw2.mirror = true;
        setRotation(rw2, 0.2617994F, 0F, 0.2617994F);
        lj = new ModelRenderer(this, 42, 32);
        lj.addBox(1F, 2F, 3F, 4, 8, 4);
        lj.setRotationPoint(0F, 0F, 0F);
        lj.setTextureSize(64, 32);
        lj.mirror = true;
        setRotation(lj, 0.2617994F, 0F, -0.2617994F);
        lw1 = new ModelRenderer(this, 42, 44);
        lw1.addBox(1F, 2F, 7F, 1, 4, 3);
        lw1.setRotationPoint(0F, 0F, 0F);
        lw1.setTextureSize(64, 32);
        lw1.mirror = true;
        setRotation(lw1, 0.2617994F, 0F, -0.2617994F);
        lw2 = new ModelRenderer(this, 42, 51);
        lw2.addBox(1F, 2F, 10F, 1, 3, 1);
        lw2.setRotationPoint(0F, 0F, 0F);
        lw2.setTextureSize(64, 32);
        lw2.mirror = true;
        setRotation(lw2, 0.2617994F, 0F, -0.2617994F);

        this.bipedBody.addChild(f1p1);
        this.bipedBody.addChild(f1p2);
        this.bipedBody.addChild(f1p3);
        this.bipedBody.addChild(f2p1);
        this.bipedBody.addChild(f2p2);
        this.bipedBody.addChild(f2p3);
        this.bipedBody.addChild(rj);
        this.bipedBody.addChild(rw1);
        this.bipedBody.addChild(rw2);
        this.bipedBody.addChild(lj);
        this.bipedBody.addChild(lw1);
        this.bipedBody.addChild(lw2);
        this.bipedRightArm.addChild(sp1);
        this.bipedRightArm.addChild(sp1d1);
        this.bipedRightArm.addChild(sp1d2);
        this.bipedRightArm.addChild(sp2);
        this.bipedRightArm.addChild(sp2d1);
        this.bipedRightArm.addChild(sp2d2);
        this.bipedRightArm.addChild(sf);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        super.render(entity, f, f1, f2, f3, f4, f5);
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
