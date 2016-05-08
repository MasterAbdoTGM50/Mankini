package matgm50.mankini.entity;

import java.util.Random;

import matgm50.mankini.Mankini;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ModEntities {
	
	public static void registerEntity(Class entityClass, String name)
    {
    int entityID = EntityRegistry.findGlobalUniqueEntityId();
    long seed = name.hashCode();
    Random rand = new Random(seed);
    int primaryColor = rand.nextInt() * 16777215;
    int secondaryColor = rand.nextInt() * 16777215;

    EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
    EntityRegistry.registerModEntity(entityClass, name, entityID, Mankini.instance, 64, 1, true);
   // EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
    }

    public static void init() {

        EntityRegistry.registerModEntity(EntityMankiniCapsule.class, "MankiniCapsule", 0, Mankini.instance, 64, 10, true);
        
        
        //registerEntity(EntityMankiniWither.class, "Mankini Wither");
    	
        

    }

}
