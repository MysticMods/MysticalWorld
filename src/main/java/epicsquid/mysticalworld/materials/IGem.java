package epicsquid.mysticalworld.materials;

import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IGem extends IMaterial {
  @Nullable
  Item getGem();

  @Nonnull
  Item setGem(@Nonnull Item item);
}
