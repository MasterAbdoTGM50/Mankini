package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.Random;

public class MankiniSpiderEntity extends Spider {

	public MankiniSpiderEntity(EntityType<? extends MankiniSpiderEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public MankiniSpiderEntity(Level worldIn) {
		super(ModRegistry.MANKINI_SPIDER.get(), worldIn);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(4, new MankiniSpiderEntity.AttackGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new MankiniSpiderEntity.TargetGoal<>(this, Player.class));
		this.targetSelector.addGoal(3, new MankiniSpiderEntity.TargetGoal<>(this, IronGolem.class));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Spider.createAttributes();
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(serverLevelAccessor, difficultyIn, reason, spawnDataIn, dataTag);
		ItemStack creeperKini = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

		if (random.nextInt(100) == 0) {
			Mob entitySkeleton = new Skeleton(EntityType.SKELETON, serverLevelAccessor.getLevel());

			if (random.nextInt(20) < 5) {
				entitySkeleton = new MankiniSkeletonEntity(serverLevelAccessor.getLevel());
			}

			entitySkeleton.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
			ForgeEventFactory.onFinalizeSpawn(entitySkeleton, serverLevelAccessor, difficultyIn, reason, (SpawnGroupData) null, (CompoundTag) null);
			entitySkeleton.setItemSlot(EquipmentSlot.CHEST, creeperKini);
			serverLevelAccessor.addFreshEntity(entitySkeleton);
			entitySkeleton.startRiding(this);
		} else if (random.nextInt(100) < 10) {
			MankiniCreeperEntity mankiniCreeper = new MankiniCreeperEntity(serverLevelAccessor.getLevel());
			mankiniCreeper.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
			ForgeEventFactory.onFinalizeSpawn(mankiniCreeper, serverLevelAccessor, difficultyIn, reason, (SpawnGroupData) null, (CompoundTag) null);
			mankiniCreeper.setItemSlot(EquipmentSlot.CHEST, creeperKini);
			serverLevelAccessor.addFreshEntity(mankiniCreeper);
			mankiniCreeper.startRiding(this);
		}

		if (spawnDataIn == null) {
			spawnDataIn = new Spider.SpiderEffectsGroupData();
			if (serverLevelAccessor.getDifficulty() == Difficulty.HARD && random.nextFloat() < 0.1F * difficultyIn.getSpecialMultiplier()) {
				((MankiniSpiderEntity.SpiderEffectsGroupData) spawnDataIn).setRandomEffect(random);
			}
		}

		if (spawnDataIn instanceof Spider.SpiderEffectsGroupData) {
			MobEffect potion = ((Spider.SpiderEffectsGroupData) spawnDataIn).effect;
			if (potion != null) {
				this.addEffect(new MobEffectInstance(potion, Integer.MAX_VALUE));
			}
		}

		return spawnDataIn;
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType reason) {
		if (MankiniConfig.COMMON.MankiniSpiderSpawn.get())
			return super.checkSpawnRules(worldIn, reason);
		else
			return false;
	}

	static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(Spider spider) {
			super(spider, 1.0D, true);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean canUse() {
			return super.canUse() && !this.mob.isVehicle();
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean canContinueToUse() {
			float f = this.mob.getLightLevelDependentMagicValue();
			if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
				this.mob.setTarget((LivingEntity) null);
				return false;
			} else {
				return super.canContinueToUse();
			}
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getBbWidth());
		}
	}

	public static class GroupData implements SpawnGroupData {
		public MobEffect effect;

		public void setRandomEffect(Random rand) {
			int i = rand.nextInt(5);
			if (i <= 1) {
				this.effect = MobEffects.MOVEMENT_SPEED;
			} else if (i == 2) {
				this.effect = MobEffects.DAMAGE_BOOST;
			} else if (i == 3) {
				this.effect = MobEffects.REGENERATION;
			} else if (i <= 4) {
				this.effect = MobEffects.INVISIBILITY;
			}

		}
	}

	static class TargetGoal<T extends LivingEntity> extends EntityAIMankiniTarget<T> {
		public TargetGoal(Spider spider, Class<T> classTarget) {
			super(spider, classTarget, true);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean canUse() {
			float f = this.mob.getLightLevelDependentMagicValue();
			return !(f >= 0.5F) && super.canUse();
		}
	}
}
