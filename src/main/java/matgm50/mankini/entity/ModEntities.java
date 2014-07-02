package matgm50.mankini.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import matgm50.mankini.Mankini;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ModEntities {

    public static void init() {

        EntityRegistry.registerModEntity(EntityMankiniCapsule.class, "MankiniCapsule", 0, Mankini.instance, 64, 10, true);

    }

}
