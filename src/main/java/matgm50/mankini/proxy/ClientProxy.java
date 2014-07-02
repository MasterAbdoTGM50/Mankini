package matgm50.mankini.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import matgm50.mankini.client.renderer.RendererMankiniCapsule;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.item.ModItems;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderers() {

        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class, new RendererMankiniCapsule(ModItems.itemMankiniCapsule));

    }

}
