package matgm50.mankini.data.server;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class MankiniItemTagsProvider extends ItemTagsProvider {
	public MankiniItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, ModLib.MOD_ID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(ItemTags.DYEABLE).add(ModRegistry.DYEABLE_MANKINI.get());
	}
}
