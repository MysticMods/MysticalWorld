package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.init.ModTileEntities;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PaintedChestTileEntity extends ChestTileEntity {

	public PaintedChestTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	public PaintedChestTileEntity() {
		this(ModTileEntities.RED_PAINTED_CHEST);
	}
}
