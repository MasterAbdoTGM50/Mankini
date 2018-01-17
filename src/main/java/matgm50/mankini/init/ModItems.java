package matgm50.mankini.init;

import java.util.ArrayList;

import matgm50.mankini.item.ItemAAMT;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ItemDyeableMankini;
import matgm50.mankini.item.ItemKawaiiMankini;
import matgm50.mankini.item.ItemMankiniCannon;
import matgm50.mankini.item.ItemMankiniCapsule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

@EventBusSubscriber
public class ModItems {

    public static Item dyeable_mankini;
    public static Item kawaii_mankini;
    public static Item aetheric_mankini;
    public static Item mankini_cannon;
    public static Item mankini_capsule;
    public static Item bat_mankini;

	public static ArrayList<Item> ITEMS = new ArrayList<>();

	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        
        dyeable_mankini = registerItem(new ItemDyeableMankini());
        kawaii_mankini = registerItem(new ItemKawaiiMankini());
        aetheric_mankini = registerItem(new ItemAAMT());
        mankini_cannon  = registerItem(new ItemMankiniCannon());
        mankini_capsule = registerItem(new ItemMankiniCapsule());
        bat_mankini = registerItem(new ItemBatMankini());
        
        registry.registerAll(ITEMS.toArray(new Item[0]));
    }
    
    public static <T extends Item> T registerItem(T item)
    {
        ITEMS.add(item);
        return item;
    }
        
    public static void registerColorRenders()
    {
    	Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            @Override
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                 NBTTagCompound tag = stack.getTagCompound();
                 if(tag!=null){
                     NBTTagCompound nbt = tag.getCompoundTag("display");
                    return nbt == null ? 10511680 : (nbt.hasKey("color", 3) ? nbt.getInteger("color") : 10511680);
                 }
                 return 10511680;
            }
        }, dyeable_mankini);
    }
}