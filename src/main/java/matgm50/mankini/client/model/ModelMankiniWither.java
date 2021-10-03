package matgm50.mankini.client.model;

import matgm50.mankini.entity.boss.MankiniWitherEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ModelMankiniWither<T extends MankiniWitherEntity> extends HierarchicalModel<T> {
    private static final String RIBCAGE = "ribcage";
    private static final String CENTER_HEAD = "center_head";
    private static final String RIGHT_HEAD = "right_head";
    private static final String LEFT_HEAD = "left_head";
    private static final float RIBCAGE_X_ROT_OFFSET = 0.065F;
    private static final float TAIL_X_ROT_OFFSET = 0.265F;
    private final ModelPart root;
    private final ModelPart centerHead;
    private final ModelPart rightHead;
    private final ModelPart leftHead;
    private final ModelPart ribcage;
    private final ModelPart tail;

    public ModelMankiniWither(ModelPart part) {
        this.root = part;
        this.ribcage = part.getChild("ribcage");
        this.tail = part.getChild("tail");
        this.centerHead = part.getChild("center_head");
        this.rightHead = part.getChild("right_head");
        this.leftHead = part.getChild("left_head");
    }

    public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("shoulders", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, 3.9F, -0.5F, 20.0F, 3.0F, 3.0F, deformation), PartPose.ZERO);
        float f = 0.20420352F;
        partdefinition.addOrReplaceChild("ribcage", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, deformation).texOffs(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, deformation).texOffs(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, deformation).texOffs(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, deformation), PartPose.offsetAndRotation(-2.0F, 6.9F, -0.5F, 0.20420352F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, deformation), PartPose.offsetAndRotation(-2.0F, 6.9F + Mth.cos(f) * 10.0F, -0.5F + Mth.sin(f) * 10.0F, 0.83252203F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("center_head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation), PartPose.ZERO);
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, deformation);
        partdefinition.addOrReplaceChild("right_head", cubelistbuilder, PartPose.offset(-8.0F, 4.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_head", cubelistbuilder, PartPose.offset(10.0F, 4.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public ModelPart root() {
        return this.root;
    }

    public void setupAnim(T p_104100_, float p_104101_, float p_104102_, float p_104103_, float p_104104_, float p_104105_) {
        float f = Mth.cos(p_104103_ * 0.1F);
        this.ribcage.xRot = (0.065F + 0.05F * f) * (float)Math.PI;
        this.tail.setPos(-2.0F, 6.9F + Mth.cos(this.ribcage.xRot) * 10.0F, -0.5F + Mth.sin(this.ribcage.xRot) * 10.0F);
        this.tail.xRot = (0.265F + 0.1F * f) * (float)Math.PI;
        this.centerHead.yRot = p_104104_ * ((float)Math.PI / 180F);
        this.centerHead.xRot = p_104105_ * ((float)Math.PI / 180F);
    }

    public void prepareMobModel(T mankiniWither, float p_104096_, float p_104097_, float p_104098_) {
        setupHeadRotation(mankiniWither, this.rightHead, 0);
        setupHeadRotation(mankiniWither, this.leftHead, 1);
    }

    private static <T extends MankiniWitherEntity> void setupHeadRotation(T mankiniWither, ModelPart part, int p_171074_) {
        part.yRot = (mankiniWither.getHeadYRot(p_171074_) - mankiniWither.yBodyRot) * ((float)Math.PI / 180F);
        part.xRot = mankiniWither.getHeadXRot(p_171074_) * ((float)Math.PI / 180F);
    }
}