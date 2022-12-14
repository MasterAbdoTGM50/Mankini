package matgm50.mankini.entity.hostile;

import matgm50.mankini.entity.ai.EntityAIMankiniTarget;
import matgm50.mankini.init.MankiniConfig;
import matgm50.mankini.init.ModRegistry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class MankiniCreeperEntity extends Creeper {

	public MankiniCreeperEntity(EntityType<? extends MankiniCreeperEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public MankiniCreeperEntity(Level worldIn) {
		super(ModRegistry.MANKINI_CREEPER.get(), worldIn);
	}

	@Override
	public EntityType<?> getType() {
		return ModRegistry.MANKINI_CREEPER.get();
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Creeper.createAttributes();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new SwellGoal(this));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new EntityAIMankiniTarget<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	/**
	 * Creates an explosion as determined by this creeper's power and explosion radius.
	 */
	@Override
	public void explodeCreeper() {
		if (this.getTarget() instanceof Player hitPlayer) {

			float f = this.isPowered() ? 2.0F : 1.0F;

			Boolean full = true;

			Inventory playerInv = hitPlayer.getInventory();

			ItemStack itemstack = hitPlayer.getInventory().armor.get(2);
			ItemStack creeperKini = new ItemStack(ModRegistry.DYEABLE_MANKINI.get());

			this.level.explode(this, this.getX(), this.getY(), this.getZ(), (float) 3 * f, Level.ExplosionInteraction.NONE);

			if (!this.level.isClientSide) {
				if (hitPlayer.getX() == (int) this.getX() || hitPlayer.getY() == (int) this.getY() || hitPlayer.getZ() == this.getZ()) {
					if (itemstack.isEmpty()) {
						playerInv.setItem(38, creeperKini);
						full = true;
					}

					if (full && itemstack != creeperKini) {
						if (MankiniConfig.COMMON.CreeperOverride.get()) {
							if (MankiniConfig.COMMON.EvilCreepers.get()) {
								playerInv.removeItemNoUpdate(38);
								playerInv.setItem(38, creeperKini);
								creeperKini.enchant(Enchantments.BINDING_CURSE, 1);
								creeperKini.enchant(Enchantments.VANISHING_CURSE, 1);
							} else {
								playerInv.removeItemNoUpdate(38);
								playerInv.setItem(38, creeperKini);
							}
						} else {
							if (MankiniConfig.COMMON.EvilCreepers.get()) {
								ItemStack oldArmour = itemstack.copy();
								playerInv.removeItemNoUpdate(38);
								playerInv.setItem(38, creeperKini);
								creeperKini.enchant(Enchantments.BINDING_CURSE, 1);
								creeperKini.enchant(Enchantments.VANISHING_CURSE, 1);
								if (hitPlayer.getInventory().getFreeSlot() == -1) {
									hitPlayer.spawnAtLocation(oldArmour, 0.5F);
								} else {
									playerInv.setItem(hitPlayer.getInventory().getFreeSlot(), oldArmour);
								}
							} else {
								ItemStack oldArmour = itemstack.copy();
								playerInv.removeItemNoUpdate(38);
								playerInv.setItem(38, creeperKini);
								if (hitPlayer.getInventory().getFreeSlot() == -1) {
									hitPlayer.spawnAtLocation(oldArmour, 0.5F);
								} else {
									playerInv.setItem(hitPlayer.getInventory().getFreeSlot(), oldArmour);
								}
							}
						}
					} else {
						hitPlayer.spawnAtLocation(creeperKini, 0.5F);
					}
				}
			}
		}
		this.discard();
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor worldIn, MobSpawnType spawnReasonIn) {
		if (MankiniConfig.COMMON.MankiniCreeperSpawn.get())
			return super.checkSpawnRules(worldIn, spawnReasonIn);
		else
			return false;
	}
}