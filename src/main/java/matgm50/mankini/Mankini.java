package matgm50.mankini;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import matgm50.mankini.crafting.ModRecipes;
import matgm50.mankini.entity.ModEntities;
import matgm50.mankini.item.ModItems;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.proxy.CommonProxy;
import matgm50.mankini.util.TabMankini;
import matgm50.mankini.util.TickHandler;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

@Mod(modid = ModLib.ID, name = ModLib.NAME, version = ModLib.VERSION)

public class Mankini {

    @Instance(ModLib.ID)
    public static Mankini instance;

    @SidedProxy(serverSide = ModLib.COMMONPROXY, clientSide = ModLib.CLIENTPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabMankini = new TabMankini(ModLib.ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ModItems.init();
        ModRecipes.init();

        ModEntities.init();

        TickHandler.init();

        proxy.initRenderers();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
