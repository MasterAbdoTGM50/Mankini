package matgm50.mankini.init;

import matgm50.mankini.entity.boss.MankiniWitherCapsuleEntity;
import matgm50.mankini.entity.boss.MankiniWitherEntity;
import matgm50.mankini.entity.hostile.MankiniCreeperEntity;
import matgm50.mankini.entity.hostile.MankiniEndermanEntity;
import matgm50.mankini.entity.hostile.MankiniEndermiteEntity;
import matgm50.mankini.entity.hostile.MankiniEvokerEntity;
import matgm50.mankini.entity.hostile.MankiniSkeletonEntity;
import matgm50.mankini.entity.hostile.MankiniSpiderEntity;
import matgm50.mankini.entity.projectiles.MankiniCapsuleEntity;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModLib.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModLib.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModLib.MOD_ID);
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ModLib.MOD_ID);

	public static final RegistryObject<EntityType<MankiniCapsuleEntity>> MANKINI_CAPSULE = ENTITY_TYPES.register("mankini_capsule", () -> register("mankini_capsule", EntityType.Builder.<MankiniCapsuleEntity>of(MankiniCapsuleEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F)
			.setCustomClientFactory(MankiniCapsuleEntity::new)));
	public static final RegistryObject<EntityType<MankiniCreeperEntity>> MANKINI_CREEPER = ENTITY_TYPES.register("mankini_creeper", () -> register("mankini_creeper", EntityType.Builder.<MankiniCreeperEntity>of(MankiniCreeperEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.7F)));
	public static final RegistryObject<EntityType<MankiniEndermanEntity>> MANKINI_ENDERMAN = ENTITY_TYPES.register("mankini_enderman", () -> register("mankini_enderman", EntityType.Builder.<MankiniEndermanEntity>of(MankiniEndermanEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 2.9F)));
	public static final RegistryObject<EntityType<MankiniEndermiteEntity>> MANKINI_ENDERMITE = ENTITY_TYPES.register("mankini_endermite", () -> register("mankini_endermite", EntityType.Builder.<MankiniEndermiteEntity>of(MankiniEndermiteEntity::new, MobCategory.MONSTER)
			.sized(0.4F, 0.3F)));
	public static final RegistryObject<EntityType<MankiniSpiderEntity>> MANKINI_SPIDER = ENTITY_TYPES.register("mankini_spider", () -> register("mankini_spider", EntityType.Builder.<MankiniSpiderEntity>of(MankiniSpiderEntity::new, MobCategory.MONSTER)
			.sized(1.4F, 0.9F)));
	public static final RegistryObject<EntityType<MankiniSkeletonEntity>> MANKINI_SKELETON = ENTITY_TYPES.register("mankini_skeleton", () -> register("mankini_skeleton", EntityType.Builder.<MankiniSkeletonEntity>of(MankiniSkeletonEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.99F)));
	public static final RegistryObject<EntityType<MankiniWitherEntity>> MANKINI_WITHER = ENTITY_TYPES.register("mankini_wither", () -> register("mankini_wither", EntityType.Builder.<MankiniWitherEntity>of(MankiniWitherEntity::new, MobCategory.MONSTER)
			.fireImmune()
			.sized(0.9F, 3.5F)));
	public static final RegistryObject<EntityType<MankiniWitherCapsuleEntity>> MANKINI_WITHER_PROJECTILE = ENTITY_TYPES.register("mankini_wither_projectile", () -> register("mankini_wither_projectile", EntityType.Builder.<MankiniWitherCapsuleEntity>of(MankiniWitherCapsuleEntity::new, MobCategory.MISC)
			.sized(0.3125F, 0.3125F)
			.setCustomClientFactory(MankiniWitherCapsuleEntity::new)));
	public static final RegistryObject<EntityType<MankiniEvokerEntity>> MANKINI_EVOKER = ENTITY_TYPES.register("mankini_evoker", () -> register("mankini_evoker", EntityType.Builder.<MankiniEvokerEntity>of(MankiniEvokerEntity::new, MobCategory.MONSTER)
			.sized(0.6F, 1.95F)));

	public static final RegistryObject<MobEffect> MANKINI_WITHER_EFFECT = MOB_EFFECTS.register("mankini_wither", () -> new MankiniWitherPotion());

	public static final RegistryObject<Item> DYEABLE_MANKINI = ITEMS.register("dyeable_mankini", () -> new ItemDyeableMankini((new Item.Properties())));
	public static final RegistryObject<Item> KAWAII_MANKINI = ITEMS.register("kawaii_mankini", () -> new ItemKawaiiMankini((new Item.Properties())));
	public static final RegistryObject<Item> AETHERIC_MANKINI = ITEMS.register("aetheric_mankini", () -> new ItemAAMT((new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_CANNON = ITEMS.register("mankini_cannon", () -> new ItemMankiniCannon((new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_CAPSULE_ITEM = ITEMS.register("mankini_capsule", () -> new ItemMankiniCapsule((new Item.Properties())));
	public static final RegistryObject<Item> BAT_MANKINI = ITEMS.register("mankini_bat", () -> new ItemBatMankini((new Item.Properties())));
	public static final RegistryObject<Item> WITHER_MANKINI = ITEMS.register("mankini_wither", () -> new ItemWitherKini((new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_HORSE_ARMOR = ITEMS.register("mankini_horse_armor", () -> new ItemMankiniHorseArmor((new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_CREEPER_SPAWN_EGG = ITEMS.register("mankini_creeper_spawn_egg", () -> new ForgeSpawnEggItem(ModRegistry.MANKINI_CREEPER, 894731, 0, (new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_ENDERMAN_SPAWN_EGG = ITEMS.register("mankini_enderman_spawn_egg", () -> new ForgeSpawnEggItem(ModRegistry.MANKINI_ENDERMAN, 1447446, 0, (new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_ENDERMITE_SPAWN_EGG = ITEMS.register("mankini_endermite_spawn_egg", () -> new ForgeSpawnEggItem(ModRegistry.MANKINI_ENDERMITE, 1447446, 7237230, (new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_SPIDER_SPAWN_EGG = ITEMS.register("mankini_spider_spawn_egg", () -> new ForgeSpawnEggItem(ModRegistry.MANKINI_SPIDER, 3419431, 11013646, (new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_SKELETON_SPAWN_EGG = ITEMS.register("mankini_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(ModRegistry.MANKINI_SKELETON, 12698049, 4802889, (new Item.Properties())));
	public static final RegistryObject<Item> MANKINI_EVOKER_SPAWN_EGG = ITEMS.register("mankini_evoker_spawn_egg", () -> new ForgeSpawnEggItem(ModRegistry.MANKINI_EVOKER, 9804699, 1973274, (new Item.Properties())));

	public static final RegistryObject<CreativeModeTab> MANKINI_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(ModRegistry.KAWAII_MANKINI.get()))
			.title(Component.translatable("itemGroup.mankini"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = ModRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, boolean sendVelocityUpdates) {
		return builder.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(sendVelocityUpdates).build(id);
	}

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		return register(id, builder, true);
	}
}
