package net.zuiron.photosynthesis.integration.waila.components;

import mcp.mobius.waila.api.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.zuiron.photosynthesis.api.Seasons;
import net.zuiron.photosynthesis.util.getCustomVarsPassiveEntity;

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
            tooltip.addLine(Text.literal("Age: "+entity_age+" Day's"));



            int water = data.getInt("photosynthesis_water");
            int water_max = data.getInt("photosynthesis_water_max");
            double waterPercentage = ((double) water / water_max) * 100;
            String formattedPercentageWater = String.format("%.1f", waterPercentage);
            tooltip.addLine(Text.literal("Water: "+ formattedPercentageWater + "%" ));



            int grass = data.getInt("photosynthesis_grass");
            int grass_max = data.getInt("photosynthesis_grass_max");
            double grassPercentage = ((double) grass / grass_max) * 100;
            String formattedPercentageGrass = String.format("%.1f", grassPercentage);
            tooltip.addLine(Text.literal("Grass: "+ formattedPercentageGrass + "%" ));



            int hay = data.getInt("photosynthesis_hay");
            int hay_max = data.getInt("photosynthesis_hay_max");
            double hayPercentage = ((double) hay / hay_max) * 100;
            String formattedPercentageHay = String.format("%.1f", hayPercentage);
            tooltip.addLine(Text.literal("Hay: "+ formattedPercentageHay + "%" ));
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
    }
}
