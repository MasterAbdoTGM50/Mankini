package matgm50.mankini.util;

import matgm50.mankini.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BatMankiniJump {
	 ItemStack batKini = new ItemStack(ModItems.itemBatMankini);
	 EntityEquipmentSlot slotIn = EntityEquipmentSlot.CHEST;
	
	public static void init() {

        MinecraftForge.EVENT_BUS.register(new BatMankiniJump());

    }
	@SubscribeEvent
	public void PlayerJump(LivingJumpEvent event)
	{
    	
    	if(event.getEntityLiving() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
    		if(!player.isSneaking()){
			if (player.inventory.armorItemInSlot(2) != null){
				if(player.inventory.armorItemInSlot(2).getItem()== ModItems.itemBatMankini){
				player.motionY += 1.1F;
				player.addPotionEffect((new PotionEffect(MobEffects.moveSlowdown, 200, 1)));
				}
			}
    		}
    	}
	}

	

    	}
    

