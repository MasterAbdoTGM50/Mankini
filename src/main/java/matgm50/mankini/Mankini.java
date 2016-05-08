package matgm50.mankini;

import matgm50.mankini.crafting.ModRecipes;
import matgm50.mankini.entity.ModEntities;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ModItems;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.proxy.CommonProxy;
import matgm50.mankini.util.BatHandler;
import matgm50.mankini.util.BatMankiniJump;
import matgm50.mankini.util.TabMankini;
import matgm50.mankini.util.TickHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

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

  
        ModItems.registerItems();
        
        ModRecipes.init();

        ModEntities.init();

        TickHandler.init();

        proxy.initRenderers();
        
        BatHandler.init();
        
        BatMankiniJump.init();       
        
       
      
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}
