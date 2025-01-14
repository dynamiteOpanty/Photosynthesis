package net.zuiron.photosynthesis.integration.waila.components;

import mcp.mobius.waila.api.*;
import net.minecraft.entity.passive.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.zuiron.photosynthesis.api.Seasons;
import net.zuiron.photosynthesis.util.*;

public enum EntityDataStatsComponent implements IEntityComponentProvider, IDataProvider<PassiveEntity> {
    INSTANCE;

    private static long calculateEntityAgeFromLivingTicks(long livingTicks) {
        // Define the constants
        long TICKS_PER_DAY = 24000;
        long DAYS_PER_YEAR = Seasons.getDaysPerYear(); //365 real life.

        // Calculate the age in days
        long totalDays = livingTicks / TICKS_PER_DAY;

        // Calculate the years and remaining days
        long years = totalDays / DAYS_PER_YEAR;
        long remainingDays = totalDays % DAYS_PER_YEAR;

        // Print the result or use it as needed
        //System.out.println("Years: " + years + ", Days: " + remainingDays);

        return totalDays;
    }

    @Override
    public void appendBody(ITooltip tooltip, IEntityAccessor accessor, IPluginConfig config) {
        if (accessor.getEntity() instanceof AnimalEntity living) {
            NbtCompound data = accessor.getData().raw();
            long living_ticks = data.getLong("photosynthesis_livingticks");

            long entity_age = calculateEntityAgeFromLivingTicks(living_ticks);
            //tooltip.addLine(Text.literal("Age: "+entity_age+" Day's"));


            int water = data.getInt("photosynthesis_water");
            int water_max = data.getInt("photosynthesis_water_max");
            double waterPercentage = ((double) water / water_max) * 100;
            String formattedPercentageWater = String.format("%.1f", waterPercentage);
            //tooltip.addLine(Text.literal("Water: "+ formattedPercentageWater + "%" ));


            int grass = data.getInt("photosynthesis_grass");
            int grass_max = data.getInt("photosynthesis_grass_max");
            double grassPercentage = ((double) grass / grass_max) * 100;
            String formattedPercentageGrass = String.format("%.1f", grassPercentage);
            //tooltip.addLine(Text.literal("Grass: "+ formattedPercentageGrass + "%" ));


            int hay = data.getInt("photosynthesis_hay");
            int hay_max = data.getInt("photosynthesis_hay_max");
            double hayPercentage = ((double) hay / hay_max) * 100;
            String formattedPercentageHay = String.format("%.1f", hayPercentage);
            //tooltip.addLine(Text.literal("Hay: "+ formattedPercentageHay + "%" ));


            int straw = data.getInt("photosynthesis_straw");
            int straw_max = data.getInt("photosynthesis_straw_max");
            double strawPercentage = ((double) straw / straw_max) * 100;
            String formattedPercentageStraw = String.format("%.1f", strawPercentage);
            //tooltip.addLine(Text.literal("Straw: "+ formattedPercentageStraw + "%" ));


            int food = data.getInt("photosynthesis_food");
            int food_max = data.getInt("photosynthesis_food_max");
            double foodPercentage = ((double) food / food_max) * 100;
            String formattedPercentageFood = String.format("%.1f", foodPercentage);
            //tooltip.addLine(Text.literal("Food: "+ formattedPercentageFood + "%" ));


            //milk, productivity
            int milk = data.getInt("photosynthesis_milk");
            int milk_max = data.getInt("photosynthesis_milk_max");
            int milk_buckets = data.getInt("photosynthesis_milk_buckets");
            float milk_productivity = data.getFloat("photosynthesis_milk_productivity");
            double milkPercentage = ((double) milk / milk_max) * 100;
            String formattedPercentageMilk = String.format("%.1f", milkPercentage);


            if (accessor.getEntity() instanceof CowEntity) {
            tooltip.addLine(Text.literal("Age: " + entity_age + " Day's - " + "Water: " + formattedPercentageWater + "% - " + "Grass: "+ formattedPercentageGrass + "%"));
            tooltip.addLine(Text.literal("Hay: " + formattedPercentageHay + "% - " + "Straw: "+ formattedPercentageStraw + "% - " + "Food: "+ formattedPercentageFood + "%" ));
                tooltip.addLine(Text.literal("Cow Milk: " + formattedPercentageMilk + "% - " + milk_buckets + " Buckets - Productivity: " + (int) milk_productivity + "%"));
            }
            if (accessor.getEntity() instanceof GoatEntity) {
                tooltip.addLine(Text.literal("Age: " + entity_age + " Day's"));
                tooltip.addLine(Text.literal("Water: " + formattedPercentageWater + "% - " + "Grass: "+ formattedPercentageGrass + "% - " + "Food: "+ formattedPercentageFood + "%" ));
                tooltip.addLine(Text.literal("Goat Milk: " + formattedPercentageMilk + "% - " + milk_buckets + " Buckets - Productivity: " + (int) milk_productivity + "%"));
            }
            if (accessor.getEntity() instanceof ChickenEntity) {
                int egg = data.getInt("photosynthesis_egg");
                int egg_max = data.getInt("photosynthesis_egg_max");

                float egg_productivity = data.getFloat("photosynthesis_egg_productivity");
                double eggPercentage = ((double) egg / egg_max) * 100;
                String formattedPercentageEgg = String.format("%.1f", eggPercentage);

                tooltip.addLine(Text.literal("Age: " + entity_age + " Day's"));
                tooltip.addLine(Text.literal("Water: " + formattedPercentageWater + "% - " + "Straw: "+ formattedPercentageStraw + "% - " + "Food: "+ formattedPercentageFood + "%" ));
                tooltip.addLine(Text.literal("Egg: " + formattedPercentageEgg + "% - Productivity: " + (int) egg_productivity + "%"));
            }
            if (accessor.getEntity() instanceof HorseEntity) {
                tooltip.addLine(Text.literal("Age: " + entity_age + " Day's - " + "Water: " + formattedPercentageWater + "%"));
                tooltip.addLine(Text.literal("Hay: " + formattedPercentageHay + "% - " + "Straw: "+ formattedPercentageStraw + "% - " + "Food: "+ formattedPercentageFood + "%" ));
            }
            if (accessor.getEntity() instanceof PigEntity) {
                tooltip.addLine(Text.literal("Age: " + entity_age + " Day's"));
                tooltip.addLine(Text.literal("Water: " + formattedPercentageWater + "% - " + "Straw: "+ formattedPercentageStraw + "% - " + "Food: "+ formattedPercentageFood + "%" ));
            }
            if (accessor.getEntity() instanceof SheepEntity) {
                int wool = data.getInt("photosynthesis_wool");
                int wool_max = data.getInt("photosynthesis_wool_max");

                float wool_productivity = data.getFloat("photosynthesis_wool_productivity");
                double woolPercentage = ((double) wool / wool_max) * 100;
                String formattedPercentageWool = String.format("%.1f", woolPercentage);

                tooltip.addLine(Text.literal("Age: " + entity_age + " Day's - " + "Water: " + formattedPercentageWater + "% - " + "Grass: "+ formattedPercentageGrass + "%"));
                tooltip.addLine(Text.literal("Wool: " + formattedPercentageWool + "% - Productivity: " + (int) wool_productivity + "%"));
            }
        }
    }

