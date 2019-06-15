package gopnikmod.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.tileentity.TileEntityBase;

public abstract class TileEntityDecorationBase extends TileEntityBase {
    public float rotation;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        rotation = compound.getFloat("rotation");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setFloat("rotation", rotation);

        return super.writeToNBT(compound);
    }

    @Override
    public void prepareS2CPacketData(NBTTagCompound nbt) {
        nbt.setFloat("rotation", rotation);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onSyncDataFromServerArrived(NBTTagCompound nbt) {
        rotation = nbt.getFloat("rotation");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        return 65536;
    }
}
