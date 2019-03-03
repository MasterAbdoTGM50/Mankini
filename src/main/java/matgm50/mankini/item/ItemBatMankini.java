package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemBatMankini extends ItemArmor implements IMankini {
	
    public ItemBatMankini(Item.Properties builder) {
		super(ArmorMaterial.IRON, EntityEquipmentSlot.CHEST, builder.group(Mankini.tabMankini).maxStackSize(1));
    }

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return "mankini:textures/models/batty_mankini.png";
	}
}