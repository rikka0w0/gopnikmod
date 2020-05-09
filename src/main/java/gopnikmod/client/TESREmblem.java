package gopnikmod.client;

import gopnikmod.GopnikMod;
import gopnikmod.blocks.TileEntityEmblem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import rikka.librikka.model.loader.EasyTextureLoader;
import rikka.librikka.model.quadbuilder.RawQuadCube;

import java.util.LinkedList;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

public class TESREmblem extends TileEntityRenderer<TileEntityEmblem> {
    public TESREmblem(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	public static ResourceLocation texture_resloc = new ResourceLocation(GopnikMod.MODID, "block/emblems/soviet");
	public static TextureAtlasSprite texture;
    public static void onPreTextureStitchEvent(TextureStitchEvent.Pre event) {
    	if (EasyTextureLoader.isBlockAtlas(event)) {
    		event.addSprite(texture_resloc);
    	}
    }

    public static void onModelBakeEvent() {
    	texture = EasyTextureLoader.blockTextureGetter().apply(texture_resloc);
    }

    private static void bake(List<BakedQuad> quadBuffer, int id, float rotation) {
        RawQuadCube cube;
        switch (id) {
            case 0:
                cube = new RawQuadCube(0.01F, 3F ,3F,
                        new TextureAtlasSprite[]{null, null, null, null, texture, null});
                cube.translateCoord(0.5F,0,0F);
                cube.rotateAroundY(rotation);
                cube.translateCoord(0.5F,0,0.5F);
                cube.bake(quadBuffer);
                break;
            case 1:
                cube = new RawQuadCube(0.01F, 10F ,10F,
                        new TextureAtlasSprite[]{null, null, null, null, texture, null});
                cube.translateCoord(0.5F,0,0F);
                cube.rotateAroundY(rotation);
                cube.translateCoord(0.5F,0,0F);
                cube.bake(quadBuffer);
                break;
            default:
        }
    }

	@Override
	public void render(TileEntityEmblem te, float partialTicks, MatrixStack matrixStack,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		
        if (te == null)
            return;

        if (te.updateRender) {
            te.updateRender = false;

            if (te.quadBuffer == null)
                te.quadBuffer = new LinkedList<>();

            bake(te.quadBuffer, te.getIndex(), te.rotation);
        }

        if (te.quadBuffer == null)
            return;
        
        IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getCutout());

    	//TODO: Fix light calculation
        int lightDummy = 15728640;
		for (BakedQuad quad: te.quadBuffer) {
			matrixStack.push();
			buffer.addQuad(matrixStack.getLast(), quad, 
					new float[]{1.0F, 1.0F, 1.0F, 1.0F}, 
					1.0F, 1.0F, 1.0F, 
					new int[]{lightDummy, lightDummy, lightDummy, lightDummy}, 
					OverlayTexture.NO_OVERLAY, true);
			matrixStack.pop();
		}
	}
	
    @Override
    public boolean isGlobalRenderer(TileEntityEmblem te) {
        return true;
    }
}
