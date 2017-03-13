package matgm50.mankini.init;

import net.minecraftforge.common.config.Configuration;

public class ModConfigGen {
	public static void configOptions(Configuration config) {
		config.load();
		// Config options here
		
		config.save();
		config.load();
	}
}
