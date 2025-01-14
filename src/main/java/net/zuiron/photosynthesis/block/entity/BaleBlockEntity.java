package net.zuiron.photosynthesis.block.entity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.*;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.zuiron.photosynthesis.Photosynthesis;
import net.zuiron.photosynthesis.block.ModBlocks;
import net.zuiron.photosynthesis.block.custom.ModBaleBlock;
import net.zuiron.photosynthesis.util.ModConstants;
import net.zuiron.photosynthesis.util.getCustomVarsPassiveEntity;

import java.util.List;
import java.util.function.Predicate;

public class BaleBlockEntity extends BlockEntity {
    private int durability = ModConstants.BALES_DURABILITY;
    private boolean durability_set = false;
    public BaleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BALE, pos, state);
    }

    public static int getDurability(BaleBlockEntity baleBlockEntity) {
        return baleBlockEntity.durability;
    }

    public static void setDurability(BaleBlockEntity baleBlockEntity, int durability) {
        baleBlockEntity.durability = durability;
        baleBlockEntity.durability_set = true;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("bale.durability", durability);
        nbt.putBoolean("bale.durability_set", durability_set);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        durability = nbt.getInt("bale.durability");
        durability_set = nbt.getBoolean("bale.durability_set");
        super.readNbt(nbt);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, BaleBlockEntity baleBlockEntity) {
        if(!baleBlockEntity.durability_set) {
            return;
        }

        if(!world.isClient()) {
            //Photosynthesis.LOGGER.info("i am ticking. " + blockState.getBlock().toString() + ", dura: " + baleBlockEntity.durability);
        }
        //baleBlockEntity.durability--; //just testing.


        if(blockState.getBlock() == ModBlocks.WRAPPED_GRASS_BALE) {
            if(baleBlockEntity.durability > 0) {
                baleBlockEntity.durability = baleBlockEntity.durability - 72; //was 2, now 72 == 20 minutes. //8640 = 10 seconds
            } else {
                //replace with silage bale.
                world.setBlockState(blockPos, ModBlocks.SILAGE_BALE.getDefaultState(), 2);
            }
        }


        if(blockState.getBlock() == ModBlocks.GRASS_BALE || blockState.getBlock() == ModBlocks.HAY_BALE || blockState.getBlock() == ModBlocks.STRAW_BALE) {
            // Get the range in which you want to scan for entities
            double range = 10.0; // Adjust this value as needed

            // Calculate the bounding box around the block position
            Box boundingBox = new Box(
                    blockPos.getX() - range, blockPos.getY() - range, blockPos.getZ() - range,
                    blockPos.getX() + range, blockPos.getY() + range, blockPos.getZ() + range
            );

            // Look for passiveEntity entities
            Predicate<Entity> entityPredicate = entity -> {
                return entity instanceof PassiveEntity;
            };

            List<Entity> filteredEntities = world.getEntitiesByClass(Entity.class, boundingBox, entityPredicate);
            for (Entity entity : filteredEntities) {

                if (entity instanceof CowEntity) {
                    if (blockState.getBlock() == ModBlocks.GRASS_BALE) {
                        onTickGrassBale(world, blockPos, entity, baleBlockEntity);
                    } else if (blockState.getBlock() == ModBlocks.HAY_BALE) {
                        onTickHayBale(world, blockPos, entity, baleBlockEntity);
                    } else if (blockState.getBlock() == ModBlocks.STRAW_BALE) {
                        onTickStrawBale(world, blockPos, entity, baleBlockEntity);
                    }
                }
                else if (entity instanceof SheepEntity) {
                    if (blockState.getBlock() == ModBlocks.GRASS_BALE) {
                        onTickGrassBale(world, blockPos, entity, baleBlockEntity);
                    } else if (blockState.getBlock() == ModBlocks.HAY_BALE) {
                        onTickHayBale(world, blockPos, entity, baleBlockEntity);
                    }
                }
                else if (entity instanceof ChickenEntity) {
                    if (blockState.getBlock() == ModBlocks.STRAW_BALE) {
                        onTickStrawBale(world, blockPos, entity, baleBlockEntity);
                    }
                }
                else if (entity instanceof PigEntity) {
                    if (blockState.getBlock() == ModBlocks.STRAW_BALE) {
                        onTickStrawBale(world, blockPos, entity, baleBlockEntity);
                    }
                }
                else if (entity instanceof GoatEntity) {
                    if (blockState.getBlock() == ModBlocks.GRASS_BALE) {
                        onTickGrassBale(world, blockPos, entity, baleBlockEntity);
                    }
                }
                if (entity instanceof HorseEntity) {
                    if (blockState.getBlock() == ModBlocks.HAY_BALE) {
                        onTickHayBale(world, blockPos, entity, baleBlockEntity);
                    } else if (blockState.getBlock() == ModBlocks.STRAW_BALE) {
                        onTickStrawBale(world, blockPos, entity, baleBlockEntity);
                    }
                }

            }
        }




    }

    public static void onTickGrassBale(World world, BlockPos blockPos, Entity entity, BaleBlockEntity baleBlockEntity) {
        int mod_Grass = ((getCustomVarsPassiveEntity) entity).getMod_Grass();
        int mod_Grass_max = ((getCustomVarsPassiveEntity) entity).getMod_Grass_max();

        int missing = mod_Grass_max - mod_Grass;

        if (baleBlockEntity.durability >= missing) {
            //add missing difference.
            ((getCustomVarsPassiveEntity) entity).setMod_Grass(mod_Grass + missing);
            baleBlockEntity.durability -= missing;
        } else if (baleBlockEntity.durability > 0) {
            //didnt have enough for missing, but giving rest of durability.
            ((getCustomVarsPassiveEntity) entity).setMod_Grass(mod_Grass + baleBlockEntity.durability);
            baleBlockEntity.durability -= baleBlockEntity.durability;
            world.breakBlock(blockPos, false);
        } else { world.breakBlock(blockPos, false); } //ehh just in case.
    }

    public static void onTickHayBale(World world, BlockPos blockPos, Entity entity, BaleBlockEntity baleBlockEntity) {
        int mod_Hay = ((getCustomVarsPassiveEntity) entity).getMod_Hay();
        int mod_Hay_max = ((getCustomVarsPassiveEntity) entity).getMod_Hay_max();

        int missing = mod_Hay_max - mod_Hay;

        if (baleBlockEntity.durability >= missing) {
            //add missing difference.
            ((getCustomVarsPassiveEntity) entity).setMod_Hay(mod_Hay + missing);
            baleBlockEntity.durability -= missing;
        } else if (baleBlockEntity.durability > 0) {
            //didnt have enough for missing, but giving rest of durability.
            ((getCustomVarsPassiveEntity) entity).setMod_Hay(mod_Hay + baleBlockEntity.durability);
            baleBlockEntity.durability -= baleBlockEntity.durability;
            world.breakBlock(blockPos, false);
        } else { world.breakBlock(blockPos, false); } //ehh just in case.
    }

    public static void onTickStrawBale(World world, BlockPos blockPos, Entity entity, BaleBlockEntity baleBlockEntity) {
        int mod_Straw = ((getCustomVarsPassiveEntity) entity).getMod_Straw();
        int mod_Straw_max = ((getCustomVarsPassiveEntity) entity).getMod_Straw_max();

        int missing = mod_Straw_max - mod_Straw;

        if (baleBlockEntity.durability >= missing) {
            //add missing difference.
            ((getCustomVarsPassiveEntity) entity).setMod_Straw(mod_Straw + missing);
            baleBlockEntity.durability -= missing;
        } else if (baleBlockEntity.durability > 0) {
            //didnt have enough for missing, but giving rest of durability.
            ((getCustomVarsPassiveEntity) entity).setMod_Straw(mod_Straw + baleBlockEntity.durability);
            baleBlockEntity.durability -= baleBlockEntity.durability;
            world.breakBlock(blockPos, false);
        } else { world.breakBlock(blockPos, false); } //ehh just in case.
    }
}
