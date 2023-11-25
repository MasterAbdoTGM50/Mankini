package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class MankiniEndermiteEntity extends Endermite {

	public MankiniEndermiteEntity(EntityType<? extends MankiniEndermiteEntity> type, Level level) {
		super(type, level);
	}

	public MankiniEndermiteEntity(Level level) {
		super(ModRegistry.MANKINI_ENDERMITE.get(), level);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Endermite.createAttributes();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(2, new EntityAIMankiniTarget<>(this, Player.class, true));
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor level, MobSpawnType reason) {
		if (MankiniConfig.COMMON.MankiniEndermiteSpawn.get())
			return super.checkSpawnRules(level, reason);
		else
			return false;
	}
}
