package matgm50.mankini;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.init.ModConfigGen;
import matgm50.mankini.init.ModCreativeTab;
import matgm50.mankini.init.ModEntities;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.proxy.CommonProxy;
import matgm50.mankini.util.BatHandler;
import matgm50.mankini.util.BatMankiniJump;
import matgm50.mankini.util.DropHandler;
import matgm50.mankini.util.TickHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModLib.MOD_ID, name = ModLib.MOD_NAME, version = ModLib.VERSION, acceptedMinecraftVersions = ModLib.ACCEPTED_VERSIONS)

public class Mankini {

    @Instance(ModLib.MOD_ID)
    public static Mankini instance;

    @SidedProxy(clientSide = ModLib.CLIENTPROXY, serverSide = ModLib.COMMONPROXY)
	public static CommonProxy proxy;

	public static final Logger logger = LogManager.getLogger(ModLib.MOD_ID);

    public static CreativeTabs tabMankini = new ModCreativeTab(ModLib.MOD_ID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    	logger.debug("Registering Config");
    	MinecraftForge.EVENT_BUS.register(new ModConfigGen());
        
        //ModRecipes.init();
    	logger.debug("Registering Entities");
        ModEntities.register();
        
        proxy.initMobRenderers();      
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	for (Biome biome : Biome.REGISTRY) {
    		if(ModConfigGen.entities.MankiniCreeper)
    		{
    			biome.getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityMankiniCreeper.class, 30, 4, 4));
    		}
    	}
    	
    	logger.debug("Registering Handlers");
    	MinecraftForge.EVENT_BUS.register(new BatHandler());
    	MinecraftForge.EVENT_BUS.register(new BatMankiniJump());
    	MinecraftForge.EVENT_BUS.register(new TickHandler());
    	MinecraftForge.EVENT_BUS.register(new DropHandler());
    	
    	proxy.RegisterColorRenders();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}