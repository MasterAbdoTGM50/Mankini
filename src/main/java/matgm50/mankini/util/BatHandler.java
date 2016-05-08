package matgm50.mankini.util;

import matgm50.mankini.item.ModItems;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BatHandler {
	public double batCount = 0;
	double newBatCount = 0;
	 public static void init() {

	        MinecraftForge.EVENT_BUS.register(new BatHandler());

	    }

	@SubscribeEvent
	public void BatCapture(EntityInteract event)
	{
	InventoryPlayer inv = event.getEntityPlayer().inventory;
	ItemStack currentItem = event.getEntityPlayer().inventory.getCurrentItem();
	ItemStack BatMankini = new ItemStack(ModItems.itemBatMankini);
	ItemStack Mankini = new ItemStack(ModItems.itemDyeableMankini);
	//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Fired event"));
	if(event.getTarget() instanceof EntityBat && event.getEntityPlayer().inventory.getCurrentItem().isItemEqual(Mankini) ){
	{
		
		event.getTarget().setDead();
    // Was used to test
	//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Detected entity"));
	//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Detected item"));
		batCount = batCount+0.5;
		setBatCount();
		//event.entityPlayer.addChatComponentMessage(new ChatComponentText("Current bat count is "+ batCount));
		if(batCount == 5.0F){
			event.getEntityPlayer().inventory.addItemStackToInventory(BatMankini);
			 ItemStack stack = new ItemStack(MankiniHelper.getFirstFoundMankini(event.getEntityPlayer()).getItem());
		        --stack.stackSize;
			//event.getEntityPlayer().inventory.consumeInventoryItem(ModItems.itemDyeableMankini);
			batCount = 0F;
		}
		
	}
	}
	}
	public void setBatCount(){
		newBatCount = batCount;
	}
	
	
}
