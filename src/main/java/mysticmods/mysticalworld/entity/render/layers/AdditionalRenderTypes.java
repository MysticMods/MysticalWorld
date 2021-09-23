package mysticmods.mysticalworld.entity.render.layers;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

import net.minecraft.client.renderer.RenderState.OffsetTexturingState;
import net.minecraft.client.renderer.RenderState.TextureState;
import net.minecraft.client.renderer.RenderType.State;

public class AdditionalRenderTypes extends RenderType {
  public AdditionalRenderTypes(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_, boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
    super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
  }

  public static RenderType getFullbrightLayer(ResourceLocation pLocation, float pU, float pV) {
    return create("fullbright_entity", DefaultVertexFormats.NEW_ENTITY, 7, 256, false, true, State.builder().setTextureState(new TextureState(pLocation, false, false)).setTexturingState(new OffsetTexturingState(pU, pV)).setFogState(NO_FOG).setDiffuseLightingState(NO_DIFFUSE_LIGHTING).setAlphaState(DEFAULT_ALPHA).setCullState(NO_CULL).setLightmapState(LIGHTMAP).setTransparencyState(RenderState.NO_TRANSPARENCY).setOverlayState(NO_OVERLAY).createCompositeState(false));
  }

  public static RenderType getSpiritEntity(ResourceLocation pLocation) {
    return create("spirit_entity", DefaultVertexFormats.NEW_ENTITY, 7, 256, false, true, State.builder().setTextureState(new TextureState(pLocation, false, false)).setFogState(NO_FOG).setDiffuseLightingState(NO_DIFFUSE_LIGHTING).setAlphaState(DEFAULT_ALPHA).setCullState(NO_CULL).setLightmapState(LIGHTMAP).setTransparencyState(RenderState.ADDITIVE_TRANSPARENCY).setOverlayState(NO_OVERLAY).createCompositeState(false));
  }
}
