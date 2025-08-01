package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class MankiniSkeleton extends AbstractMankiniSkeleton {

	public MankiniSkeleton(EntityType<? extends MankiniSkeleton> type, Level level) {
		super(type, level);
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
		if (MankiniConfig.COMMON.MankiniSkeletonSpawn.get())
			return super.checkSpawnRules(level, reason);
		else
			return false;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.SKELETON_STEP;
	}
}