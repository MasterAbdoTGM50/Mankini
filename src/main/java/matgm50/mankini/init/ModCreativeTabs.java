package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModCreativeTabs {

	private static CreativeModeTab MANKINI_TAB;

	@SubscribeEvent
	public void registerCreativeTabs(final CreativeModeTabEvent.Register event) {
		MANKINI_TAB = event.registerCreativeModeTab(new ResourceLocation(ModLib.MOD_ID, "tab"), builder ->
				builder.icon(() -> new ItemStack(ModRegistry.KAWAII_MANKINI.get()))
						.title(Component.translatable("itemGroup.mankini"))
						.displayItems((displayParameters, output) -> {
							List<ItemStack> stacks = ModRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
							output.acceptAll(stacks);
						}));
	}
}
