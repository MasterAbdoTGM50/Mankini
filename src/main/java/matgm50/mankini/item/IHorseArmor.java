package matgm50.mankini.item;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.ItemStack;

public interface IHorseArmor {

    HorseArmorType getArmorType ();

    String getArmorTexture (EntityHorse horse, ItemStack stack);
}