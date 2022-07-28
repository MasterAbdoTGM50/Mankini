package matgm50.mankini.util;

import matgm50.mankini.entity.hostile.MankiniEndermiteEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnHandler {
	@SubscribeEvent
	public static void EntitySpawnEvent(LivingSpawnEvent event) {
		final Entity entity = event.getEntity();
		Level worldIn = entity.level;
		if (entity instanceof Endermite && !(entity instanceof MankiniEndermiteEntity)) {
			if (((Endermite) entity).getRandom().nextBoolean()) {
				MankiniEndermiteEntity mankiniMite = new MankiniEndermiteEntity(worldIn);
				mankiniMite.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), 0.0F);
				event.setCanceled(true);
				worldIn.addFreshEntity(mankiniMite);
			}
		}
	}
}
