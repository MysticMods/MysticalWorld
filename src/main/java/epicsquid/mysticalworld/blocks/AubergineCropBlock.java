package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;

@SuppressWarnings("ConstantConditions")
public class AubergineCropBlock extends CropsBlock {
  public AubergineCropBlock(Properties builder) {
    super(builder);
  }

  @Override
  protected IItemProvider getSeedsItem() {
    return () -> ModItems.AUBERGINE_SEEDS.get();
  }
}
