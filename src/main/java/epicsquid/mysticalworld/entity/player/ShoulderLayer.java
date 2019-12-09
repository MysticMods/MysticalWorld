package epicsquid.mysticalworld.entity.player;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import epicsquid.mysticalworld.entity.model.BeetleModel;
import epicsquid.mysticalworld.entity.model.ShoulderRidingModel;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ShoulderLayer<T extends PlayerEntity> extends LayerRenderer<T, PlayerModel<T>> {
  private final Map<EntityType<?>, ShoulderRidingModel<?>> MODEL_MAP = new HashMap<>();

  public ShoulderLayer(IEntityRenderer<T, PlayerModel<T>> player) {
    super(player);
  }

  @Nullable
  public ShoulderRidingModel<?> getModelFor(EntityType<?> type) {
    return MODEL_MAP.computeIfAbsent(type, (e) -> {
      if (e == ModEntities.BEETLE.get()) {
        return new BeetleModel();
      } else {
        return null;
      }
    });
  }

  public void render(T entityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
    GlStateManager.enableRescaleNormal();
    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    entityIn.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY).ifPresent((cap) -> {
      EntityType<?> type = cap.getEntityType();
      ShoulderRidingModel<?> model = getModelFor(type);
      if (model != null) {
        this.renderModel(entityIn, limbSwing, limbSwingAmount, partialTicks, netHeadYaw, headPitch, 0.3f, model);
      }
    });
    GlStateManager.disableRescaleNormal();
  }

  private void renderModel(T player, float limbSwing, float limbSwingAmount, float partialTicks, float netHeadYaw, float headPitch, float scaleIn, ShoulderRidingModel<?> model) {
    GlStateManager.pushMatrix();
    GlStateManager.translatef(0.4F /*: -0.4F*/, player.shouldRenderSneaking() ? -1.3F : -1.5F, 0.0F);
    this.bindTexture(model.getTexture());
    model.renderOnShoulder(limbSwing, limbSwingAmount, netHeadYaw, headPitch, scaleIn, player.ticksExisted);
    GlStateManager.popMatrix();
  }

  public boolean shouldCombineTextures() {
    return false;
  }
}
