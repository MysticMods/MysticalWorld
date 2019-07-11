package epicsquid.mysticalworld.blocks;

import epicsquid.mysticallib.block.BaseCropBlock;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nonnull;

public class BlockAubergineCrop extends BaseCropBlock {

	public BlockAubergineCrop(Properties props, @Nonnull PlantType plantType) {
		super(props, plantType);
	}

//	/**
//	 * Gets the seed to drop for the crop
//	 */
//	@Override
//	@Nonnull
//	public Item getSeed() {
//		return ModItems.aubergine_seed;
//	}
//
//	/**
//	 * Gets the crop to drop for the plant
//	 */
//	@Override
//	@Nonnull
//	public Item getCrop() {
//		return ModItems.aubergine;
//	}

}
