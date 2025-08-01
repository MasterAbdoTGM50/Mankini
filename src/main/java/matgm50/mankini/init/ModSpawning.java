package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.boss.MankiniWither;
import matgm50.mankini.entity.hostile.AbstractMankiniSkeleton;
import matgm50.mankini.entity.hostile.MankiniCreeper;
import matgm50.mankini.entity.hostile.MankiniEnderman;
import matgm50.mankini.entity.hostile.MankiniEndermite;
import matgm50.mankini.entity.hostile.MankiniEvoker;
import matgm50.mankini.entity.hostile.MankiniSpider;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber
public class ModSpawning {

	@SubscribeEvent
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		Mankini.logger.info("Registering Mankini Mob spawn placement");
		//Mobs that spawn on the ground
		event.register(ModRegistry.MANKINI_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_ENDERMITE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_SKELETON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_WITHER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_EVOKER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
	}

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		Mankini.logger.info("Registering Mankini Mob Attributes");
		event.put(ModRegistry.MANKINI_CREEPER.get(), MankiniCreeper.registerAttributes().build());
		event.put(ModRegistry.MANKINI_ENDERMAN.get(), MankiniEnderman.registerAttributes().build());
		event.put(ModRegistry.MANKINI_ENDERMITE.get(), MankiniEndermite.registerAttributes().build());
		event.put(ModRegistry.MANKINI_SPIDER.get(), MankiniSpider.registerAttributes().build());
		event.put(ModRegistry.MANKINI_SKELETON.get(), AbstractMankiniSkeleton.registerAttributes().build());
		event.put(ModRegistry.MANKINI_WITHER.get(), MankiniWither.registerAttributes().build());
		event.put(ModRegistry.MANKINI_EVOKER.get(), MankiniEvoker.registerAttributes().build());
	}
}
