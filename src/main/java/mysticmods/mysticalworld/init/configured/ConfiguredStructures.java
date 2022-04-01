package mysticmods.mysticalworld.init.configured;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConfiguredStructures {
  public static final ConfiguredRegistry<ConfiguredStructureFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE);

  public static Map<StructureFeature<?>, ConfiguredRegistry.Entry<ConfiguredStructureFeature<?, ?>>> CONFIGURED_STRUCTURES = new HashMap<>();

/*  public static ConfiguredStructureFeature<?, ?> CONFIGURED_HUT = register("hut", ModStructures.HUT_STRUCTURE, ModStructures.HUT_STRUCTURE.configured(FeatureConfiguration.NONE));
  public static ConfiguredStructureFeature<?, ?> CONFIGURED_BARROW = register("barrow", ModStructures.BARROW_STRUCTURE, ModStructures.BARROW_STRUCTURE.configured(FeatureConfiguration.NONE));
  public static ConfiguredStructureFeature<?, ?> CONFIGURED_RUINED_HUT = register("ruined_hut", ModStructures.RUINED_HUT_STRUCTURE, ModStructures.RUINED_HUT_STRUCTURE.configured(FeatureConfiguration.NONE));
  public static ConfiguredStructureFeature<?, ?> CONFIGURED_SAND_HOUSE = register("sand_house", ModStructures.SAND_HOUSE_STRUCTURE, ModStructures.SAND_HOUSE_STRUCTURE.configured(FeatureConfiguration.NONE));*/

  private static ConfiguredRegistry.Entry<ConfiguredStructureFeature<?, ?>> register(String id, @Nullable StructureFeature<?> structure, Supplier<ConfiguredStructureFeature<?, ?>> feature) {
    ConfiguredRegistry.Entry<ConfiguredStructureFeature<?, ?>> result = REGISTRY.register(id, feature);
    CONFIGURED_STRUCTURES.put(structure, result);

    return result;
  }

/*  public static void registerStructures() {
    FlatLevelGeneratorSettings.STRUCTURE_FEATURES.putAll(CONFIGURED_STRUCTURES);
  }*/
}
