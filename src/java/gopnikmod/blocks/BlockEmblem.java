package gopnikmod.blocks;

import gopnikmod.CreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rikka.librikka.block.MetaBlock;
import rikka.librikka.item.ISimpleTexture;
import rikka.librikka.item.ItemBlockBase;

public class BlockEmblem extends MetaBlock implements ISimpleTexture {
    public static String[] subCls = new String[] {"soviet", "soviet_large"};

    public BlockEmblem() {
        super("emblem",
                subCls,
                Material.ROCK, ItemBlockBase.class);
        setCreativeTab(CreativeTab.instance);
    }

    @Override
    public String getIconName(int damage) {
        return "emblem_" + this.getSubBlockUnlocalizedNames()[damage];
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileEntityEmblem) {
            int rotation = MathHelper.floor(placer.rotationYaw * 8.0F / 360.0F + 0.5D) & 7;
            ((TileEntityEmblem) te).rotation = 270-rotation*45;
        }
    }

    ///////////////////////////////
    /// TileEntity
    ///////////////////////////////
    @Override
    public boolean hasTileEntity(IBlockState state) {return true;}

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityEmblem();
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
