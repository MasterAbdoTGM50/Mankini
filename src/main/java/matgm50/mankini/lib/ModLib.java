package matgm50.mankini.lib;

import net.minecraft.resources.ResourceLocation;

/**
 * Created by MasterAbdoTGM50 on 4/23/2014.
 */

public class ModLib {
	public static final String MOD_ID = "mankini";
	public static final String MOD_PREFIX = MOD_ID + ":";

	public static final String BAT_COUNT_TAG = MOD_PREFIX + "bat_count";

	public static ResourceLocation modLoc(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
