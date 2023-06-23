package matgm50.mankini.data.server;

import matgm50.mankini.init.ModRegistry;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

public class MankiniEntityLootProvider extends EntityLootSubProvider {
	public MankiniEntityLootProvider() {
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
