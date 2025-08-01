package matgm50.mankini.potion;

import matgm50.mankini.init.MankiniDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MankiniWitherPotion extends MobEffect {
	public MankiniWitherPotion() {
		super(MobEffectCategory.HARMFUL, 3484199);
//        setIconIndex(1, 2);
//        setEffectiveness(0.25D);
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		return entity.hurtServer(level, entity.damageSources().source(MankiniDamageTypes.MANKINI_WITHER), 1.0F);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		int i = 40 >> amplifier;
		if (i > 0) {
			return duration % i == 0;
		} else {
			return true;
		}
	}
}
