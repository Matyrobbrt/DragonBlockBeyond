package io.github.FireTamer.modules.namekFeature.blocks;

import io.github.FireTamer.modules.namekFeature.NamekModule;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

/**
 * For now I need to put this class and it's special features on hold. Special Features being the budding/resource.
 * The reason for this is because I simply cannot get it done yet and I want to get the ball rolling on other things.
 * So, I will revisit this later when I go to do the polishing on everything.
 *
 * Remember, when I go to do this do not forget about the blockstate file and changing the "age=15" model to "namek_kelp_stem_budding"
**/

@SuppressWarnings("deprecation")
public class NamekKelpStem extends AbstractBodyPlantBlock implements ILiquidContainer, IGrowable
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;

    public NamekKelpStem(Properties properties)
    {
        super(properties, Direction.UP, VoxelShapes.block(), true);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    public boolean isRandomlyTicking(BlockState state) { return state.getValue(AGE) < 15; }



/**
    public boolean budPopulationLimitReached(ServerWorld worldIn, BlockPos pos, BlockState state)
    {
        BlockPos above_1 = pos.above();
        BlockPos above_2 = above_1.above();
        BlockPos above_3 = above_2.above();
        BlockPos below_1 = pos.below();
        BlockPos below_2 = below_1.below();
        BlockPos below_3 = below_2.below();

        //.is(this.stateDefinition.any().getValue(AGE) == 15
        //.is(this)
        //state.getBlockState().getValue(AGE) == 15
        //worldIn.getBlockState(above_2).getValue(AGE) == 15

        if (above_1.equals(BlockInit.NAMEK_KELP_STEM_BUDDING) ||
                above_2.equals(BlockInit.NAMEK_KELP_STEM_BUDDING) ||
                above_3.equals(BlockInit.NAMEK_KELP_STEM_BUDDING) ||
                below_1.equals(BlockInit.NAMEK_KELP_STEM_BUDDING) ||
                below_2.equals(BlockInit.NAMEK_KELP_STEM_BUDDING) ||
                below_3.equals(BlockInit.NAMEK_KELP_STEM_BUDDING))
        {
            return true;
        } else { return false; }
    }
**/

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        int i = state.getValue(AGE);

        if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading}

        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(2) == 0))
        {
            worldIn.setBlockAndUpdate(pos, state.setValue(AGE, Integer.valueOf(i + 1)));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }


        /**
        if (budPopulationLimitReached(worldIn, pos, state) == false)
        {
            //I believe this if statement meant that if the random number (3 are available. 0, 1, and 2) is equal to 0 then the below portion will be enacted.
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(2) == 0))
            {
                worldIn.setBlockAndUpdate(pos, state.setValue(AGE, Integer.valueOf(i + 1)));
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
            }
        }
        **/
    }

    /**
     * @Override
     *     public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
     *     {
     *         if (player.getItemInHand(handIn).getItem() == Items.BONE_MEAL)
     *         {
     *             return ActionResultType.PASS;
     *         } else {
     *             popResource(worldIn, pos, new ItemStack(ItemInit.NAMEK_KELP_BUDS.getItem(), 1));
     *             worldIn.playSound((PlayerEntity)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
     *             worldIn.setBlockAndUpdate(pos, BlockInit.NAMEK_KELP_STEM.defaultBlockState());
     *             return ActionResultType.sidedSuccess(worldIn.isClientSide);
     *         }
     *     }
    **/

    protected AbstractTopPlantBlock getHeadBlock() {
        return (AbstractTopPlantBlock) NamekModule.NAMEK_KELP_TOP;
    }

    public FluidState getFluidState(BlockState state) {
        return NamekModule.NAMEK_FLUID_SOURCE.get().getSource(false);
    }

    public boolean canPlaceLiquid(IBlockReader reader, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    public boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return state.getValue(AGE) < 15;
    }

    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return true;
    }

    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        int i = state.getValue(AGE);
        worldIn.setBlockAndUpdate(pos, state.setValue(AGE, Integer.valueOf(i + 1)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }
}
