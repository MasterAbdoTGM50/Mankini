package matgm50.mankini.data.client;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.Nullable;

public class MankiniLanguageProvider extends LanguageProvider {
	public MankiniLanguageProvider(PackOutput packOutput) {
		super(packOutput, ModLib.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.mankini", "Mankini");

		addItem(ModRegistry.DYEABLE_MANKINI, "Plain Dyeable Mankini");
		addItem(ModRegistry.KAWAII_MANKINI, "So Kawaii Mankini");
		addItem(ModRegistry.AETHERIC_MANKINI, "Angelic Aetheric Mankini Thingy");
		addItem(ModRegistry.MANKINI_CANNON, "Mankini Cannon");
		addItem(ModRegistry.MANKINI_CAPSULE_ITEM, "Mankini Capsule Mankini");
		addItem(ModRegistry.BAT_MANKINI, "Batty Mankini");
		addItem(ModRegistry.WITHER_MANKINI, "WitherKini");
		addItem(ModRegistry.MANKINI_HORSE_ARMOR, "Mankini Horse Armor");

		addItem(ModRegistry.MANKINI_CREEPER_SPAWN_EGG, "Mankini Creeper Spawn Egg");
		addItem(ModRegistry.MANKINI_ENDERMAN_SPAWN_EGG, "Mankini Enderman Spawn Egg");
		addItem(ModRegistry.MANKINI_ENDERMITE_SPAWN_EGG, "Mankini Endermite Spawn Egg");
		addItem(ModRegistry.MANKINI_SPIDER_SPAWN_EGG, "Mankini Spider Spawn Egg");
		addItem(ModRegistry.MANKINI_SKELETON_SPAWN_EGG, "Mankini Skeleton Spawn Egg");
		addItem(ModRegistry.MANKINI_EVOKER_SPAWN_EGG, "Mankini Evoker Spawn Egg");

		add("mankini.bat.message", "Successfully captured a bat in a Mankini.");

		addEntityType(ModRegistry.MANKINI_CREEPER, "Mankini Creeper");
		addEntityType(ModRegistry.MANKINI_ENDERMAN, "Mankini Enderman");
		addEntityType(ModRegistry.MANKINI_ENDERMITE, "Mankini Endermite");
		addEntityType(ModRegistry.MANKINI_SPIDER, "Mankini Spider");
		addEntityType(ModRegistry.MANKINI_SKELETON, "Mankini Skeleton");
		addEntityType(ModRegistry.MANKINI_WITHER, "Mankini Wither");
		addEntityType(ModRegistry.MANKINI_CAPSULE, "Mankini Capsule");
		addEntityType(ModRegistry.MANKINI_WITHER_PROJECTILE, "Mankini Wither Capsule");
		addEntityType(ModRegistry.MANKINI_EVOKER, "Mankini Evoker");

		add("death.attack.mankini_wither", "%1$s could not handle the power of the mankini");
		add("death.attack.mankini_wither.player", "%1$s withered away whilst fighting %2$s 's mankini's");

		addEffect(ModRegistry.MANKINI_WITHER_EFFECT, "Wither");

		addConfig("title", "Mankini Config", null);
		addConfig("general", "General", "General settings");
		addConfig("ShootMankinisOntoMobs", "Shoot Mankinis Onto Mobs", "Ability to shoot Mankini's onto capable mobs (default: true)");
		addConfig("entities", "Entities", "Entity settings");
		addConfig("MankiniCreeperSpawn", "Mankini Creeper Spawn", "Enables the Mankini Creeper spawning (default: true)");
		addConfig("MankiniEndermanSpawn", "Mankini Enderman Spawn", "Enables the Mankini Enderman spawning (default: true)");
		addConfig("MankiniEndermiteSpawn", "Mankini Endermite Spawn", "Enables the Mankini Endermite spawning (default: true)");
		addConfig("MankiniSpiderSpawn", "Mankini Spider Spawn", "Enables the Mankini Spider spawning (default: true)");
		addConfig("MankiniSkeletonSpawn", "Mankini Skeleton Spawn", "Enables the Mankini Skeleton spawning (default: true)");
		addConfig("mobbehavior", "Mob Behavior", "Mob behavior settings");
		addConfig("CreeperOverride", "Creeper Override", "Mankini creepers overrides armor (default: false)");
		addConfig("EvilCreepers", "Evil Creepers", "Mankini creepers become evil and mankini's default with curse of binding (default: false)");
	}

	/**
	 * Add the translation for a config entry
	 *
	 * @param path        The path of the config entry
	 * @param name        The name of the config entry
	 * @param description The description of the config entry (optional in case of targeting "title" or similar entries that have no tooltip)
	 */
	private void addConfig(String path, String name, @Nullable String description) {
		this.add("mankini.configuration." + path, name);
		if (description != null && !description.isEmpty())
			this.add("mankini.configuration." + path + ".tooltip", description);
	}
}
