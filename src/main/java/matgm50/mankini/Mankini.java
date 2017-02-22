package matgm50.mankini;

import matgm50.mankini.entity.ModEntities;
import matgm50.mankini.init.ModItems;
import matgm50.mankini.init.ModRecipes;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.proxy.CommonProxy;
import matgm50.mankini.util.BatHandler;
import matgm50.mankini.util.BatMankiniJump;
import matgm50.mankini.util.TabMankini;
import matgm50.mankini.util.TickHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

@Mod(modid = ModLib.MOD_ID, name = ModLib.MOD_NAME, version = ModLib.VERSION, acceptedMinecraftVersions = ModLib.ACCEPTED_VERSIONS)

public class Mankini {

    @Instance(ModLib.MOD_ID)
    public static Mankini instance;

    @SidedProxy(clientSide = ModLib.CLIENTPROXY, serverSide = ModLib.COMMONPROXY)
	public static CommonProxy proxy;

    public static CreativeTabs tabMankini = new TabMankini(ModLib.MOD_ID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ModItems.init();

        ModItems.register();
        
        ModRecipes.init();
        proxy.RegisterRenders();

        ModEntities.init();
        
        proxy.initMobRenderers();
      
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	MinecraftForge.EVENT_BUS.register(new BatHandler());
    	MinecraftForge.EVENT_BUS.register(new BatMankiniJump());
    	MinecraftForge.EVENT_BUS.register(new TickHandler());
    	
    	proxy.RegisterColorRenders();
    	/*
    	if(event.getSide() == Side.CLIENT)
    	{
    	    	RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    	    		
    	    	renderItem.getItemModelMesher().register(ModItems.itemBatMankini, 0, new ModelResourceLocation(ModLib.ID + ":" + ((ItemBatMankini) ModItems.itemBatMankini).getName()));
    	}*/
 
    	
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}