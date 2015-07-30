package matgm50.mankini.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ModItems;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class BatHandler {
	public double batCount = 0;
	double newBatCount = 0;
	 public static void init() {

	        MinecraftForge.EVENT_BUS.register(new BatHandler());

	    }

	@SubscribeEvent
	public void BatCapture(EntityInteractEvent event)
	{
	InventoryPlayer inv = event.entityPlayer.inventory;
	ItemStack currentItem = event.entityPlayer.inventory.getCurrentItem();
	ItemStack BatMankini = new ItemStack(ModItems.itemBatMankini);
	ItemStack Mankini = new ItemStack(ModItems.itemDyeableMankini);
	//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Fired event"));
	if(event.target instanceof EntityBat && event.entityPlayer.inventory.getCurrentItem().isItemEqual(Mankini) ){
	{
		
		event.target.setDead();
    // Was used to test
	//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Detected entity"));
	//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Detected item"));
		batCount = batCount+0.5;
		setBatCount();
		//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Current bat count is "+ batCount));
		if(batCount == 5.0F){
			event.entityPlayer.inventory.addItemStackToInventory(BatMankini);
			event.entityPlayer.inventory.consumeInventoryItem(ModItems.itemDyeableMankini);
			batCount = 0F;
		}
		
	}
	}
	}
	public void setBatCount(){
		newBatCount = batCount;
	}
	
	
}
