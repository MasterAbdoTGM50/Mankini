package matgm50.mankini.data;


import matgm50.mankini.data.client.MankiniEquipmentProvider;
import matgm50.mankini.data.client.MankiniLanguageProvider;
import matgm50.mankini.data.client.MankiniModelProvider;
import matgm50.mankini.data.server.MankiniDatapackProvider;
import matgm50.mankini.data.server.MankiniEntityLootProvider;
import matgm50.mankini.data.server.MankiniEntityTypeTagProvider;
import matgm50.mankini.data.server.MankiniItemTagsProvider;
import matgm50.mankini.data.server.MankiniRecipeProvider;
import matgm50.mankini.lib.ModLib;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class MankiniDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new MankiniDatapackProvider(
				packOutput,
				event.getLookupProvider(),
				Set.of(ModLib.MOD_ID)
		));

		generator.addProvider(true, new MankiniItemTagsProvider(packOutput, lookupProvider));
		generator.addProvider(true, new MankiniEntityTypeTagProvider(packOutput, lookupProvider));

		generator.addProvider(true, new LootTableProvider(packOutput, Set.of(),
				List.of(
						new LootTableProvider.SubProviderEntry(MankiniEntityLootProvider::new, LootContextParamSets.ENTITY)
				), lookupProvider
		) {
			@Override
			protected void validate(WritableRegistry<LootTable> tables, ValidationContextSource validationContext, ProblemReporter.Collector problems) {
//				super.validate(tables, validationContext, problems);
			}
		});
		generator.addProvider(true, new MankiniRecipeProvider.Runner(packOutput, lookupProvider));

		generator.addProvider(true, new MankiniLanguageProvider(packOutput));
		generator.addProvider(true, new MankiniEquipmentProvider(packOutput));
		generator.addProvider(true, new MankiniModelProvider(packOutput));
	}
}
