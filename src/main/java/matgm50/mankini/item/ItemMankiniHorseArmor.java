package matgm50.mankini.item;

import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemMankiniHorseArmor extends HorseArmorItem {
    public ItemMankiniHorseArmor(Item.Properties builder) {
        super(3, new ResourceLocation(ModLib.MOD_ID, "textures/entity/horse_armor/mankini_horse_armor.png"), builder.maxStackSize(1));
    }

    @Override
    public void onHorseArmorTick(ItemStack stack, World world, MobEntity horse) {

    }
}
