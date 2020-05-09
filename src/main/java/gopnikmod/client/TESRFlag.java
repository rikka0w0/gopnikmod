package gopnikmod.client;

import gopnikmod.GopnikMod;
import gopnikmod.blocks.TileEntityFlag;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.TextureStitchEvent;
import rikka.librikka.model.loader.EasyTextureLoader;
import rikka.librikka.model.quadbuilder.BakedQuadHelper;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

public class TESRFlag extends TileEntityRenderer<TileEntityFlag> {
    public TESRFlag(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	public static ResourceLocation texture_resloc = new ResourceLocation(GopnikMod.MODID, "block/flags/soviet");
	public static TextureAtlasSprite texture;
    public static void onPreTextureStitchEvent(TextureStitchEvent.Pre event) {
    	if (EasyTextureLoader.isBlockAtlas(event)) {
    		event.addSprite(texture_resloc);
    	}
    }

    public static void onModelBakeEvent() {
    	texture = EasyTextureLoader.blockTextureGetter().apply(texture_resloc);
    }

	@Override
	public void render(TileEntityFlag te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		double rotation = te.rotation;

		int steps = 16;
		float A = 0.4F, k = 10, w = 4F;
		float maxX = 4, maxY = 2;
		long sysTime = System.currentTimeMillis() % 100000;
		float theta = (sysTime / 1000.0F * w + te.phase) % 6.28F;
		
    	//TODO: Fix light calculation
        int lightDummy = 15728640;
        IVertexBuilder buffer = bufferIn.getBuffer(RenderType.getSolid());

		matrixStack.push();
		matrixStack.translate(0.5, 0, 0.5);
		matrixStack.rotate(Vector3f.YP.rotation((float) (rotation/180*3.14)));
		for (int step = 0; step < steps; step++) {
			float x1 = step * maxX / steps;
			float x2 = (step + 1) * maxX / steps;
			float u1 = 16 * step / steps;
			float u2 = 16 * (step + 1) / steps;
			float z1 = A * x1 / maxX * MathHelper.sin(x1 / maxX * k - theta);
			float z2 = A * x2 / maxX * MathHelper.sin(x2 / maxX * k - theta);
			
			BakedQuad quad1 = BakedQuadHelper.bake(
					x1, maxY, z1, u1, 0, 
					x1, 0, z1, u1, 16, 
					x2, 0, z2, u2, 16, 
					x2, maxY, z2, u2, 0, 
					texture);

			BakedQuad quad2 = BakedQuadHelper.bake(
					x2, maxY, z2, u2, 0, 
					x2, 0, z2, u2, 16, 
					x1, 0, z1, u1, 16, 
					x1, maxY, z1, u1, 0, 
					texture);
			
			buffer.addQuad(matrixStack.getLast(), quad1, 
					new float[]{1.0F, 1.0F, 1.0F, 1.0F}, 
					1.0F, 1.0F, 1.0F, 
					new int[]{lightDummy, lightDummy, lightDummy, lightDummy}, 
					OverlayTexture.NO_OVERLAY, true);
			
			buffer.addQuad(matrixStack.getLast(), quad2, 
					new float[]{1.0F, 1.0F, 1.0F, 1.0F}, 
					1.0F, 1.0F, 1.0F, 
					new int[]{lightDummy, lightDummy, lightDummy, lightDummy}, 
					OverlayTexture.NO_OVERLAY, true);
		}
		matrixStack.pop();
	}
}
