package matgm50.mankini.init;

import com.google.common.base.Preconditions;
import matgm50.mankini.lib.ModLib;
import matgm50.mankini.potion.MankiniWitherPotion;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(ModLib.MOD_ID)
public class ModEffects {
    public static Effect MANKINI_WITHER;

    public static ArrayList<Effect> POTIONS = new ArrayList<>();

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Effect> event)
    {
        IForgeRegistry<Effect> registry = event.getRegistry();

        MANKINI_WITHER = registerEffect(new MankiniWitherPotion(), "mankini_wither");

        registry.registerAll(POTIONS.toArray(new Effect[0]));
    }

    public static <T extends Effect> T registerEffect(T potion, String name)
    {
        POTIONS.add(potion);

        potion.setRegistryName(new ResourceLocation(ModLib.MOD_ID, name));
        Preconditions.checkNotNull(potion, "registryName");
        return potion;
    }
}
