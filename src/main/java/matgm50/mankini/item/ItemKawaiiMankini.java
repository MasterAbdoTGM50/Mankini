package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ItemLib;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * Created by MasterAbdoTGM50 on 5/28/2014.
 */

public class ItemKawaiiMankini extends ItemArmor implements IMankini {

    public ItemKawaiiMankini() {

        super(ArmorMaterial.GOLD, 0, EntityEquipmentSlot.CHEST);
        setUnlocalizedName(ItemLib.ModItems.KAWAII_MAKNINI_NAME.getUnlocalisedName());
		setRegistryName(ItemLib.ModItems.KAWAII_MAKNINI_NAME.getRegistryName());
        setCreativeTab(Mankini.tabMankini);
        setMaxStackSize(1);

    }
}
