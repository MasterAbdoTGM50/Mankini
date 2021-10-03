package matgm50.mankini.client.renderer;

import matgm50.mankini.client.ClientHandler;
import matgm50.mankini.client.model.ModelAAMT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

public class AAMTRenderer implements IItemRenderProperties {
	private ModelAAMT model;

	@Override
	public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
		if (armorSlot == EquipmentSlot.CHEST) {
//			if(model == null) {
				refreshArmorModel();
//			}
			return (A) model;
		}

		return null;
	}

	public void refreshArmorModel() {
//		return (A) new ModelAAMT(0.5F);
		this.model = new ModelAAMT(ModelAAMT.createMesh().bakeRoot());
	}
}
