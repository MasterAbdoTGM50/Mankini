package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BatHandler {

	@SubscribeEvent
	public static void BatCapture(EntityInteract event)
	{
		EntityPlayer player = event.getEntityPlayer();
		InventoryPlayer inv = event.getEntityPlayer().inventory;
		ItemStack currentItem = event.getEntityPlayer().inventory.getCurrentItem();
		ItemStack BatMankini = new ItemStack(ModItems.bat_mankini);
		ItemStack Mankini = new ItemStack(ModItems.dyeable_mankini);

		if(event.getTarget() instanceof EntityBat && currentItem.isItemEqual(Mankini) ){
			{
				event.getTarget().remove();
				NBTTagCompound playerData = player.getEntityData();
				NBTTagCompound data = getTag(playerData, EntityPlayer.PERSISTED_NBT_TAG);

				incrementBatTag(player);
				int batCount = data.getInt(ModLib.BAT_COUNT_TAG);

				if(batCount == 8){
					event.getEntityPlayer().sendMessage(new TextComponentTranslation("mankini.bat.message"));
					inv.removeStackFromSlot(inv.getSlotFor(Mankini));
					inv.addItemStackToInventory(BatMankini);
					setBatTag(player);
				}
			}
		}
	}

	public static void incrementBatTag(EntityPlayer player){
		NBTTagCompound playerData = player.getEntityData();
		NBTTagCompound data = getTag(playerData, EntityPlayer.PERSISTED_NBT_TAG);

		if(data.hasKey(ModLib.BAT_COUNT_TAG))
		{
			int currentBat = data.getInt(ModLib.BAT_COUNT_TAG);
			data.setInt(ModLib.BAT_COUNT_TAG, currentBat++);
		}
		else {
			data.setInt(ModLib.BAT_COUNT_TAG, 1);
		}

		playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, data);
	}

	public static void setBatTag(EntityPlayer player){
		NBTTagCompound playerData = player.getEntityData();
		NBTTagCompound data = getTag(playerData, EntityPlayer.PERSISTED_NBT_TAG);

		data.setInt(ModLib.BAT_COUNT_TAG, 0);

		playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, data);
	}

	public static NBTTagCompound getTag(NBTTagCompound tag, String key) {
		if(tag == null || !tag.hasKey(key)) {
			return new NBTTagCompound();
		}
		return tag.getCompound(key);
	}
}
