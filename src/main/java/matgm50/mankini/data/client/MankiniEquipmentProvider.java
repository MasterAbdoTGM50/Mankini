package matgm50.mankini.data.client;

import matgm50.mankini.init.ModEquipmentAssets;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.function.BiConsumer;

public class MankiniEquipmentProvider extends EquipmentAssetProvider {
	public MankiniEquipmentProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
		output.accept(
				ModEquipmentAssets.MANKINI,
				EquipmentClientInfo.builder()
						.addHumanoidLayers(ModLib.modLoc("mankini"), true)
						.addLayers(
								EquipmentClientInfo.LayerType.HORSE_BODY, EquipmentClientInfo.Layer.leatherDyeable(ModLib.modLoc("mankini"), true)
						)
						.build()
		);
		output.accept(
				ModEquipmentAssets.BATTY_MANKINI,
				EquipmentClientInfo.builder()
						.addHumanoidLayers(ModLib.modLoc("batty_mankini"), false)
						.build()
		);
		output.accept(
				ModEquipmentAssets.KAWAII_MANKINI,
				EquipmentClientInfo.builder()
						.addHumanoidLayers(ModLib.modLoc("kawaii_mankini"), false)
						.build()
		);
		output.accept(
				ModEquipmentAssets.WITHER_KINI,
				EquipmentClientInfo.builder()
						.addHumanoidLayers(ModLib.modLoc("wither_kini"), false)
						.build()
		);
	}
}
