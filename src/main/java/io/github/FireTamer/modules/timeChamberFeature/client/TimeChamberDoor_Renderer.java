package io.github.FireTamer.modules.timeChamberFeature.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.FireTamer.modules.timeChamberFeature.tiles.TimeChamberDoor_Tile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class TimeChamberDoor_Renderer extends GeoBlockRenderer<TimeChamberDoor_Tile> {

    public TimeChamberDoor_Renderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new TimeChamberDoor_Model());
    }

    @Override
    public RenderType getRenderType(TimeChamberDoor_Tile animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
