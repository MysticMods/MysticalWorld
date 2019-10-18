package epicsquid.mysticalworld.blocks;

import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;

public class PaintedChestTileEntityRenderer extends ChestTileEntityRenderer<PaintedChestTileEntity> {

	/*private static final ResourceLocation TEXTURE_WHITE_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/white_double.png");
	private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/white.png");
	private static final ResourceLocation TEXTURE_ORANGE_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/orange_double.png");
	private static final ResourceLocation TEXTURE_ORANGE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/orange.png");
	private static final ResourceLocation TEXTURE_MAGENTA_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/magenta_double.png");
	private static final ResourceLocation TEXTURE_MAGENTA = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/magenta.png");
	private static final ResourceLocation TEXTURE_LIGHT_BLUE_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/light_blue_double.png");
	private static final ResourceLocation TEXTURE_LIGHT_BLUE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/light_blue.png");
	private static final ResourceLocation TEXTURE_YELLOW_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/yellow_double.png");
	private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/yellow.png");
	private static final ResourceLocation TEXTURE_LIME_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/lime_double.png");
	private static final ResourceLocation TEXTURE_LIME = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/lime.png");
	private static final ResourceLocation TEXTURE_PINK_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/pink_double.png");
	private static final ResourceLocation TEXTURE_PINK = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/pink.png");
	private static final ResourceLocation TEXTURE_GRAY_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/gray_double.png");
	private static final ResourceLocation TEXTURE_GRAY = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/gray.png");
	private static final ResourceLocation TEXTURE_LIGHT_GRAY_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/light_gray_double.png");
	private static final ResourceLocation TEXTURE_LIGHT_GRAY = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/light_gray.png");
	private static final ResourceLocation TEXTURE_CYAN_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/cyan_double.png");
	private static final ResourceLocation TEXTURE_CYAN = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/cyan.png");
	private static final ResourceLocation TEXTURE_PURPLE_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/purple_double.png");
	private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/purple.png");
	private static final ResourceLocation TEXTURE_BLUE_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/blue_double.png");
	private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/blue.png");
	private static final ResourceLocation TEXTURE_BROWN_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/brown_double.png");
	private static final ResourceLocation TEXTURE_BROWN = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/brown.png");
	private static final ResourceLocation TEXTURE_GREEN_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/green_double.png");
	private static final ResourceLocation TEXTURE_GREEN = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/green.png");
	private static final ResourceLocation TEXTURE_RED_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/red_double.png");
	private static final ResourceLocation TEXTURE_RED = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/red.png");
	private static final ResourceLocation TEXTURE_BLACK_DOUBLE = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/black_double.png");
	private static final ResourceLocation TEXTURE_BLACK = new ResourceLocation(MysticalWorld.MODID, "textures/entity/chest/painted/black.png");

	private final ChestModel simpleChest = new ChestModel();
	private final ChestModel largeChest = new LargeChestModel();

	@Override
	public void render(PaintedChestTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.enableDepthTest();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		BlockState blockstate = tileEntityIn.hasWorld() ? tileEntityIn.getBlockState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
		ChestType chesttype = blockstate.has(ChestBlock.TYPE) ? blockstate.get(ChestBlock.TYPE) : ChestType.SINGLE;
		if (chesttype != ChestType.LEFT) {
			boolean flag = chesttype != ChestType.SINGLE;
			ChestModel chestmodel = this.getChestModel(tileEntityIn, destroyStage, flag);
			if (destroyStage >= 0) {
				GlStateManager.matrixMode(5890);
				GlStateManager.pushMatrix();
				GlStateManager.scalef(flag ? 8.0F : 4.0F, 4.0F, 1.0F);
				GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
				GlStateManager.matrixMode(5888);
			} else {
				GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.translatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GlStateManager.scalef(1.0F, -1.0F, -1.0F);
			float f = blockstate.get(ChestBlock.FACING).getHorizontalAngle();
			if ((double) Math.abs(f) > 1.0E-5D) {
				GlStateManager.translatef(0.5F, 0.5F, 0.5F);
				GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
				GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
			}

			this.applyLidRotation(tileEntityIn, partialTicks, chestmodel);
			chestmodel.renderAll();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			if (destroyStage >= 0) {
				GlStateManager.matrixMode(5890);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(5888);
			}

		}
	}

	private ChestModel getChestModel(PaintedChestTileEntity tileEntityIn, int destroyStage, boolean doubleChest) {
		ResourceLocation resourcelocation;
		if (destroyStage >= 0) {
			resourcelocation = DESTROY_STAGES[destroyStage];
		} else {
			resourcelocation = doubleChest ? getTextureDouble(tileEntityIn.getColor()) : getTexture(tileEntityIn.getColor());
		}

		this.bindTexture(resourcelocation);
		return doubleChest ? this.largeChest : this.simpleChest;
	}

	private ResourceLocation getTexture(DyeColor color) {
		switch (color) {
		case WHITE:
			return TEXTURE_WHITE;
		case ORANGE:
			return TEXTURE_ORANGE;
		case MAGENTA:
			return TEXTURE_MAGENTA;
		case LIGHT_BLUE:
			return TEXTURE_LIGHT_BLUE;
		case YELLOW:
			return TEXTURE_YELLOW;
		case LIME:
			return TEXTURE_LIME;
		case PINK:
			return TEXTURE_PINK;
		case GRAY:
			return TEXTURE_GRAY;
		case LIGHT_GRAY:
			return TEXTURE_LIGHT_GRAY;
		case CYAN:
			return TEXTURE_CYAN;
		case PURPLE:
			return TEXTURE_PURPLE;
		case BLUE:
			return TEXTURE_BLUE;
		case BROWN:
			return TEXTURE_BROWN;
		case GREEN:
			return TEXTURE_GREEN;
		case RED:
			return TEXTURE_RED;
		case BLACK:
			return TEXTURE_BLACK;
		default:
			return TEXTURE_RED;
		}
	}

	private ResourceLocation getTextureDouble(DyeColor color) {
		switch (color) {
		case WHITE:
			return TEXTURE_WHITE_DOUBLE;
		case ORANGE:
			return TEXTURE_ORANGE_DOUBLE;
		case MAGENTA:
			return TEXTURE_MAGENTA_DOUBLE;
		case LIGHT_BLUE:
			return TEXTURE_LIGHT_BLUE_DOUBLE;
		case YELLOW:
			return TEXTURE_YELLOW_DOUBLE;
		case LIME:
			return TEXTURE_LIME_DOUBLE;
		case PINK:
			return TEXTURE_PINK_DOUBLE;
		case GRAY:
			return TEXTURE_GRAY_DOUBLE;
		case LIGHT_GRAY:
			return TEXTURE_LIGHT_GRAY_DOUBLE;
		case CYAN:
			return TEXTURE_CYAN_DOUBLE;
		case PURPLE:
			return TEXTURE_PURPLE_DOUBLE;
		case BLUE:
			return TEXTURE_BLUE_DOUBLE;
		case BROWN:
			return TEXTURE_BROWN_DOUBLE;
		case GREEN:
			return TEXTURE_GREEN_DOUBLE;
		case RED:
			return TEXTURE_RED_DOUBLE;
		case BLACK:
			return TEXTURE_BLACK_DOUBLE;
		default:
			return TEXTURE_RED_DOUBLE;
		}
	}

	private void applyLidRotation(PaintedChestTileEntity p_199346_1_, float p_199346_2_, ChestModel p_199346_3_) {
		float f = ((IChestLid) p_199346_1_).getLidAngle(p_199346_2_);
		f = 1.0F - f;
		f = 1.0F - f * f * f;
		p_199346_3_.getLid().rotateAngleX = -(f * ((float) Math.PI / 2F));
	}*/
}
