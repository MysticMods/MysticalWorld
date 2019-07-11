package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.BeetleEntity;
import epicsquid.mysticalworld.entity.model.BeetleModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class BeetleRender extends LivingRenderer<BeetleEntity, BeetleModel> {

	private BeetleRender(@Nonnull EntityRendererManager renderManager, @Nonnull BeetleModel m, float f) {
		super(renderManager, m, f);
	}

	public static class Factory implements IRenderFactory {

		@Override
		@Nonnull
		public BeetleRender createRenderFor(@Nonnull EntityRendererManager manager) {
			return new BeetleRender(manager, ModelHolder.models.get("beetle"), 0.05f);
		}
	}

	@Override
	public void renderModel(@Nonnull BeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch,
													float scaleFactor) {
		GlStateManager.pushMatrix();
		if ((entity).getGrowingAge() < 0) {
			GlStateManager.scaled(0.3, 0.3, 0.3);
			GlStateManager.translated(0, 3.5, 0);
		} else {
			GlStateManager.scaled(0.45, 0.45, 0.45);
			GlStateManager.translated(0, 1.85, 0);
		}
		super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		GlStateManager.popMatrix();
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull BeetleEntity entity) {
		return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/beetle_blue.png");
	}
}
