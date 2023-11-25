package matgm50.mankini.modifier;

import com.mojang.serialization.Codec;
import matgm50.mankini.init.MankiniModifiers;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;

import java.util.List;

public record AddRelativeSpawnBiomeModifier(EntityType<?> originalType,
											EntityType<?> newType, int relativeWeight) implements BiomeModifier {
	@Override
	public void modify(Holder<Biome> biomeHolder, Phase phase, Builder builder) {
		if (phase == Phase.ADD) {
			MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
			MobSpawnSettings info = biomeHolder.get().getMobSettings();
			final List<SpawnerData> spawnsList = spawns.getSpawner(MobCategory.MONSTER);
			List<SpawnerData> monsterList = info.getMobs(MobCategory.MONSTER).unwrap()
					.stream().filter(entry -> entry.type == originalType).toList();
			for (SpawnerData entry : monsterList) {
				spawnsList.add(new SpawnerData(newType, Math.min(1, entry.getWeight().asInt() / relativeWeight), entry.minCount, entry.maxCount));
			}
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return MankiniModifiers.ADD_RELATIVE_MOB_SPAWNS.get();
	}
}
