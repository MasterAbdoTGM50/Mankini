package matgm50.mankini.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemKawaiiMankini extends ArmorItem implements IMankini {

	public ItemKawaiiMankini(Item.Properties builder) {
		super(ArmorMaterials.GOLD, EquipmentSlot.CHEST, builder.stacksTo(1));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "mankini:textures/models/kawaii_mankini.png";
	}
}
