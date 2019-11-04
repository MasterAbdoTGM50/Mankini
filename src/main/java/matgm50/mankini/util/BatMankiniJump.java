package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BatMankiniJump {
	@SubscribeEvent
	public static void PlayerJump(LivingJumpEvent event)
	{
    	if(event.getEntityLiving() instanceof PlayerEntity) {
    		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
    		if(!player.isSneaking()){
				if (player.inventory.armorItemInSlot(2) != null)
				{
					if(player.inventory.armorItemInSlot(2).getItem()== ModItems.bat_mankini){
						Vec3d motion = player.getMotion();
						double motionY = motion.getY();
						motionY += 1.1;
						player.setMotion(motion.x, motionY, motion.z);
						player.addPotionEffect((new EffectInstance(Effects.SLOWNESS, 200, 1)));
					}
				}
    		}
    	}
	}
}