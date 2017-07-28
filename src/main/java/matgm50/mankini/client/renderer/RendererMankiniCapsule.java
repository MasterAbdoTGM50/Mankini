package matgm50.mankini.client.renderer;

import matgm50.mankini.entity.EntityMankiniCapsule;
import matgm50.mankini.init.ModItems;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RendererMankiniCapsule extends RenderSnowball<EntityMankiniCapsule> {

    public RendererMankiniCapsule(RenderManager renderManagerIn, RenderItem renderItem) {
        super(renderManagerIn, ModItems.mankini_capsule, renderItem);
    }
    
}