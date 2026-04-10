package matgm50.mankini.data.server;

import matgm50.mankini.init.MankiniDamageTypes;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MankiniDatapackProvider extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.DAMAGE_TYPE, context -> {
				context.register(MankiniDamageTypes.MANKINI_WITHER, new DamageType("mankini_wither", 0.0F));
			})
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
				context.register(createModifierKey("creeper"), new AddRelativeSpawnBiomeModifier(EntityType.CREEPER, ModRegistry.MANKINI_CREEPER.get(), 4));
				context.register(createModifierKey("enderman"), new AddRelativeSpawnBiomeModifier(EntityType.ENDERMAN, ModRegistry.MANKINI_ENDERMAN.get(), 4));
				context.register(createModifierKey("spider"), new AddRelativeSpawnBiomeModifier(EntityType.SPIDER, ModRegistry.MANKINI_SPIDER.get(), 4));
				context.register(createModifierKey("skeleton"), new AddRelativeSpawnBiomeModifier(EntityType.SKELETON, ModRegistry.MANKINI_SKELETON.get(), 4));
				context.register(createModifierKey("evoker"), new AddRelativeSpawnBiomeModifier(EntityType.EVOKER, ModRegistry.MANKINI_EVOKER.get(), 4));
			});

	private static ResourceKey<BiomeModifier> createModifierKey(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModLib.modLoc(name));
	}

	public MankiniDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, Set<String> modIds) {
		super(output, registries, BUILDER, modIds);
	}
}
