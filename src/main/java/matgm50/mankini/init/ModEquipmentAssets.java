package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public interface ModEquipmentAssets {
	ResourceKey<EquipmentAsset> MANKINI = createId("mankini");
	ResourceKey<EquipmentAsset> KAWAII_MANKINI = createId("kawaii_mankini");
	ResourceKey<EquipmentAsset> BATTY_MANKINI = createId("batty_mankini");
	ResourceKey<EquipmentAsset> WITHER_KINI = createId("wither_kini");

	static ResourceKey<EquipmentAsset> createId(String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, ModLib.modLoc(name));
	}
}
