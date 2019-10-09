package epicsquid.mysticalworld.integration.harvest;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.init.Items;
import tehnut.harvest.BlockStack;
import tehnut.harvest.Crop;
import tehnut.harvest.Harvest;

public class HarvestIntegration {
  public static void init() {
    BlockStack stack = new BlockStack(ModBlocks.aubergine, 7);
    Crop crop = new Crop(ModBlocks.aubergine, 7);
    Harvest.config.getCropMap().put(stack, crop);

    stack = new BlockStack(ModBlocks.poisoned_potato, 7);
    crop = new Crop(ModBlocks.poisoned_potato, 7);
    Harvest.config.getCropMap().put(stack, crop);
  }
}
