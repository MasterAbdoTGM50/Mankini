package matgm50.mankini.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModCreativeTab extends ItemGroup {

    public ModCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.kawaii_mankini);
    }
}
