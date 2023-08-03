package net.zuiron.photosynthesis.integration.rei.fluidpress;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Arrow;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.zuiron.photosynthesis.Photosynthesis;
import net.zuiron.photosynthesis.block.ModBlocks;
import net.zuiron.photosynthesis.integration.rei.PhotosynthesisREI;
import net.zuiron.photosynthesis.util.FluidStack;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class FluidPressRecipeCategory implements DisplayCategory<FluidPressRecipeDisplay> {
    private static final Identifier GUI_TEXTURE = new Identifier(Photosynthesis.MOD_ID, "textures/gui/fluid_press_gui_rei.png");

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.FLUID_PRESS);
    }

    @Override
    public Text getTitle() {
        //return Photosynthesis.i18n("rei.cooking");
        return Text.literal("Fluid Press");
    }

    @Override
    public CategoryIdentifier<? extends FluidPressRecipeDisplay> getCategoryIdentifier() {
        return PhotosynthesisREI.FLUIDPRESS;
    }

    @Override
    public List<Widget> setupDisplay(FluidPressRecipeDisplay display, Rectangle bounds) {
        Point origin = bounds.getLocation();
        final List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));
        Rectangle bgBounds = PhotosynthesisREI.centeredIntoRecipeBase(origin, 122, 61);
        widgets.add(Widgets.createTexturedWidget(GUI_TEXTURE, bgBounds, 27, 4));


        List<EntryIngredient> ingredientEntries = display.getIngredientEntries();
        DefaultedList counts = display.getCounts();

        //output fluid
        FluidStack outputFluid = display.getOutputFluid();
        widgets.add(Widgets.createSlot(new Point(bgBounds.x + 75, bgBounds.y + 27))
                .entries(EntryIngredients.of(outputFluid.fluidVariant.getFluid(), outputFluid.amount)).markOutput());

        widgets.add(Widgets.createLabel(new Point(bgBounds.x + 92, bgBounds.y + 46), Text.literal(FluidStack.convertDropletsToMb(outputFluid.amount)+" mB"))
                .noShadow().rightAligned().color(Formatting.WHITE.getColorValue(), Formatting.GRAY.getColorValue()).shadow(true));


        //INPUTS
        if (ingredientEntries != null) {
            int[] posX = {30, 44, 62, 80};
            int[] posY = {27, 22, 22, 22};

            for (int i = 0; i < ingredientEntries.size(); i++) {
                //INPUT ITEMS TODO foreach Item item = ((ItemStack) ingredientEntries.get(i) - might be multiple? if tags.
                Item item = ((ItemStack) ingredientEntries.get(i).get(0).getValue()).getItem();
                widgets.add(Widgets.createSlot(new Point(bgBounds.x + posX[i], bgBounds.y + posY[i])).markInput().entries(List.of(EntryStacks.of(new ItemStack(item, (Integer) counts.get(i))))));
            }
        }



        int cookTime = display.getCookTime();
        int cookTimeSeconds = cookTime / 20;
        int cookTimeMinutes = cookTimeSeconds / 60;


        Arrow cookArrow = Widgets.createArrow(new Point(bgBounds.x + 49, bgBounds.y + 29))
                .animationDurationTicks(cookTime);
        widgets.add(cookArrow);
        widgets.add(Widgets.createLabel(new Point(
                                cookArrow.getBounds().x + cookArrow.getBounds().width / 2 - 5, cookArrow.getBounds().y - 5),
                        Text.literal(cookTimeSeconds + "s"))
                .noShadow().centered().tooltip(Text.literal(cookTimeSeconds + "seconds ("+cookTime+" ticks) \n" + cookTimeMinutes + "minutes"))
                .color(Formatting.DARK_GRAY.getColorValue(), Formatting.GRAY.getColorValue()));



        return widgets;
    }
}
