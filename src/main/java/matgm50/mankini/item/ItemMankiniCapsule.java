package matgm50.mankini.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/**
 * Created by MasterAbdoTGM50 on 7/2/2014.
 */

public class ItemMankiniCapsule extends Item {

	public ItemMankiniCapsule(Item.Properties builder) {
		super(builder.stacksTo(1));
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		return InteractionResult.PASS;
	}
}