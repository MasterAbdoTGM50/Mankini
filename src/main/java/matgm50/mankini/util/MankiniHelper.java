package matgm50.mankini.util;

import matgm50.mankini.item.IMankini;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by MasterAbdoTGM50 on 7/3/2014.
 */
public class MankiniHelper {
	public static ItemStack findMankini(EntityPlayer player)
    {
        for (int i = 0; i < player.inventory.getSizeInventory()-5; ++i)
        {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            
            if(itemstack != null && isMankini(itemstack)) {
                ItemStack foundMankini = player.inventory.getStackInSlot(i);
                return foundMankini;
            }
        }

        return ItemStack.EMPTY;
}
    
    public static boolean isMankini(ItemStack stack)
    {
        return stack.getItem() instanceof IMankini;
    }   
}