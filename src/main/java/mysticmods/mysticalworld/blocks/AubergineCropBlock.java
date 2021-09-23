package mysticmods.mysticalworld.blocks;

import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

import net.minecraft.block.AbstractBlock.Properties;

@SuppressWarnings("ConstantConditions")
public class AubergineCropBlock extends CropsBlock {
  public AubergineCropBlock(Properties builder) {
    super(builder);
  }

  @Override
  protected IItemProvider getBaseSeedId() {
    return () -> ModItems.AUBERGINE_SEEDS.get();
  }
}
