package net.zuiron.photosynthesis.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.CropBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.zuiron.photosynthesis.Photosynthesis;
import net.zuiron.photosynthesis.block.ModBlocks;
import net.zuiron.photosynthesis.mixin.ItemAccessor;

public class ModItems {

    // BERRIES ---------------------------------------------------------------------------------------------------------
    public static final Item BLUEBERRIES = registerBerryItem("blueberries", ModBlocks.BLUEBERRY_BUSH,
            new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS));

    public static final Item BLACKBERRIES = registerBerryItem("blackberries", ModBlocks.BLACKBERRY_BUSH,
            new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS));

    public static final Item RASPBERRIES = registerBerryItem("raspberries", ModBlocks.RASPBERRY_BUSH,
            new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS));

    public static final Item CLOUDBERRIES = registerBerryItem("cloudberries", ModBlocks.CLOUDBERRY_BUSH,
            new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS));

    public static final Item WILD_STRAWBERRIES = registerBerryItem("wild_strawberries", ModBlocks.WILD_STRAWBERRY_BUSH,
            new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS));



    // CROP PRODUCE ----------------------------------------------------------------------------------------------------
    public static final Item TOMATO = registerItem("tomato",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item BASIL = registerItem("basil",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item OREGANO = registerItem("oregano",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item STRAWBERRY = registerItem("strawberry",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item OAT = registerItem("oat",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item JALAPENO = registerItem("jalapeno",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CHILI = registerItem("chili",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CUCUMBER = registerItem("cucumber",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item ONION = registerItem("onion",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item LEEK = registerItem("leek",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CELERY = registerItem("celery",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item PEAS = registerItem("peas",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SWEET_POTATO = registerItem("sweet_potato",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item ASPARAGUS = registerItem("asparagus",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SCALLION = registerItem("scallion",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item GARLIC = registerItem("garlic",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CHIVE = registerItem("chive",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item BROCCOLI = registerItem("broccoli",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CAULIFLOWER = registerItem("cauliflower",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CORN = registerItem("corn",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item BLACK_PEPPER = registerItem("black_pepper",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CABBAGE = registerItem("cabbage",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item RED_BELLPEPPER = registerItem("red_bellpepper",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item GREEN_BELLPEPPER = registerItem("green_bellpepper",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item YELLOW_BELLPEPPER = registerItem("yellow_bellpepper",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item TURNIP = registerItem("turnip",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item RUTABAGA = registerItem("rutabaga",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CANOLA = registerItem("canola",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item BARLEY = registerItem("barley",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item COTTON = registerItem("cotton",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SUGARBEET = registerItem("sugarbeet",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item RICE = registerItem("rice",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SOYBEAN = registerItem("soybean",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SPINACH = registerItem("spinach",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item ARROWROOT = registerItem("arrowroot",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item ARTICHOKE = registerItem("artichoke",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item BRUSSELS_SPROUTS = registerItem("brussels_sprouts",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item CASSAVA = registerItem("cassava",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item EGGPLANT = registerItem("eggplant",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SUNFLOWER = registerItem("sunflower",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item JICAMA = registerItem("jicama",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item KALE = registerItem("kale",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item KOHLRABI = registerItem("kohlrabi",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item LETTUCE = registerItem("lettuce",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item OKRA = registerItem("okra",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item PARSNIP = registerItem("parsnip",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item RADISH = registerItem("radish",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item RHUBARB = registerItem("rhubarb",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));





    public static final Item MILLET = registerItem("millet",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item RYE = registerItem("rye",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SQUASH = registerItem("squash",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item ZUCCHINI = registerItem("zucchini",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item COFFEA = registerItem("coffea",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item GRAPE = registerItem("grape",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));




    public static final Item CAMELLIA_SINENSIS = registerItem("camellia_sinensis",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item NICOTIANA_RUSTICA = registerItem("nicotiana_rustica",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item PAPAVER_SOMNIFERUM = registerItem("papaver_somniferum",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item ERYTHROXYLUM_COCA = registerItem("erythroxylum_coca",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));





    public static final Item SATIVA_COLUMBIAN = registerItem("sativa_columbian",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SATIVA_SOUR_DIESEL = registerItem("sativa_sour_diesel",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SATIVA_NEPALESE = registerItem("sativa_nepalese",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SATIVA_THAI = registerItem("sativa_thai",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SATIVA_SUPER_SILVER_HAZE = registerItem("sativa_super_silver_haze",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item SATIVA_OAXACAN = registerItem("sativa_oaxacan",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));






    public static final Item INDICA_ACAPULCO_GOLD = registerItem("indica_acapulco_gold",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item INDICA_PURPLE_KUSH = registerItem("indica_purple_kush",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item INDICA_AFGHAN_KUSH = registerItem("indica_afghan_kush",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item INDICA_BUBBA_KUSH = registerItem("indica_bubba_kush",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item INDICA_LA_CONFIDENTIAL = registerItem("indica_la_confidential",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));

    public static final Item INDICA_NORTHERN_LIGHTS = registerItem("indica_northern_lights",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));





    public static final Item HYBRID_DURBAN = registerItem("hybrid_durban",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item HYBRID_HINDU_KUSH = registerItem("hybrid_hindu_kush",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item HYBRID_BLUE_DREAM = registerItem("hybrid_blue_dream",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item HYBRID_MAUI = registerItem("hybrid_maui",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item HYBRID_GOLDEN_GOAT = registerItem("hybrid_golden_goat",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item HYBRID_WHITE_WIDOW = registerItem("hybrid_white_widow",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item HYBRID_PINEAPPLE_EXPRESS = registerItem("hybrid_pineapple_express",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));



    public static final Item RUDERALIS_SKUNK = registerItem("ruderalis_skunk",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item RUDERALIS_DO_SI_DOS = registerItem("ruderalis_do_si_dos",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item RUDERALIS_MEMBRANA_HIPER_AUTOFLOWERING = registerItem("ruderalis_membrana_hiper_autoflowering",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));
    public static final Item RUDERALIS_AUTO_MAZAR = registerItem("ruderalis_auto_mazar",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build())
                    .group(ModItemGroup.PHOTOSYNTHESIS)));



    // CROP SEEDS ----------------------------------------------------------------------------------------------------
    public static final Item TOMATO_SEEDS = registerSeedItem("tomato_seeds", ModBlocks.TOMATO_CROP);
    public static final Item BASIL_SEEDS = registerSeedItem("basil_seeds", ModBlocks.BASIL_CROP);
    public static final Item OREGANO_SEEDS = registerSeedItem("oregano_seeds", ModBlocks.OREGANO_CROP);
    public static final Item STRAWBERRY_SEEDS = registerSeedItem("strawberry_seeds", ModBlocks.STRAWBERRY_CROP);
    public static final Item OAT_SEEDS = registerSeedItem("oat_seeds", ModBlocks.OAT_CROP);
    public static final Item JALAPENO_SEEDS = registerSeedItem("jalapeno_seeds", ModBlocks.JALAPENO_CROP);
    public static final Item CHILI_SEEDS = registerSeedItem("chili_seeds", ModBlocks.CHILI_CROP);
    public static final Item CUCUMBER_SEEDS = registerSeedItem("cucumber_seeds", ModBlocks.CUCUMBER_CROP);
    public static final Item ONION_SEEDS = registerSeedItem("onion_seeds", ModBlocks.ONION_CROP);
    public static final Item LEEK_SEEDS = registerSeedItem("leek_seeds", ModBlocks.LEEK_CROP);
    public static final Item CELERY_SEEDS = registerSeedItem("celery_seeds", ModBlocks.CELERY_CROP);
    public static final Item PEAS_SEEDS = registerSeedItem("peas_seeds", ModBlocks.PEAS_CROP);
    public static final Item SWEET_POTATO_SEEDS = registerSeedItem("sweet_potato_seeds", ModBlocks.SWEET_POTATO_CROP);
    public static final Item ASPARAGUS_SEEDS = registerSeedItem("asparagus_seeds", ModBlocks.ASPARAGUS_CROP);
    public static final Item SCALLION_SEEDS = registerSeedItem("scallion_seeds", ModBlocks.SCALLION_CROP);
    public static final Item GARLIC_SEEDS = registerSeedItem("garlic_seeds", ModBlocks.GARLIC_CROP);
    public static final Item CHIVE_SEEDS = registerSeedItem("chive_seeds", ModBlocks.CHIVE_CROP);
    public static final Item BROCCOLI_SEEDS = registerSeedItem("broccoli_seeds", ModBlocks.BROCCOLI_CROP);
    public static final Item CAULIFLOWER_SEEDS = registerSeedItem("cauliflower_seeds", ModBlocks.CAULIFLOWER_CROP);
    public static final Item CORN_SEEDS = registerSeedItem("corn_seeds", ModBlocks.CORN_CROP);
    public static final Item BLACK_PEPPER_SEEDS = registerSeedItem("black_pepper_seeds", ModBlocks.BLACK_PEPPER_CROP);
    public static final Item CABBAGE_SEEDS = registerSeedItem("cabbage_seeds", ModBlocks.CABBAGE_CROP);
    public static final Item BELLPEPPER_SEEDS = registerSeedItem("bellpepper_seeds", ModBlocks.BELLPEPPER_CROP);
    public static final Item TURNIP_SEEDS = registerSeedItem("turnip_seeds", ModBlocks.TURNIP_CROP);
    public static final Item RUTABAGA_SEEDS = registerSeedItem("rutabaga_seeds", ModBlocks.RUTABAGA_CROP);
    public static final Item CANOLA_SEEDS = registerSeedItem("canola_seeds", ModBlocks.CANOLA_CROP);
    public static final Item BARLEY_SEEDS = registerSeedItem("barley_seeds", ModBlocks.BARLEY_CROP);
    public static final Item COTTON_SEEDS = registerSeedItem("cotton_seeds", ModBlocks.COTTON_CROP);
    public static final Item SUGARBEET_SEEDS = registerSeedItem("sugarbeet_seeds", ModBlocks.SUGARBEET_CROP);
    public static final Item RICE_SEEDS = registerSeedItem("rice_seeds", ModBlocks.RICE_CROP);
    public static final Item SOYBEAN_SEEDS = registerSeedItem("soybean_seeds", ModBlocks.SOYBEAN_CROP);
    public static final Item SPINACH_SEEDS = registerSeedItem("spinach_seeds", ModBlocks.SPINACH_CROP);
    public static final Item ARROWROOT_SEEDS = registerSeedItem("arrowroot_seeds", ModBlocks.ARROWROOT_CROP);
    public static final Item ARTICHOKE_SEEDS = registerSeedItem("artichoke_seeds", ModBlocks.ARTICHOKE_CROP);
    public static final Item BRUSSELS_SPROUTS_SEEDS = registerSeedItem("brussels_sprouts_seeds", ModBlocks.BRUSSELS_SPROUTS_CROP);
    public static final Item CASSAVA_SEEDS = registerSeedItem("cassava_seeds", ModBlocks.CASSAVA_CROP);
    public static final Item EGGPLANT_SEEDS = registerSeedItem("eggplant_seeds", ModBlocks.EGGPLANT_CROP);
    public static final Item SUNFLOWER_SEEDS = registerSeedItem("sunflower_seeds", ModBlocks.SUNFLOWER_CROP);
    public static final Item JICAMA_SEEDS = registerSeedItem("jicama_seeds", ModBlocks.JICAMA_CROP);
    public static final Item KALE_SEEDS = registerSeedItem("kale_seeds", ModBlocks.KALE_CROP);
    public static final Item KOHLRABI_SEEDS = registerSeedItem("kohlrabi_seeds", ModBlocks.KOHLRABI_CROP);
    public static final Item LETTUCE_SEEDS = registerSeedItem("lettuce_seeds", ModBlocks.LETTUCE_CROP);
    public static final Item OKRA_SEEDS = registerSeedItem("okra_seeds", ModBlocks.OKRA_CROP);
    public static final Item PARSNIP_SEEDS = registerSeedItem("parsnip_seeds", ModBlocks.PARSNIP_CROP);
    public static final Item RADISH_SEEDS = registerSeedItem("radish_seeds", ModBlocks.RADISH_CROP);
    public static final Item RHUBARB_SEEDS = registerSeedItem("rhubarb_seeds", ModBlocks.RHUBARB_CROP);



    public static final Item MILLET_SEEDS = registerSeedItem("millet_seeds", ModBlocks.MILLET_CROP);
    public static final Item RYE_SEEDS = registerSeedItem("rye_seeds", ModBlocks.RYE_CROP);
    public static final Item SQUASH_SEEDS = registerSeedItem("squash_seeds", ModBlocks.SQUASH_CROP);
    public static final Item ZUCCHINI_SEEDS = registerSeedItem("zucchini_seeds", ModBlocks.ZUCCHINI_CROP);
    public static final Item COFFEA_SEEDS = registerSeedItem("coffea_seeds", ModBlocks.COFFEA_CROP);
    public static final Item GRAPE_SEEDS = registerSeedItem("grape_seeds", ModBlocks.GRAPE_CROP);

    public static final Item CAMELLIA_SINENSIS_SEEDS = registerSeedItem("camellia_sinensis_seeds", ModBlocks.CAMELLIA_SINENSIS_CROP);
    public static final Item NICOTIANA_RUSTICA_SEEDS = registerSeedItem("nicotiana_rustica_seeds", ModBlocks.NICOTIANA_RUSTICA_CROP);
    public static final Item PAPAVER_SOMNIFERUM_SEEDS = registerSeedItem("papaver_somniferum_seeds", ModBlocks.PAPAVER_SOMNIFERUM_CROP);
    public static final Item ERYTHROXYLUM_COCA_SEEDS = registerSeedItem("erythroxylum_coca_seeds", ModBlocks.ERYTHROXYLUM_COCA_CROP);

    public static final Item SATIVA_COLUMBIAN_SEEDS = registerSeedItem("sativa_columbian_seeds", ModBlocks.SATIVA_COLUMBIAN_CROP);
    public static final Item SATIVA_SOUR_DIESEL_SEEDS = registerSeedItem("sativa_sour_diesel_seeds", ModBlocks.SATIVA_SOUR_DIESEL_CROP);
    public static final Item SATIVA_NEPALESE_SEEDS = registerSeedItem("sativa_nepalese_seeds", ModBlocks.SATIVA_NEPALESE_CROP);
    public static final Item SATIVA_THAI_SEEDS = registerSeedItem("sativa_thai_seeds", ModBlocks.SATIVA_THAI_CROP);
    public static final Item SATIVA_SUPER_SILVER_HAZE_SEEDS = registerSeedItem("sativa_super_silver_haze_seeds", ModBlocks.SATIVA_SUPER_SILVER_HAZE_CROP);
    public static final Item SATIVA_OAXACAN_SEEDS = registerSeedItem("sativa_oaxacan_seeds", ModBlocks.SATIVA_OAXACAN_CROP);

    public static final Item INDICA_ACAPULCO_GOLD_SEEDS = registerSeedItem("indica_acapulco_gold_seeds", ModBlocks.INDICA_ACAPULCO_GOLD_CROP);
    public static final Item INDICA_PURPLE_KUSH_SEEDS = registerSeedItem("indica_purple_kush_seeds", ModBlocks.INDICA_PURPLE_KUSH_CROP);
    public static final Item INDICA_AFGHAN_KUSH_SEEDS = registerSeedItem("indica_afghan_kush_seeds", ModBlocks.INDICA_AFGHAN_KUSH_CROP);
    public static final Item INDICA_BUBBA_KUSH_SEEDS = registerSeedItem("indica_bubba_kush_seeds", ModBlocks.INDICA_BUBBA_KUSH_CROP);
    public static final Item INDICA_LA_CONFIDENTIAL_SEEDS = registerSeedItem("indica_la_confidential_seeds", ModBlocks.INDICA_LA_CONFIDENTIAL_CROP);
    public static final Item INDICA_NORTHERN_LIGHTS_SEEDS = registerSeedItem("indica_northern_lights_seeds", ModBlocks.INDICA_NORTHERN_LIGHTS_CROP);

    public static final Item HYBRID_DURBAN_SEEDS = registerSeedItem("hybrid_durban_seeds", ModBlocks.HYBRID_DURBAN_CROP);
    public static final Item HYBRID_HINDU_KUSH_SEEDS = registerSeedItem("hybrid_hindu_kush_seeds", ModBlocks.HYBRID_HINDU_KUSH_CROP);
    public static final Item HYBRID_BLUE_DREAM_SEEDS = registerSeedItem("hybrid_blue_dream_seeds", ModBlocks.HYBRID_BLUE_DREAM_CROP);
    public static final Item HYBRID_MAUI_SEEDS = registerSeedItem("hybrid_maui_seeds", ModBlocks.HYBRID_MAUI_CROP);
    public static final Item HYBRID_GOLDEN_GOAT_SEEDS = registerSeedItem("hybrid_golden_goat_seeds", ModBlocks.HYBRID_GOLDEN_GOAT_CROP);
    public static final Item HYBRID_WHITE_WIDOW_SEEDS = registerSeedItem("hybrid_white_widow_seeds", ModBlocks.HYBRID_WHITE_WIDOW_CROP);
    public static final Item HYBRID_PINEAPPLE_EXPRESS_SEEDS = registerSeedItem("hybrid_pineapple_express_seeds", ModBlocks.HYBRID_PINEAPPLE_EXPRESS_CROP);

    public static final Item RUDERALIS_SKUNK_SEEDS = registerSeedItem("ruderalis_skunk_seeds", ModBlocks.RUDERALIS_SKUNK_CROP);
    public static final Item RUDERALIS_DO_SI_DOS_SEEDS = registerSeedItem("ruderalis_do_si_dos_seeds", ModBlocks.RUDERALIS_DO_SI_DOS_CROP);
    public static final Item RUDERALIS_MEMBRANA_HIPER_AUTOFLOWERING_SEEDS = registerSeedItem("ruderalis_membrana_hiper_autoflowering_seeds", ModBlocks.RUDERALIS_MEMBRANA_HIPER_AUTOFLOWERING_CROP);
    public static final Item RUDERALIS_AUTO_MAZAR_SEEDS = registerSeedItem("ruderalis_auto_mazar_seeds", ModBlocks.RUDERALIS_AUTO_MAZAR_CROP);


    // END OF ModItems -------------------------------------------------------------------------------------------------
    private static Item registerBerryItem(String name, SweetBerryBushBlock alias, FabricItemSettings settings) {
        return Registry.register(Registry.ITEM, new Identifier(Photosynthesis.MOD_ID, name),
                new AliasedBlockItem(alias, settings));
    }

    private static Item registerBlockItem(String name, BlockItem item) {
        return Registry.register(Registry.ITEM, new Identifier(Photosynthesis.MOD_ID, name), item);
    }

    private static Item registerSeedItem(String name, CropBlock alias) {
        return Registry.register(Registry.ITEM, new Identifier(Photosynthesis.MOD_ID, name),
                new AliasedBlockItem(alias, new FabricItemSettings().group(ModItemGroup.PHOTOSYNTHESIS)));
    }

    private static Item registerRemainderItem(String name, Item item) {
        Item remainderItem = registerItem(name, item);
        ((ItemAccessor) remainderItem).setRecipeRemainder(remainderItem);

        return remainderItem;
    }

    private static Item registerItem(String name, Item item){
        Photosynthesis.LOGGER.info("Registering item with name: " + name);
        return Registry.register(Registry.ITEM, new Identifier(Photosynthesis.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registered Mod Items for " + Photosynthesis.MOD_ID);
    }
}
