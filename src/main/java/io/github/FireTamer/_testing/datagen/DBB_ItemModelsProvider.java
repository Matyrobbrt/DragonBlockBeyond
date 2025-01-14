package io.github.FireTamer._testing.datagen;

import io.github.FireTamer.DBB_Main;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DBB_ItemModelsProvider extends ItemModelProvider implements IDataProvider
{
    public DBB_ItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, DBB_Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //Creates an Item Model that points to the parent Block Model
        //withExistingParent("test_block", modLoc("block/test_block"));

        //Uses the "itemGenerated" variable and "builder" method to create a standard non-special item model
        //ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        //builder(itemGenerated, "test_ingot");

        //Full Blocks
        withExistingParent("dirty_stone", modLoc("block/dirty_stone"));

        //Items
        builder(itemGenerated, "warenai_crystal");
    }




    private ItemModelBuilder builder(ModelFile itemGenerated, String name)
    {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
