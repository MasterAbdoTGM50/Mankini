package matgm50.mankini.init;

import matgm50.mankini.Mankini;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class MankiniConfig {
    public static class Common {
        //General
        public final ForgeConfigSpec.BooleanValue ShootMankinisOntoMobs;

        //Mobs
        public final ForgeConfigSpec.BooleanValue MankiniCreeperSpawn;

        //Mob Behavior
        public final ForgeConfigSpec.BooleanValue CreeperOverride;
        public final ForgeConfigSpec.BooleanValue EvilCreepers;


        Common(ForgeConfigSpec.Builder builder) {
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

    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        Mankini.logger.debug("Loaded Mankini config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.ConfigReloading configEvent) {
        Mankini.logger.fatal("Mankini config just got changed on the file system!");
    }
}
