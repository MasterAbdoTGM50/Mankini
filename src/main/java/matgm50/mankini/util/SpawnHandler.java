package matgm50.mankini.util;

import matgm50.mankini.entity.hostile.MankiniEndermiteEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnHandler {
	@SubscribeEvent
	public static void EntitySpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
		final Entity entity = event.getEntity();
		Level level = entity.level();
		if (entity instanceof Endermite && !(entity instanceof MankiniEndermiteEntity)) {
			if (((Endermite) entity).getRandom().nextBoolean()) {
				MankiniEndermiteEntity mankiniMite = new MankiniEndermiteEntity(level);
				mankiniMite.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), 0.0F);
				event.setSpawnCancelled(true);
				level.addFreshEntity(mankiniMite);
			}
		}
	}
}
