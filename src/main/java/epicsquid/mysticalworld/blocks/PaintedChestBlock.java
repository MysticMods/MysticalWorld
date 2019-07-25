package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.init.ModTileEntities;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class PaintedChestBlock extends ChestBlock {

	public PaintedChestBlock(Properties properties) {
		super(properties);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return ModTileEntities.RED_PAINTED_CHEST.create();
	}
}
