package gopnikmod.client;


import gopnikmod.BlockRegistry;
import gopnikmod.GopnikMod;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import rikka.librikka.model.loader.ISimpleItemDataProvider;

public class ModelDataProvider extends BlockStateProvider implements ISimpleItemDataProvider {
	private final ExistingFileHelper exfh;
	public ModelDataProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, GopnikMod.MODID, exFileHelper);
        this.exfh = exFileHelper;
    }
	
	private void registerFake (Block block) {
		VariantBlockStateBuilder builder = getVariantBuilder(block);

		final ModelFile modelFile = new ModelFile.ExistingModelFile(mcLoc("block/torch"), exfh);
		builder.forAllStates((blockstate)->ConfiguredModel.builder().modelFile(modelFile).build());
	}
	
	@Override
	protected void registerStatesAndModels() {
		for (Block block: BlockRegistry.blockFlag) {
			registerFake(block);
			registerSimpleItem(block);
		}
			
		for (Block block: BlockRegistry.blockEmblem) {
			registerFake(block);
			registerSimpleItem(block);
		}
	}

}
