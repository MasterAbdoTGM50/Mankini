package matgm50.mankini.init;

import matgm50.mankini.entity.boss.MankiniWither;
import matgm50.mankini.entity.boss.MankiniWitherCapsuleEntity;
import matgm50.mankini.entity.hostile.MankiniCreeper;
import matgm50.mankini.entity.hostile.MankiniEnderman;
import matgm50.mankini.entity.hostile.MankiniEndermite;
import matgm50.mankini.entity.hostile.MankiniEvoker;
import matgm50.mankini.entity.hostile.MankiniSkeleton;
import matgm50.mankini.entity.hostile.MankiniSpider;
import matgm50.mankini.entity.projectiles.MankiniCapsule;
import matgm50.mankini.item.ItemAAMT;
import matgm50.mankini.item.ItemBatMankini;
import matgm50.mankini.item.ItemDyeableMankini;
import matgm50.mankini.item.ItemKawaiiMankini;
import matgm50.mankini.item.ItemMankiniCannon;
import matgm50.mankini.item.ItemMankiniCapsule;
import matgm50.mankini.item.ItemMankiniHorseArmor;
import matgm50.mankini.item.ItemWitherKini;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.potion.MankiniWitherPotion;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModLib.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModLib.MOD_ID);
	public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(ModLib.MOD_ID);
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, ModLib.MOD_ID);

	public static final Supplier<EntityType<MankiniCapsule>> MANKINI_CAPSULE = ENTITY_TYPES.registerEntityType("mankini_capsule", MankiniCapsule::new, MobCategory.MISC, builder -> builder
			.sized(0.25F, 0.25F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniCreeper>> MANKINI_CREEPER = ENTITY_TYPES.registerEntityType("mankini_creeper", MankiniCreeper::new, MobCategory.MONSTER, builder -> builder
			.sized(0.6F, 1.7F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniEnderman>> MANKINI_ENDERMAN = ENTITY_TYPES.registerEntityType("mankini_enderman", MankiniEnderman::new, MobCategory.MONSTER, builder -> builder
			.sized(0.6F, 2.9F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniEndermite>> MANKINI_ENDERMITE = ENTITY_TYPES.registerEntityType("mankini_endermite", MankiniEndermite::new, MobCategory.MONSTER, builder -> builder
			.sized(0.4F, 0.3F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniSpider>> MANKINI_SPIDER = ENTITY_TYPES.registerEntityType("mankini_spider", MankiniSpider::new, MobCategory.MONSTER, builder -> builder
			.sized(1.4F, 0.9F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniSkeleton>> MANKINI_SKELETON = ENTITY_TYPES.registerEntityType("mankini_skeleton", MankiniSkeleton::new, MobCategory.MONSTER, builder -> builder
			.sized(0.6F, 1.99F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniWither>> MANKINI_WITHER = ENTITY_TYPES.registerEntityType("mankini_wither", MankiniWither::new, MobCategory.MONSTER, builder -> builder
			.fireImmune().sized(0.9F, 3.5F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniWitherCapsuleEntity>> MANKINI_WITHER_PROJECTILE = ENTITY_TYPES.registerEntityType("mankini_wither_projectile", MankiniWitherCapsuleEntity::new, MobCategory.MISC, builder -> builder
			.sized(0.3125F, 0.3125F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final Supplier<EntityType<MankiniEvoker>> MANKINI_EVOKER = ENTITY_TYPES.registerEntityType("mankini_evoker", MankiniEvoker::new, MobCategory.MONSTER, builder -> builder
			.sized(0.6F, 1.95F).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));

	public static final DeferredHolder<MobEffect, MankiniWitherPotion> MANKINI_WITHER_EFFECT = MOB_EFFECTS.register("mankini_wither", MankiniWitherPotion::new);

	public static final DeferredItem<ItemDyeableMankini> DYEABLE_MANKINI = ITEMS.registerItem("dyeable_mankini", ItemDyeableMankini::new);
	public static final DeferredItem<ItemKawaiiMankini> KAWAII_MANKINI = ITEMS.registerItem("kawaii_mankini", ItemKawaiiMankini::new);
	public static final DeferredItem<ItemAAMT> AETHERIC_MANKINI = ITEMS.registerItem("aetheric_mankini", ItemAAMT::new);
	public static final DeferredItem<ItemMankiniCannon> MANKINI_CANNON = ITEMS.registerItem("mankini_cannon", ItemMankiniCannon::new);
	public static final DeferredItem<ItemMankiniCapsule> MANKINI_CAPSULE_ITEM = ITEMS.registerItem("mankini_capsule", ItemMankiniCapsule::new);
	public static final DeferredItem<ItemBatMankini> BAT_MANKINI = ITEMS.registerItem("mankini_bat", ItemBatMankini::new);
	public static final DeferredItem<ItemWitherKini> WITHER_MANKINI = ITEMS.registerItem("mankini_wither", ItemWitherKini::new);
	public static final DeferredItem<ItemMankiniHorseArmor> MANKINI_HORSE_ARMOR = ITEMS.registerItem("mankini_horse_armor", ItemMankiniHorseArmor::new);
	public static final DeferredItem<SpawnEggItem> MANKINI_CREEPER_SPAWN_EGG = ITEMS.registerItem("mankini_creeper_spawn_egg", (properties) -> new SpawnEggItem(ModRegistry.MANKINI_CREEPER.get(), properties));
	public static final DeferredItem<SpawnEggItem> MANKINI_ENDERMAN_SPAWN_EGG = ITEMS.registerItem("mankini_enderman_spawn_egg", (properties) -> new SpawnEggItem(ModRegistry.MANKINI_ENDERMAN.get(), properties));
	public static final DeferredItem<SpawnEggItem> MANKINI_ENDERMITE_SPAWN_EGG = ITEMS.registerItem("mankini_endermite_spawn_egg", (properties) -> new SpawnEggItem(ModRegistry.MANKINI_ENDERMITE.get(), properties));
	public static final DeferredItem<SpawnEggItem> MANKINI_SPIDER_SPAWN_EGG = ITEMS.registerItem("mankini_spider_spawn_egg", (properties) -> new SpawnEggItem(ModRegistry.MANKINI_SPIDER.get(), properties));
	public static final DeferredItem<SpawnEggItem> MANKINI_SKELETON_SPAWN_EGG = ITEMS.registerItem("mankini_skeleton_spawn_egg", (properties) -> new SpawnEggItem(ModRegistry.MANKINI_SKELETON.get(), properties));
	public static final DeferredItem<SpawnEggItem> MANKINI_EVOKER_SPAWN_EGG = ITEMS.registerItem("mankini_evoker_spawn_egg", (properties) -> new SpawnEggItem(ModRegistry.MANKINI_EVOKER.get(), properties));

	public static final Supplier<CreativeModeTab> MANKINI_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(ModRegistry.KAWAII_MANKINI.get()))
			.title(Component.translatable("itemGroup.mankini"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = ModRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
}
