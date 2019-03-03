package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BatMankiniJump {
	@SubscribeEvent
	public static void PlayerJump(LivingJumpEvent event)
	{
    	if(event.getEntityLiving() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
    		if(!player.isSneaking()){
				if (player.inventory.armorItemInSlot(2) != null)
				{
					if(player.inventory.armorItemInSlot(2).getItem()== ModItems.bat_mankini){
						player.motionY += 1.1F;
						player.addPotionEffect((new PotionEffect(MobEffects.SLOWNESS, 200, 1)));
					}
				}
    		}
    	}
	}
}