package matgm50.mankini.crafting;

import matgm50.mankini.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by MasterAbdoTGM50 on 5/27/2014.
 */

public class ModRecipes {

    public static void init() {

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemDyeableMankini), "X X", "X X", " X ", 'X',  new ItemStack(Items.leather));
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemKawaiiMankini), "1 7", "2X6", "345", 'X',  new ItemStack(Items.leather),
                '1', new ItemStack(Items.dye, 1, 13), '2', new ItemStack(Items.dye, 1, 5), '3', new ItemStack(Items.dye, 1, 4), '4', new ItemStack(Items.dye, 1, 2),
                '5', new ItemStack(Items.dye, 1, 11), '6', new ItemStack(Items.dye, 1, 14), '7', new ItemStack(Items.dye, 1, 1));

      //  GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemAethericMankini), "X X", "DOD", " D ", 'X',  new ItemStack(Items.nether_star), 'D', new ItemStack(Blocks.diamond_block),
        //        'O', new ItemStack(ModItems.itemKawaiiMankini));

    }

}
