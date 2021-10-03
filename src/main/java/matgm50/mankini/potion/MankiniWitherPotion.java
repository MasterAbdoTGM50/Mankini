package matgm50.mankini.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.damagesource.DamageSource;

public class MankiniWitherPotion extends MobEffect {
    public MankiniWitherPotion() {
        super(MobEffectCategory.HARMFUL, 3484199);
//        setIconIndex(1, 2);
//        setEffectiveness(0.25D);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.hurt(new DamageSource("mankini_wither").bypassArmor(), 1.0F);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int i = 40 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }
}
