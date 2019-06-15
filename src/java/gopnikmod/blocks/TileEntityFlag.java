package gopnikmod.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityFlag extends TileEntityDecorationBase {
    @SideOnly(Side.CLIENT)
    public float phase;

    @Override
    public void onLoad() {
        if (world.isRemote)
            this.phase = this.world.rand.nextFloat() * 6.28F;
    }

    public String getFlagName() {
        IBlockState blockState = world.getBlockState(pos);
        return ((BlockFlag)blockState.getBlock()).stateToFlagName(blockState);
    }
}
