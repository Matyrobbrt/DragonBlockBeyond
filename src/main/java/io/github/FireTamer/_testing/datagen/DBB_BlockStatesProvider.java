package io.github.FireTamer._testing.datagen;

import io.github.FireTamer.DBB_Main;

import io.github.FireTamer.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DBB_BlockStatesProvider extends BlockStateProvider implements IDataProvider {
    public DBB_BlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, DBB_Main.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //Gets the actual instance of the block object, and then sets up the texture pattern where it gets the registry name to look for the texture
        //simpleBlock(BlockInit.TEST_BLOCK.get(), cubeAll(BlockInit.TEST_BLOCK.get()));

        //Full Blocks
        simpleBlock(BlockInit.DIRTY_STONE, cubeAll(BlockInit.DIRTY_STONE));

        //Stairs
        //stairsBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_STAIRS.get(), modLoc("block/warenai_block_black"));

        //Slabs
        //slabBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_SLAB.get(), modLoc("block/warenai_block_black"), modLoc("block/warenai_block_black"));

        //Fences
        //fenceBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_FENCE.get(), modLoc("block/warenai_block_black"));

        //Walls
        //wallBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_WALL.get(), modLoc("block/warenai_block_black"));
    }
}
