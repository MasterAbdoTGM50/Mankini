package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class MankiniConfig {
	public static class Common {
		//General
		public final ModConfigSpec.BooleanValue ShootMankinisOntoMobs;

		//Mobs
		public final ModConfigSpec.BooleanValue MankiniCreeperSpawn;
		public final ModConfigSpec.BooleanValue MankiniEndermanSpawn;
		public final ModConfigSpec.BooleanValue MankiniEndermiteSpawn;
		public final ModConfigSpec.BooleanValue MankiniSpiderSpawn;
		public final ModConfigSpec.BooleanValue MankiniSkeletonSpawn;

		//Mob Behavior
		public final ModConfigSpec.BooleanValue CreeperOverride;
		public final ModConfigSpec.BooleanValue EvilCreepers;


		Common(ModConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("general");

			ShootMankinisOntoMobs = builder
					.comment("Ability to shoot Mankini's onto capable mobs (default: true)")
					.define("ShootMankinisOntoMobs", true);

			builder.pop();
			builder.comment("Enable mobs.")
					.push("entities");


			MankiniCreeperSpawn = builder
					.comment("Enables the Mankini Creeper spawning (default: true)")
					.define("MankiniCreeperSpawn", true);

			MankiniEndermanSpawn = builder
					.comment("Enables the Mankini Enderman spawning (default: true)")
					.define("MankiniEndermanSpawn", true);

			MankiniEndermiteSpawn = builder
					.comment("Enables the Mankini Endermite spawning (default: true)")
					.define("MankiniEndermiteSpawn", true);

			MankiniSpiderSpawn = builder
					.comment("Enables the Mankini Spider spawning (default: true)")
					.define("MankiniSpiderSpawn", true);

			MankiniSkeletonSpawn = builder
					.comment("Enables the Mankini Skeleton spawning (default: true)")
					.define("MankiniSkeletonSpawn", true);


			builder.pop();
			builder.comment("For configuring changes to mob behavior.")
					.push("mobbehavior");

			CreeperOverride = builder
					.comment("Mankini creepers overrides armor (default: false)")
					.define("CreeperOverride", false);

			EvilCreepers = builder
					.comment("Mankini creepers become evil and mankini's default with curse of binding (default: false)")
					.define("EvilCreepers", false);

			builder.pop();
		}
	}

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		Mankini.logger.debug("Loaded Mankini config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		Mankini.logger.warn("Mankini config just got changed on the file system!");
	}
}
