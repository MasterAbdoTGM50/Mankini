package matgm50.mankini.util;

import matgm50.mankini.item.IMankini;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;

/**
 * Created by MasterAbdoTGM50 on 7/3/2014.
 */
public class MankiniHelper {

    public static ItemStack findMankini(Player player) {
        if (isMankini(player.getItemInHand(InteractionHand.OFF_HAND))) {
            return player.getItemInHand(InteractionHand.OFF_HAND);
        } else if (isMankini(player.getItemInHand(InteractionHand.MAIN_HAND))) {
            return player.getItemInHand(InteractionHand.MAIN_HAND);
        } else {
            for(int i = 0; i < player.getInventory().items.size(); ++i) {
                ItemStack itemstack = player.getInventory().items.get(i);
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