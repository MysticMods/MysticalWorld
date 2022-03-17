package mysticmods.mysticalworld.init.configured;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;

public class ConfiguredFeatures {
  public static final ConfiguredRegistry<ConfiguredFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, BuiltinRegistries.CONFIGURED_FEATURE);

  /*  public static final ConfiguredFeature<?, ?> HUGE_UNCANNY_MUSHROOM = register("huge_uncanny_mushroom", Feature.HUGE_RED_MUSHROOM.configured(new HugeMushroomFeatureConfiguration(new SupplierBlockStateProvider("mysticalworld", "uncanny_mushroom_block").addPair("down", false), new SimpleStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()), 2)));*/

  private static ConfiguredFeature<?, ?> register(String id, ConfiguredFeature<?, ?> feature) {
    return REGISTRY.register(id, feature);
  }

  public static void load() {
  }
}
