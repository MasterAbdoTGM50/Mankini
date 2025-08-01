package matgm50.mankini.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;

/**
 * Created by MasterAbdoTGM50 on 5/30/2014.
 */

public class ItemAAMT extends Item implements IMankini {

	public ItemAAMT(Item.Properties properties) {
		super(properties.humanoidArmor(ArmorMaterials.DIAMOND, ArmorType.CHESTPLATE));
	}
}
