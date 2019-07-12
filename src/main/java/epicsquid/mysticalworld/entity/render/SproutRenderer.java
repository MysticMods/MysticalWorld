package epicsquid.mysticalworld.entity.render;

import epicsquid.mysticalworld.entity.SproutEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.model.SproutModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class SproutRenderer extends LivingRenderer<SproutEntity, SproutModel> {
	public SproutRenderer(EntityRendererManager renderManager, SproutModel modelBase, float shadowSize) {
		super(renderManager, modelBase, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(SproutEntity entity) {
		switch (entity.getDataManager().get(SproutEntity.variant)) {
			case 0: {
				return new ResourceLocation("mysticalworld:textures/entity/sprout_green.png");
			}
			case 1: {
				return new ResourceLocation("mysticalworld:textures/entity/sprout_tan.png");
			}
			case 2: {
				return new ResourceLocation("mysticalworld:textures/entity/sprout_red.png");
			}
			case 3: {
				return new ResourceLocation("mysticalworld:textures/entity/sprout_purple.png");
			}
			default: {
				return new ResourceLocation("mysticalworld:textures/entity/sprout_green.png");
			}
		}
	}

	public static class Factory implements IRenderFactory<SproutEntity> {
		@Override
		public EntityRenderer<SproutEntity> createRenderFor(EntityRendererManager manager) {
			return new SproutRenderer(manager, ModelHolder.sproutModel, 0.15f);
		}
	}
}
