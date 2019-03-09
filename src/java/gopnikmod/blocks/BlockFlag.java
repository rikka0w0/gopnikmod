package gopnikmod.blocks;

import gopnikmod.CreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.block.MetaBlock;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBlockBase;

public class BlockFlag extends MetaBlock implements ISimpleTexture {
    public BlockFlag() {
        super("flag",
                new String[] {"soviet"},
                Material.ROCK, ItemBlockBase.class);
        setCreativeTab(CreativeTab.instance);
    }

    @Override
    public String getIconName(int damage) {
        return "flag_" + this.getSubBlockUnlocalizedNames()[damage];
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.4,0,0.4, 0.6,1,0.6);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileEntityFlag) {
            ((TileEntityFlag) te).rotation = 270-placer.rotationYaw;
        }
    }

    public String stateToFlagName(IBlockState state) {
        int meta = getMetaFromState(state);
        return this.getSubBlockUnlocalizedNames()[meta];
    }

    ///////////////////////////////
    /// TileEntity
    ///////////////////////////////
    @Override
    public boolean hasTileEntity(IBlockState state) {return true;}

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityFlag();
    }

    ////////////////////////////////////
    /// Rendering
    ////////////////////////////////////
    //This will tell minecraft not to render any side of our cube.
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    //And this tell it that you can see through this block, and neighbor blocks should be rendered.
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}
