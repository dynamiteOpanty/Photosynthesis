package net.zuiron.photosynthesis.block.entity;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.zuiron.photosynthesis.Photosynthesis;
import net.zuiron.photosynthesis.block.ModBlocks;
import net.zuiron.photosynthesis.block.custom.CropSticksBlock;
import net.zuiron.photosynthesis.block.custom.SeasonsCalendarBlock;
import net.zuiron.photosynthesis.recipe.CookingPotRecipe;
import net.zuiron.photosynthesis.recipe.CropSticksRecipe;
import net.zuiron.photosynthesis.state.property.ModProperties;

import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import static net.zuiron.photosynthesis.util.ModUtil.checkSuccess;

public class CropSticksBlockEntity extends BlockEntity {
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 100; //5sec
    private int selected_1 = -1;
    private int selected_2 = -1;
    public CropSticksBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CROPSTICKS, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return CropSticksBlockEntity.this.progress;
                    case 1: return CropSticksBlockEntity.this.maxProgress;
                    case 2: return CropSticksBlockEntity.this.selected_1;
                    case 3: return CropSticksBlockEntity.this.selected_2;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CropSticksBlockEntity.this.progress = value; break;
                    case 1: CropSticksBlockEntity.this.maxProgress = value; break;
                    case 2: CropSticksBlockEntity.this.selected_1 = value; break;
                    case 3: CropSticksBlockEntity.this.selected_2 = value; break;
                }
            }

            public int size() {
                return 4;
            }
        };
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("cropsticks.progress", progress);
        nbt.putInt("cropsticks.cookingTime", maxProgress);
        nbt.putInt("cropsticks.selected_1", selected_1);
        nbt.putInt("cropsticks.selected_2", selected_2);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        progress = nbt.getInt("cropsticks.progress");
        maxProgress = nbt.getInt("cropsticks.cookingTime");
        selected_1 = nbt.getInt("cropsticks.selected_1");
        selected_2 = nbt.getInt("cropsticks.selected_2");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void resetSelection() {
        this.selected_1 = -1;
        this.selected_2 = -1;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, CropSticksBlockEntity entity) {
        if (world.isClient()) {
            return;
        }
        if(hasRecipe(entity)) {
            entity.progress++;
            //Photosynthesis.LOGGER.info("found match in recipes! Working...");
            //Photosynthesis.LOGGER.info("progress: "+entity.progress+"/"+entity.maxProgress);

            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            entity.resetSelection();
        }
    }

    private static void craftItem(CropSticksBlockEntity entity) {
        //Photosynthesis.LOGGER.info("crafting item!");

        SimpleInventory inventory = new SimpleInventory(4);

        ItemStack seed_n = entity.world.getBlockState(entity.pos.north()).getBlock().asItem().getDefaultStack();    //selected - 0
        ItemStack seed_s = entity.world.getBlockState(entity.pos.south()).getBlock().asItem().getDefaultStack();    //selected - 1
        ItemStack seed_e = entity.world.getBlockState(entity.pos.east()).getBlock().asItem().getDefaultStack();     //selected - 2
        ItemStack seed_w = entity.world.getBlockState(entity.pos.west()).getBlock().asItem().getDefaultStack();     //selected - 3
        ItemStack[] seeds = {seed_n, seed_s, seed_e, seed_w};

        inventory.setStack(0, seeds[entity.selected_1]);
        inventory.setStack(1, seeds[entity.selected_2]);
        inventory.setStack(2, entity.world.getBlockState(entity.pos.down()).getBlock().asItem().getDefaultStack());

        Optional<CropSticksRecipe> recipe = entity.getWorld().getRecipeManager()
                .getFirstMatch(CropSticksRecipe.Type.INSTANCE, inventory, entity.getWorld());

        //IF no recipe is found, check with modifier block
        if(recipe.isEmpty()) {
            inventory.setStack(3, entity.world.getBlockState(entity.pos.down(2)).getBlock().asItem().getDefaultStack());
            recipe = entity.getWorld().getRecipeManager()
                    .getFirstMatch(CropSticksRecipe.Type.INSTANCE, inventory, entity.getWorld());
        }

        ItemStack output = recipe.get().getOutputStack().getItem().getDefaultStack();

        BlockState state;
        if(((BlockItem) output.getItem()).getBlock() instanceof PlantBlock)
        {
            //get plantblockstate from itemstack
            PlantBlock block = ((PlantBlock) ((BlockItem) output.getItem()).getBlock());
            state = block.getDefaultState();
        } else {
            Block block = ((BlockItem) output.getItem()).getBlock();
            state = block.getDefaultState();
        }

        //IF cropsticks contains pesticide and fertilizer, apply from cropsticks.
        if(state.contains(ModProperties.MOD_FERTILIZED)) {
            state = state.with(ModProperties.MOD_FERTILIZED, entity.world.getBlockState(entity.pos).get(ModProperties.MOD_FERTILIZED));
        }
        if(state.contains(ModProperties.MOD_PESTICIDED)) {
            state = state.with(ModProperties.MOD_PESTICIDED, entity.world.getBlockState(entity.pos).get(ModProperties.MOD_PESTICIDED));
        }

        //check if success based on recipe % chance of success. roll dice. if FAIL, if we are in water, seagrass, otherwise grass.
        float chance = recipe.get().getChancePercentage();

        //increase chance if pesticide or fertilizer is applied.
        int fertilizer_amount = entity.world.getBlockState(entity.pos).get(ModProperties.MOD_FERTILIZED);
        int pesticide_amount = entity.world.getBlockState(entity.pos).get(ModProperties.MOD_PESTICIDED);
        int total_applications = fertilizer_amount + pesticide_amount;
        chance += (10.0f * total_applications);

        boolean isSuccess = checkSuccess(chance);

        if(isSuccess) {
            //replace cropsticks with result.
            entity.world.setBlockState(entity.pos, state);
            Photosynthesis.LOGGER.info("CropSticks SUCCESS! output should be: "+output+", chance was: "+chance+", @POS: "+entity.pos);
        } else { //chance roll FAILED. spawn "weeds"
            //spawn seaweed or grass based on what its placed on.
            if(entity.world.getBlockState(entity.pos).get(CropSticksBlock.WATERLOGGED)) {
                PlantBlock block_water_weed = ((PlantBlock) ((BlockItem) Items.SEAGRASS).getBlock());
                entity.world.setBlockState(entity.pos, block_water_weed.getDefaultState());
                Photosynthesis.LOGGER.info("CropSticks FAILED! output could have been: "+output+", chance was: "+chance+", @POS: "+entity.pos);
            } else {
                PlantBlock block_land_weed = ((PlantBlock) ((BlockItem) Items.GRASS).getBlock());
                entity.world.setBlockState(entity.pos, block_land_weed.getDefaultState());
                Photosynthesis.LOGGER.info("CropSticks FAILED! output could have been: "+output+", chance was: "+chance+", @POS: "+entity.pos);
            }
        }

        //reset. not needed...
        //entity.resetProgress();
        //entity.resetSelection();
    }

    private static boolean hasRecipe(CropSticksBlockEntity entity) {
        int checkSize = 4;
        SimpleInventory inventory = new SimpleInventory(checkSize);
        for (int i = 0; i < checkSize; i++) {
            Random random = new Random();

            ItemStack seed_n = entity.world.getBlockState(entity.pos.north()).getBlock().asItem().getDefaultStack();    //selected - 0
            ItemStack seed_s = entity.world.getBlockState(entity.pos.south()).getBlock().asItem().getDefaultStack();    //selected - 1
            ItemStack seed_e = entity.world.getBlockState(entity.pos.east()).getBlock().asItem().getDefaultStack();     //selected - 2
            ItemStack seed_w = entity.world.getBlockState(entity.pos.west()).getBlock().asItem().getDefaultStack();     //selected - 3

            //if its crop, make sure its age max otherwise set to air.
            if(!seed_n.isEmpty()) {
                BlockState state_n = entity.world.getBlockState(entity.pos.north());
                if(state_n.contains(CropBlock.AGE)) {
                    if(state_n.get(CropBlock.AGE) < 7 && !state_n.isOf(ModBlocks.RICE_CROP)) {
                        seed_n = Blocks.AIR.asItem().getDefaultStack();
                    }
                }
            }
            if(!seed_s.isEmpty()) {
                BlockState state_s = entity.world.getBlockState(entity.pos.south());
                if(state_s.contains(CropBlock.AGE)) {
                    if(state_s.get(CropBlock.AGE) < 7 && !state_s.isOf(ModBlocks.RICE_CROP)) {
                        seed_s = Blocks.AIR.asItem().getDefaultStack();
                    }
                }
            }
            if(!seed_e.isEmpty()) {
                BlockState state_e = entity.world.getBlockState(entity.pos.east());
                if(state_e.contains(CropBlock.AGE)) {
                    if(state_e.get(CropBlock.AGE) < 7 && !state_e.isOf(ModBlocks.RICE_CROP)) {
                        seed_e = Blocks.AIR.asItem().getDefaultStack();
                    }
                }
            }
            if(!seed_w.isEmpty()) {
                BlockState state_w = entity.world.getBlockState(entity.pos.west());
                if(state_w.contains(CropBlock.AGE)) {
                    if(state_w.get(CropBlock.AGE) < 7 && !state_w.isOf(ModBlocks.RICE_CROP)) {
                        seed_w = Blocks.AIR.asItem().getDefaultStack();
                    }
                }
            }

            //if all is empty. return false.
            if(seed_n.isEmpty() && seed_s.isEmpty() && seed_e.isEmpty() && seed_w.isEmpty()) {
                return false;
            }

            ItemStack[] seeds = {seed_n, seed_s, seed_e, seed_w};

            //double check if something has changed.
            if(entity.selected_1 != -1) {
                ItemStack selected_seed = seeds[entity.selected_1];
                if(selected_seed.isEmpty()) {
                    entity.selected_1 = -1;
                    entity.resetProgress();
                }
            }
            if(entity.selected_2 != -1) {
                ItemStack selected_seed = seeds[entity.selected_2];
                if(selected_seed.isEmpty()) {
                    entity.selected_2 = -1;
                    entity.resetProgress();
                }
            }

            //random selection - if not selected...
            if(entity.selected_1 == -1 || entity.selected_2 == -1) {
                int randomIndex = random.nextInt(seeds.length);

                if (i == 2) {
                    inventory.setStack(i, entity.world.getBlockState(entity.pos.down()).getBlock().asItem().getDefaultStack());
                }
                else {
                    ItemStack rando_seed = seeds[randomIndex];

                    //save selection.
                    if(!rando_seed.isEmpty()) {
                        if(i == 0) {
                            entity.selected_1 = randomIndex;
                        }
                        if (i == 1) {
                            entity.selected_2 = randomIndex;
                        }
                    }

                    //if empty... loop until we fill both slots.
                    while (rando_seed.isEmpty()) {
                        int newRandom = random.nextInt(seeds.length);

                        if(i == 0) {
                            entity.selected_1 = newRandom;
                        }
                        if(i == 1) {
                            entity.selected_2 = newRandom;
                        }

                        rando_seed = seeds[newRandom];
                    }

                    inventory.setStack(i, rando_seed);
                }
            } else {
                //Photosynthesis.LOGGER.info(entity.selected_1+" , "+entity.selected_2);
                if(i == 0) {
                    inventory.setStack(i, seeds[entity.selected_1]);
                } else if (i == 1) {
                    inventory.setStack(i, seeds[entity.selected_2]);
                } else if (i == 2) {
                    inventory.setStack(i, entity.world.getBlockState(entity.pos.down()).getBlock().asItem().getDefaultStack());
                } else { // i = 3
                    inventory.setStack(i, entity.world.getBlockState(entity.pos.down(2)).getBlock().asItem().getDefaultStack());
                }
            }
        }

        //Photosynthesis.LOGGER.info(inventory);

        Optional<CropSticksRecipe> match = entity.getWorld().getRecipeManager()
                .getFirstMatch(CropSticksRecipe.Type.INSTANCE, inventory, entity.getWorld());

        entity.maxProgress = match.map(CropSticksRecipe::getCookTime).orElse(100);

        if (match.isPresent()) {
            return true;
        }
        else {
            //if no match, check without modifier block checker!
            inventory.removeStack(3); //remove modifier block.

            Optional<CropSticksRecipe> match2 = entity.getWorld().getRecipeManager()
                    .getFirstMatch(CropSticksRecipe.Type.INSTANCE, inventory, entity.getWorld());

            entity.maxProgress = match2.map(CropSticksRecipe::getCookTime).orElse(100);

            return match2.isPresent();
        }
    }
}