    @Override
    public void appendData(IDataWriter data, IServerAccessor<PassiveEntity> accessor, IPluginConfig config) {
        var mob = accessor.getTarget();
        long mod_LivingTicks = ((getCustomVarsPassiveEntity) mob).getMod_LivingTicks();
        data.raw().putLong("photosynthesis_livingticks", mod_LivingTicks);

        int mod_Water = ((getCustomVarsPassiveEntity) mob).getMod_Water();
        data.raw().putInt("photosynthesis_water", mod_Water);
        int mod_Water_max = ((getCustomVarsPassiveEntity) mob).getMod_Water_max();
        data.raw().putInt("photosynthesis_water_max", mod_Water_max);

        int mod_grass = ((getCustomVarsPassiveEntity) mob).getMod_Grass();
        data.raw().putInt("photosynthesis_grass", mod_grass);
        int mod_grass_max = ((getCustomVarsPassiveEntity) mob).getMod_Grass_max();
        data.raw().putInt("photosynthesis_grass_max", mod_grass_max);

        int mod_hay = ((getCustomVarsPassiveEntity) mob).getMod_Hay();
        data.raw().putInt("photosynthesis_hay", mod_hay);
        int mod_hay_max = ((getCustomVarsPassiveEntity) mob).getMod_Hay_max();
        data.raw().putInt("photosynthesis_hay_max", mod_hay_max);

        int mod_straw = ((getCustomVarsPassiveEntity) mob).getMod_Straw();
        data.raw().putInt("photosynthesis_straw", mod_straw);
        int mod_straw_max = ((getCustomVarsPassiveEntity) mob).getMod_Straw_max();
        data.raw().putInt("photosynthesis_straw_max", mod_straw_max);

        int mod_food = ((getCustomVarsPassiveEntity) mob).getMod_Food();
        data.raw().putInt("photosynthesis_food", mod_food);
        int mod_food_max = ((getCustomVarsPassiveEntity) mob).getMod_Food_max();
        data.raw().putInt("photosynthesis_food_max", mod_food_max);

        if (accessor.getTarget() instanceof GoatEntity) {
            int mod_milk = ((getGoatStuff) mob).getMod_Milk();
            data.raw().putInt("photosynthesis_milk", mod_milk);
            int mod_milk_max = ((getGoatStuff) mob).getMod_Milk_Max();
            data.raw().putInt("photosynthesis_milk_max", mod_milk_max);
            int mod_milk_buckets = ((getGoatStuff) mob).photosynthesis$getAvailBucketsMilk();
            data.raw().putInt("photosynthesis_milk_buckets", mod_milk_buckets);
            float mod_milk_productivity = ((getGoatStuff) mob).photosynthesis$getMilkProductivity(mod_Water, mod_Water_max, mod_grass, mod_grass_max, mod_food, mod_food_max);
            data.raw().putFloat("photosynthesis_milk_productivity", mod_milk_productivity);
        }

        if (accessor.getTarget() instanceof CowEntity) {
            int mod_milk = ((getCowStuff) mob).getMod_Milk();
            data.raw().putInt("photosynthesis_milk", mod_milk);
            int mod_milk_max = ((getCowStuff) mob).getMod_Milk_Max();
            data.raw().putInt("photosynthesis_milk_max", mod_milk_max);
            int mod_milk_buckets = ((getCowStuff) mob).photosynthesis$getAvailBucketsMilk();
            data.raw().putInt("photosynthesis_milk_buckets", mod_milk_buckets);
            float mod_milk_productivity = ((getCowStuff) mob).photosynthesis$getMilkProductivity(mod_Water, mod_Water_max, mod_grass, mod_grass_max, mod_straw, mod_straw_max, mod_hay, mod_hay_max, mod_food, mod_food_max);
            data.raw().putFloat("photosynthesis_milk_productivity", mod_milk_productivity);

            int mod_manure = ((getCowStuff) mob).getMod_Manure();
            data.raw().putInt("photosynthesis_manure", mod_manure);
            int mod_manure_max = ((getCowStuff) mob).getMod_Manure_Max();
            data.raw().putInt("photosynthesis_manure_max", mod_manure_max);
        }

        if (accessor.getTarget() instanceof ChickenEntity) {
            int mod_egg = ((getChickenStuff) mob).getMod_Egg();
            data.raw().putInt("photosynthesis_egg", mod_egg);
            int mod_egg_max = ((getChickenStuff) mob).getMod_Egg_Max();
            data.raw().putInt("photosynthesis_egg_max", mod_egg_max);

            float mod_egg_productivity = ((getChickenStuff) mob).photosynthesis$getChickenProductivity(mod_Water, mod_Water_max, mod_straw, mod_straw_max, mod_food, mod_food_max);
            data.raw().putFloat("photosynthesis_egg_productivity", mod_egg_productivity);
        }

        if (accessor.getTarget() instanceof SheepEntity) {
            int mod_wool = ((getSheepStuff) mob).getMod_Wool();
            data.raw().putInt("photosynthesis_wool", mod_wool);
            int mod_wool_max = ((getSheepStuff) mob).getMod_Wool_Max();
            data.raw().putInt("photosynthesis_wool_max", mod_wool_max);

            float mod_wool_productivity = ((getSheepStuff) mob).photosynthesis$getWoolProductivity(mod_Water, mod_Water_max, mod_grass, mod_grass_max);
            data.raw().putFloat("photosynthesis_wool_productivity", mod_wool_productivity);
        }
    }
}
