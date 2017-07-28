package matgm50.mankini.util;

import matgm50.mankini.init.ModItems;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
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
		ItemStack BatMankini = new ItemStack(ModItems.bat_mankini);
		ItemStack Mankini = new ItemStack(ModItems.dyeable_mankini);
		
		if(event.getTarget() instanceof EntityBat && currentItem.isItemEqual(Mankini) ){
			{
				event.getTarget().setDead();
		    
				batCount = batCount+0.5F/4;
				setBatCount();
				System.out.println(batCount);
				if(batCount == 5.0F){
					event.getEntityPlayer().sendMessage(new TextComponentTranslation("bat.message"));
					inv.removeStackFromSlot(inv.getSlotFor(Mankini));
					inv.addItemStackToInventory(BatMankini);	
					batCount = 0F;
				}
			}
		}
	}
	public void setBatCount(){
		newBatCount = batCount;
	}
}
