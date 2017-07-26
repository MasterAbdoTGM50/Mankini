package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.EntityMankiniCapsule;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void register() {

		 EntityRegistry.registerModEntity(ModEntityNames.MANKINI_CAPSULE_REGISTRY, EntityMankiniCapsule.class, ModEntityNames.MANKINI_CAPSULE, 0, Mankini.instance, 64, 10, true);        //EntityRegistry.registerModEntity(EntityMankiniCreeper.class, "MankiniCreeper", 1, Mankini.instance, 80, 3, true);
        //EntityRegistry.registerModEntity(EntityMankiniSpider.class, "MankiniSpider", 2, Mankini.instance, 80, 3, true);
        //EntityRegistry.registerModEntity(EntityMankiniEnderman.class, "MankiniEnderman", 3, Mankini.instance, 80, 3, true);
        //EntityRegistry.registerModEntity(EntityMankiniEndermite.class, "MankiniEnderMite", 4, Mankini.instance, 80, 3, true);
        //registerEntity(EntityMankiniWither.class, "Mankini Wither");
        
    }
}
