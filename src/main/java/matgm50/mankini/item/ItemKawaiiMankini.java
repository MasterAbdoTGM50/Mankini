package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemKawaiiMankini extends Item implements IMankini {

	public ItemKawaiiMankini(Item.Properties properties) {
		super(properties.humanoidArmor(ModArmorMaterials.KAWAII_MANKINI, ArmorType.CHESTPLATE));
	}
}
