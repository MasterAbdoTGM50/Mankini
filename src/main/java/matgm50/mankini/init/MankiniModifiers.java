package matgm50.mankini.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.core.Registry;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MankiniModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ModLib.MOD_ID);

	public static final RegistryObject<Codec<AddRelativeSpawnBiomeModifier>> ADD_RELATIVE_MOB_SPAWNS = BIOME_MODIFIER_SERIALIZERS.register("add_relative_mob_spawns", () ->
			RecordCodecBuilder.create(builder -> builder.group(
					Registry.ENTITY_TYPE.byNameCodec().fieldOf("originalType").forGetter(AddRelativeSpawnBiomeModifier::originalType),
					Registry.ENTITY_TYPE.byNameCodec().fieldOf("newType").forGetter(AddRelativeSpawnBiomeModifier::newType),
					Codec.INT.fieldOf("relativeWeight").forGetter(AddRelativeSpawnBiomeModifier::relativeWeight)
			).apply(builder, AddRelativeSpawnBiomeModifier::new))
	);
}
