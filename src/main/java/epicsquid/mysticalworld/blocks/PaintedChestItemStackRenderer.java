package epicsquid.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class PaintedChestItemStackRenderer extends ItemStackTileEntityRenderer {

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		if (Block.getBlockFromItem(itemStackIn.getItem()) instanceof ChestBlock) {
			TileEntityRendererDispatcher.instance.renderAsItem(new PaintedChestTileEntity());
		} else {
			super.renderByItem(itemStackIn);
		}
	}
}
