package gopnikmod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(GopnikMod.MODID)
public class GopnikMod {
    public static final String MODID = "gopnikmod";
    public static final String NAME = "The Gopnik Mod";
    public static final String VERSION = "0.1";

    public static GopnikMod instance = null;
    public static ItemGroup itemGroup = null;

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void newRegistry(RegistryEvent.NewRegistry event) {
        	itemGroup = new ItemGroup(GopnikMod.MODID) {
				@Override
				public ItemStack createIcon() {
					return new ItemStack(BlockRegistry.blockFlag[0]);
				}
        	};
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            IForgeRegistry registry = event.getRegistry();
            BlockRegistry.initBlocks();
            BlockRegistry.registerBlocks(registry, false);
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry registry = event.getRegistry();
            ItemRegistry.initItems();
            BlockRegistry.registerBlocks(registry, true);
            ItemRegistry.registerItems(registry);
            
        }
        
    	@SubscribeEvent
    	public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {
    		BlockRegistry.registerTileEntities(event.getRegistry());
    	}
    }
}
