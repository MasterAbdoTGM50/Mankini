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

    public static Item dyeable_mankini;
    public static Item kawaii_mankini;
    public static Item aetheric_mankini;
    public static Item mankini_cannon;
    public static Item mankini_capsule;
    public static Item bat_mankini;

    public static void init() {

    	dyeable_mankini = new ItemDyeableMankini();
        kawaii_mankini = new ItemKawaiiMankini();
        aetheric_mankini = new ItemAAMT();
        mankini_cannon  = new ItemMankiniCannon();
        mankini_capsule = new ItemMankiniCapsule();
        bat_mankini = new ItemBatMankini();
    	}

    public static void register()
    {
    	GameRegistry.register(dyeable_mankini);
    	GameRegistry.register(kawaii_mankini);
    	GameRegistry.register(aetheric_mankini);
    	GameRegistry.register(mankini_cannon);
    	GameRegistry.register(mankini_capsule);
    	GameRegistry.register(bat_mankini);
    }
    
        public static void registerRenders()
    	{
    		registerRender(dyeable_mankini);
    		registerRender(kawaii_mankini);
    		registerRender(aetheric_mankini);
    		registerRender(mankini_cannon);
    		registerRender(mankini_capsule);
    		registerRender(bat_mankini);
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
            }, dyeable_mankini);
        }
    	
        public static void registerRender(Item item)
    	{
    		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    	}
}