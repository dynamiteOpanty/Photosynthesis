package net.zuiron.photosynthesis.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.zuiron.photosynthesis.Photosynthesis;
import net.zuiron.photosynthesis.util.FluidStack;

public class MilkSeperatorRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final FluidStack output;
    private final FluidStack output2;
    private final FluidStack fluidInput;
    private final DefaultedList<Ingredient> recipeItems;

    private final DefaultedList<Ingredient> recipeOutputBuckets;

    private final int cookingTime;

    //private final DefaultedList counts;


    //public MilkSeperatorRecipe(Identifier id, FluidStack output, FluidStack output2, DefaultedList<Ingredient> recipeItems, int cookingTime, DefaultedList counts, FluidStack fluidInput) {
    public MilkSeperatorRecipe(Identifier id, FluidStack output, FluidStack output2, DefaultedList<Ingredient> recipeItems, int cookingTime, FluidStack fluidInput, DefaultedList<Ingredient> recipeOutputBuckets) {
        this.id = id;
        this.output = output;
        this.output2 = output2;
        this.recipeItems = recipeItems;
        this.cookingTime = cookingTime;
        //this.counts = counts;
        this.fluidInput = fluidInput;
        this.recipeOutputBuckets = recipeOutputBuckets;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }
        //Photosynthesis.LOGGER.info(inventory.getStack(0).getItem().getDefaultStack()+" - == - "+fluidInput.fluidVariant.getFluid().getBucketItem().getDefaultStack());
        if(inventory.getStack(0).getItem() == fluidInput.fluidVariant.getFluid().getBucketItem()) {
            //Photosynthesis.LOGGER.info("true");
            return true;
        } else {
            //Photosynthesis.LOGGER.info("false");
            return false;
        }
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        //return new ItemStack(output.fluidVariant.getFluid().getBucketItem());
        if (output != null && output.fluidVariant != null && output.fluidVariant.getFluid() != null) {
            return new ItemStack(output.fluidVariant.getFluid().getBucketItem());
        } else {
            // Return a default ItemStack or handle the situation as appropriate for your context.
            return ItemStack.EMPTY; // Example: Returning an empty ItemStack.
        }
    }

    /*public ItemStack getOutputStack() {
        return new ItemStack(output.fluidVariant.getFluid().getBucketItem());
    }*/

    public ItemStack getOutputStack() {
        return new ItemStack(output.fluidVariant.getFluid().getBucketItem());
    }

    public DefaultedList<Ingredient> getOutputsBuckets() {
        //return recipeOutputBuckets;
        if (recipeOutputBuckets == null) {
            return DefaultedList.ofSize(1, Ingredient.EMPTY);
        } else {
            return recipeOutputBuckets;
        }
    }

    public FluidStack getOutputFluid() {
        return output;
    }

    public FluidStack getOutputFluid2() {
        return output2;
    }

    public int getCookTime() {
        return cookingTime;
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    //public DefaultedList getCounts() { return counts; }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        //return recipeItems;
        if (recipeItems == null) {
            return DefaultedList.ofSize(1, Ingredient.EMPTY);
        } else {
            return recipeItems;
        }
        //return null;
    } //IMPORTANT FOR REI

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MilkSeperatorRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "milkseperator";
    }

    public static class Serializer implements RecipeSerializer<MilkSeperatorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "milkseperator";
        // this is the name given in the json file

        @Override
        public MilkSeperatorRecipe read(Identifier id, JsonObject json) {

            //FLUID output -- DONE
            JsonObject outputobj = JsonHelper.getObject(json, "output");
            int fluidamount = JsonHelper.getInt(outputobj, "amount", 81000);

            String fluidstring = JsonHelper.getString(outputobj, "fluid");
            Fluid fluid = (Fluid) Registries.FLUID.getOrEmpty(new Identifier(fluidstring)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown fluid '" + fluidstring + "'");
            });
            FluidStack fluidOutput = new FluidStack(FluidVariant.of(fluid), fluidamount);
            //Photosynthesis.LOGGER.info("output fluid: " + fluidOutput.fluidVariant.getFluid() + ", amount: " + fluidOutput.amount);
            //----------------------------------------------------------------------------------------------------------


            //FLUID output2 -- DONE
            JsonObject outputobj2 = JsonHelper.getObject(json, "output2");
            int fluidamount2 = JsonHelper.getInt(outputobj2, "amount", 81000);

            String fluidstring2 = JsonHelper.getString(outputobj2, "fluid");
            Fluid fluid2 = (Fluid) Registries.FLUID.getOrEmpty(new Identifier(fluidstring2)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown fluid '" + fluidstring2 + "'");
            });
            FluidStack fluidOutput2 = new FluidStack(FluidVariant.of(fluid2), fluidamount2);
            //Photosynthesis.LOGGER.info("output fluid: " + fluidOutput2.fluidVariant.getFluid() + ", amount: " + fluidOutput2.amount);
            //----------------------------------------------------------------------------------------------------------



            //ITEM input -- DONE
            //ITEM input -- DONE
            JsonObject ingredients = JsonHelper.getObject(json, "fluidinput");
            int fluidamount3 = JsonHelper.getInt(ingredients, "amount", 81000);
            String fluidstringinput = JsonHelper.getString(ingredients, "fluid");
            Fluid fluid3 = (Fluid) Registries.FLUID.getOrEmpty(new Identifier(fluidstringinput)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown fluid '" + fluidstringinput + "'");
            });
            FluidStack fluidinput = new FluidStack(FluidVariant.of(fluid3), fluidamount3);

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);
            inputs.set(0, Ingredient.ofStacks(fluidinput.fluidVariant.getFluid().getBucketItem().getDefaultStack()));

            /*JsonArray ingredients = JsonHelper.getArray(json, "fluidinput");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(4, Ingredient.EMPTY);
            DefaultedList<Integer> counts = DefaultedList.ofSize(4, 0);

            for (int i = 0; i < ingredients.size(); i++) {
                if (i >= inputs.size()) {
                    inputs.add(Ingredient.EMPTY);
                    counts.add(0);
                }
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
                //counts.set(i, JsonHelper.getInt(ingredients.get(i).getAsJsonObject(),"count"));
                //Photosynthesis.LOGGER.info("Ingredient: " + inputs.get(i).toJson() + ", count: " + counts.get(i));
            }*/
            //----------------------------------------------------------------------------------------------------------


            //FLUID input -- DONE
            JsonObject inputobj = JsonHelper.getObject(json, "fluidinput");
            int inputfluidamount = JsonHelper.getInt(inputobj, "amount", 81000);

            String inputfluidstring = JsonHelper.getString(inputobj, "fluid");
            Fluid inputfluid = (Fluid) Registries.FLUID.getOrEmpty(new Identifier(inputfluidstring)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown fluid '" + inputfluidstring + "'");
            });
            FluidStack fluidInput = new FluidStack(FluidVariant.of(inputfluid), inputfluidamount);
            //Photosynthesis.LOGGER.info("input fluid: " + fluidInput.fluidVariant.getFluid() + ", amount: " + fluidInput.amount);
            //----------------------------------------------------------------------------------------------------------



            //outputs bucketlist.
            DefaultedList<Ingredient> outputs_buckets = DefaultedList.ofSize(2, Ingredient.EMPTY);
            outputs_buckets.set(0, Ingredient.ofStacks(fluidOutput.fluidVariant.getFluid().getBucketItem().getDefaultStack()));
            outputs_buckets.set(1, Ingredient.ofStacks(fluidOutput2.fluidVariant.getFluid().getBucketItem().getDefaultStack()));

            /*
            until its done.
             */
            //DefaultedList<Ingredient> inputsS = DefaultedList.ofSize(5, Ingredient.EMPTY);
            //ItemStack output = new ItemStack(Items.STICK);

            int CookTime = JsonHelper.getInt(json, "cookingtime");
            //return new MilkSeperatorRecipe(id, fluidOutput, fluidOutput2, inputs, CookTime, counts, fluidInput);
            return new MilkSeperatorRecipe(id, fluidOutput, fluidOutput2, inputs, CookTime, fluidInput, outputs_buckets);
        }

        @Override
        public MilkSeperatorRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            DefaultedList<Ingredient> outputBuckets = DefaultedList.ofSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            FluidVariant fluidInput = FluidVariant.fromPacket(buf);
            FluidVariant fluidOutput = FluidVariant.fromPacket(buf);
            FluidVariant fluidOutput2 = FluidVariant.fromPacket(buf);

            long amount1 = buf.readLong();
            long amount2 = buf.readLong();
            long amount3 = buf.readLong();

            FluidStack fluidInputStack = new FluidStack(FluidVariant.of(fluidInput.getFluid()), amount1);
            FluidStack fluidOutputStack = new FluidStack(FluidVariant.of(fluidOutput.getFluid()), amount2);
            FluidStack fluidOutputStack2 = new FluidStack(FluidVariant.of(fluidOutput2.getFluid()), amount3);

            outputBuckets.set(0, Ingredient.ofItems(fluidOutput.getFluid().getBucketItem()));
            outputBuckets.set(1, Ingredient.ofItems(fluidOutput2.getFluid().getBucketItem()));

            int cookingTime = buf.readInt();

            return new MilkSeperatorRecipe(id, fluidOutputStack, fluidOutputStack2, inputs, cookingTime, fluidInputStack, outputBuckets);
        }

        @Override
        public void write(PacketByteBuf buf, MilkSeperatorRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            recipe.getFluidInput().getFluidVariant().toPacket(buf);
            recipe.getOutputFluid().getFluidVariant().toPacket(buf);
            recipe.getOutputFluid2().getFluidVariant().toPacket(buf);

            buf.writeLong(recipe.getFluidInput().amount);
            buf.writeLong(recipe.getOutputFluid().amount);
            buf.writeLong(recipe.getOutputFluid2().amount);

            buf.writeInt(recipe.getCookTime());
        }
    }
}
