package gopnikmod.blocks;

import javax.annotation.Nullable;

import gopnikmod.GopnikMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import rikka.librikka.block.BlockBase;

public class BlockFlag extends BlockBase {
    public static String[] subCls = new String[] {"soviet"};
    
    public final int meta;
    public BlockFlag(int meta) {
        super("flag_" + subCls[meta],
                Block.Properties.create(Material.ROCK),
                GopnikMod.itemGroup);
        this.meta = meta;
    }
    
    public static BlockFlag[] create() {
    	BlockFlag[] ret = new BlockFlag[subCls.length];
    	for (int i=0; i<subCls.length; i++) {
    		ret[i] = new BlockFlag(i);
    	}
    	return ret;
    }

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.create(0.4, 0, 0.4, 0.6, 1, 0.6);
	}

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        TileEntity te = world.getTileEntity(pos);

        if (te instanceof TileEntityFlag) {
            ((TileEntityFlag) te).rotation = 270-placer.rotationYaw;
        }
    }

    ///////////////////////////////
    /// TileEntity
    ///////////////////////////////
    @Override
    public boolean hasTileEntity(BlockState state) {return true;}

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityFlag();
    }

    ////////////////////////////////////
    /// Rendering
    ////////////////////////////////////
    //This will tell minecraft not to render any side of our cube.
    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }
    
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}
}
