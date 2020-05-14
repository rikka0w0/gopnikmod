package gopnikmod;

import gopnikmod.blocks.TileEntityEmblem;
import gopnikmod.blocks.TileEntityFlag;
import gopnikmod.client.ModelDataProvider;
import gopnikmod.client.TESREmblem;
import gopnikmod.client.TESRFlag;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import rikka.librikka.model.loader.TERHelper;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = GopnikMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper exfh = event.getExistingFileHelper();
		if (event.includeClient()) {
			generator.addProvider(new ModelDataProvider(generator, exfh));
		}
	}
}
