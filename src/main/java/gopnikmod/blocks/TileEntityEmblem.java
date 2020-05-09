package gopnikmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class TileEntityEmblem extends TileEntityDecorationBase {
    @OnlyIn(Dist.CLIENT)
    public List<BakedQuad> quadBuffer;

    @OnlyIn(Dist.CLIENT)
    public boolean updateRender = false;

    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onSyncDataFromServerArrived(CompoundNBT nbt) {
        super.onSyncDataFromServerArrived(nbt);
        updateRender = true;
    }

    @OnlyIn(Dist.CLIENT)
    public int getIndex() {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof BlockEmblem) {
        	return ((BlockEmblem) blockState.getBlock()).meta;
        }
        return -1;
    }
}
