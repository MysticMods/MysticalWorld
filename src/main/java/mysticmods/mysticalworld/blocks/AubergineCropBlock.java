package mysticmods.mysticalworld.blocks;

import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.ItemLike;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

@SuppressWarnings("ConstantConditions")
public class AubergineCropBlock extends CropBlock {
  public AubergineCropBlock(Properties builder) {
    super(builder);
  }

  @Override
  protected ItemLike getBaseSeedId() {
    return () -> ModItems.AUBERGINE_SEEDS.get();
  }
}
