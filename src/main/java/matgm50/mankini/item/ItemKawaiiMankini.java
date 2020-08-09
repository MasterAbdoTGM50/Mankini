package matgm50.mankini.item;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemKawaiiMankini extends ArmorItem implements IMankini {

    public ItemKawaiiMankini(Item.Properties builder) {
        super(ArmorMaterial.GOLD, EquipmentSlotType.CHEST, builder.maxStackSize(1));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
   		return "mankini:textures/models/kawaii_mankini.png";
   	}
}
