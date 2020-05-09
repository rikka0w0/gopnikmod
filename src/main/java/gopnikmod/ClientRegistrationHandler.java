package gopnikmod;

import gopnikmod.blocks.TileEntityEmblem;
import gopnikmod.blocks.TileEntityFlag;
import gopnikmod.client.TESREmblem;
import gopnikmod.client.TESRFlag;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rikka.librikka.model.loader.TERHelper;

@Mod.EventBusSubscriber(modid = GopnikMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrationHandler {
	public static void registerTileEntityRenders() {
		TERHelper.bind(TileEntityFlag.class, TESRFlag::new);
		TERHelper.bind(TileEntityEmblem.class, TESREmblem::new);
	}

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) { 
    	TESRFlag.onPreTextureStitchEvent(event);
    	TESREmblem.onPreTextureStitchEvent(event);
	}
    
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
    	TESRFlag.onModelBakeEvent();
    	TESREmblem.onModelBakeEvent();
    }
    
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event){
		registerTileEntityRenders();
	}
}
