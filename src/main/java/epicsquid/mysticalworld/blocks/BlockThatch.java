package epicsquid.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.util.BlockRenderLayer;

import javax.annotation.Nonnull;

public class BlockThatch extends Block {

	public BlockThatch(Properties props) {
		super(props);
	}

	@Nonnull
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

}