package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.init.ModItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMankiniHorseArmor extends Item {
    public static final HorseArmorType MANKINI_ARMOR = HorseArmorType.create("mankini",3,"mankini:textures/entity/horse_armor/mankini_horse_armor.png", "maa", ModItems.mankini_horse_armor);

    public ItemMankiniHorseArmor(Item.Properties builder) {
        super(builder.group(Mankini.tabMankini).maxStackSize(1));
    }

    @Override
    public HorseArmorType getHorseArmorType(ItemStack stack) {
        return MANKINI_ARMOR;
    }

    @Override
    public void onHorseArmorTick(ItemStack stack, World world, EntityLiving horse) {
        //TODO: Fancy rainbow mankini?
    }
}
