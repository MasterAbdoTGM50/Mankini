package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class MankiniSkeletonEntity extends AbstractMankiniSkeleton {

	public MankiniSkeletonEntity(EntityType<? extends MankiniSkeletonEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public MankiniSkeletonEntity(Level worldIn) {
		super(ModRegistry.MANKINI_SKELETON.get(), worldIn);
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType reason) {
		if (MankiniConfig.COMMON.MankiniCreeperSpawn.get())
			return super.checkSpawnRules(worldIn, reason);
		else
			return false;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.SKELETON_STEP;
	}
}