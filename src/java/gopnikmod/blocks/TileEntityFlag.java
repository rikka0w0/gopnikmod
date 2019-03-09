package gopnikmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.tileentity.TileEntityBase;

public class TileEntityFlag extends TileEntityBase {
    @SideOnly(Side.CLIENT)
    public float phase;
    public float rotation;

    @Override
    public void onLoad() {
        if (world.isRemote)
            this.phase = this.world.rand.nextFloat() * 6.28F;
    }

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

    public String getFlagName() {
        IBlockState blockState = world.getBlockState(pos);
        return ((BlockFlag)blockState.getBlock()).stateToFlagName(blockState);
    }
}
