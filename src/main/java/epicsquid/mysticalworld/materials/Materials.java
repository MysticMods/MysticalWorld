package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.material.MaterialTypes;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Materials {
  public static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
  public static Material copper;
  public static Material silver;
  public static Material amethyst;
  public static Material quartz;

  public static List<Material> getMaterials() {
    return Arrays.asList(copper, silver, amethyst, quartz);
  }

  public static void createMaterials() {
    copper = new MaterialCopper("Copper", 3.5f, 0.65f, 1, -1, -1, MaterialTypes.material("mysticalworld:copper"), true);
    silver = new MaterialSilver("Silver", 5f, 0.35f, 2, -1, -1, MaterialTypes.material("mysticalworld:silver"), true);
    amethyst = new MaterialAmethyst("Amethyst", 3.0f, 0.7f, 2, 3, 7, MaterialTypes.material("mysticalworld:amethyst"), true);
    quartz = new MaterialQuartz("Quartz", 3.0f, 3.0f, 1, 2, 5, null, false);
  }

  public static void initMaterials(@Nonnull RegisterContentEvent event) {
    createMaterials();
    getMaterials().forEach(o -> o.initMaterial(event));
    copper.getMaterial().setRepairItem(new ItemStack(copper.getItem()));
    silver.getMaterial().setRepairItem(new ItemStack(silver.getItem()));
    amethyst.getMaterial().setRepairItem(new ItemStack(amethyst.getItem()));
  }

  public static void registerOreDictionary() {
    getMaterials().forEach(Material::initOreDictionary);
  }
}
