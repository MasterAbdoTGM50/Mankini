package matgm50.mankini.util;

import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropHandler {
	
	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		if (event.getEntity() instanceof EntityMankiniCreeper) {
            {
            	ItemStack itemStackToDrop = new ItemStack(ModItems.dyeable_mankini, 1);
            	event.getDrops().add(new EntityItem(event.getEntity().world, event.getEntity().posX, 
            		event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
		}
		if (event.getEntity() instanceof EntityMankiniEnderman) {
            {
            	ItemStack itemStackToDrop = new ItemStack(ModItems.dyeable_mankini, 1);
            	event.getDrops().add(new EntityItem(event.getEntity().world, event.getEntity().posX, 
            		event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
		}
		if (event.getEntity() instanceof EntityMankiniSpider) {
            {
            	ItemStack itemStackToDrop = new ItemStack(ModItems.dyeable_mankini, 1);
            	event.getDrops().add(new EntityItem(event.getEntity().world, event.getEntity().posX, 
            		event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
		}
    }
}
