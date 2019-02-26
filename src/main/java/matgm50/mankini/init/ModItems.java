package matgm50.mankini.init;

import com.google.common.base.Preconditions;
import matgm50.mankini.Mankini;
import matgm50.mankini.item.ItemAAMT;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ItemDyeableMankini;
import matgm50.mankini.item.ItemKawaiiMankini;
import matgm50.mankini.item.ItemMankiniCannon;
import matgm50.mankini.item.ItemMankiniCapsule;
import matgm50.mankini.item.ItemMankiniHorseArmor;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(ModLib.MOD_ID)
public class ModItems {

    public static Item dyeable_mankini;
    public static Item kawaii_mankini;
    public static Item aetheric_mankini;
    public static Item mankini_cannon;
    public static Item mankini_capsule;
    public static Item bat_mankini;
    public static Item mankini_horse_armor;

    public static Item mankini_creeper_spawn_egg;

    public static ArrayList<Item> ITEMS = new ArrayList<>();

	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        
        dyeable_mankini = registerItem(new ItemDyeableMankini(itemBuilder()), "dyeable_mankini");
        kawaii_mankini = registerItem(new ItemKawaiiMankini(itemBuilder()), "kawaii_mankini");
        aetheric_mankini = registerItem(new ItemAAMT(itemBuilder()), "aetheric_mankini");
        mankini_cannon  = registerItem(new ItemMankiniCannon(itemBuilder()), "mankini_cannon");
        mankini_capsule = registerItem(new ItemMankiniCapsule(itemBuilder()), "mankini_capsule");
        bat_mankini = registerItem(new ItemBatMankini(itemBuilder()), "mankini_bat");
        mankini_horse_armor = registerItem(new ItemMankiniHorseArmor(itemBuilder()), "mankini_horse_armor");

        mankini_creeper_spawn_egg = registerItem(new ItemSpawnEgg(ModEntities.MANKINI_CREEPER, 894731, 0, itemBuilderWithGroup()), "mankini_creeper_spawn_egg");
        
        registry.registerAll(ITEMS.toArray(new Item[0]));
    }

    public static <T extends Item> T registerItem(T item, String name)
    {
        ITEMS.add(item);

        item.setRegistryName(new ResourceLocation(ModLib.MOD_ID, name));
        Preconditions.checkNotNull(item, "registryName");
        Mankini.logger.debug(item + " " + name);
        return item;
    }
        
//    public static void registerColorRenders()
//    {
//    	Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
//            @Override
//            public int colorMultiplier(ItemStack stack, int tintIndex) {
//                 NBTTagCompound tag = stack.getTagCompound();
//                 if(tag!=null){
//                     NBTTagCompound nbt = tag.getCompoundTag("display");
//                    return nbt == null ? 10511680 : (nbt.hasKey("color", 3) ? nbt.getInteger("color") : 10511680);
//                 }
//                 return 10511680;
//            }
//        }, dyeable_mankini);
//    }

    private static Item.Properties itemBuilder()
    {
        return new Item.Properties();
    }

    private static Item.Properties itemBuilderWithGroup()
    {
        return new Item.Properties().group(Mankini.tabMankini);
    }
}