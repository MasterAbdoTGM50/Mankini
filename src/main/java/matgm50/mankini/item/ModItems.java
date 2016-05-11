package matgm50.mankini.item;

import java.util.Locale;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

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
    		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ModLib.ID + ":" + item.getUnlocalizedName().substring(5).toLowerCase(Locale.US), "inventory"));
    		
    	}
}



