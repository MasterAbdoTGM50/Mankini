package matgm50.mankini.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.util.Mth;

public class ModelMankiniWither<S extends WitherRenderState> extends EntityModel<S> {
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
		super(part);
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

	public void setupAnim(S state) {
		super.setupAnim(state);
		setupHeadRotation(state, this.rightHead, 0);
		setupHeadRotation(state, this.leftHead, 1);
		float f = Mth.cos(state.ageInTicks * 0.1F);
		this.ribcage.xRot = (0.065F + 0.05F * f) * (float) Math.PI;
		this.tail.setPos(-2.0F, 6.9F + Mth.cos(this.ribcage.xRot) * 10.0F, -0.5F + Mth.sin(this.ribcage.xRot) * 10.0F);
		this.tail.xRot = (0.265F + 0.1F * f) * (float) Math.PI;
		this.centerHead.yRot = state.yRot * (float) (Math.PI / 180.0);
		this.centerHead.xRot = state.xRot * (float) (Math.PI / 180.0);
	}

	private static void setupHeadRotation(WitherRenderState renderState, ModelPart head, int headIndex) {
		head.yRot = (renderState.yHeadRots[headIndex] - renderState.bodyRot) * (float) (Math.PI / 180.0);
		head.xRot = renderState.xHeadRots[headIndex] * (float) (Math.PI / 180.0);
	}
}