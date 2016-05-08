package matgm50.mankini.proxy;

import matgm50.mankini.client.renderer.RendererMankiniCapsule;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.item.ModItems;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderers() {

        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class, new RendererMankiniCapsule(null, ModItems.itemMankiniCapsule, null));
     
        //RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWither.class, new RendererMankiniWither(new ModelMankiniWither(), 0.5F));


    }

}
