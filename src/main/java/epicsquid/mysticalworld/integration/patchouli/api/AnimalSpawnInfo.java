package epicsquid.mysticalworld.integration.patchouli.api;

import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.common.util.ItemStackUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.StringJoiner;

@SuppressWarnings("unused")
public class AnimalSpawnInfo implements IComponentProcessor {
  private String animalName;
  private Object animal;

  @Override
  public void setup(IVariableProvider<String> iVariableProvider) {
    String name = iVariableProvider.get("animal");
    this.animalName = name;
    switch (name) {
      case "deer":
        this.animal = ConfigManager.DEER_CONFIG;
        break;
      case "frog":
        this.animal = ConfigManager.FROG_CONFIG;
        break;
      case "sprout":
        this.animal = ConfigManager.SPROUT_CONFIG;
        break;
      case "beetle":
        this.animal = ConfigManager.BEETLE_CONFIG;
        break;
      case "fox":
        this.animal = ConfigManager.SILVER_FOX_CONFIG;
        break;
      case "endermini":
        this.animal = ConfigManager.ENDERMINI_CONFIG;
        break;
      case "lavacat":
        this.animal = ConfigManager.LAVA_CAT_CONFIG;
        break;
      case "owl":
        this.animal = ConfigManager.OWL_CONFIG;
        break;
      default:
        this.animal = null;
        break;
    }
  }

  private int getInt(String name) {
    Field field;
    try {
      field = animal.getClass().getField(name);
    } catch (NoSuchFieldException e) {
      return 0;
    }
    try {
      return field.getInt(animal);
    } catch (IllegalAccessException e) {
      return 0;
    }
  }

  private String[] getString(String name) {
    Field field;
    try {
      field = animal.getClass().getField(name);
    } catch (NoSuchFieldException e) {
      return new String[]{};
    }
    try {
      return (String[]) field.get(animal);
    } catch (IllegalAccessException e) {
      return new String[]{};
    }
  }

  @Override
  public String process(String s) {
    if (this.animal == null && !animalName.equals("squid")) return null;
    if (s.startsWith("groupSize")) {
      if (animalName.equals("squid")) {
        return "Standard group sizes.";
      } else {
        return String.format("Groups of: %d-%d", getInt("min"), getInt("max"));
      }
    }
    if (s.startsWith("biomes")) {
      if (animalName.equals("squid")) {
        return "Default biomes.";
      } else if (animalName.equals("endermini")) {
        return "The End";
      } else {
        StringJoiner joiner = new StringJoiner(", ");
        for (String biomeName : getString("biomes")) {
          joiner.add(WordUtils.capitalize(biomeName.toLowerCase()));
        }
        return "Biomes tagged with: " + joiner.toString();
      }
    }
    if (s.startsWith("title")) {
      return WordUtils.capitalize(this.animalName.toLowerCase());
    }
    if (s.startsWith("item")) {
      List<ItemStack> drops = StandardDrops.getDrops(animalName);
      int index = Integer.parseInt(s.replace("item", "")) - 1;
      if (index < drops.size()) {
        return ItemStackUtil.serializeStack(drops.get(index));
      } else {
        return ItemStackUtil.serializeStack(ItemStack.EMPTY);
      }
    }
    return null;
  }
}
