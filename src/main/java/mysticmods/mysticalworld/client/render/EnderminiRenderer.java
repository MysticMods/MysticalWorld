package mysticmods.mysticalworld.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.entity.EnderminiEntity;
import mysticmods.mysticalworld.client.model.EnderminiModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;
import java.util.Random;

public class EnderminiRenderer extends MobRenderer<EnderminiEntity, EnderminiModel<EnderminiEntity>> {
  private static final ResourceLocation ENDERMINI_TEXTURES = new ResourceLocation(MysticalWorld.MODID, "textures/entity/endermini.png");
  private final Random rnd = new Random();

  public EnderminiRenderer(EntityRendererManager renderManagerIn, EnderminiModel<EnderminiEntity> model, float size) {
    super(renderManagerIn, model, size);
    this.addLayer(new EnderminiEyesLayer<>(this));
    this.addLayer(new HeldBlockLayer(this));
  }

  @Nonnull
  @Override
  public Vector3d getRenderOffset(EnderminiEntity pEntity, float pPartialTicks) {
    if (pEntity.isScreaming()) {
      return new Vector3d(this.rnd.nextGaussian() * 0.02D, 0.0D, this.rnd.nextGaussian() * 0.02D);
    } else {
      return super.getRenderOffset(pEntity, pPartialTicks);
    }
  }

  @Override
  public void render(EnderminiEntity pEntity, float pEntityYaw, float pPartialTicks, MatrixStack stack, IRenderTypeBuffer pBuffer, int pPackedLight) {
    BlockState blockstate = pEntity.getHeldBlockState();
    EnderminiModel<EnderminiEntity> endermanmodel = this.getModel();
    endermanmodel.carrying = blockstate != null;
    endermanmodel.creepy = pEntity.isScreaming();
    stack.pushPose();
    stack.scale(0.4f, 0.4f, 0.4f);
    super.render(pEntity, pEntityYaw, pPartialTicks, stack, pBuffer, pPackedLight);
    stack.popPose();
  }

  @Nonnull
  @Override
  public ResourceLocation getTextureLocation(@Nonnull EnderminiEntity entity) {
    return ENDERMINI_TEXTURES;
  }

  @Nonnull
  @Override
  public EnderminiModel<EnderminiEntity> getModel() {
    return ModelHolder.enderminiModel;
  }

  public static class Factory implements IRenderFactory<EnderminiEntity> {
    @Override
    public EntityRenderer<? super EnderminiEntity> createRenderFor(EntityRendererManager manager) {
      return new EnderminiRenderer(manager, ModelHolder.enderminiModel, 0.35f);
    }
  }

  public static class HeldBlockLayer extends LayerRenderer<EnderminiEntity, EnderminiModel<EnderminiEntity>> {
    public HeldBlockLayer(IEntityRenderer<EnderminiEntity, EnderminiModel<EnderminiEntity>> p_i50949_1_) {
      super(p_i50949_1_);
    }

    @Override
    public void render(@Nonnull MatrixStack pMatrixStack, @Nonnull IRenderTypeBuffer pBuffer, int pPackedLight, EnderminiEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
      BlockState blockstate = pLivingEntity.getHeldBlockState();
      if (blockstate != null) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0D, 0.6875D, -0.75D);
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(20.0F));
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
        pMatrixStack.translate(0.25D, 0.1875D, 0.25D);
        pMatrixStack.scale(-0.5F, -0.5F, 0.5F);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        Minecraft.getInstance().getBlockRenderer().renderBlock(blockstate, pMatrixStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
        pMatrixStack.popPose();
      }
    }
  }

  public static class EnderminiEyesLayer<T extends LivingEntity> extends AbstractEyesLayer<T, EnderminiModel<T>> {
    private static final RenderType SKIN = RenderType.eyes(new ResourceLocation("textures/entity/enderman/enderman_eyes.png"));

    public EnderminiEyesLayer(IEntityRenderer<T, EnderminiModel<T>> p_i50939_1_) {
      super(p_i50939_1_);
    }

    @Override
    public RenderType renderType() {
      return SKIN;
    }
  }
}
