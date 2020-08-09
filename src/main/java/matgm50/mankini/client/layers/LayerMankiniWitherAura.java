package matgm50.mankini.client.layers;

import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMankiniWitherAura extends EnergyLayer<EntityMankiniWither, ModelMankiniWither<EntityMankiniWither>> {
    private static final ResourceLocation WITHER_ARMOR = new ResourceLocation(ModLib.MOD_ID, "textures/entity/mankini_wither_armor.png");
    private final ModelMankiniWither<EntityMankiniWither> witherModel = new ModelMankiniWither(0.5F);

    public LayerMankiniWitherAura(IEntityRenderer<EntityMankiniWither, ModelMankiniWither<EntityMankiniWither>> p_i50915_1_) {
        super(p_i50915_1_);
    }

    protected float func_225634_a_(float p_225634_1_) {
        return MathHelper.cos(p_225634_1_ * 0.02F) * 3.0F;
    }

    protected ResourceLocation func_225633_a_() {
        return WITHER_ARMOR;
    }

    protected EntityModel<EntityMankiniWither> func_225635_b_() {
        return this.witherModel;
    }
}