package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.boss.MankiniWitherEntity;
import matgm50.mankini.entity.hostile.AbstractMankiniSkeleton;
import matgm50.mankini.entity.hostile.MankiniCreeperEntity;
import matgm50.mankini.entity.hostile.MankiniEndermanEntity;
import matgm50.mankini.entity.hostile.MankiniEndermiteEntity;
import matgm50.mankini.entity.hostile.MankiniEvokerEntity;
import matgm50.mankini.entity.hostile.MankiniSpiderEntity;
import matgm50.mankini.lib.ModLib;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSpawning {

	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		Mankini.logger.info("Registering Mankini Mob spawn placement");
		//Mobs that spawn on the ground
		event.register(ModRegistry.MANKINI_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_ENDERMITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_WITHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(ModRegistry.MANKINI_EVOKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
	}

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		Mankini.logger.info("Registering Mankini Mob Attributes");
		event.put(ModRegistry.MANKINI_CREEPER.get(), MankiniCreeperEntity.registerAttributes().build());
		event.put(ModRegistry.MANKINI_ENDERMAN.get(), MankiniEndermanEntity.registerAttributes().build());
		event.put(ModRegistry.MANKINI_ENDERMITE.get(), MankiniEndermiteEntity.registerAttributes().build());
		event.put(ModRegistry.MANKINI_SPIDER.get(), MankiniSpiderEntity.registerAttributes().build());
		event.put(ModRegistry.MANKINI_SKELETON.get(), AbstractMankiniSkeleton.registerAttributes().build());
		event.put(ModRegistry.MANKINI_WITHER.get(), MankiniWitherEntity.registerAttributes().build());
		event.put(ModRegistry.MANKINI_EVOKER.get(), MankiniEvokerEntity.registerAttributes().build());
	}
}
