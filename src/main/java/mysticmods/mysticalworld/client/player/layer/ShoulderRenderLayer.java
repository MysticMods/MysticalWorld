package mysticmods.mysticalworld.client.player.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mysticmods.mysticalworld.api.Capabilities;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.ModelState;
import mysticmods.mysticalworld.client.model.ShoulderRidingModel;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class ShoulderRenderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {

  public ShoulderRenderLayer(RenderLayerParent<T, PlayerModel<T>> p_i50929_1_) {
    super(p_i50929_1_);
  }

  @Nullable
  public ShoulderRidingModel<?> getModelFor(EntityType<?> type) {
    if (type == ModEntities.BEETLE.get()) {
      return ModelHolder.beetleModel;
    }

    return null;
  }

  public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    pLivingEntity.getCapability(Capabilities.SHOULDER_CAPABILITY).ifPresent(cap -> {
      EntityType<?> type = cap.getEntityType();
      ShoulderRidingModel<?> model = getModelFor(type);
      if (model != null) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.375, pLivingEntity.isCrouching() ? -0.3 : -0.5, 0);
        pMatrixStack.scale(0.35f, 0.35f, 0.35f);
        VertexConsumer vertex = pBuffer.getBuffer(model.renderType(model.getTexture(ModelState.SHOULDER)));
        model.renderOnShoulder(pMatrixStack, vertex, pPackedLight, OverlayTexture.NO_OVERLAY, pLimbSwing, pLimbSwingAmount, pNetHeadYaw, pHeadPitch, pLivingEntity.tickCount);
        pMatrixStack.popPose();
      }
    });
  }
}
