package matgm50.mankini.client.extension;

import matgm50.mankini.client.model.ModelAAMT;
import matgm50.mankini.lib.ModLib;
import net.minecraft.client.model.Model;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class AAMTItemExtension implements IClientItemExtensions {
	private static final ModelAAMT<?> MODEL = new ModelAAMT<>(ModelAAMT.createMesh().bakeRoot());

	@Override
	public @Nullable Identifier getArmorTexture(@NonNull ItemStack stack, EquipmentClientInfo.@NonNull LayerType type,
	                                            EquipmentClientInfo.@NonNull Layer layer, @NonNull Identifier _default) {
		return ModLib.modLoc("textures/models/aetheric_mankini.png");
	}

	@Override
	public @NonNull Model getHumanoidArmorModel(ItemStack itemStack, EquipmentClientInfo.@NonNull LayerType layerType, @NonNull Model original) {
		Equippable equippable = itemStack.get(DataComponents.EQUIPPABLE);
		EquipmentSlot slot = equippable != null ? equippable.slot() : null;

		if (slot == EquipmentSlot.CHEST) {
			return MODEL;
		}

		return original;
	}
}
