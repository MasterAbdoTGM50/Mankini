package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemWitherKini extends ArmorItem implements IMankini {

    public ItemWitherKini(Item.Properties builder) {
        super(ArmorMaterial.IRON, EquipmentSlotType.CHEST, builder.group(Mankini.tabMankini).maxStackSize(1));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
   		return "mankini:textures/models/wither_kini.png";
   	}
}
