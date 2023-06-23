package matgm50.mankini.data.server;

import matgm50.mankini.init.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class MankiniRecipeProvider extends RecipeProvider {
	public MankiniRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.AETHERIC_MANKINI.get())
				.pattern("BEB")
				.pattern("DKD")
				.pattern("CWO")
				.define('K', ModRegistry.BAT_MANKINI.get())
				.define('E', Items.ELYTRA)
				.define('W', ModRegistry.WITHER_MANKINI.get())
				.define('D', Tags.Items.STORAGE_BLOCKS_DIAMOND)
				.define('B', Items.DRAGON_BREATH)
				.define('C', Items.CLAY)
				.define('O', Tags.Items.ORES_EMERALD)
				.unlockedBy("has_elytra", has(Items.ELYTRA))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.DYEABLE_MANKINI.get())
				.pattern("X X")
				.pattern("X X")
				.pattern(" X ")
				.define('X', Tags.Items.LEATHER)
				.unlockedBy("has_leather", has(Tags.Items.LEATHER))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.KAWAII_MANKINI.get())
				.pattern("1 7")
				.pattern("2X6")
				.pattern("345")
				.define('1', Tags.Items.DYES_MAGENTA)
				.define('2', Tags.Items.DYES_PURPLE)
				.define('3', Tags.Items.DYES_BLUE)
				.define('4', Tags.Items.DYES_GREEN)
				.define('5', Tags.Items.DYES_YELLOW)
				.define('6', Tags.Items.DYES_ORANGE)
				.define('7', Tags.Items.DYES_RED)
				.define('X', Tags.Items.LEATHER)
				.unlockedBy("has_leather", has(Tags.Items.LEATHER))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.MANKINI_CANNON.get())
				.pattern(" R ")
				.pattern(" B ")
				.pattern("IID")
				.define('D', Items.DISPENSER)
				.define('R', Tags.Items.DYES_RED)
				.define('B', Tags.Items.DYES_BLUE)
				.define('I', Tags.Items.INGOTS_IRON)
				.unlockedBy("has_dispenser", has(Items.DISPENSER))
				.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
				.save(consumer);
	}
}
