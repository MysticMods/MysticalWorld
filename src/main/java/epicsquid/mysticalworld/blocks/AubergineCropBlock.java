package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.items.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

public class AubergineCropBlock extends CropsBlock {
	public AubergineCropBlock (Properties builder) {
		super(builder);
	}

	@Override
	protected IItemProvider getSeedsItem () {
		return ModItems.AUBERGINE_SEEDS;
	}
}