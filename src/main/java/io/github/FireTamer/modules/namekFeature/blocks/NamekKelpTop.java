package io.github.FireTamer.modules.namekFeature.blocks;

import io.github.FireTamer.modules.namekFeature.NamekModule;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class NamekKelpTop extends AbstractTopPlantBlock implements ILiquidContainer
{
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public NamekKelpTop(Properties properties)
    {
        super(properties, Direction.UP, SHAPE, true, 0.14D);
    }

    protected boolean canGrowInto(BlockState state) {
        return state.is(NamekModule.NAMEK_FLUID_BLOCK);
    }

    protected Block getBodyBlock() { return NamekModule.NAMEK_KELP_STEM; }

    protected boolean canAttachToBlock(Block block) {
        return block != Blocks.MAGMA_BLOCK;
    }

    public boolean canPlaceLiquid(IBlockReader reader, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    public boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    protected int getBlocksToGrowWhenBonemealed(Random rand) {
        return 1;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return fluidstate.getType() == NamekModule.NAMEK_FLUID_FLOWING.get() ||
                fluidstate.getType() == NamekModule.NAMEK_FLUID_SOURCE.get() &&
                        fluidstate.getAmount() == 8 ? super.getStateForPlacement(context) : null;
    }

    public FluidState getFluidState(BlockState p_204507_1_) {
        return NamekModule.NAMEK_FLUID_SOURCE.get().getSource(false);
    }
}
