package matgm50.mankini.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class ItemBatMankini extends ArmorItem implements IMankini {

	public ItemBatMankini(Item.Properties builder) {
		super(ArmorMaterials.IRON, Type.CHESTPLATE, builder.stacksTo(1));
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "mankini:textures/models/batty_mankini.png";
	}
}