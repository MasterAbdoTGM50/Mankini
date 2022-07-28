package matgm50.mankini.data;


import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MankiniDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			Map<ResourceLocation, BiomeModifier> biomeModifiers = new LinkedHashMap<>();
			biomeModifiers.putAll(addModifierForType(EntityType.CREEPER, ModRegistry.MANKINI_CREEPER.get(), 4));
			biomeModifiers.putAll(addModifierForType(EntityType.ENDERMAN, ModRegistry.MANKINI_ENDERMAN.get(), 4));
			biomeModifiers.putAll(addModifierForType(EntityType.SPIDER, ModRegistry.MANKINI_SPIDER.get(), 4));
			biomeModifiers.putAll(addModifierForType(EntityType.SKELETON, ModRegistry.MANKINI_SKELETON.get(), 4));
			biomeModifiers.putAll(addModifierForType(EntityType.EVOKER, ModRegistry.MANKINI_EVOKER.get(), 4));

			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
					generator, helper, ModLib.MOD_ID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS, biomeModifiers
			));

			generator.addProvider(event.includeServer(), new Loots(generator));
		}
	}

	private static Map<ResourceLocation, BiomeModifier> addModifierForType(EntityType<?> originalType, EntityType<?> newType, int relativeWeight) {
		return Map.of(ForgeRegistries.ENTITY_TYPES.getKey(originalType), new AddRelativeSpawnBiomeModifier(
				originalType, newType, relativeWeight));
	}

	private static class Loots extends LootTableProvider {
		public Loots(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
			return ImmutableList.of(
					Pair.of(MankiniEntityLoot::new, LootContextParamSets.ENTITY)
			);
		}

		public static class MankiniEntityLoot extends EntityLoot {

			@Override
			protected void addTables() {
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
			protected Iterable<EntityType<?>> getKnownEntities() {
				Stream<EntityType<?>> entityTypeStream = ModRegistry.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
				return entityTypeStream::iterator;
			}
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
//			map.forEach((name, table) -> LootTables.validate(validationContext, name, table));
		}
	}
}
