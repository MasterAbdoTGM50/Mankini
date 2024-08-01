package matgm50.mankini.data;


import matgm50.mankini.data.client.MankiniLanguageProvider;
import matgm50.mankini.data.server.MankiniEntityLootProvider;
import matgm50.mankini.data.server.MankiniEntityTypeTagProvider;
import matgm50.mankini.data.server.MankiniItemTagsProvider;
import matgm50.mankini.data.server.MankiniRecipeProvider;
import matgm50.mankini.init.MankiniDamageTypes;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class MankiniDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					packOutput, CompletableFuture.supplyAsync(MankiniDatagen::getProvider), Set.of(ModLib.MOD_ID)));

			BlockTagsProvider blockTagProvider = new BlockTagsProvider(packOutput, lookupProvider, ModLib.MOD_ID, fileHelper) {
				@Override
				protected void addTags(HolderLookup.Provider provider) {

				}
			};
			generator.addProvider(event.includeServer(), blockTagProvider);
			generator.addProvider(event.includeServer(), new MankiniItemTagsProvider(packOutput, lookupProvider,
					blockTagProvider, fileHelper));
			generator.addProvider(event.includeServer(), new MankiniEntityTypeTagProvider(packOutput, lookupProvider, fileHelper));

			generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Set.of(),
					List.of(
							new LootTableProvider.SubProviderEntry(MankiniEntityLootProvider::new, LootContextParamSets.ENTITY)
					), lookupProvider
			) {
				@Override
				protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {
//					super.validate(writableregistry, validationcontext, problemreporter$collector);
				}
			});
			generator.addProvider(event.includeServer(), new MankiniRecipeProvider(packOutput, lookupProvider));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new MankiniLanguageProvider(packOutput));
		}
	}

	private static RegistrySetBuilder.PatchedRegistries getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.DAMAGE_TYPE, context -> {
			context.register(MankiniDamageTypes.MANKINI_WITHER, new DamageType("mankini_wither", 0.0F));
		});
		// We need the BIOME registry to be present, so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, context -> {
		});
		registryBuilder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
			context.register(createModifierKey("creeper"), new AddRelativeSpawnBiomeModifier(EntityType.CREEPER, ModRegistry.MANKINI_CREEPER.get(), 4));
			context.register(createModifierKey("enderman"), new AddRelativeSpawnBiomeModifier(EntityType.ENDERMAN, ModRegistry.MANKINI_ENDERMAN.get(), 4));
			context.register(createModifierKey("spider"), new AddRelativeSpawnBiomeModifier(EntityType.SPIDER, ModRegistry.MANKINI_SPIDER.get(), 4));
			context.register(createModifierKey("skeleton"), new AddRelativeSpawnBiomeModifier(EntityType.SKELETON, ModRegistry.MANKINI_SKELETON.get(), 4));
			context.register(createModifierKey("evoker"), new AddRelativeSpawnBiomeModifier(EntityType.EVOKER, ModRegistry.MANKINI_EVOKER.get(), 4));
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		Cloner.Factory cloner$factory = new Cloner.Factory();
		net.neoforged.neoforge.registries.DataPackRegistriesHooks.getDataPackRegistriesWithDimensions().forEach(data -> data.runWithArguments(cloner$factory::addCodec));
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup(), cloner$factory);
	}

	private static ResourceKey<BiomeModifier> createModifierKey(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ModLib.MOD_ID, name));
	}
}
