package gopnikmod;

import gopnikmod.blocks.TileEntityFlag;
import gopnikmod.client.CustomStateMapper;
import gopnikmod.client.TESRFlag;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import rikka.librikka.model.loader.AdvancedModelLoader;

@Mod.EventBusSubscriber(modid = GopnikMod.MODID, value = Side.CLIENT)
public class ClientRegistrationHandler {
	@SubscribeEvent
	public static void registerModel(ModelRegistryEvent event) {
		AdvancedModelLoader loader = new AdvancedModelLoader(GopnikMod.MODID);
		
		//Blocks
		CustomStateMapper customStateMapper = new CustomStateMapper(GopnikMod.MODID);
		loader.registerModelLoader(customStateMapper);
		loader.registerInventoryIcon(BlockRegistry.blockFlag);
		customStateMapper.register(BlockRegistry.blockFlag);

		//Items
		// loader.registerInventoryIcon();
	}
	
	public static void registerTileEntityRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFlag.class, new TESRFlag());
	}
}
