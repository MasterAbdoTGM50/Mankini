package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniCannon;
import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.entity.projectiles.MankiniCapsuleEntity;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public abstract class AbstractMankiniSkeleton extends AbstractSkeleton {

	protected AbstractMankiniSkeleton(EntityType<? extends AbstractMankiniSkeleton> type, Level p_i48555_2_) {
		super(type, p_i48555_2_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(4, new EntityAIMankiniCannon<AbstractMankiniSkeleton>(this, 1.0D, 40, 15.0F));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new EntityAIMankiniTarget<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, WitherBoss.class, true));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.FOLLOW_RANGE, 16.0D);
	}

	/**
	 * Gives armor or weapon for entity based on given DifficultyInstance
	 */
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
		this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModRegistry.DYEABLE_MANKINI.get()));
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModRegistry.MANKINI_CANNON.get()));
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(level, difficultyIn, reason, spawnDataIn, dataTag);
		this.populateDefaultEquipmentSlots(random, difficultyIn);
		this.populateDefaultEquipmentEnchantments(random, difficultyIn);
		this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * difficultyIn.getSpecialMultiplier());
		if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);
			if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
				this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
			}
		}

		return spawnDataIn;
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		MankiniCapsuleEntity entityCapsule = this.getCapsule(distanceFactor);
		double d0 = target.getX() - this.getX();
		double d1 = target.getY(0.3333333333333333D) - entityCapsule.getY();
		double d2 = target.getZ() - this.getZ();
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		entityCapsule.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level().getDifficulty().getId() * 4));
		this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.level().addFreshEntity(entityCapsule);
	}

	protected MankiniCapsuleEntity getCapsule(float unusedFloat) {
		ItemStack stack = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());
		stack.setDamageValue(this.random.nextInt(stack.getMaxDamage()));
		return new MankiniCapsuleEntity(this.level(), this, stack, false);
	}
}