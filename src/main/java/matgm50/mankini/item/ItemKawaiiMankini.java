package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemKawaiiMankini extends ArmorItem implements IMankini {

	public ItemKawaiiMankini(Item.Properties builder) {
		super(ModArmorMaterials.KAWAII_MANKINI, Type.CHESTPLATE, builder.stacksTo(1));
	}
}
