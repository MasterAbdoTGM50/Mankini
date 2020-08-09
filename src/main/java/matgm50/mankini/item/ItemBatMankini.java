package matgm50.mankini.item;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemBatMankini extends ArmorItem implements IMankini {
	
    public ItemBatMankini(Item.Properties builder) {
		super(ArmorMaterial.IRON, EquipmentSlotType.CHEST, builder.maxStackSize(1));
    }

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return "mankini:textures/models/batty_mankini.png";
	}
}