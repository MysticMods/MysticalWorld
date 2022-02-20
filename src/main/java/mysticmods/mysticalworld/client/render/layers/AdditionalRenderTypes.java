package mysticmods.mysticalworld.client.render.layers;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class AdditionalRenderTypes extends RenderType {
  public AdditionalRenderTypes(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
    super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
  }

/*  public static RenderType getFullbrightLayer(ResourceLocation pLocation, float pU, float pV) {
    return create("fullbright_entity", DefaultVertexFormat.NEW_ENTITY, 7, 256, false, true, CompositeState.builder().setTextureState(new TextureStateShard(pLocation, false, false)).setTexturingState(new OffsetTexturingStateShard(pU, pV)).setFogState(NO_FOG).setDiffuseLightingState(NO_DIFFUSE_LIGHTING).setAlphaState(DEFAULT_ALPHA).setCullState(NO_CULL).setLightmapState(LIGHTMAP).setTransparencyState(RenderStateShard.NO_TRANSPARENCY).setOverlayState(NO_OVERLAY).createCompositeState(false));
  }

  public static RenderType getSpiritEntity(ResourceLocation pLocation) {
    return create("spirit_entity", DefaultVertexFormat.NEW_ENTITY, 7, 256, false, true, CompositeState.builder().setTextureState(new TextureStateShard(pLocation, false, false)).setFogState(NO_FOG).setDiffuseLightingState(NO_DIFFUSE_LIGHTING).setAlphaState(DEFAULT_ALPHA).setCullState(NO_CULL).setLightmapState(LIGHTMAP).setTransparencyState(RenderStateShard.ADDITIVE_TRANSPARENCY).setOverlayState(NO_OVERLAY).createCompositeState(false));
  }*/
}
