package matgm50.mankini.item;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import matgm50.mankini.lib.ItemLib;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;


/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModItems {

	public static final Set<Item> items = new HashSet<>();
    public static Item itemDyeableMankini;
    public static Item itemKawaiiMankini;
    public static Item itemAethericMankini;
    public static final Item Itemmankinicannon;
    public static Item itemMankiniCapsule;
    public static Item itemBatMankini;
    	
    	static {
    		Itemmankinicannon = registerItem(new ItemMankiniCannon(ItemLib.MANKINI_CANNON_NAME));
    	}
    	
    	   public static void registerItems() {
    		   
    	   }


	
	private static <T extends Item> T registerItem(T item) {
		GameRegistry.register(item);
		items.add(item);

		return item;
	}
}



