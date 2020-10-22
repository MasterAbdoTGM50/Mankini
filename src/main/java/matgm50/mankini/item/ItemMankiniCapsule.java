package matgm50.mankini.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCapsule extends Item {

    public ItemMankiniCapsule(Item.Properties builder) {
        super(builder.maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return new ActionResult<>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }
}