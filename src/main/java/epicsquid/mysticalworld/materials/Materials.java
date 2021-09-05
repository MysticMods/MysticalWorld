package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

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

  public static ItemArmor.ArmorMaterial copperArmor = EnumHelper.addArmorMaterial("mysticalworld:copper", MysticalWorld.MODID + ":copper", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
  public static ItemArmor.ArmorMaterial silverArmor = EnumHelper.addArmorMaterial("mysticalworld:silver", MysticalWorld.MODID + ":gold", 25, new int[]{2, 5, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
  public static ItemArmor.ArmorMaterial miscArmor = EnumHelper.addArmorMaterial("mysticalworld:misc", MysticalWorld.MODID + ":misc", 15, new int[]{2, 1, 1, 1}, 7, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 0.2f);

  public static List<Material> getMaterials() {
    return Arrays.asList(copper, silver, amethyst, quartz);
  }

  public static void createMaterials() {
    Item.ToolMaterial copper = EnumHelper.addToolMaterial("mysticalworld:copper", 2, 200, 4.0f, 2.0f, 7);
    Item.ToolMaterial silver = EnumHelper.addToolMaterial("mysticalworld:silver", 2, 175, 6.0f, 2.5f, 18);
    Item.ToolMaterial amethyst = EnumHelper.addToolMaterial("mysticalworld:amethyst", 3, 1561, 8.0f, 3.0f, 12);
    Item.ToolMaterial quartz = EnumHelper.addToolMaterial("mysticalworld:quartz", 0, 99, 1.0f, 1.0f, 1);
    MaterialTypes.addMaterial("mysticalworld:copper", copper, copperArmor, 2.5f, -1.5f);
    MaterialTypes.addMaterial("mysticalworld:silver", silver, silverArmor, 2.5f, -1.0f);
    MaterialTypes.addMaterial("mysticalworld:amethyst", amethyst, null, 3f, -1.0f);
    MaterialTypes.addMaterial("mysticalworld:quartz", quartz, null, 0f, -1.5f);
    Materials.copper = new MaterialCopper("Copper", 3.5f, 0.65f, 1, -1, -1, MaterialTypes.material("mysticalworld:copper"), true);
    Materials.silver = new MaterialSilver("Silver", 5f, 0.35f, 2, -1, -1, MaterialTypes.material("mysticalworld:silver"), true);
    Materials.amethyst = new MaterialAmethyst("Amethyst", 3.0f, 0.7f, 2, 3, 7, MaterialTypes.material("mysticalworld:amethyst"), true);
    Materials.quartz = new MaterialQuartz("Quartz", 3.0f, 3.0f, 1, 2, 5, MaterialTypes.material("mysticalworld:quartz"), false);
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
    if (ConfigManager.iron.enableDusts) {
      OreDictionary.registerOre("dustIron", ModItems.iron_dust);
    }
    if (ConfigManager.iron.enableTinyDusts) {
      OreDictionary.registerOre("tinyDustIron", ModItems.iron_dust_tiny);
    }
    if (ConfigManager.gold.enableDusts) {
      OreDictionary.registerOre("dustGold", ModItems.gold_dust);
    }
    if (ConfigManager.gold.enableTinyDusts) {
      OreDictionary.registerOre("tinyDustGold", ModItems.gold_dust_tiny);
    }
  }
}
