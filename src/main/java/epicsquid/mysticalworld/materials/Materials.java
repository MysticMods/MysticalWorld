package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.material.MaterialTypes;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class Materials {
  public static Material copper;
  public static Material silver;
  public static Material amethyst;

  public static List<Material> getMaterials() {
    return Arrays.asList(copper, silver, amethyst);
  }

  public static void createMaterials() {
    copper = new MaterialCopper("Copper", 3.5f, 0.65f, 1, -1, -1, MaterialTypes.material("mysticalworld:copper"), true);
    silver = new MaterialSilver("Silver", 5f, 0.35f, 2, -1, -1, MaterialTypes.material("mysticalworld:silver"), true);
    amethyst = new MaterialAmethyst("Amethyst", 3.0f, 0.7f, 2, 3, 7, MaterialTypes.material("mysticalworld:amethyst"), true);
  }

  public static void initMaterials(@Nonnull RegisterContentEvent event) {
    createMaterials();
    getMaterials().forEach(o -> o.initMaterial(event));
  }

  public static void registerOreDictionary() {
    getMaterials().forEach(Material::initOreDictionary);
  }
}
