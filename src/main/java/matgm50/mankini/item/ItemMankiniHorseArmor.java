package matgm50.mankini.item;

import matgm50.mankini.lib.ModLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterials;

public class ItemMankiniHorseArmor extends AnimalArmorItem {
	public ItemMankiniHorseArmor(Properties properties) {
		super(ArmorMaterials.LEATHER, AnimalArmorItem.BodyType.EQUESTRIAN, false, properties.stacksTo(1));
	}

	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation(ModLib.MOD_ID, "textures/entity/horse_armor/mankini_horse_armor.png");
	}
}
