package matgm50.mankini.init;

import com.google.common.base.Preconditions;
import matgm50.mankini.Mankini;
import matgm50.mankini.entity.boss.EntityMankiniWither;
import matgm50.mankini.entity.boss.EntityMankiniWitherCapsule;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
import matgm50.mankini.entity.hostile.EntityMankiniEnderman;
import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.entity.hostile.EntityMankiniSkeleton;
import matgm50.mankini.entity.hostile.EntityMankiniSpider;
import matgm50.mankini.entity.projectiles.EntityMankiniCapsule;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(ModLib.MOD_ID)
public class ModEntities {
	public static final EntityType<EntityMankiniCapsule> MANKINI_CAPSULE = register("mankini_capsule", EntityType.Builder.create(EntityMankiniCapsule.class, EntityMankiniCapsule::new), 80, 1, true);
	public static final EntityType<EntityMankiniCreeper> MANKINI_CREEPER = register("mankini_creeper", EntityType.Builder.create(EntityMankiniCreeper.class, EntityMankiniCreeper::new), 80, 3, true);
	public static final EntityType<EntityMankiniEnderman> MANKINI_ENDERMAN = register("mankini_enderman", EntityType.Builder.create(EntityMankiniEnderman.class, EntityMankiniEnderman::new), 80, 3, true);
	public static final EntityType<EntityMankiniEndermite> MANKINI_ENDERMITE = register("mankini_endermite", EntityType.Builder.create(EntityMankiniEndermite.class, EntityMankiniEndermite::new), 80, 3, true);
	public static final EntityType<EntityMankiniSpider> MANKINI_SPIDER = register("mankini_spider", EntityType.Builder.create(EntityMankiniSpider.class, EntityMankiniSpider::new), 80, 3, true);
	public static final EntityType<EntityMankiniSkeleton> MANKINI_SKELETON = register("mankini_skeleton", EntityType.Builder.create(EntityMankiniSkeleton.class, EntityMankiniSkeleton::new), 80, 3, true);
	public static final EntityType<EntityMankiniWither> MANKINI_WITHER = register("mankini_wither", EntityType.Builder.create(EntityMankiniWither.class, EntityMankiniWither::new), 80, 3, true);
	public static final EntityType<EntityMankiniWitherCapsule> MANKINI_WITHER_PROJECTILE = register("mankini_wither_projectile", EntityType.Builder.create(EntityMankiniWitherCapsule.class, EntityMankiniWitherCapsule::new), 80, 3, true);

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		EntityType<T> entityType = builder.build("");
		ResourceLocation name = new ResourceLocation(ModLib.MOD_ID, id);

		entityType.setRegistryName(name);

		return entityType;
	}

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, int range, int updateFrequency, boolean velocityUpdates) {
		EntityType<T> entityType = builder.tracker(range, updateFrequency, velocityUpdates).build("");
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

		//registerEntity(EntityMankiniWither.class, "Mankini Wither");
	}
}
