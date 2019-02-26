package matgm50.mankini.init;

import com.google.common.base.Preconditions;
import matgm50.mankini.Mankini;
import matgm50.mankini.entity.hostile.EntityMankiniCreeper;
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

		//registerEntity(EntityMankiniWither.class, "Mankini Wither");
	}
}
