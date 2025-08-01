package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent.LivingJumpEvent;
@EventBusSubscriber
public class BatMankiniJump {
	@SubscribeEvent
	public static void PlayerJump(LivingJumpEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (!player.isShiftKeyDown()) {
				ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
				if (!chestStack.isEmpty()) {
					if (chestStack.is(ModRegistry.BAT_MANKINI)) {
						Vec3 motion = player.getDeltaMovement();
						double motionY = motion.y();
						motionY += 1.1;
						player.setDeltaMovement(motion.x, motionY, motion.z);
						player.addEffect((new MobEffectInstance(MobEffects.SLOWNESS, 200, 1)));
					}
				}
			}
		}
	}
}