package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;

public class ItemBatMankini extends Item implements IMankini {

	public ItemBatMankini(Item.Properties properties) {
		super(properties.humanoidArmor(ModArmorMaterials.BATTY_MANKINI, ArmorType.CHESTPLATE));
	}
}