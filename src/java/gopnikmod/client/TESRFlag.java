package gopnikmod.client;

import gopnikmod.GopnikMod;
import gopnikmod.blocks.TileEntityFlag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class TESRFlag extends TileEntitySpecialRenderer<TileEntityFlag> {
    public static void startDrawingQuads() {
        Tessellator.getInstance().getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
    }

    public static void addVertexWithUV(double x, double y, double z, double u, double v) {
        Tessellator.getInstance().getBuffer().pos(x, y, z).tex(u, v).endVertex();
    }

    public static void endDrawingQuads() {
        Tessellator.getInstance().draw();
    }

    public void render(TileEntityFlag te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);

        double rotation = te.rotation;
        String flag = te.getFlagName();

        GlStateManager.disableLighting();
        GL11.glPushMatrix();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 60, 240);

        GL11.glTranslated(x, y, z);
        GL11.glTranslated(0.5, 0, 0.5);
        GL11.glRotated(rotation,0.0,1.0,0.0);

        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(GopnikMod.MODID, "textures/blocks/flags/"+flag+".png"));

        int steps = 25;
        float A = 0.4F, k = 10, w = 4F;
        float maxX = 4, maxY = 2;
        float theta = (Minecraft.getMinecraft().getSystemTime() / 1000.0F * w + te.phase) % 6.28F;

        for (int step=0; step<steps; step++) {
            float x1 = step*maxX/steps;
            float x2 = (step+1)*maxX/steps;
            float u1 = 1.0F*step/steps;
            float u2 = 1.0F*(step+1)/steps;
            float z1 = A*x1/maxX* MathHelper.sin(x1/maxX * k - theta);
            float z2 = A*x2/maxX*MathHelper.sin(x2/maxX * k - theta);

            startDrawingQuads();
            addVertexWithUV(x1, maxY, z1, u1, 0);
            addVertexWithUV(x1, 0, z1, u1, 1);
            addVertexWithUV(x2, 0, z2, u2, 1);
            addVertexWithUV(x2, maxY, z2, u2, 0);
            endDrawingQuads();


            startDrawingQuads();
            addVertexWithUV(x2, maxY, z2, u2, 0);
            addVertexWithUV(x2, 0, z2, u2, 1);
            addVertexWithUV(x1, 0, z1, u1, 1);
            addVertexWithUV(x1, maxY, z1, u1, 0);
            endDrawingQuads();
        }

        GL11.glPopMatrix();
        GlStateManager.enableLighting();
    }
}
