package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class MankiniEnderman extends EnderMan {

	public MankiniEnderman(EntityType<? extends MankiniEnderman> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return EnderMan.createAttributes();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MankiniEndermite.class, true, false));
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason reason) {
		if (MankiniConfig.COMMON.MankiniEndermanSpawn.get())
			return super.checkSpawnRules(level, reason);
		else
			return false;
	}
}
