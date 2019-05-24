package epicsquid.mysticalworld.materials;

import net.minecraft.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IMaterial {
  String name();

  float getHardness();

  float getExperience();

  @Nonnull
  String getOredictNameSuffix();

  @Nullable
  Block getBlock();

  @Nonnull
  Block setBlock(@Nonnull Block block);

  @Nullable
  Block getOre();

  @Nonnull
  Block setOre(@Nonnull Block ore);

  boolean isEnabled();

  boolean hasGrindables();

  boolean hasOre();

  int getLevel();

  int getMinXP();

  int getMaxXP();
}
