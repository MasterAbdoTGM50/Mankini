package matgm50.mankini.data.server;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class MankiniEntityTypeTagProvider extends EntityTypeTagsProvider {
	public MankiniEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider, ModLib.MOD_ID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(EntityTypeTags.SKELETONS).add(
				ModRegistry.MANKINI_SKELETON.get()
		);
		this.tag(EntityTypeTags.UNDEAD).add(
				ModRegistry.MANKINI_WITHER.get()
		);
	}
}
