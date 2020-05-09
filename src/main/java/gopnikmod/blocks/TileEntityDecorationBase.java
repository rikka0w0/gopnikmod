package gopnikmod.blocks;

import gopnikmod.GopnikMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rikka.librikka.tileentity.TileEntityBase;

public abstract class TileEntityDecorationBase extends TileEntityBase {
    public TileEntityDecorationBase() {
		super(GopnikMod.MODID);
	}

	public float rotation;

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        rotation = compound.getFloat("rotation");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putFloat("rotation", rotation);

        return super.write(compound);
    }

    @Override
    public void prepareS2CPacketData(CompoundNBT nbt) {
        nbt.putFloat("rotation", rotation);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onSyncDataFromServerArrived(CompoundNBT nbt) {
        rotation = nbt.getFloat("rotation");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536;
    }
}
