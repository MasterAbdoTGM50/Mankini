package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemWitherKini extends Item implements IMankini {

	public ItemWitherKini(Item.Properties properties) {
		super(properties.humanoidArmor(ModArmorMaterials.WITHER_KINI, ArmorType.CHESTPLATE));
	}
}
