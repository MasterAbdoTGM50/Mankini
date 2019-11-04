package matgm50.mankini.util;

import matgm50.mankini.item.IMankini;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

/**
 * Created by MasterAbdoTGM50 on 7/3/2014.
 */
public class MankiniHelper {

    public static ItemStack findMankini(PlayerEntity player) {
        if (isMankini(player.getHeldItem(Hand.OFF_HAND))) {
            return player.getHeldItem(Hand.OFF_HAND);
        } else if (isMankini(player.getHeldItem(Hand.MAIN_HAND))) {
            return player.getHeldItem(Hand.MAIN_HAND);
        } else {
            for(int i = 0; i < player.inventory.mainInventory.size(); ++i) {
                ItemStack itemstack = player.inventory.mainInventory.get(i);
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