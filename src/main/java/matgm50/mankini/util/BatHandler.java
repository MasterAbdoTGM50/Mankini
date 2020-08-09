package matgm50.mankini.util;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BatHandler {

	@SubscribeEvent
	public static void BatCapture(EntityInteract event) {
		PlayerEntity player = event.getPlayer();
		PlayerInventory inv = player.inventory;
		ItemStack currentItem = player.inventory.getCurrentItem();
		ItemStack BatMankini = new ItemStack(ModRegistry.BAT_MANKINI.get());
		ItemStack Mankini = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

		if(event.getTarget() instanceof BatEntity && currentItem.isItemEqual(Mankini)) {
			event.getTarget().remove();
			CompoundNBT playerData = player.getPersistentData();
			CompoundNBT data = getTag(playerData, PlayerEntity.PERSISTED_NBT_TAG);

			incrementBatTag(player);
			int batCount = data.getInt(ModLib.BAT_COUNT_TAG);

			if(batCount == 8){
				player.sendMessage(new TranslationTextComponent("mankini.bat.message"));
				inv.removeStackFromSlot(inv.getSlotFor(Mankini));
				inv.addItemStackToInventory(BatMankini);
				setBatTag(player);
			}
		}
	}

	public static void incrementBatTag(PlayerEntity player){
		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTag(playerData, PlayerEntity.PERSISTED_NBT_TAG);

		if(data.contains(ModLib.BAT_COUNT_TAG)) {
			int currentBat = data.getInt(ModLib.BAT_COUNT_TAG);
			data.putInt(ModLib.BAT_COUNT_TAG, currentBat++);
		} else {
			data.putInt(ModLib.BAT_COUNT_TAG, 1);
		}

		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}

	public static void setBatTag(PlayerEntity player){
		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTag(playerData, PlayerEntity.PERSISTED_NBT_TAG);

		data.putInt(ModLib.BAT_COUNT_TAG, 0);

		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}

	public static CompoundNBT getTag(CompoundNBT tag, String key) {
		if(tag == null || !tag.contains(key)) {
			return new CompoundNBT();
		}
		return tag.getCompound(key);
	}
}
