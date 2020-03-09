package epicsquid.mysticalworld.integration.patchouli.api;

import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;
import scala.annotation.meta.field;
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
        this.animal = ConfigManager.deer;
        break;
      case "frog":
        this.animal = ConfigManager.frog;
        break;
      case "sprout":
        this.animal = ConfigManager.sprout;
        break;
      case "beetle":
        this.animal = ConfigManager.beetle;
        break;
      case "fox":
        this.animal = ConfigManager.fox;
        break;
      case "endermini":
        this.animal = ConfigManager.endermini;
        break;
      case "owl":
        this.animal = ConfigManager.owl;
        break;
      case "silkworm":
        this.animal = ConfigManager.silkworm;
        break;
      case "lava_cat":
        this.animal = ConfigManager.lavaCat;
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
        return I18n.format("mysticalworld.patchouli.standard_group");
      } else {
        return I18n.format("mysticalworld.patchouli.groups", getInt("min"), getInt("max"));
      }
    }
    if (s.startsWith("biomes")) {
      if (animalName.equals("squid")) {
        return I18n.format("forge.biome.tags.default.name");
      } else if (animalName.equals("endermini")) {
        return I18n.format("forge.biome.tags.end.name");
      } else {
        StringJoiner joiner = new StringJoiner(", ");
        for (String biomeName : getString("biomes")) {
          joiner.add(I18n.format("forge.biome.tags." + biomeName.toLowerCase() + ".name"));
        }
        return I18n.format("mysticalworld.patchouli.biomes", joiner.toString());
      }
    }
    if (s.startsWith("title")) {
      return I18n.format("entity.entity_" + this.animalName.toLowerCase() + ".name");
      //WordUtils.capitalize(this.animalName.toLowerCase());
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
