package matgm50.mankini.data.client;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MankiniModelProvider extends ModelProvider {
	public MankiniModelProvider(PackOutput output) {
		super(output, ModLib.MOD_ID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		for (DeferredHolder<Item, ? extends Item> deferredHolder : ModRegistry.ITEMS.getEntries()) {
			if (deferredHolder.is(ModRegistry.DYEABLE_MANKINI)) {
				generateDyedItem(itemModels, ModRegistry.DYEABLE_MANKINI.get(), -6265536);
			} else {
				itemModels.generateFlatItem(deferredHolder.get(), ModelTemplates.FLAT_ITEM);
			}
		}
	}

	public void generateDyedItem(ItemModelGenerators itemModels, Item item, int color) {
		Identifier model = itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM);
		itemModels.itemModelOutput.accept(item, ItemModelUtils.tintedModel(model, new Dye(color)));
	}
}
