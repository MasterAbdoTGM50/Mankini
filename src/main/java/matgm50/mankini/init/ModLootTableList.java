package matgm50.mankini.init;

import matgm50.mankini.lib.ModLib;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class ModLootTableList {
    public static final ResourceLocation ENTITIES_MANKINI_CREEPER = LootTableList.register(new ResourceLocation(ModLib.MOD_ID, "entities/mankini_creeper"));
    public static final ResourceLocation ENTITIES_MANKINI_SKELETON = LootTableList.register(new ResourceLocation(ModLib.MOD_ID, "entities/mankini_skeleton"));
    public static final ResourceLocation ENTITIES_MANKINI_EVOKER = LootTableList.register(new ResourceLocation(ModLib.MOD_ID, "entities/mankini_evoker"));
}
