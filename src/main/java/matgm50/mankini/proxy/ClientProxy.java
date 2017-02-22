package matgm50.mankini.proxy;

import matgm50.mankini.client.renderer.RenderMankiniCreeper;
import matgm50.mankini.client.renderer.RenderingFactoryMankiniCapsule;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.entity.EntityMankiniCreeper;
import matgm50.mankini.init.ModItems;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ClientProxy extends CommonProxy {

	@Override
	public void RegisterRenders(){
		ModItems.registerRenders();
	}
	
	@Override
	public void RegisterColorRenders(){
		ModItems.registerColorRenders();
	}
    @Override
    public void initRenderers() {

        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class, new RenderingFactoryMankiniCapsule());
    	RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCreeper.class, RenderMankiniCreeper.FACTORY);
        //RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWither.class, new RendererMankiniWither(new ModelMankiniWither(), 0.5F));


    }

}
