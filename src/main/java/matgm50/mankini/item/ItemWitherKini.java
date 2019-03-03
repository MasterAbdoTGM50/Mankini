package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemWitherKini extends ItemArmor implements IMankini {

    public ItemWitherKini(Properties builder) {
        super(ArmorMaterial.GOLD, EntityEquipmentSlot.CHEST, builder.group(Mankini.tabMankini).maxStackSize(1));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
   		return "mankini:textures/models/witherkini.png";
   	}
}
