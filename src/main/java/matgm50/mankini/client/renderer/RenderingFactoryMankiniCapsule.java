package matgm50.mankini.client.renderer;

import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderingFactoryMankiniCapsule implements IRenderFactory<EntityMankiniCapsule>{

	@Override
	public Render<? super EntityMankiniCapsule> createRenderFor(RenderManager manager) {
		RenderItem renderer = FMLClientHandler.instance().getClient().getRenderItem();
		return new RendererMankiniCapsule(manager, renderer);
	}
}