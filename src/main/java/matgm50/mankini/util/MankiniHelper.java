package matgm50.mankini.util;

import matgm50.mankini.item.IMankini;
import matgm50.mankini.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by MasterAbdoTGM50 on 7/3/2014.
 */

public class MankiniHelper {

    public static ItemStack getFirstFoundMankini(EntityPlayer thrower) {

        for(int i = 0; i < thrower.inventory.getSizeInventory(); i++) {

            if(thrower.inventory.getStackInSlot(i) != null && thrower.inventory.getStackInSlot(i).getItem() instanceof IMankini) {

                ItemStack foundMankini = thrower.inventory.getStackInSlot(i);
                return foundMankini;

            }

        }

        return new ItemStack(ModItems.itemDyeableMankini);

    }

    public static Boolean mankiniinInventory(EntityPlayer thrower) {

        for(int i = 0; i < thrower.inventory.getSizeInventory(); i++) {

            if(thrower.inventory.getStackInSlot(i) != null && thrower.inventory.getStackInSlot(i).getItem() instanceof IMankini) {

 
                return true;

            }

        }

        return false;

    }
    public static int mankiniSlot(EntityPlayer thrower){
		
    	return thrower.inventory.getSlotFor(getFirstFoundMankini(thrower));
    	
    }

}
