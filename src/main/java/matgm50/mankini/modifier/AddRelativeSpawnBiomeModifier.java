package matgm50.mankini.modifier;

import com.mojang.serialization.MapCodec;
import matgm50.mankini.init.MankiniModifiers;
import net.minecraft.core.Holder;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;

import java.util.ArrayList;
import java.util.List;

public record AddRelativeSpawnBiomeModifier(EntityType<?> originalType,
											EntityType<?> newType, int relativeWeight) implements BiomeModifier {
	@Override
	public void modify(Holder<Biome> biomeHolder, Phase phase, Builder builder) {
		if (phase == Phase.ADD) {
			MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
			WeightedList.Builder<MobSpawnSettings.SpawnerData> spawner = spawns.getSpawner(originalType.getCategory());
			List<Weighted<SpawnerData>> addedSpawns = new ArrayList<>(); //Add the spawns to this list to avoid modifying the original list
			final List<Weighted<SpawnerData>> spawnsList = spawner.getList();
			for (Weighted<SpawnerData> entry : spawnsList) {
				EntityType<?> type = entry.value().type();
				if (type == originalType) {
					addedSpawns.add(new Weighted<>(new SpawnerData(newType, entry.value().minCount(), entry.value().maxCount()),
							Math.min(1, entry.weight() / relativeWeight)));
				}
			}
			if (!addedSpawns.isEmpty()) {
				spawner.addAll(addedSpawns);
			}
		}
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return MankiniModifiers.ADD_RELATIVE_MOB_SPAWNS.get();
	}
}
