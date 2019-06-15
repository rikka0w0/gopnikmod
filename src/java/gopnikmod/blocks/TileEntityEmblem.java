package gopnikmod.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class TileEntityEmblem extends TileEntityDecorationBase {
    @SideOnly(Side.CLIENT)
    public List<BakedQuad> quadBuffer;

    @SideOnly(Side.CLIENT)
    public boolean updateRender = false;

    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onSyncDataFromServerArrived(NBTTagCompound nbt) {
        super.onSyncDataFromServerArrived(nbt);
        updateRender = true;
    }

    @SideOnly(Side.CLIENT)
    public int getIndex() {
        IBlockState blockState = world.getBlockState(pos);
        return blockState.getBlock().getMetaFromState(blockState);
    }
}
