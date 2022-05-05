package mysticmods.mysticalworld.world;

import com.mojang.serialization.Codec;
import mysticmods.mysticalworld.init.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.Random;

public class DebugPlacement extends PlacementFilter {
  private static final DebugPlacement INSTANCE = new DebugPlacement();
  public static final Codec<DebugPlacement> CODEC = Codec.unit(() -> INSTANCE);

  public DebugPlacement() {
  }

  @Override
  protected boolean shouldPlace(PlacementContext p_191835_, Random p_191836_, BlockPos p_191837_) {
    return true;
  }

  @Override
  public PlacementModifierType<?> type() {
    return ModFeatures.DEBUG_PLACEMENT.get();
  }

  public static DebugPlacement debug() {
    return INSTANCE;
  }

  public static class Type implements PlacementModifierType<DebugPlacement> {
    @Override
    public Codec<DebugPlacement> codec() {
      return CODEC;
    }
  }
}
