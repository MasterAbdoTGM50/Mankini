package matgm50.mankini.proxy;

import matgm50.mankini.client.renderer.mobs.RenderMankiniCreeper;
import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.init.ModConfigGen;
import matgm50.mankini.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
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
    	RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCapsule.class, new IRenderFactory<EntityMankiniCapsule>() {
			@Override
			public Render<? super EntityMankiniCapsule> createRenderFor(RenderManager manager) {
				return new RenderSnowball<EntityMankiniCapsule>(manager, ModItems.mankini_capsule, Minecraft.getMinecraft().getRenderItem());
			}
    	});
    	if(ModConfigGen.entities.MankiniCreeper)
    	{
    		RenderingRegistry.registerEntityRenderingHandler(EntityMankiniCreeper.class, RenderMankiniCreeper.FACTORY);
    	}
        //RenderingRegistry.registerEntityRenderingHandler(EntityMankiniWither.class, new RendererMankiniWither(new ModelMankiniWither(), 0.5F));


    }

}
