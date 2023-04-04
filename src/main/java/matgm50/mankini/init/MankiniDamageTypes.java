package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class MankiniDamageTypes {
	public static final ResourceKey<DamageType> MANKINI_WITHER = register("mankini_wither");

	private static ResourceKey<DamageType> register(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ModLib.MOD_ID, name));
	}
}
