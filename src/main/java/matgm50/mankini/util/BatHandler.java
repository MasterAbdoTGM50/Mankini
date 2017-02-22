package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BatHandler {
	public double batCount = 0;
	double newBatCount = 0;


	@SubscribeEvent
	public void BatCapture(EntityInteract event)
	{
	InventoryPlayer inv = event.getEntityPlayer().inventory;
	ItemStack currentItem = event.getEntityPlayer().inventory.getCurrentItem();
	ItemStack BatMankini = new ItemStack(ModItems.itemBatMankini);
	ItemStack Mankini = new ItemStack(ModItems.itemDyeableMankini);
	
	if(event.getTarget() instanceof EntityBat && event.getEntityPlayer().inventory.getCurrentItem().isItemEqual(Mankini) ){
		{
			
			event.getTarget().setDead();
	    
			batCount = batCount+0.5;
			setBatCount();
		
			if(batCount == 5.0F){
				event.getEntityPlayer().setHeldItem(EnumHand.MAIN_HAND, null);
				event.getEntityPlayer().inventory.addItemStackToInventory(BatMankini);	
				batCount = 0F;
			}
			
		}
	}
	
	}
	public void setBatCount(){
		newBatCount = batCount;
	}
	
	
}
