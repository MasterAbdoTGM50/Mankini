package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.entity.hostile.AbstractMankiniSkeleton;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.entity.hostile.EntityMankiniEvoker;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.Heightmap;
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

    @SubscribeEvent(priority =  EventPriority.HIGH)
    public static void addSpawn(BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if(biome != null) {
            MobSpawnInfo info = biome.getMobSpawnInfo();
            List<MobSpawnInfo.Spawners> spawns = event.getSpawns().getSpawner(EntityClassification.MONSTER);
            for(Spawners entry : info.getSpawners(EntityClassification.MONSTER)) {
                registerSpawn(spawns, entry, EntityType.CREEPER, ModRegistry.MANKINI_CREEPER.get());
                registerSpawn(spawns, entry, EntityType.ENDERMAN, ModRegistry.MANKINI_ENDERMAN.get());
                registerSpawn(spawns, entry, EntityType.SPIDER, ModRegistry.MANKINI_SPIDER.get());
                registerSpawn(spawns, entry, EntityType.SKELETON, ModRegistry.MANKINI_SKELETON.get());
                registerSpawn(spawns, entry, EntityType.EVOKER, ModRegistry.MANKINI_EVOKER.get());
            }
        }
    }

    public static void registerSpawn(List<Spawners> spawns, Spawners entry, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity) {
        if(entry.type == oldEntity) {
            spawns.add(new MobSpawnInfo.Spawners(newEntity, entry.itemWeight / 4, entry.minCount, entry.maxCount));
        }
    }

    public static void registerSpawnPlacement() {
        Mankini.logger.info("Registering Mankini Mob spawn placement");
        //Mobs that spawn on the ground
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_CREEPER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_ENDERMAN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_ENDERMITE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_SPIDER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_SKELETON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_WITHER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_EVOKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
    }

    public static void registerAttributes() {
        Mankini.logger.info("Registering Mankini Mob Attributes");
        GlobalEntityTypeAttributes.put(ModRegistry.MANKINI_CREEPER.get(), EntityMankiniCreeper.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModRegistry.MANKINI_ENDERMAN.get(), EntityMankiniEnderman.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModRegistry.MANKINI_ENDERMITE.get(), EntityMankiniEndermite.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModRegistry.MANKINI_SPIDER.get(), EntityMankiniSpider.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModRegistry.MANKINI_SKELETON.get(), AbstractMankiniSkeleton.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModRegistry.MANKINI_WITHER.get(), EntityMankiniWither.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ModRegistry.MANKINI_EVOKER.get(), EntityMankiniEvoker.registerAttributes().create());
    }
}
