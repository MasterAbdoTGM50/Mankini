package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraft.util.ResourceLocation;

public class ModEntityNames {
public static final String MANKINI_CAPSULE = ModLib.MOD_PREFIX + "MankiniCapsule";
	
	public static final ResourceLocation MANKINI_CAPSULE_REGISTRY = Name("mankini_capsule");

	private static ResourceLocation Name(String s) {
		return new ResourceLocation(ModLib.MOD_ID, s);
}
}
