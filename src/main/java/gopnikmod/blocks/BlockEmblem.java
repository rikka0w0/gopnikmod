package gopnikmod.blocks;

import javax.annotation.Nullable;

import gopnikmod.GopnikMod;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import rikka.librikka.block.BlockBase;

public final class BlockEmblem extends BlockBase {
    public static String[] subCls = new String[] {"soviet", "soviet_large"};

    public final int meta;
    private BlockEmblem(int meta) {
        super("emblem_" + subCls[meta],
                Block.Properties.create(Material.ROCK),
                GopnikMod.itemGroup);
        this.meta = meta;
    }
    
    public static BlockEmblem[] create() {
    	BlockEmblem[] ret = new BlockEmblem[subCls.length];
    	for (int i=0; i<subCls.length; i++) {
    		ret[i] = new BlockEmblem(i);
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

        if (te instanceof TileEntityEmblem) {
            int rotation = MathHelper.floor(placer.rotationYaw * 8.0F / 360.0F + 0.5D) & 7;
            ((TileEntityEmblem) te).rotation = 270-rotation*45;
        }
    }

    ///////////////////////////////
    /// TileEntity
    ///////////////////////////////
    @Override
    public boolean hasTileEntity(BlockState state) {return true;}

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityEmblem();
    }

    ////////////////////////////////////
    /// Rendering
    ////////////////////////////////////
    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }
    
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}
}
