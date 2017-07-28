package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BatMankiniJump {
	@SubscribeEvent
	public void PlayerJump(LivingJumpEvent event)
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