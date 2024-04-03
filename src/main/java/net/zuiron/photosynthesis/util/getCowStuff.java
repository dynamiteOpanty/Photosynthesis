package net.zuiron.photosynthesis.util;

public interface getCowStuff {
    int getMod_Milk();
    int getMod_Milk_Max();

    int getMod_Manure();
    int getMod_Manure_Max();

    int photosynthesis$getAvailBucketsMilk();

    float photosynthesis$getMilkProductivity(int mod_Water, int mod_Water_max, int mod_Grass, int mod_Grass_max, int mod_Straw, int mod_Straw_max, int mod_Hay, int mod_Hay_max, int mod_Food, int mod_Food_max);
}
