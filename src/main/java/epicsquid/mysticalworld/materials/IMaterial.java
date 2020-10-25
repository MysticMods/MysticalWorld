package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.event.RegisterContentEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IMaterial {
  boolean hasTool();

  Item.ToolMaterial getMaterial();

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

  @Nullable
  Item getItem();

  @Nonnull
  Item setItem(@Nonnull Item item);

  @Nullable
  Item getDust();

  @Nonnull
  Item setDust(@Nonnull Item dust);

  @Nullable
  Item getDustTiny();

  @Nonnull
  Item setDustTiny(@Nonnull Item dustTiny);

  @Nullable
  Item getNugget();

  @Nonnull
  Item setNugget(@Nonnull Item nugget);

  int getLevel();

  int getMinXP();

  int getMaxXP();

  void initMaterial(@Nonnull RegisterContentEvent event);

  void initOreDictionary();
}
