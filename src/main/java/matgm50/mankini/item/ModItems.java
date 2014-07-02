package matgm50.mankini.item;

import cpw.mods.fml.common.registry.GameRegistry;
import matgm50.mankini.lib.ItemLib;
import net.minecraft.item.Item;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModItems {

    public static Item itemDyeableMankini;
    public static Item itemKawaiiMankini;
    public static Item itemAethericMankini;
    public static Item itemMankiniCannon;
    public static Item itemMankiniCapsule;

    public static void init() {

        itemDyeableMankini = new ItemDyeableMankini();
        itemKawaiiMankini = new ItemKawaiiMankini();
        itemAethericMankini = new ItemAAMT();
        itemMankiniCannon  = new ItemMankiniCannon();
        itemMankiniCapsule = new ItemMankiniCapsule();

        GameRegistry.registerItem(itemDyeableMankini, ItemLib.DYEABLE_MAKNINI_NAME);
        GameRegistry.registerItem(itemKawaiiMankini, ItemLib.KAWAII_MAKNINI_NAME);
        GameRegistry.registerItem(itemAethericMankini, ItemLib.AETHERIC_MAKNINI_NAME);
        GameRegistry.registerItem(itemMankiniCannon, ItemLib.MANKINI_CANNON_NAME);
        GameRegistry.registerItem(itemMankiniCapsule, ItemLib.MANKINI_CAPSULE_NAME);

    }

}
