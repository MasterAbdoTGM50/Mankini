package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSpawning
{
    public static void register() {
        registerSpawnPlacement();

        for(Biome biome : ForgeRegistries.BIOMES)
        {
            for (Biome.SpawnListEntry entry : new ArrayList<>(biome.getSpawns(EntityClassification.MONSTER))) {
                registerSpawn(entry, biome, EntityType.CREEPER, ModRegistry.MANKINI_CREEPER.get());
                registerSpawn(entry, biome, EntityType.ENDERMAN, ModRegistry.MANKINI_ENDERMAN.get());
                registerSpawn(entry, biome, EntityType.SPIDER, ModRegistry.MANKINI_SPIDER.get());
                registerSpawn(entry, biome, EntityType.SKELETON, ModRegistry.MANKINI_SKELETON.get());
                registerSpawn(entry, biome, EntityType.EVOKER, ModRegistry.MANKINI_EVOKER.get());
            }
        }
    }

    public static void registerSpawnWithArguments(Biome.SpawnListEntry entry, Biome biome, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity, int weight, int minGroup, int maxGroup)
    {
        if(entry.entityType == oldEntity)
        {
            biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(newEntity, weight, minGroup, maxGroup));
        }
    }

    public static void registerSpawn(Biome.SpawnListEntry entry, Biome biome, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity)
    {
        if(entry.entityType == oldEntity)
        {
            biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(newEntity, entry.itemWeight / 4, entry.minGroupCount, entry.maxGroupCount));
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
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_WITHER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(ModRegistry.MANKINI_EVOKER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canSpawnOn);
    }
}
