package gopnikmod;

import gopnikmod.blocks.BlockEmblem;
import gopnikmod.blocks.BlockFlag;
import gopnikmod.blocks.TileEntityEmblem;
import gopnikmod.blocks.TileEntityFlag;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.IForgeRegistry;
import rikka.librikka.block.BlockBase;
import rikka.librikka.tileentity.TileEntityHelper;

public class BlockRegistry {
	public static BlockFlag[] blockFlag;
	public static BlockEmblem[] blockEmblem;
	
	public static void initBlocks() {
		blockFlag = BlockFlag.create();
		blockEmblem = BlockEmblem.create();
	}
	
	public static void registerBlocks(IForgeRegistry<Block> registry, boolean isItemBlock) {
		registerBlocks(registry, isItemBlock, blockFlag);
		registerBlocks(registry, isItemBlock, blockEmblem);
	}
	
	public static void registerTileEntities(final IForgeRegistry<TileEntityType<?>> registry) {
		TileEntityHelper.registerTileEntity(registry, TileEntityFlag.class, blockFlag);
		TileEntityHelper.registerTileEntity(registry, TileEntityEmblem.class, blockEmblem);
	}
	
    private static void registerBlocks(IForgeRegistry registry, boolean isItemBlock, BlockBase... blocks) {
    	if (isItemBlock) {
        	for (BlockBase block: blocks)
        		registry.register(block.itemBlock);
    	} else {
    		registry.registerAll(blocks);
    	}
    }
}
