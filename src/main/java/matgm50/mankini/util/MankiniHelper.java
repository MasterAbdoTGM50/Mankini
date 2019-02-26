package matgm50.mankini.util;

import matgm50.mankini.item.IMankini;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

/**
 * Created by MasterAbdoTGM50 on 7/3/2014.
 */
public class MankiniHelper {
	public static ItemStack findMankini(EntityPlayer player)
    {
        if (isMankini(player.getHeldItem(EnumHand.OFF_HAND))) {
            return player.getHeldItem(EnumHand.OFF_HAND);
        } else if (isMankini(player.getHeldItem(EnumHand.MAIN_HAND))) {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        } else {
            for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);
                if (isMankini(itemstack)) {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }
    
    public static boolean isMankini(ItemStack stack)
    {
        return stack.getItem() instanceof IMankini;
    }
}