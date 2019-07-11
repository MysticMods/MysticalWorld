package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.FrogEntity;
import epicsquid.mysticalworld.entity.model.FrogModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class FrogRenderer extends LivingRenderer<FrogEntity, FrogModel> {

	private FrogRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull FrogModel m, float f) {
		super(renderManager, m, f);
	}

	public static class Factory implements IRenderFactory<FrogEntity> {

		@Override
		@Nonnull
		public FrogRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
			return new FrogRenderer(manager, ModelHolder.frogModel, 0.125f);
		}
	}

	@Override
	public void renderModel(@Nonnull FrogEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch,
													float scaleFactor) {
		GlStateManager.pushMatrix();
		if ((entity).getGrowingAge() < 0) {
			GlStateManager.scaled(0.5, 0.5, 0.5);
			GlStateManager.translated(0, 1.5, 0);
		}
		super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		GlStateManager.popMatrix();
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull FrogEntity entity) {
		return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/" + (entity.getEntityId() % 2 == 0 ? "frog" : "toad") + ".png");
	}
}
