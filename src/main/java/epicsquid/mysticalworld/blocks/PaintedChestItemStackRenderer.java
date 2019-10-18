package epicsquid.mysticalworld.blocks;

import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;

public class PaintedChestItemStackRenderer extends ItemStackTileEntityRenderer {

	/*@Override
	public void renderByItem(ItemStack itemStackIn) {
		if (Block.getBlockFromItem(itemStackIn.getItem()) instanceof PaintedChestBlock) {
			PaintedChestBlock block = (PaintedChestBlock) Block.getBlockFromItem(itemStackIn.getItem());
			TileEntityRendererDispatcher.instance.renderAsItem(new PaintedChestTileEntity(PaintedChestBlock.getTypeFromColor(block.getColor()), block.getColor()));
		} else {
			super.renderByItem(itemStackIn);
		}
	}*/
}
