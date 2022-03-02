package matgm50.mankini.item;

import matgm50.mankini.lib.ModLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemMankiniHorseArmor extends HorseArmorItem {
	public ItemMankiniHorseArmor(Item.Properties builder) {
		super(3, new ResourceLocation(ModLib.MOD_ID, "textures/entity/horse_armor/mankini_horse_armor.png"), builder.stacksTo(1));
	}

	@Override
	public void onHorseArmorTick(ItemStack stack, Level world, Mob horse) {

	}
}
