package matgm50.mankini.entity.hostile;

import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class MankiniEndermanEntity extends EnderMan {

	public MankiniEndermanEntity(EntityType<? extends MankiniEndermanEntity> type, Level level) {
		super(type, level);
	}

	public MankiniEndermanEntity(Level level) {
		super(ModRegistry.MANKINI_ENDERMAN.get(), level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return EnderMan.createAttributes();
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MankiniEndermiteEntity.class, true, false));
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType reason) {
		if (MankiniConfig.COMMON.MankiniEndermanSpawn.get())
			return super.checkSpawnRules(level, reason);
		else
			return false;
	}
}
