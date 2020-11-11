package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.EnderminiEntity;
import epicsquid.mysticalworld.entity.model.EnderminiModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

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
  public Vector3d getRenderOffset(EnderminiEntity p_225627_1_, float p_225627_2_) {
    if (p_225627_1_.isScreaming()) {
      double d0 = 0.02D;
      return new Vector3d(this.rnd.nextGaussian() * 0.02D, 0.0D, this.rnd.nextGaussian() * 0.02D);
    } else {
      return super.getRenderOffset(p_225627_1_, p_225627_2_);
    }
  }

  @Override
  public void render(EnderminiEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack stack, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
    BlockState blockstate = p_225623_1_.getHeldBlockState();
    EnderminiModel<EnderminiEntity> endermanmodel = this.getEntityModel();
    endermanmodel.isCarrying = blockstate != null;
    endermanmodel.isAttacking = p_225623_1_.isScreaming();
    stack.push();
    stack.scale(0.4f, 0.4f, 0.4f);
    super.render(p_225623_1_, p_225623_2_, p_225623_3_, stack, p_225623_5_, p_225623_6_);
    stack.pop();
  }

  @Nonnull
  @Override
  public ResourceLocation getEntityTexture(@Nonnull EnderminiEntity entity) {
    return ENDERMINI_TEXTURES;
  }

  @Nonnull
  @Override
  public EnderminiModel<EnderminiEntity> getEntityModel() {
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
    public void render(@Nonnull MatrixStack p_225628_1_, @Nonnull IRenderTypeBuffer p_225628_2_, int p_225628_3_, EnderminiEntity p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
      BlockState blockstate = p_225628_4_.getHeldBlockState();
      if (blockstate != null) {
        p_225628_1_.push();
        p_225628_1_.translate(0.0D, 0.6875D, -0.75D);
        p_225628_1_.rotate(Vector3f.XP.rotationDegrees(20.0F));
        p_225628_1_.rotate(Vector3f.YP.rotationDegrees(45.0F));
        p_225628_1_.translate(0.25D, 0.1875D, 0.25D);
        p_225628_1_.scale(-0.5F, -0.5F, 0.5F);
        p_225628_1_.rotate(Vector3f.YP.rotationDegrees(90.0F));
        Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(blockstate, p_225628_1_, p_225628_2_, p_225628_3_, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
        p_225628_1_.pop();
      }
    }
  }

  public static class EnderminiEyesLayer<T extends LivingEntity> extends AbstractEyesLayer<T, EnderminiModel<T>> {
    private static final RenderType SKIN = RenderType.getEyes(new ResourceLocation("textures/entity/enderman/enderman_eyes.png"));

    public EnderminiEyesLayer(IEntityRenderer<T, EnderminiModel<T>> p_i50939_1_) {
      super(p_i50939_1_);
    }

    @Override
    public RenderType getRenderType() {
      return SKIN;
    }
  }
}
