package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.world.SupplierBlockStateProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class ConfiguredFeatures {
  public static final ConfiguredRegistry<ConfiguredFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, BuiltinRegistries.CONFIGURED_FEATURE);

  public static final ConfiguredFeature<?, ?> HUGE_UNCANNY_MUSHROOM = register("huge_uncanny_mushroom", Feature.HUGE_RED_MUSHROOM.configured(new HugeMushroomFeatureConfiguration(new SupplierBlockStateProvider("mysticalworld", "uncanny_mushroom_block").addPair("down", false), new SimpleStateProvider(Blocks.MUSHROOM_STEM.defaultBlockState()), 2)));

  private static ConfiguredFeature<?, ?> register(String id, ConfiguredFeature<?, ?> feature) {
    return REGISTRY.register(id, feature);
  }

  public static void load() {
  }
}
