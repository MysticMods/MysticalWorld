package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.init.ModTileEntities;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PaintedChestTileEntity extends ChestTileEntity {

	private DyeColor color;

	public PaintedChestTileEntity(TileEntityType<?> typeIn, DyeColor color) {
		super(typeIn);
		this.color = color;
	}

	public PaintedChestTileEntity(DyeColor color) {
		this(ModTileEntities.RED_PAINTED_CHEST, color);
	}

	public DyeColor getColor() {
		return color;
	}
}
