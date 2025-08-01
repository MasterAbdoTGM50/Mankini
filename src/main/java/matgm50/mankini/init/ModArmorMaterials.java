package matgm50.mankini.init;

import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public interface ModArmorMaterials {
	ArmorMaterial MANKINI = new ArmorMaterial(
			5, makeDefense(1, 2, 3, 1, 3),
			15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
			MankiniTags.REPAIRS_MANKINI_ARMOR, ModEquipmentAssets.MANKINI
	);

	ArmorMaterial KAWAII_MANKINI = new ArmorMaterial(
			7, makeDefense(1, 3, 5, 2, 7),
			25, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
			MankiniTags.REPAIRS_KAWAII_MANKINI_ARMOR, ModEquipmentAssets.KAWAII_MANKINI
	);

	ArmorMaterial BATTY_MANKINI = new ArmorMaterial(
			25, makeDefense(2, 5, 6, 2, 5),
			9, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
			MankiniTags.REPAIRS_BATTY_MANKINI_ARMOR, ModEquipmentAssets.BATTY_MANKINI
	);

	ArmorMaterial WITHER_KINI = new ArmorMaterial(
			25, makeDefense(2, 5, 6, 2, 5),
			9, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
			MankiniTags.REPAIRS_WITHER_KINI_ARMOR, ModEquipmentAssets.WITHER_KINI
	);

	private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
		return Maps.newEnumMap(
				Map.of(
						ArmorType.BOOTS,
						boots,
						ArmorType.LEGGINGS,
						leggings,
						ArmorType.CHESTPLATE,
						chestplate,
						ArmorType.HELMET,
						helmet,
						ArmorType.BODY,
						body
				)
		);
	}
}
