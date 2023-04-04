package matgm50.mankini.client.layers;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.entity.boss.MankiniWitherEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMankiniWitherAura extends EnergySwirlLayer<MankiniWitherEntity, ModelMankiniWither<MankiniWitherEntity>> {
	private static final ResourceLocation WITHER_ARMOR = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_wither_armor.png");
	private final ModelMankiniWither<MankiniWitherEntity> model;

	public LayerMankiniWitherAura(RenderLayerParent<MankiniWitherEntity, ModelMankiniWither<MankiniWitherEntity>> p_174554_, EntityModelSet modelSet) {
		super(p_174554_);
		this.model = new ModelMankiniWither<>(modelSet.bakeLayer(ClientHandler.MANKINI_WITHER));
	}

	protected float xOffset(float offset) {
		return Mth.cos(offset * 0.02F) * 3.0F;
	}

	protected ResourceLocation getTextureLocation() {
		return WITHER_ARMOR;
	}

	protected EntityModel<MankiniWitherEntity> model() {
		return this.model;
	}
}