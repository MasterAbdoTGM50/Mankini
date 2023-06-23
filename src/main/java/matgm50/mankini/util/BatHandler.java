package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BatHandler {

	public static void BatCapture(PlayerInteractEvent.EntityInteract event) {
		final Player player = event.getEntity();
		Inventory inv = player.getInventory();
		ItemStack currentItem = player.getInventory().getSelected();
		ItemStack batMankiniStack = new ItemStack(ModRegistry.BAT_MANKINI.get());
		ItemStack dyeableMankiniStack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

		if (event.getTarget() instanceof Bat && ItemStack.isSameItem(currentItem, dyeableMankiniStack)) {
			event.getTarget().discard();
			CompoundTag playerData = player.getPersistentData();
			CompoundTag data = getTag(playerData, Player.PERSISTED_NBT_TAG);

			incrementBatTag(player);
			int batCount = data.getInt(ModLib.BAT_COUNT_TAG);

			if (batCount == 8) {
				player.sendSystemMessage(Component.translatable("mankini.bat.message"));
				inv.removeItemNoUpdate(inv.findSlotMatchingItem(dyeableMankiniStack));
				inv.add(batMankiniStack);
				setBatTag(player);
			}
		}
	}

	public static void incrementBatTag(Player player) {
		CompoundTag playerData = player.getPersistentData();
		CompoundTag data = getTag(playerData, Player.PERSISTED_NBT_TAG);

		if (data.contains(ModLib.BAT_COUNT_TAG)) {
			int currentBat = data.getInt(ModLib.BAT_COUNT_TAG);
			currentBat++;
			data.putInt(ModLib.BAT_COUNT_TAG, currentBat);
		} else {
			data.putInt(ModLib.BAT_COUNT_TAG, 1);
		}

		playerData.put(Player.PERSISTED_NBT_TAG, data);
	}

	public static void setBatTag(Player player) {
		CompoundTag playerData = player.getPersistentData();
		CompoundTag data = getTag(playerData, Player.PERSISTED_NBT_TAG);

		data.putInt(ModLib.BAT_COUNT_TAG, 0);

		playerData.put(Player.PERSISTED_NBT_TAG, data);
	}

	public static CompoundTag getTag(CompoundTag tag, String key) {
		if (tag == null || !tag.contains(key)) {
			return new CompoundTag();
		}
		return tag.getCompound(key);
	}
}
