package gopnikmod.client;

import gopnikmod.blocks.BlockFlag;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.block.MetaBlock;
import rikka.librikka.model.GhostModel;
import rikka.librikka.model.loader.IModelLoader;

@SideOnly(Side.CLIENT)
public class CustomStateMapper extends StateMapperBase implements IModelLoader {
    public static final String VPATH = "virtual/blockstates";
    public final String domain;

    public CustomStateMapper(String domain) {
        this.domain = domain;
        OBJLoader.INSTANCE.addDomain(domain);
    }

    @Override
    public boolean accepts(String resPath) {
        return resPath.startsWith(VPATH);
    }

    @Override
    public ModelResourceLocation getModelResourceLocation(IBlockState state) {
        Block block = state.getBlock();
        String blockDomain = block.getRegistryName().getResourceDomain();
        String blockName = block.getRegistryName().getResourcePath();

        String varStr = "";

        if (block instanceof BlockFlag) {
            varStr = ((BlockFlag) block).stateToFlagName(state);
        }

        ModelResourceLocation res = new ModelResourceLocation(domain + ":" + VPATH,
                blockDomain + "," + blockName + "," + varStr);
        return res;
    }

    @Override
    public IModel loadModel(String domain, String resPath, String variantStr) throws Exception {
        String[] splited = variantStr.split(",");
        String blockDomain = splited[0];
        String blockName = splited[1];
        Block block = Block.getBlockFromName(blockDomain + ":" + blockName);

        if (block instanceof BlockFlag)
            return new GhostModel();

        return null;
    }

    public void register(Block block) {
        ModelLoader.setCustomStateMapper(block, this);
    }
}
