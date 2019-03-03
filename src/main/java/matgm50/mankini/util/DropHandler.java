package matgm50.mankini.util;

import matgm50.mankini.lib.ModLib;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DropHandler {
	
	@SubscribeEvent
	public static void onLivingDrop(LivingDropsEvent event) {
		
    }
}
