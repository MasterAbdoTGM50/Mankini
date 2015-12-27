package matgm50.mankini.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class BatMankiniJump {
	
	
	public static void init() {

        MinecraftForge.EVENT_BUS.register(new BatMankiniJump());

    }
	@SubscribeEvent
	public void PlayerJump(LivingJumpEvent event)
	{
    	
    	if(event.entityLiving instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.entityLiving;
    		if(!player.isSneaking()){
			if (player.inventory.armorItemInSlot(2) != null){
				if(player.inventory.armorItemInSlot(2).getItem()== ModItems.itemBatMankini){
				player.motionY += 1.1F;
				player.addPotionEffect((new PotionEffect(Potion.moveSlowdown.getId(), 200, 1)));
				}
			}
    		}
    	}
	}
	@SubscribeEvent
    public void PlayerFall(LivingFallEvent event)
	{	
    	if(event.entityLiving instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.entityLiving;
    		event.distance = 0F;
    	}

    	}
    
}
