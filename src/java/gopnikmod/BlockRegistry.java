package gopnikmod;

import gopnikmod.blocks.BlockEmblem;
import gopnikmod.blocks.BlockFlag;
import gopnikmod.blocks.TileEntityEmblem;
import gopnikmod.blocks.TileEntityFlag;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import rikka.librikka.block.BlockBase;

public class BlockRegistry {
	public static BlockFlag blockFlag;
	public static BlockEmblem blockEmblem;
	
	public static void initBlocks() {
		blockFlag = new BlockFlag();
		blockEmblem = new BlockEmblem();
	}
	
	public static void registerBlocks(IForgeRegistry registry, boolean isItemBlock) {
		registerBlocks(registry, isItemBlock,
				blockFlag, blockEmblem
				);
	}
	
	public static void registerTileEntities() {
		registerTile(TileEntityFlag.class);
		registerTile(TileEntityEmblem.class);
	}
	
    
    private static void registerBlocks(IForgeRegistry registry, boolean isItemBlock, BlockBase... blocks) {
    	if (isItemBlock) {
        	for (BlockBase block: blocks)
        		registry.register(block.itemBlock);
    	} else {
    		registry.registerAll(blocks);
    	}
    }
	
    private static void registerTile(Class<? extends TileEntity> teClass) {
        String registryName = teClass.getName();
        registryName = registryName.substring(registryName.lastIndexOf(".") + 1);
        registryName = GopnikMod.MODID + ":" + registryName;
        GameRegistry.registerTileEntity(teClass, registryName);
    }
}
