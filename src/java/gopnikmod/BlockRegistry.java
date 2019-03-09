package gopnikmod;

import gopnikmod.blocks.BlockFlag;
import gopnikmod.blocks.TileEntityFlag;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import rikka.librikka.block.BlockBase;

public class BlockRegistry {
	public static BlockFlag blockFlag;
	
	public static void initBlocks() {
		blockFlag = new BlockFlag();
	}
	
	public static void registerBlocks(IForgeRegistry registry, boolean isItemBlock) {
		registerBlocks(registry, isItemBlock,
				blockFlag
				);
	}
	
	public static void registerTileEntities() {
		registerTile(TileEntityFlag.class);
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
