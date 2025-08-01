package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.Item;

public class ItemMankiniHorseArmor extends Item {
	public ItemMankiniHorseArmor(Properties properties) {
		super(properties.horseArmor(ModArmorMaterials.MANKINI));
	}
}
