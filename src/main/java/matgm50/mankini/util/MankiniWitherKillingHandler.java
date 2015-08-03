/*
 * 
 * This is a WIP class
 * package matgm50.mankini.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import matgm50.mankini.Mankini;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class MankiniWitherKillingHandler {

		
		 public static void init() {
			 MinecraftForge.EVENT_BUS.register(new MankiniWitherKillingHandler());
		    }
		 
		@SubscribeEvent
		public void WitherKilled(LivingDeathEvent event)
		{					
				//if((event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && ((EntityPlayer) event.source.getEntity()).getCurrentEquippedItem() != null)){
					//ItemStack equip = ((EntityPlayer) event.source.getEntity()).getEquipmentInSlot(2);
					ItemStack batMankini = new ItemStack(ModItems.itemBatMankini);
					ItemStack aamt = new ItemStack(ModItems.itemAethericMankini);
					//event.source.getEntity().setFire(10);
					//if(equip != null && equip == batMankini){
						//((EntityPlayer) event.source.getEntity()).setCurrentItemOrArmor(2, null);
					//}
				
			//}
		//if (source.getEntity() instanceof EntityPlayer){
		
	//	}
		}
		
}
*/
