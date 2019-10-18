package epicsquid.mysticalworld.blocks;

import net.minecraft.block.ChestBlock;
import net.minecraft.item.DyeColor;

public class PaintedChestBlock extends ChestBlock {

  private DyeColor color;

  public PaintedChestBlock(Properties properties, DyeColor color) {
    super(properties);
    this.color = color;
  }

	/*public DyeColor getColor() {
		return color;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return getTypeFromColor(color).create();
	}

	public static TileEntityType<?> getTypeFromColor(DyeColor color) {
		switch (color) {
		case WHITE:
			return ModTileEntities.WHITE_PAINTED_CHEST;
		case ORANGE:
			return ModTileEntities.ORANGE_PAINTED_CHEST;
		case MAGENTA:
			return ModTileEntities.MAGENTA_PAINTED_CHEST;
		case LIGHT_BLUE:
			return ModTileEntities.LIGHT_BLUE_PAINTED_CHEST;
		case YELLOW:
			return ModTileEntities.YELLOW_PAINTED_CHEST;
		case LIME:
			return ModTileEntities.LIME_PAINTED_CHEST;
		case PINK:
			return ModTileEntities.PINK_PAINTED_CHEST;
		case GRAY:
			return ModTileEntities.GRAY_PAINTED_CHEST;
		case LIGHT_GRAY:
			return ModTileEntities.LIGHT_GRAY_PAINTED_CHEST;
		case CYAN:
			return ModTileEntities.CYAN_PAINTED_CHEST;
		case PURPLE:
			return ModTileEntities.PURPLE_PAINTED_CHEST;
		case BLUE:
			return ModTileEntities.BLUE_PAINTED_CHEST;
		case BROWN:
			return ModTileEntities.BROWN_PAINTED_CHEST;
		case GREEN:
			return ModTileEntities.GREEN_PAINTED_CHEST;
		case RED:
			return ModTileEntities.RED_PAINTED_CHEST;
		case BLACK:
			return ModTileEntities.BLACK_PAINTED_CHEST;
		default:
			return ModTileEntities.RED_PAINTED_CHEST;
		}
	}*/
}
