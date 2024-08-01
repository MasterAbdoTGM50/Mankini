package matgm50.mankini.item;

import matgm50.mankini.init.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ItemBatMankini extends ArmorItem implements IMankini {

	public ItemBatMankini(Item.Properties builder) {
		super(ModArmorMaterials.BATTY_MANKINI, Type.CHESTPLATE, builder.stacksTo(1));
	}
}