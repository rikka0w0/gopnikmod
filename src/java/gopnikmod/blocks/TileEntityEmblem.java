package gopnikmod.blocks;

import net.minecraft.block.state.IBlockState;

public class TileEntityEmblem extends TileEntityDecorationBase {
    public String getEmblemName() {
        IBlockState blockState = world.getBlockState(pos);
        return ((BlockEmblem)blockState.getBlock()).stateToEmblemName(blockState);
    }

    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }
}
