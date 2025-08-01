package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;

/**
 * Created by MasterAbdoTGM50 on 5/26/2014.
 */

public class ItemDyeableMankini extends Item implements IMankini {

	public ItemDyeableMankini(Item.Properties properties) {
		super(properties.humanoidArmor(ModArmorMaterials.MANKINI, ArmorType.CHESTPLATE));
	}
}