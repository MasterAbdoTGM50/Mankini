package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class TabMankini extends CreativeTabs {

    public TabMankini(String label) {

        super(label);

    }

    @Override
    public Item getTabIconItem() {

        return ModItems.dyeable_mankini;

    }

}
