package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

/**
 * Created by MasterAbdoTGM50 on 5/26/2014.
 */

public class ItemDyeableMankini extends ArmorItem implements IMankini {

	public ItemDyeableMankini(Item.Properties builder) {
		super(ModArmorMaterials.MANKINI, Type.CHESTPLATE, builder.stacksTo(1));
	}
}