package matgm50.mankini.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class ModelAAMT<T extends LivingEntity> extends HumanoidModel<T> {
	private final ModelPart f1p2;
	private final ModelPart f1p3;
	private final ModelPart f2p1;
	private final ModelPart f2p2;
	private final ModelPart f2p3;
	private final ModelPart sp1;
	private final ModelPart sp1d1;
	private final ModelPart sp1d2;
	private final ModelPart sp2;
	private final ModelPart sp2d1;
	private final ModelPart sp2d2;
	private final ModelPart sf;
	private final ModelPart rj;
	private final ModelPart rw1;
	private final ModelPart rw2;
	private final ModelPart lj;
	private final ModelPart lw1;
	private final ModelPart lw2;
	private final ModelPart f1p1;

	public ModelAAMT(ModelPart part) {
		super(part);

		this.f1p2 = part.getChild("f1p2");
		this.f1p3 = part.getChild("f1p3");
		this.f2p1 = part.getChild("f2p1");
		this.f2p2 = part.getChild("f2p2");
		this.f2p3 = part.getChild("f2p3");
		this.sp1 = part.getChild("sp1");
		this.sp1d1 = part.getChild("sp1d1");
		this.sp1d2 = part.getChild("sp1d2");
		this.sp2 = part.getChild("sp2");
		this.sp2d1 = part.getChild("sp2d1");
		this.sp2d2 = part.getChild("sp2d2");
		this.sf = part.getChild("sf");
		this.rj = part.getChild("rj");
		this.rw1 = part.getChild("rw1");
		this.rw2 = part.getChild("rw2");
		this.lj = part.getChild("lj");
		this.lw1 = part.getChild("lw1");
		this.lw2 = part.getChild("lw2");
		this.f1p1 = part.getChild("f1p1");
	}

	public static LayerDefinition createMesh() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

		partdefinition.addOrReplaceChild("f1p2",
				CubeListBuilder.create()
						.texOffs(0, 37).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 1.0F, 1.0F).mirror(),
				PartPose.ZERO);

		partdefinition.addOrReplaceChild("f1p3",
				CubeListBuilder.create()
						.texOffs(0, 39).addBox(-2.0F, 5.0F, -3.0F, 4.0F, 1.0F, 1.0F).mirror(),
				PartPose.ZERO);

		partdefinition.addOrReplaceChild("f2p1",
				CubeListBuilder.create()
						.texOffs(0, 41).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 3.0F, 1.0F).mirror(),
				PartPose.ZERO);

		partdefinition.addOrReplaceChild("f2p2",
				CubeListBuilder.create()
						.texOffs(0, 45).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 1.0F).mirror(),
				PartPose.offset(0.0F, 3.0F, 0.0F));

		partdefinition.addOrReplaceChild("f2p3",
				CubeListBuilder.create()
						.texOffs(0, 47).addBox(-1.0F, 4.0F, -4.0F, 2.0F, 1.0F, 1.0F).mirror(),
				PartPose.ZERO);

		partdefinition.addOrReplaceChild("sp1",
				CubeListBuilder.create()
						.texOffs(18, 32).addBox(-6.0F, -4.0F, -2.5F, 7.0F, 1.0F, 5.0F).mirror(),
				PartPose.rotation(0.0F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("sp1d1",
				CubeListBuilder.create()
						.texOffs(18, 38).addBox(-7.0F, -4.0F, -2.5F, 1.0F, 1.0F, 1.0F).mirror(),
				PartPose.rotation(0.0F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("sp1d2",
				CubeListBuilder.create()
						.texOffs(18, 38).addBox(-7.0F, -4.0F, 1.5F, 1.0F, 1.0F, 1.0F).mirror(),
				PartPose.rotation(0.0F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("sp2",
				CubeListBuilder.create()
						.texOffs(18, 40).addBox(0.0F, -6.0F, -2.5F, 1.0F, 2.0F, 5.0F).mirror(),
				PartPose.rotation(0.0F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("sp2d1",
				CubeListBuilder.create()
						.texOffs(18, 47).addBox(0.0F, -7.0F, -2.5F, 1.0F, 1.0F, 1.0F).mirror(),
				PartPose.rotation(0.0F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("sp2d2",
				CubeListBuilder.create()
						.texOffs(18, 47).addBox(0.0F, -7.0F, 1.5F, 1.0F, 1.0F, 1.0F).mirror(),
				PartPose.rotation(0.0F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("sf",
				CubeListBuilder.create()
						.texOffs(18, 49).addBox(-5.0F, -5.0F, -1.5F, 5.0F, 1.0F, 3.0F).mirror(),
				PartPose.rotation(0.0F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("rj",
				CubeListBuilder.create()
						.texOffs(42, 32).addBox(-5.0F, 2.0F, 3.0F, 4.0F, 8.0F, 4.0F).mirror(),
				PartPose.rotation(0.2618F, 0.0F, 0.2618F));

		partdefinition.addOrReplaceChild("rw1",
				CubeListBuilder.create()
						.texOffs(42, 44).addBox(-2.0F, 2.0F, 7.0F, 1.0F, 4.0F, 3.0F).mirror(),
				PartPose.rotation(0.2618F, 0.0F, 0.2618F));

		partdefinition.addOrReplaceChild("rw2",
				CubeListBuilder.create()
						.texOffs(42, 51).addBox(-2.0F, 2.0F, 10.0F, 1.0F, 3.0F, 1.0F).mirror(),
				PartPose.rotation(0.2618F, 0.0F, 0.2618F));

		partdefinition.addOrReplaceChild("lj",
				CubeListBuilder.create()
						.texOffs(42, 32).addBox(1.0F, 2.0F, 3.0F, 4.0F, 8.0F, 4.0F).mirror(),
				PartPose.rotation(0.2618F, 0.0F, -0.2618F));

		partdefinition.addOrReplaceChild("lw1",
				CubeListBuilder.create()
						.texOffs(42, 44).addBox(1.0F, 2.0F, 7.0F, 1.0F, 4.0F, 3.0F).mirror(),
				PartPose.rotation(0.2618F, 0.0F, -0.2618F));

		partdefinition.addOrReplaceChild("lw2",
				CubeListBuilder.create()
						.texOffs(42, 51).addBox(1.0F, 2.0F, 10.0F, 1.0F, 3.0F, 1.0F).mirror(),
				PartPose.rotation(0.2618F, 0.0F, -0.2618F));

		partdefinition.addOrReplaceChild("f1p1",
				CubeListBuilder.create()
						.texOffs(0, 32).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 4.0F, 1.0F).mirror(),
				PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	protected Iterable<ModelPart> AAMTParts() {
		return ImmutableList.of(this.f1p2, this.f1p3, this.f2p1, this.f2p2, this.f2p3, this.sp1, this.sp1d1,
				this.sp1d2, this.sp2, this.sp2d1, this.sp2d2, this.sf, this.rj, this.rw1, this.rw2, this.lj,
				this.lw1, this.lw2, this.f1p1);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.AAMTParts().forEach((part) -> {
			part.render(poseStack, buffer, packedLight, packedOverlay);
		});
	}
}
