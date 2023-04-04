package matgm50.mankini.data;


import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MankiniDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		HolderLookup.Provider provider = getProvider();
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					packOutput, CompletableFuture.supplyAsync(MankiniDatagen::getProvider), Set.of(ModLib.MOD_ID)));

			generator.addProvider(event.includeServer(), new Loots(packOutput));
		}
	}

	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, context -> {
		});
		registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
			context.register(createModifierKey("creeper"), new AddRelativeSpawnBiomeModifier(EntityType.CREEPER, ModRegistry.MANKINI_CREEPER.get(), 4));
			context.register(createModifierKey("enderman"), new AddRelativeSpawnBiomeModifier(EntityType.ENDERMAN, ModRegistry.MANKINI_ENDERMAN.get(), 4));
			context.register(createModifierKey("spider"), new AddRelativeSpawnBiomeModifier(EntityType.SPIDER, ModRegistry.MANKINI_SPIDER.get(), 4));
			context.register(createModifierKey("skeleton"), new AddRelativeSpawnBiomeModifier(EntityType.SKELETON, ModRegistry.MANKINI_SKELETON.get(), 4));
			context.register(createModifierKey("evoker"), new AddRelativeSpawnBiomeModifier(EntityType.EVOKER, ModRegistry.MANKINI_EVOKER.get(), 4));
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}

	private static ResourceKey<BiomeModifier> createModifierKey(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ModLib.MOD_ID, name));
	}

	private static class Loots extends LootTableProvider {
		public Loots(PackOutput packOutput) {
			super(packOutput, Set.of(), List.of(
					new SubProviderEntry(MankiniEntityLoot::new, LootContextParamSets.ENTITY)
			));
		}

		public static class MankiniEntityLoot extends EntityLootSubProvider {
			protected MankiniEntityLoot() {
				super(FeatureFlags.REGISTRY.allFlags());
			}

			@Override
			public void generate() {
				this.add(ModRegistry.MANKINI_CREEPER.get(), LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(ModRegistry.DYEABLE_MANKINI.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))))));
				this.add(ModRegistry.MANKINI_ENDERMAN.get(), LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootTableReference.lootTableReference(new ResourceLocation("entities/enderman")))));
				this.add(ModRegistry.MANKINI_ENDERMITE.get(), LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootTableReference.lootTableReference(new ResourceLocation("entities/endermite")))));
				this.add(ModRegistry.MANKINI_EVOKER.get(), LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(ModRegistry.DYEABLE_MANKINI.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootTableReference.lootTableReference(new ResourceLocation("entities/evoker")))));
				this.add(ModRegistry.MANKINI_SKELETON.get(), LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(ModRegistry.DYEABLE_MANKINI.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))))
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootTableReference.lootTableReference(new ResourceLocation("entities/skeleton")))));
				this.add(ModRegistry.MANKINI_SPIDER.get(), LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootTableReference.lootTableReference(new ResourceLocation("entities/spider")))));
				this.add(ModRegistry.MANKINI_WITHER.get(), LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootTableReference.lootTableReference(new ResourceLocation("entities/wither")))));
			}

			@Override
			protected Stream<EntityType<?>> getKnownEntityTypes() {
				return ModRegistry.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
			}
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
//			map.forEach((name, table) -> LootTables.validate(validationContext, name, table));
		}
	}
}
