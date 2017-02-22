package matgm50.mankini.item;

import matgm50.mankini.Mankini;
import matgm50.mankini.lib.ItemLib;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHorseMankini extends Item implements IHorseArmor,IMankini {
	
	public ItemHorseMankini() {
		this.setUnlocalizedName(ItemLib.ModItems.MANKINI_HORSE_ARMOR.getUnlocalisedName());
        this.setRegistryName(ItemLib.ModItems.MANKINI_HORSE_ARMOR.getRegistryName());
		this.setCreativeTab(Mankini.tabMankini);
        this.setMaxStackSize(1);
	}
	
	@Override
	public HorseArmorType getArmorType() {
		return HorseArmorType.IRON;
	}

    @Override
    public String getArmorTexture (EntityHorse horse, ItemStack stack) {
        return "mankini:textures/entity/armor/mankini_horse_armor.png";
    }
}
