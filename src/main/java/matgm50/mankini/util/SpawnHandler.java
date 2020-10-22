package matgm50.mankini.util;

import matgm50.mankini.entity.hostile.EntityMankiniEndermite;
import matgm50.mankini.lib.ModLib;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnHandler {
    @SubscribeEvent
    public static void EntitySpawnEvent(LivingSpawnEvent event) {
        Entity entity = event.getEntity();
        World worldIn = entity.world;
        if(entity instanceof EndermiteEntity && !(entity instanceof EntityMankiniEndermite)) {
            if(((EndermiteEntity) entity).getRNG().nextBoolean()) {
                EntityMankiniEndermite mankiniMite = new EntityMankiniEndermite(worldIn);
                mankiniMite.setLocationAndAngles(entity.getPosX(), entity.getPosY(), entity.getPosZ(), entity.rotationYaw, 0.0F);
                event.setCanceled(true);
                worldIn.addEntity(mankiniMite);
            }
        }
    }
}
