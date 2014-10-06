package matgm50.mankini.client.renderer;

import matgm50.mankini.lib.ModLib;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class RendererMankiniCreeper extends RenderCreeper{

	private static final ResourceLocation textureLocation = new ResourceLocation(ModLib.ID.toLowerCase() + ":" + "textures/entity/mankiniCreeper.png");

	public RendererMankiniCreeper(ModelCreeper model, float shadowSize) {
	super();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
	return textureLocation;
	}
	}

