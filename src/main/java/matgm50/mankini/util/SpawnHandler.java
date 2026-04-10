package matgm50.mankini.util;

import matgm50.mankini.entity.hostile.MankiniEndermite;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

@EventBusSubscriber
public class SpawnHandler {
	@SubscribeEvent
	public static void EntitySpawnEvent(FinalizeSpawnEvent event) {
		final Entity entity = event.getEntity();
		Level level = entity.level();
		if (entity instanceof Endermite && !(entity instanceof MankiniEndermite)) {
			if (((Endermite) entity).getRandom().nextBoolean()) {
				MankiniEndermite mankiniMite = new MankiniEndermite(level);
				mankiniMite.snapTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), 0.0F);
				event.setSpawnCancelled(true);
				level.addFreshEntity(mankiniMite);
			}
		}
	}
}
