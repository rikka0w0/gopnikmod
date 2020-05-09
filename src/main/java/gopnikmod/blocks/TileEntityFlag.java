package gopnikmod.blocks;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileEntityFlag extends TileEntityDecorationBase {
    @OnlyIn(Dist.CLIENT)
    public float phase;

    @Override
    public void onLoad() {
        if (world.isRemote)
            this.phase = this.world.rand.nextFloat() * 6.28F;
    }
}
