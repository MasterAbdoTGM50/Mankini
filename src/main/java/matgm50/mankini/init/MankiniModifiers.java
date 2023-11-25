package matgm50.mankini.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class MankiniModifiers {
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ModLib.MOD_ID);

	public static final Supplier<Codec<AddRelativeSpawnBiomeModifier>> ADD_RELATIVE_MOB_SPAWNS = BIOME_MODIFIER_SERIALIZERS.register("add_relative_mob_spawns", () ->
			RecordCodecBuilder.create(builder -> builder.group(
					BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("originalType").forGetter(AddRelativeSpawnBiomeModifier::originalType),
					BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("newType").forGetter(AddRelativeSpawnBiomeModifier::newType),
					Codec.INT.fieldOf("relativeWeight").forGetter(AddRelativeSpawnBiomeModifier::relativeWeight)
			).apply(builder, AddRelativeSpawnBiomeModifier::new))
	);
}
