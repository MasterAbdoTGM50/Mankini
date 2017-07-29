package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void register() {

		 EntityRegistry.registerModEntity(ModEntityNames.MANKINI_CAPSULE_REGISTRY, EntityMankiniCapsule.class, ModEntityNames.MANKINI_CAPSULE, 0, Mankini.instance, 80, 1, true);   
		 if (ModConfigGen.entities.MankiniCreeper)
		 {
		 EntityRegistry.registerModEntity(ModEntityNames.MANKINI_CREEPER_REGISTRY, EntityMankiniCreeper.class, ModEntityNames.MANKINI_CREEPER, 1, Mankini.instance, 80, 3, true, 894731, 0);
		 }
		 //registerEntity(EntityMankiniWither.class, "Mankini Wither");
        
    }
}
