package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStructures {
/*  private static final Set<StructureFeature<?>> STRUCTURES = new HashSet<>();

  public static final StructureFeature<NoneFeatureConfiguration> HUT_STRUCTURE = register("hut", new HutStructure(NoneFeatureConfiguration.CODEC));
  public static final StructureFeature<NoneFeatureConfiguration> BARROW_STRUCTURE = register("barrow", new BarrowStructure(NoneFeatureConfiguration.CODEC));
  public static final StructureFeature<NoneFeatureConfiguration> RUINED_HUT_STRUCTURE = register("ruined_hut", new RuinedHutStructure(NoneFeatureConfiguration.CODEC));
  public static final StructureFeature<NoneFeatureConfiguration> SAND_HOUSE_STRUCTURE = register("sand_house", new SandHouseStructure(NoneFeatureConfiguration.CODEC));

  private static <T extends FeatureConfiguration> StructureFeature<T> register(String name, StructureFeature<T> feature) {
    ResourceLocation rl = new ResourceLocation(MysticalWorld.MODID, name);
    feature.setRegistryName(rl);
    STRUCTURES.add(feature);
    return feature;
  }

  @SubscribeEvent
  public static void register(RegistryEvent.Register<StructureFeature<?>> event) {
    event.getRegistry().registerAll(STRUCTURES.toArray(new StructureFeature[0]));
  }

  public static void setupStructures() {
    setupStructure(HUT_STRUCTURE, new StructureFeatureConfiguration(45, 20, 12886419), true);
    setupStructure(BARROW_STRUCTURE, new StructureFeatureConfiguration(150, 65, 314159223), true);
    setupStructure(RUINED_HUT_STRUCTURE, new StructureFeatureConfiguration(70, 35, 8266497), true);
    setupStructure(SAND_HOUSE_STRUCTURE, new StructureFeatureConfiguration(90, 40, 11496812), true);
  }

  public static <F extends StructureFeature<?>> void setupStructure(F structure, StructureFeatureConfiguration structureSeparationSettings, boolean transformSurroundingLand) {
    StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

    if (transformSurroundingLand) {
      StructureFeature.NOISE_AFFECTING_FEATURES = ImmutableList.<StructureFeature<?>>builder().addAll(StructureFeature.NOISE_AFFECTING_FEATURES).add(structure).build();
    }

    StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder().putAll(StructureSettings.DEFAULTS).put(structure, structureSeparationSettings).build();

    BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
      Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

      if (structureMap instanceof ImmutableMap) {
        Map<StructureFeature<?>, StructureFeatureConfiguration> temp = new HashMap<>(structureMap);
        temp.put(structure, structureSeparationSettings);
        settings.getValue().structureSettings().structureConfig = temp;
      } else {
        structureMap.put(structure, structureSeparationSettings);
      }
    });
  }*/
}
