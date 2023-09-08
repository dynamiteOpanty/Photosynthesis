package net.zuiron.photosynthesis.mixin.season_weather;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.zuiron.photosynthesis.api.Seasons;
import net.zuiron.photosynthesis.state.property.ModProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public abstract class ModLeavesBlock extends Block {
    @Unique
    private static final IntProperty SEASON = ModProperties.SEASON;
    @Unique
    private static final BooleanProperty SNOWY = Properties.SNOWY;
    public ModLeavesBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(SNOWY, false));
    }

    @Inject(at = @At("HEAD"), method = "hasRandomTicks", cancellable = true)
    public void hasRandomTicks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
        cir.cancel();
    }
    @Inject(at = @At("HEAD"), method = "randomTick")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if(Seasons.isSeasonsEnabled()) {
            int season = Seasons.getCurrentSeason(world.getTimeOfDay());
            //world.setBlockState(pos, state.with(SEASON, season));

            if(season == 2 && world.getBlockState(pos.up()).isOf(Blocks.SNOW) || world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
                world.setBlockState(pos, state.with(SNOWY, true).with(SEASON,season));
            } else {
                world.setBlockState(pos, state.with(SNOWY, false).with(SEASON,season));
            }
            if(season != 2) {
                world.setBlockState(pos, state.with(SNOWY, false).with(SEASON,season));
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "appendProperties")
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(SEASON,SNOWY);
    }
}
