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
    public static TextureAtlasSprite[] texture = new TextureAtlasSprite[1];

    public static void stitchTexture(TextureMap map) {
        texture[0] = map.registerSprite(new ResourceLocation(GopnikMod.MODID, "blocks/emblems/soviet"));
    }

    @Override
    public boolean isGlobalRenderer(TileEntityEmblem te)
    {
        return true;
    }

    private static void bake(List<BakedQuad> quadBuffer, int id, float rotation) {
        RawQuadCube cube;
        switch (id) {
            case 0:
                cube = new RawQuadCube(0.01F, 3F ,3F,
                        new TextureAtlasSprite[]{null, null, null, null, texture[0], null});
                cube.translateCoord(0.5F,0,0F);
                cube.rotateAroundY(rotation);
                cube.translateCoord(0.5F,0,0.5F);
                cube.bake(quadBuffer);
                break;
            case 1:
                cube = new RawQuadCube(0.01F, 10F ,10F,
                        new TextureAtlasSprite[]{null, null, null, null, texture[0], null});
                cube.translateCoord(0.5F,0,0F);
                cube.rotateAroundY(rotation);
                cube.translateCoord(0.5F,0,0.5F);
                cube.bake(quadBuffer);
                break;
            default:
        }
    }

    @Override
    public void renderTileEntityFast(TileEntityEmblem te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
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

        buffer.setTranslation(x, y, z);

        int i = 15728640;
        for (BakedQuad quad: te.quadBuffer) {
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
