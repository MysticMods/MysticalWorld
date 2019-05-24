package epicsquid.mysticalworld.materials;

import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IMetal extends IMaterial {

  @Nullable
  Item getIngot();

  @Nonnull
  Item setIngot(@Nonnull Item item);

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

}
