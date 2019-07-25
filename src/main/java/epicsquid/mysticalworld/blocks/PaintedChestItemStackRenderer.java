package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.init.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;

public class PaintedChestItemStackRenderer extends ItemStackTileEntityRenderer {

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		if (Block.getBlockFromItem(itemStackIn.getItem()) instanceof PaintedChestBlock) {
			PaintedChestBlock block = (PaintedChestBlock) Block.getBlockFromItem(itemStackIn.getItem());
			TileEntityRendererDispatcher.instance.renderAsItem(new PaintedChestTileEntity(PaintedChestBlock.getTypeFromColor(block.getColor()), block.getColor()));
		} else {
			super.renderByItem(itemStackIn);
		}
	}
}
