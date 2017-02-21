package matgm50.mankini.init;

import matgm50.mankini.item.ItemAAMT;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ItemDyeableMankini;
import matgm50.mankini.item.ItemKawaiiMankini;
import matgm50.mankini.item.ItemMankiniCannon;
import matgm50.mankini.item.ItemMankiniCapsule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModItems {

    public static Item itemDyeableMankini;
    public static Item itemKawaiiMankini;
    public static Item itemAethericMankini;
    public static Item itemMankiniCannon;
    public static Item itemMankiniCapsule;
    public static Item itemBatMankini;

    public static void init() {

        itemDyeableMankini = new ItemDyeableMankini();
        itemKawaiiMankini = new ItemKawaiiMankini();
        itemAethericMankini = new ItemAAMT();
        itemMankiniCannon  = new ItemMankiniCannon();
        itemMankiniCapsule = new ItemMankiniCapsule();
        itemBatMankini = new ItemBatMankini();
    	}

    public static void register()
    {
    	GameRegistry.register(itemDyeableMankini);
    	GameRegistry.register(itemKawaiiMankini);
    	GameRegistry.register(itemAethericMankini);
    	GameRegistry.register(itemMankiniCannon);
    	GameRegistry.register(itemMankiniCapsule);
    	GameRegistry.register(itemBatMankini);
    }
       // GameRegistry.registerItem(itemDyeableMankini, ItemLib.DYEABLE_MAKNINI_NAME);
       // GameRegistry.registerItem(itemKawaiiMankini, ItemLib.KAWAII_MAKNINI_NAME);
       // GameRegistry.registerItem(itemAethericMankini, ItemLib.AETHERIC_MAKNINI_NAME);
       // GameRegistry.registerItem(itemMankiniCannon, ItemLib.MANKINI_CANNON_NAME);
       //  GameRegistry.registerItem(itemMankiniCapsule, ItemLib.MANKINI_CAPSULE_NAME);
      //  GameRegistry.registerItem(itemBatMankini, ItemLib.MANKINI_BAT_NAME);

        public static void registerRenders()
    	{
    		registerRender(itemDyeableMankini);
    		registerRender(itemKawaiiMankini);
    		registerRender(itemAethericMankini);
    		registerRender(itemMankiniCannon);
    		registerRender(itemMankiniCapsule);
    		registerRender(itemBatMankini);
    	}
        
        public static void registerColorRenders()
        {
        	Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
                @Override
                public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                     NBTTagCompound tag = stack.getTagCompound();
                     if(tag!=null){
                         NBTTagCompound nbt = tag.getCompoundTag("display");
                        return nbt == null ? 10511680 : (nbt.hasKey("color", 3) ? nbt.getInteger("color") : 10511680);
                     }
                     return 10511680;
                }
            }, itemDyeableMankini);
        }
    	
        public static void registerRender(Item item)
    	{
    		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    	}
}