package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BatHandler {

	public static void BatCapture(PlayerInteractEvent.EntityInteract event) {
		Player player = event.getPlayer();
		Inventory inv = player.getInventory();
		ItemStack currentItem = player.getInventory().getSelected();
		ItemStack BatMankini = new ItemStack(ModRegistry.BAT_MANKINI.get());
		ItemStack Mankini = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

		if (event.getTarget() instanceof Bat && currentItem.sameItem(Mankini)) {
			event.getTarget().discard();
			CompoundTag playerData = player.getPersistentData();
			CompoundTag data = getTag(playerData, Player.PERSISTED_NBT_TAG);

			incrementBatTag(player);
			int batCount = data.getInt(ModLib.BAT_COUNT_TAG);

			if (batCount == 8) {
				player.sendMessage(new TranslatableComponent("mankini.bat.message"), Util.NIL_UUID);
				inv.removeItemNoUpdate(inv.findSlotMatchingItem(Mankini));
				inv.add(BatMankini);
				setBatTag(player);
			}
		}
	}

	public static void incrementBatTag(Player player) {
		CompoundTag playerData = player.getPersistentData();
		CompoundTag data = getTag(playerData, Player.PERSISTED_NBT_TAG);

		if (data.contains(ModLib.BAT_COUNT_TAG)) {
			int currentBat = data.getInt(ModLib.BAT_COUNT_TAG);
			data.putInt(ModLib.BAT_COUNT_TAG, currentBat++);
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
