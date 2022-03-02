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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID)
public class ModSpawning {
	public static void register() {
		registerSpawnPlacement();
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void addSpawn(BiomeLoadingEvent event) {
		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
		if (biome != null) {
			MobSpawnSettings info = biome.getMobSettings();
			List<MobSpawnSettings.SpawnerData> spawns = event.getSpawns().getSpawner(MobCategory.MONSTER);
			for (SpawnerData entry : info.getMobs(MobCategory.MONSTER).unwrap()) {
				registerSpawn(spawns, entry, EntityType.CREEPER, ModRegistry.MANKINI_CREEPER.get());
				registerSpawn(spawns, entry, EntityType.ENDERMAN, ModRegistry.MANKINI_ENDERMAN.get());
				registerSpawn(spawns, entry, EntityType.SPIDER, ModRegistry.MANKINI_SPIDER.get());
				registerSpawn(spawns, entry, EntityType.SKELETON, ModRegistry.MANKINI_SKELETON.get());
				registerSpawn(spawns, entry, EntityType.EVOKER, ModRegistry.MANKINI_EVOKER.get());
			}
		}
	}

	public static void registerSpawn(List<SpawnerData> spawns, SpawnerData entry, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity) {
		if (entry.type == oldEntity) {
			spawns.add(new MobSpawnSettings.SpawnerData(newEntity, entry.getWeight().asInt() / 4, entry.minCount, entry.maxCount));
		}
	}

	public static void registerSpawnPlacement() {
		Mankini.logger.info("Registering Mankini Mob spawn placement");
		//Mobs that spawn on the ground
		SpawnPlacements.register(ModRegistry.MANKINI_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules);
		SpawnPlacements.register(ModRegistry.MANKINI_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules);
		SpawnPlacements.register(ModRegistry.MANKINI_ENDERMITE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules);
		SpawnPlacements.register(ModRegistry.MANKINI_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules);
		SpawnPlacements.register(ModRegistry.MANKINI_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules);
		SpawnPlacements.register(ModRegistry.MANKINI_WITHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModRegistry.MANKINI_EVOKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules);
	}

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
