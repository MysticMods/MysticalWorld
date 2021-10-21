package mysticmods.mysticalworld.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.world.structures.BarrowStructure;
import mysticmods.mysticalworld.world.structures.HutStructure;
import mysticmods.mysticalworld.world.structures.RuinedHutStructure;
import mysticmods.mysticalworld.world.structures.SandHouseStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStructures {
  private static final Set<Structure<?>> STRUCTURES = new HashSet<>();

  public static final Structure<NoFeatureConfig> HUT_STRUCTURE = register("hut", new HutStructure(NoFeatureConfig.CODEC));
  public static final Structure<NoFeatureConfig> BARROW_STRUCTURE = register("barrow", new BarrowStructure(NoFeatureConfig.CODEC));
  public static final Structure<NoFeatureConfig> RUINED_HUT_STRUCTURE = register("ruined_hut", new RuinedHutStructure(NoFeatureConfig.CODEC));
  public static final Structure<NoFeatureConfig> SAND_HOUSE_STRUCTURE = register("sand_house", new SandHouseStructure(NoFeatureConfig.CODEC));

  private static <T extends IFeatureConfig> Structure<T> register(String name, Structure<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalWorld.MODID, name);
    feature.setRegistryName(rl);
    STRUCTURES.add(feature);
    return feature;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<Structure<?>> event) {
    event.getRegistry().registerAll(STRUCTURES.toArray(new Structure[0]));
  }

  public static void setupStructures() {
    setupStructure(HUT_STRUCTURE, new StructureSeparationSettings(45, 20, 12886419), true);
    setupStructure(BARROW_STRUCTURE, new StructureSeparationSettings(150, 65, 314159223), true);
    setupStructure(RUINED_HUT_STRUCTURE, new StructureSeparationSettings(70, 35, 8266497), true);
    setupStructure(SAND_HOUSE_STRUCTURE, new StructureSeparationSettings(90, 40, 11496812), true);
  }

  public static <F extends Structure<?>> void setupStructure(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
    Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

    if (transformSurroundingLand) {
      Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder().addAll(Structure.NOISE_AFFECTING_FEATURES).add(structure).build();
    }

    DimensionStructuresSettings.DEFAULTS = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings).build();

    WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
      Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();

      if (structureMap instanceof ImmutableMap) {
        Map<Structure<?>, StructureSeparationSettings> temp = new HashMap<>(structureMap);
        temp.put(structure, structureSeparationSettings);
        settings.getValue().structureSettings().structureConfig = temp;
      } else {
        structureMap.put(structure, structureSeparationSettings);
      }
    });
  }
}
