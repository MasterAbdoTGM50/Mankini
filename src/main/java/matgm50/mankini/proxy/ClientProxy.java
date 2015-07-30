package matgm50.mankini.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import matgm50.mankini.client.renderer.RendererMankiniCapsule;
import matgm50.mankini.client.renderer.RendererMankiniCreeper;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.entity.EntityMankiniCreeper;
import matgm50.mankini.item.ModItems;
import net.minecraft.client.model.ModelCreeper;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderers() {

        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class, new RendererMankiniCapsule(ModItems.itemMankiniCapsule));
        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCreeper.class, new RendererMankiniCreeper(new ModelCreeper(), 0.5F));
        //RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWither.class, new RendererMankiniWither(new ModelMankiniWither(), 0.5F));


    }

}
