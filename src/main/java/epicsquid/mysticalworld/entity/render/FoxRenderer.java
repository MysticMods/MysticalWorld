package epicsquid.mysticalworld.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.FoxEntity;
import epicsquid.mysticalworld.entity.model.FoxModel;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class FoxRenderer extends LivingRenderer<FoxEntity, FoxModel> {

	private FoxRenderer(@Nonnull EntityRendererManager renderManager, @Nonnull FoxModel m, float f) {
		super(renderManager, m, f);
	}

	public static class Factory implements IRenderFactory<FoxEntity> {

		@Override
		@Nonnull
		public FoxRenderer createRenderFor(@Nonnull EntityRendererManager manager) {
			return new FoxRenderer(manager, ModelHolder.foxModel, 0.25f);
		}
	}

	@Override
	public void renderModel(@Nonnull FoxEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch,
													float scaleFactor) {
		GlStateManager.pushMatrix();
		if ((entity).getGrowingAge() < 0) {
			GlStateManager.scaled(0.5, 0.5, 0.5);
			GlStateManager.translated(0, 1.5, 0);
		}
		GlStateManager.translated(0, -0.0625, 0);
		super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		GlStateManager.popMatrix();
	}

	@Override
	@Nonnull
	protected ResourceLocation getEntityTexture(@Nonnull FoxEntity entity) {
		return new ResourceLocation(MysticalWorld.MODID + ":textures/entity/fox.png");
	}
}
