package matgm50.mankini.client.renderer;

import matgm50.mankini.client.model.ModelMankiniWither;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class RendererMankiniWither extends RenderWither {
	private static final ResourceLocation textureLocation = new ResourceLocation(ModLib.ID.toLowerCase() + ":" + "textures/entity/wither.png");
	
    public RendererMankiniWither(Item par1Item) {

        super();

    }

    public RendererMankiniWither(ModelMankiniWither modelMankiniWither, float f) {

        super();

    }

}
