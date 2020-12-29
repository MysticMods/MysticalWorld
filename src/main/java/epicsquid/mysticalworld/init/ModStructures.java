package epicsquid.mysticalworld.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.world.structures.BarrowStructure;
import epicsquid.mysticalworld.world.structures.HutStructure;
import epicsquid.mysticalworld.world.structures.RuinedHutStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStructures {
  private static Set<Structure<?>> STRUCTURES = new HashSet<>();

  public static final Structure<NoFeatureConfig> HUT_STRUCTURE = register("hut", new HutStructure(NoFeatureConfig.field_236558_a_));
  public static final Structure<NoFeatureConfig> BARROW_STRUCTURE = register("barrow", new BarrowStructure(NoFeatureConfig.field_236558_a_));
  public static final Structure<NoFeatureConfig> RUINED_HUT_STRUCTURE = register("ruined_hut", new RuinedHutStructure(NoFeatureConfig.field_236558_a_));

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
    setupStructure(HUT_STRUCTURE, new StructureSeparationSettings(45, 20, 0xf6804c10), true);
    setupStructure(BARROW_STRUCTURE, new StructureSeparationSettings(150, 65, 0x3679f17f), true);
    setupStructure(RUINED_HUT_STRUCTURE, new StructureSeparationSettings(70, 35, 0xc455139f), true);
  }

  public static <F extends Structure<?>> void setupStructure(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
    Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

    if (transformSurroundingLand) {
      Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_).add(structure).build();
    }

    DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.field_236191_b_).put(structure, structureSeparationSettings).build();
  }
}
