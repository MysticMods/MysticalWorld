package epicsquid.mysticalworld.entity.render.layers;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

public class AdditionalRenderTypes extends RenderType {
  public AdditionalRenderTypes(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_, boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
    super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
  }

  public static RenderType getFullbrightLayer(ResourceLocation p_228636_0_, float p_228636_1_, float p_228636_2_) {
    return makeType("fullbright_entity", DefaultVertexFormats.ENTITY, 7, 256, false, true, State.getBuilder().texture(new TextureState(p_228636_0_, false, false)).texturing(new OffsetTexturingState(p_228636_1_, p_228636_2_)).fog(NO_FOG).diffuseLighting(DIFFUSE_LIGHTING_DISABLED).alpha(DEFAULT_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).transparency(RenderState.NO_TRANSPARENCY).overlay(OVERLAY_DISABLED).build(false));
  }

  public static RenderType getSpiritEntity(ResourceLocation p_228636_0_) {
    return makeType("spirit_entity", DefaultVertexFormats.ENTITY, 7, 256, false, true, State.getBuilder().texture(new TextureState(p_228636_0_, false, false)).fog(NO_FOG).diffuseLighting(DIFFUSE_LIGHTING_DISABLED).alpha(DEFAULT_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).transparency(RenderState.ADDITIVE_TRANSPARENCY).overlay(OVERLAY_DISABLED).build(false));
  }
}
