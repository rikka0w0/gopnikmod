package gopnikmod;

import java.util.LinkedHashSet;
import java.util.Set;

import gopnikmod.blocks.BlockEmblem;
import gopnikmod.blocks.BlockFlag;
import gopnikmod.blocks.TileEntityEmblem;
import gopnikmod.blocks.TileEntityFlag;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.IForgeRegistry;
import rikka.librikka.block.BlockBase;
import rikka.librikka.tileentity.TileEntityHelper;

public class BlockRegistry {
	private final static Set<Item> blockItems = new LinkedHashSet<>();
	
	public static BlockFlag[] blockFlag;
	public static BlockEmblem[] blockEmblem;
	
	public static void initBlocks() {
		blockFlag = BlockFlag.create();
		blockEmblem = BlockEmblem.create();
	}
	
	public static void registerBlocks(IForgeRegistry<Block> registry) {
		registerBlocks(registry, blockFlag);
		registerBlocks(registry, blockEmblem);
	}
	
	public static void registerBlockItems(IForgeRegistry<Item> registry) {
		blockItems.forEach(registry::register);
	}
	
	public static void registerTileEntities(final IForgeRegistry<TileEntityType<?>> registry) {
		TileEntityHelper.registerTileEntity(registry, TileEntityFlag.class, blockFlag);
		TileEntityHelper.registerTileEntity(registry, TileEntityEmblem.class, blockEmblem);
	}
	
    private static void registerBlocks(IForgeRegistry<Block> registry, BlockBase... blocks) {
		registry.registerAll(blocks);
		
		for (BlockBase block: blocks)
    		blockItems.add(block.asItem());
    }
}
