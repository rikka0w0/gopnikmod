package gopnikmod.client;

import gopnikmod.GopnikMod;
import gopnikmod.blocks.BlockEmblem;
import gopnikmod.blocks.TileEntityEmblem;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.animation.FastTESR;
import rikka.librikka.model.quadbuilder.RawQuadCube;

import java.util.LinkedList;
import java.util.List;

public class TESREmblem extends FastTESR<TileEntityEmblem> {
    private static TextureAtlasSprite[] texture = new TextureAtlasSprite[BlockEmblem.subCls.length];

    public static void stitchTexture(TextureMap map) {
        texture[0] = map.registerSprite(new ResourceLocation(GopnikMod.MODID, "blocks/emblems/soviet"));
    }

    @Override
    public boolean isGlobalRenderer(TileEntityEmblem te)
    {
        return true;
    }

    @Override
    public void renderTileEntityFast(TileEntityEmblem te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        if (te == null)
            return;

        List<BakedQuad> quadBuffer = new LinkedList<>();

        RawQuadCube cube = new RawQuadCube(0.01F, 3F ,3F,
                new TextureAtlasSprite[]{null, null, null, null, texture[0], null});
        cube.translateCoord(0.5F,0,0F);
        cube.rotateAroundY(te.rotation);
        cube.translateCoord(0.5F,0,0.5F);
        cube.bake(quadBuffer);

        buffer.setTranslation(x, y, z);

        int i = 15728640;
        for (BakedQuad quad: quadBuffer) {
            buffer.addVertexData(quad.getVertexData());
            buffer.putBrightness4(i, i, i, i);

            float diffuse = 1;
            if(quad.shouldApplyDiffuseLighting())
                diffuse = net.minecraftforge.client.model.pipeline.LightUtil.diffuseLight(quad.getFace());

            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 4);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 3);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 2);
            buffer.putColorMultiplier(diffuse, diffuse, diffuse, 1);

            buffer.putPosition(0, 0, 0);
        }
    }
}
