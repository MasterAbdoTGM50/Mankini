package matgm50.mankini.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class MankiniWitherPotion extends Effect {
    public MankiniWitherPotion() {
        super(EffectType.HARMFUL, 3484199);
//        setIconIndex(1, 2);
//        setEffectiveness(0.25D);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.attackEntityFrom(new DamageSource("mankini_wither").setDamageBypassesArmor(), 1.0F);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int i = 40 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }
}
