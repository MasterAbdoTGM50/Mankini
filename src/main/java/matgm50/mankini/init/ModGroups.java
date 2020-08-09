package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModGroups {
    public static final ItemGroup MANKINI_TAB = new ItemGroup(ModLib.MOD_ID) {
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModRegistry.KAWAII_MANKINI.get());
        }
    };
}
