package io.github.FireTamer.modules.namekFeature.blocks;

import io.github.FireTamer.modules.namekFeature.NamekModule;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public class NamekSeaGrassTall extends DoublePlantBlock implements ILiquidContainer
{
    public static final EnumProperty<DoubleBlockHalf> HALF = DoublePlantBlock.HALF;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public NamekSeaGrassTall(Properties properties)
    {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext selectionContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn (BlockState state, IBlockReader reader, BlockPos pos)
    {
        return state.isFaceSturdy(reader, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK);
    }

    //Makes the Item Icon and Item for this block the same as the returned ItemStack
    public ItemStack getCloneItemStack(IBlockReader reader, BlockPos pos, BlockState state) {
        return new ItemStack(NamekModule.NAMEK_SEAGRASS);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState blockstate = super.getStateForPlacement(context);

        if (blockstate!= null)
        {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos().above());

            if (fluidstate.getType() == NamekModule.NAMEK_FLUID_FLOWING.get() ||
                    fluidstate.getType() == NamekModule.NAMEK_FLUID_SOURCE.get() &&
                            fluidstate.getAmount() == 8)
            {
                return blockstate;
            }
        }

        return null;
    }

    public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockstate = reader.getBlockState(pos.below());
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        } else {
            FluidState fluidstate = reader.getFluidState(pos);
            return super.canSurvive(state, reader, pos) && fluidstate.getType() == NamekModule.NAMEK_FLUID_FLOWING.get() ||
                    fluidstate.getType() == NamekModule.NAMEK_FLUID_SOURCE.get() && fluidstate.getAmount() == 8;
        }
    }

    public FluidState getFluidState(BlockState state) { return NamekModule.NAMEK_FLUID_SOURCE.get().getSource(false); }

    public boolean canPlaceLiquid(IBlockReader reader, BlockPos pos, BlockState state, Fluid fluid)
    {
        return false;
    }

    public boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluidState)
    {
        return false;
    }


    //public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean bool) { return false; }

    //public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) { return false; }

    //public void performBonemeal(ServerWorld server, Random rand, BlockPos pos, BlockState state) {}
}
