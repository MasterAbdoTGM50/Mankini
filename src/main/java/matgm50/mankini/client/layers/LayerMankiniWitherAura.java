package matgm50.mankini.client.layers;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class LayerMankiniWitherAura extends EnergySwirlLayer<WitherRenderState, ModelMankiniWither<WitherRenderState>> {
	private static final ResourceLocation WITHER_ARMOR = ModLib.modLoc("textures/entity/mankini_wither_armor.png");
	private final ModelMankiniWither<WitherRenderState> model;

	public LayerMankiniWitherAura(RenderLayerParent<WitherRenderState, ModelMankiniWither<WitherRenderState>> parent, EntityModelSet modelSet) {
		super(parent);
		this.model = new ModelMankiniWither<>(modelSet.bakeLayer(ClientHandler.MANKINI_WITHER));
	}

	@Override
	protected boolean isPowered(WitherRenderState state) {
		return state.isPowered;
	}

	protected float xOffset(float offset) {
		return Mth.cos(offset * 0.02F) * 3.0F;
	}

	protected ResourceLocation getTextureLocation() {
		return WITHER_ARMOR;
	}

	protected ModelMankiniWither<WitherRenderState> model() {
		return this.model;
	}
}