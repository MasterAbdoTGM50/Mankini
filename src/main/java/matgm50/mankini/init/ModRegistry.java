package matgm50.mankini.init;

import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWitherCapsule;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.entity.hostile.EntityMankiniEvoker;
import matgm50.mankini.entity.hostile.EntityMankiniSkeleton;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.item.CustomSpawnEggItem;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModLib.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, ModLib.MOD_ID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ModLib.MOD_ID);

    public static final RegistryObject<EntityType<EntityMankiniCapsule>> MANKINI_CAPSULE = ENTITIES.register("mankini_capsule", () -> register("mankini_capsule", EntityType.Builder.<EntityMankiniCapsule>create(EntityMankiniCapsule::new, EntityClassification.MISC)
            .size(0.25F, 0.25F)
            .setCustomClientFactory(EntityMankiniCapsule::new)));
    public static final RegistryObject<EntityType<EntityMankiniCreeper>> MANKINI_CREEPER = ENTITIES.register("mankini_creeper", () -> register("mankini_creeper", EntityType.Builder.<EntityMankiniCreeper>create(EntityMankiniCreeper::new, EntityClassification.MONSTER)
            .size(0.6F, 1.7F)));
    public static final RegistryObject<EntityType<EntityMankiniEnderman>> MANKINI_ENDERMAN = ENTITIES.register("mankini_enderman", () -> register("mankini_enderman", EntityType.Builder.<EntityMankiniEnderman>create(EntityMankiniEnderman::new, EntityClassification.MONSTER)
            .size(0.6F, 2.9F)));
    public static final RegistryObject<EntityType<EntityMankiniEndermite>> MANKINI_ENDERMITE = ENTITIES.register("mankini_endermite", () -> register("mankini_endermite", EntityType.Builder.<EntityMankiniEndermite>create(EntityMankiniEndermite::new, EntityClassification.MONSTER)
            .size(0.4F, 0.3F)));
    public static final RegistryObject<EntityType<EntityMankiniSpider>> MANKINI_SPIDER = ENTITIES.register("mankini_spider", () -> register("mankini_spider", EntityType.Builder.<EntityMankiniSpider>create(EntityMankiniSpider::new, EntityClassification.MONSTER)
            .size(1.4F, 0.9F)));
    public static final RegistryObject<EntityType<EntityMankiniSkeleton>> MANKINI_SKELETON = ENTITIES.register("mankini_skeleton", () -> register("mankini_skeleton", EntityType.Builder.<EntityMankiniSkeleton>create(EntityMankiniSkeleton::new, EntityClassification.MONSTER)
            .size(0.6F, 1.99F)));
    public static final RegistryObject<EntityType<EntityMankiniWither>> MANKINI_WITHER = ENTITIES.register("mankini_wither", () -> register("mankini_wither", EntityType.Builder.<EntityMankiniWither>create(EntityMankiniWither::new, EntityClassification.MONSTER)
            .immuneToFire()
            .size(0.9F, 3.5F)));
    public static final RegistryObject<EntityType<EntityMankiniWitherCapsule>> MANKINI_WITHER_PROJECTILE = ENTITIES.register("mankini_wither_projectile", () -> register("mankini_wither_projectile", EntityType.Builder.<EntityMankiniWitherCapsule>create(EntityMankiniWitherCapsule::new, EntityClassification.MISC)
            .size(0.3125F, 0.3125F)
            .setCustomClientFactory(EntityMankiniWitherCapsule::new)));
    public static final RegistryObject<EntityType<EntityMankiniEvoker>> MANKINI_EVOKER = ENTITIES.register("mankini_evoker", () -> register("mankini_evoker", EntityType.Builder.<EntityMankiniEvoker>create(EntityMankiniEvoker::new, EntityClassification.MONSTER)
            .size(0.6F, 1.95F)));

    public static final RegistryObject<Effect> MANKINI_WITHER_EFFECT = EFFECTS.register("mankini_wither", () -> new MankiniWitherPotion());

    public static final RegistryObject<Item> DYEABLE_MANKINI = ITEMS.register("dyeable_mankini", () -> new ItemDyeableMankini(itemBuilder()));
    public static final RegistryObject<Item> KAWAII_MANKINI = ITEMS.register("kawaii_mankini", () -> new ItemKawaiiMankini(itemBuilder()));
    public static final RegistryObject<Item> AETHERIC_MANKINI = ITEMS.register("aetheric_mankini", () -> new ItemAAMT(itemBuilder()));
    public static final RegistryObject<Item> MANKINI_CANNON = ITEMS.register("mankini_cannon", () -> new ItemMankiniCannon(itemBuilder()));
    public static final RegistryObject<Item> MANKINI_CAPSULE_ITEM = ITEMS.register("mankini_capsule", () -> new ItemMankiniCapsule(itemBuilder()));
    public static final RegistryObject<Item> BAT_MANKINI = ITEMS.register("mankini_bat", () -> new ItemBatMankini(itemBuilder()));
    public static final RegistryObject<Item> WITHER_MANKINI = ITEMS.register("mankini_wither", () -> new ItemWitherKini(itemBuilder()));
    public static final RegistryObject<Item> MANKINI_HORSE_ARMOR = ITEMS.register("mankini_horse_armor", () -> new ItemMankiniHorseArmor(itemBuilder()));
    public static final RegistryObject<Item> MANKINI_CREEPER_SPAWN_EGG = ITEMS.register("mankini_creeper_spawn_egg", () -> new CustomSpawnEggItem(() -> ModRegistry.MANKINI_CREEPER.get(), 894731, 0, itemBuilder()));
    public static final RegistryObject<Item> MANKINI_ENDERMAN_SPAWN_EGG = ITEMS.register("mankini_enderman_spawn_egg", () -> new CustomSpawnEggItem(() -> ModRegistry.MANKINI_ENDERMAN.get(), 1447446, 0, itemBuilder()));
    public static final RegistryObject<Item> MANKINI_ENDERMITE_SPAWN_EGG = ITEMS.register("mankini_endermite_spawn_egg", () -> new CustomSpawnEggItem(() -> ModRegistry.MANKINI_ENDERMITE.get(), 1447446, 7237230, itemBuilder()));
    public static final RegistryObject<Item> MANKINI_SPIDER_SPAWN_EGG = ITEMS.register("mankini_spider_spawn_egg", () -> new CustomSpawnEggItem(() -> ModRegistry.MANKINI_SPIDER.get(), 3419431, 11013646, itemBuilder()));
    public static final RegistryObject<Item> MANKINI_SKELETON_SPAWN_EGG = ITEMS.register("mankini_skeleton_spawn_egg", () -> new CustomSpawnEggItem(() -> ModRegistry.MANKINI_SKELETON.get(), 12698049, 4802889, itemBuilder()));
    public static final RegistryObject<Item> MANKINI_EVOKER_SPAWN_EGG = ITEMS.register("mankini_evoker_spawn_egg", () -> new CustomSpawnEggItem(() -> ModRegistry.MANKINI_EVOKER.get(), 9804699, 1973274, itemBuilder()));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, boolean sendVelocityUpdates) {
        return builder.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(sendVelocityUpdates).build(id);
    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return register(id, builder, true);
    }

    private static Item.Properties itemBuilder() {
        return new Item.Properties().group(ModGroups.MANKINI_TAB);
    }
}
