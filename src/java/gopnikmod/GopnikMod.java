package gopnikmod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = GopnikMod.MODID, name = GopnikMod.NAME, version = GopnikMod.VERSION)
public class GopnikMod {
    public static final String MODID = "gopnikmod";
    public static final String NAME = "The Gopnik Mod";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "gopnikmod.ClientProxy", serverSide = "gopnikmod.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(GopnikMod.MODID)
    public static GopnikMod instance;

    public SimpleNetworkWrapper networkChannel;

    /**
     * PreInitialize
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // networkChannel = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        //Register network channels
        // networkChannel.registerMessage(MessageLED12864.Handler.class, MessageLED12864.class, 0, Side.SERVER);
        proxy.preInit();
    }

    @Mod.EventBusSubscriber(modid = GopnikMod.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void newRegistry(RegistryEvent.NewRegistry event) {
            new CreativeTab();
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
    }

    /**
     * Initialize
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        BlockRegistry.registerTileEntities();

        proxy.init();

        //Register GUI handler
        // NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }

    /**
     * PostInitialize
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
