package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, ModLib.MOD_ID);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> MANKINI = register(
			"mankini",
			Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 1);
				map.put(ArmorItem.Type.LEGGINGS, 2);
				map.put(ArmorItem.Type.CHESTPLATE, 3);
				map.put(ArmorItem.Type.HELMET, 1);
				map.put(ArmorItem.Type.BODY, 3);
			}),
			15,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			() -> Ingredient.of(),
			List.of(new ArmorMaterial.Layer(ModLib.modLoc("mankini"), "", true))
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> KAWAII_MANKINI = register(
			"kawaii_mankini",
			Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 1);
				map.put(ArmorItem.Type.LEGGINGS, 3);
				map.put(ArmorItem.Type.CHESTPLATE, 5);
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.BODY, 7);
			}),
			25,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			() -> Ingredient.of(),
			List.of(new ArmorMaterial.Layer(ModLib.modLoc("kawaii_mankini"), "", true))
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BATTY_MANKINI = register(
			"batty_mankini",
			Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 2);
				map.put(ArmorItem.Type.LEGGINGS, 5);
				map.put(ArmorItem.Type.CHESTPLATE, 6);
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.BODY, 5);
			}),
			9,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			() -> Ingredient.of(),
			List.of(new ArmorMaterial.Layer(ModLib.modLoc("batty_mankini"), "", true))
	);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> WITHER_KINI = register(
			"wither_kini",
			Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 2);
				map.put(ArmorItem.Type.LEGGINGS, 5);
				map.put(ArmorItem.Type.CHESTPLATE, 6);
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.BODY, 5);
			}),
			9,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			() -> Ingredient.of(),
			List.of(new ArmorMaterial.Layer(ModLib.modLoc("wither_kini"), "", true))
	);

	private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(
			String name,
			EnumMap<ArmorItem.Type, Integer> defense,
			int enchantmentValue,
			Holder<SoundEvent> equipSound,
			float toughness,
			float knockbackResistance,
			Supplier<Ingredient> repairIngridient,
			List<ArmorMaterial.Layer> layers
	) {
		EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

		for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
			enummap.put(armoritem$type, defense.get(armoritem$type));
		}

		return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(
				enummap, enchantmentValue, equipSound, repairIngridient, layers, toughness, knockbackResistance));
	}
}
