package matgm50.mankini.init;

import com.google.common.base.Preconditions;
import matgm50.mankini.Mankini;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWitherCapsule;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.entity.hostile.EntityMankiniEvoker;
import matgm50.mankini.entity.hostile.EntityMankiniSkeleton;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static final EntityType<EntityMankiniCapsule> MANKINI_CAPSULE = register("mankini_capsule", EntityType.Builder.<EntityMankiniCapsule>create(EntityMankiniCapsule::new, EntityClassification.MISC)
			.size(0.25F, 0.25F)
			.setTrackingRange(80).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.setCustomClientFactory(EntityMankiniCapsule::new));
	public static final EntityType<EntityMankiniCreeper> MANKINI_CREEPER = register("mankini_creeper", EntityType.Builder.<EntityMankiniCreeper>create(EntityMankiniCreeper::new, EntityClassification.MONSTER)
			.size(0.6F, 1.7F)
			.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final EntityType<EntityMankiniEnderman> MANKINI_ENDERMAN = register("mankini_enderman", EntityType.Builder.<EntityMankiniEnderman>create(EntityMankiniEnderman::new, EntityClassification.MONSTER)
			.size(0.6F, 2.9F)
			.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final EntityType<EntityMankiniEndermite> MANKINI_ENDERMITE = register("mankini_endermite", EntityType.Builder.<EntityMankiniEndermite>create(EntityMankiniEndermite::new, EntityClassification.MONSTER)
			.size(0.4F, 0.3F)
			.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final EntityType<EntityMankiniSpider> MANKINI_SPIDER = register("mankini_spider", EntityType.Builder.<EntityMankiniSpider>create(EntityMankiniSpider::new, EntityClassification.MONSTER)
			.size(1.4F, 0.9F)
			.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final EntityType<EntityMankiniSkeleton> MANKINI_SKELETON = register("mankini_skeleton", EntityType.Builder.<EntityMankiniSkeleton>create(EntityMankiniSkeleton::new, EntityClassification.MONSTER)
			.size(0.6F, 1.99F)
			.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final EntityType<EntityMankiniWither> MANKINI_WITHER = register("mankini_wither", EntityType.Builder.<EntityMankiniWither>create(EntityMankiniWither::new, EntityClassification.MONSTER)
			.immuneToFire()
			.size(0.9F, 3.5F)
			.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	public static final EntityType<EntityMankiniWitherCapsule> MANKINI_WITHER_PROJECTILE = register("mankini_wither_projectile", EntityType.Builder.<EntityMankiniWitherCapsule>create(EntityMankiniWitherCapsule::new, EntityClassification.MISC)
			.size(0.3125F, 0.3125F)
			.setTrackingRange(80).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.setCustomClientFactory(EntityMankiniWitherCapsule::new));
	public static final EntityType<EntityMankiniEvoker> MANKINI_EVOKER = register("mankini_evoker", EntityType.Builder.<EntityMankiniEvoker>create(EntityMankiniEvoker::new, EntityClassification.MONSTER)
			.size(0.6F, 1.95F)
			.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		EntityType<T> entityType = builder.build("");
		ResourceLocation name = new ResourceLocation(ModLib.MOD_ID, id);

		entityType.setRegistryName(name);

		return entityType;
	}

	public static void register(EntityType<?> entity, String name, RegistryEvent.Register<EntityType<?>> event) {
		Preconditions.checkNotNull(entity, "registryName");
		event.getRegistry().register(entity);
	}


	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
	{
		Mankini.logger.debug("Registering Entities");

		register(MANKINI_CAPSULE, "mankini_capsule", event);
		register(MANKINI_CREEPER, "mankini_creeper", event);
		register(MANKINI_ENDERMAN, "mankini_enderman", event);
		register(MANKINI_ENDERMITE, "mankini_endermite", event);
		register(MANKINI_SPIDER, "mankini_spider", event);
		register(MANKINI_SKELETON, "mankini_skeleton", event);
		register(MANKINI_WITHER, "mankini_wither", event);
		register(MANKINI_WITHER_PROJECTILE, "mankini_wither_capsule", event);
		register(MANKINI_EVOKER, "mankini_evoker", event);
	}
}
