package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemWitherKini extends ArmorItem implements IMankini {

	public ItemWitherKini(Item.Properties builder) {
		super(ModArmorMaterials.WITHER_KINI, Type.CHESTPLATE, builder.stacksTo(1));
	}
}
