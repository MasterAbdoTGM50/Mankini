package matgm50.mankini.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import matgm50.mankini.client.renderer.state.MankiniSkeletonRenderState;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class ModelMankiniSkeleton<S extends MankiniSkeletonRenderState> extends HumanoidModel<S> {
	public ModelMankiniSkeleton(ModelPart part) {
		super(part);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
	 * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how "far"
	 * arms and legs can swing at most.
	 */
	public void setupAnim(S state) {
		super.setupAnim(state);
		if (state.isAggressive && !state.isHoldingBow) {
			float f = state.attackTime;
			float f1 = Mth.sin(f * (float) Math.PI);
			float f2 = Mth.sin((1.0F - (1.0F - f) * (1.0F - f)) * (float) Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.1F - f1 * 0.6F);
			this.leftArm.yRot = 0.1F - f1 * 0.6F;
			this.rightArm.xRot = (float) (-Math.PI / 2);
			this.leftArm.xRot = (float) (-Math.PI / 2);
			this.rightArm.xRot -= f1 * 1.2F - f2 * 0.4F;
			this.leftArm.xRot -= f1 * 1.2F - f2 * 0.4F;
			AnimationUtils.bobArms(this.rightArm, this.leftArm, state.ageInTicks);
		}
	}

	public void translateToHand(HumanoidArm side, PoseStack poseStack) {
		this.root().translateAndRotate(poseStack);
		float f = side == HumanoidArm.RIGHT ? 1.0F : -1.0F;
		ModelPart modelpart = this.getArm(side);
		modelpart.x += f;
		modelpart.translateAndRotate(poseStack);
		modelpart.x -= f;
	}
}