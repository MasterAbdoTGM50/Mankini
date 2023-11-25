package matgm50.mankini.data.client;

import matgm50.mankini.init.ModRegistry;
import matgm50.mankini.lib.ModLib;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

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
	}
}
