package matgm50.mankini.data.server;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MankiniItemTagsProvider extends ItemTagsProvider {
	public MankiniItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
	                             TagsProvider<Block> blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTagProvider.contentsGetter(), ModLib.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(ItemTags.DYEABLE).add(ModRegistry.DYEABLE_MANKINI.get());
	}
}
