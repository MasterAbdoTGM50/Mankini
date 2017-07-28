package matgm50.mankini.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModCreativeTab extends CreativeTabs {

    public ModCreativeTab(String label) {

        super(label);

    }

    @Override
    public ItemStack getTabIconItem() {

        return new ItemStack(ModItems.kawaii_mankini);

    }

}
