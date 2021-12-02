package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BatMankiniJump {
	@SubscribeEvent
	public static void PlayerJump(LivingJumpEvent event) {
    	if(event.getEntityLiving() instanceof Player player) {
			if(!player.isShiftKeyDown()) {
				if (player.getInventory().getArmor(2) != null) {
					if(player.getInventory().getArmor(2).getItem()== ModRegistry.BAT_MANKINI.get()){
						Vec3 motion = player.getDeltaMovement();
						double motionY = motion.y();
						motionY += 1.1;
						player.setDeltaMovement(motion.x, motionY, motion.z);
						player.addEffect((new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1)));
					}
				}
    		}
    	}
	}
}