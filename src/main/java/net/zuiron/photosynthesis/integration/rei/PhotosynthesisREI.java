package net.zuiron.photosynthesis.integration.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.zuiron.photosynthesis.Photosynthesis;
import net.zuiron.photosynthesis.block.ModBlocks;
import net.zuiron.photosynthesis.integration.rei.cutting_board.CuttingBoardRecipeCategory;
import net.zuiron.photosynthesis.integration.rei.cutting_board.CuttingBoardRecipeDisplay;
import net.zuiron.photosynthesis.integration.rei.skillet.SkilletRecipeCategory;
import net.zuiron.photosynthesis.integration.rei.skillet.SkilletRecipeDisplay;
import net.zuiron.photosynthesis.recipe.CuttingBoardRecipe;
import net.zuiron.photosynthesis.recipe.SkilletRecipe;
import net.zuiron.photosynthesis.screen.CuttingBoardScreen;
import net.zuiron.photosynthesis.screen.SkilletScreen;

@Environment(EnvType.CLIENT)
public class PhotosynthesisREI implements REIClientPlugin {
    public static final CategoryIdentifier<SkilletRecipeDisplay> SKILLET = CategoryIdentifier.of(Photosynthesis.MOD_ID, "skillet");
    public static final CategoryIdentifier<CuttingBoardRecipeDisplay> CUTTINGBOARD = CategoryIdentifier.of(Photosynthesis.MOD_ID, "cuttingboard");

    public static Rectangle centeredIntoRecipeBase(Point origin, int width, int height) {
        return centeredInto(new Rectangle(origin.x, origin.y, 150, 66), width, height);
    }

    public static Rectangle centeredInto(Rectangle origin, int width, int height) {
        return new Rectangle(origin.x + (origin.width - width) / 2, origin.y + (origin.height - height) / 2, width, height);
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(
                new SkilletRecipeCategory());
        registry.addWorkstations(SKILLET, EntryStacks.of(ModBlocks.SKILLET));

        registry.add(
                new CuttingBoardRecipeCategory());
        registry.addWorkstations(CUTTINGBOARD, EntryStacks.of(ModBlocks.CUTTINGBOARD));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(SkilletRecipe.class, SkilletRecipe.Type.INSTANCE, SkilletRecipeDisplay::new);

        registry.registerRecipeFiller(CuttingBoardRecipe.class, CuttingBoardRecipe.Type.INSTANCE, CuttingBoardRecipeDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(89, 25, 24, 17), SkilletScreen.class, SKILLET);

        registry.registerContainerClickArea(new Rectangle(99, 25, 24, 17), CuttingBoardScreen.class, CUTTINGBOARD);
    }
}