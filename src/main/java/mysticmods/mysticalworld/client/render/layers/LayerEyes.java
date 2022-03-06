package mysticmods.mysticalworld.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.LavaCatModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class LayerEyes<T extends LavaCatEntity> extends EyesLayer<T, LavaCatModel<T>> {
	private static final RenderType LAVA_EYES = AdditionalRenderTypes.getFullbrightEyes(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_eyes.png"));
	private static final RenderType OBSIDIAN_EYES = AdditionalRenderTypes.getFullbrightEyes(new ResourceLocation(MysticalWorld.MODID, "textures/entity/magma_cat_obsidian_eyes.png"));

	public LayerEyes(RenderLayerParent<T, LavaCatModel<T>> renderLayerParent) {
		super(renderLayerParent);
	}

	@Override
	public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		VertexConsumer vertexConsumer;
		if (pLivingEntity.getIsLava()) {
			vertexConsumer = pBuffer.getBuffer(LAVA_EYES);
		} else {
			vertexConsumer = pBuffer.getBuffer(OBSIDIAN_EYES);
		}
		this.getParentModel().renderToBuffer(pMatrixStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	public RenderType renderType() {
		return null;
	}
}