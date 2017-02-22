package matgm50.mankini.proxy;

import matgm50.mankini.client.renderer.RenderMankiniCreeper;
import matgm50.mankini.client.renderer.RenderMankiniEnderman;
import matgm50.mankini.client.renderer.RenderMankiniEndermite;
import matgm50.mankini.client.renderer.RenderMankiniSpider;
import matgm50.mankini.client.renderer.RenderingFactoryMankiniCapsule;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
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
    public void initMobRenderers() {

        RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class, new RenderingFactoryMankiniCapsule());
    	RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCreeper.class, RenderMankiniCreeper.FACTORY);
    	RenderingRegistry.registerEntityRenderingHandler(EntityMankiniSpider.class, RenderMankiniSpider.FACTORY);
    	RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEnderman.class, RenderMankiniEnderman.FACTORY);
    	RenderingRegistry.registerEntityRenderingHandler(EntityMankiniEndermite.class, RenderMankiniEndermite.FACTORY);
        //RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWither.class, new RendererMankiniWither(new ModelMankiniWither(), 0.5F));


    }

}
