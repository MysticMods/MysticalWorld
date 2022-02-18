package mysticmods.mysticalworld.client.render.layers;

import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.client.renderer.RenderStateShard.OffsetTexturingStateShard;
import net.minecraft.client.renderer.RenderStateShard.TextureStateShard;
import net.minecraft.client.renderer.RenderType.CompositeState;

public class AdditionalRenderTypes extends RenderType {
  public AdditionalRenderTypes(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_, boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
    super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
  }

  public static RenderType getFullbrightLayer(ResourceLocation pLocation, float pU, float pV) {
    return create("fullbright_entity", DefaultVertexFormat.NEW_ENTITY, 7, 256, false, true, CompositeState.builder().setTextureState(new TextureStateShard(pLocation, false, false)).setTexturingState(new OffsetTexturingStateShard(pU, pV)).setFogState(NO_FOG).setDiffuseLightingState(NO_DIFFUSE_LIGHTING).setAlphaState(DEFAULT_ALPHA).setCullState(NO_CULL).setLightmapState(LIGHTMAP).setTransparencyState(RenderStateShard.NO_TRANSPARENCY).setOverlayState(NO_OVERLAY).createCompositeState(false));
  }

  public static RenderType getSpiritEntity(ResourceLocation pLocation) {
    return create("spirit_entity", DefaultVertexFormat.NEW_ENTITY, 7, 256, false, true, CompositeState.builder().setTextureState(new TextureStateShard(pLocation, false, false)).setFogState(NO_FOG).setDiffuseLightingState(NO_DIFFUSE_LIGHTING).setAlphaState(DEFAULT_ALPHA).setCullState(NO_CULL).setLightmapState(LIGHTMAP).setTransparencyState(RenderStateShard.ADDITIVE_TRANSPARENCY).setOverlayState(NO_OVERLAY).createCompositeState(false));
  }
}
