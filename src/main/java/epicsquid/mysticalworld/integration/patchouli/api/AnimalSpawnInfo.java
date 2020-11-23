package epicsquid.mysticalworld.integration.patchouli.api;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.config.MobConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import org.apache.commons.lang3.text.WordUtils;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.List;
import java.util.StringJoiner;

@SuppressWarnings("unused")
public class AnimalSpawnInfo implements IComponentProcessor {
  private String animalName;
  private MobConfig animal;

  @Override
  public void setup(IVariableProvider iVariableProvider) {
    String name = iVariableProvider.get("animal").asString();
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
      case "lava_cat":
        this.animal = ConfigManager.LAVA_CAT_CONFIG;
        break;
      case "owl":
        this.animal = ConfigManager.OWL_CONFIG;
        break;
      case "hell_sprout":
        this.animal = ConfigManager.HELL_SPROUT_CONFIG;
        break;
      default:
        this.animal = null;
        break;
    }
  }

  @Override
  public IVariable process(String s) {
    if (this.animal == null && !animalName.equals("squid")) return null;
    if (s.startsWith("groupSize")) {
      if (animalName.equals("squid")) {
        return IVariable.wrap("Standard group sizes.");
      } else {
        return IVariable.wrap(String.format("Groups of: %d-%d", animal.getMin(), animal.getMax()));
      }
    }
    if (s.startsWith("biomes")) {
      if (animalName.equals("squid")) {
        return IVariable.wrap("Default biomes.");
      } else if (animalName.equals("endermini")) {
        return IVariable.wrap("The End");
      } else {
        StringJoiner joiner = new StringJoiner(", ");
        for (BiomeDictionary.Type biomeName : animal.getBiomes()) {
          // TODO: Localise according to 1.12
          joiner.add(WordUtils.capitalize(biomeName.getName().toLowerCase()));
        }
        return IVariable.wrap("Biomes tagged with: " + joiner.toString());
      }
    }
    if (s.startsWith("title")) {
      if (this.animalName.contains("_")) {
        return IVariable.wrap(WordUtils.capitalize(this.animalName.replace("_", " ")));
      }
      return IVariable.wrap(WordUtils.capitalize(this.animalName.toLowerCase()));
    }
    if (s.startsWith("item")) {
      List<ItemStack> drops = StandardDrops.getDrops(animalName);
      int index = Integer.parseInt(s.replace("item", "")) - 1;
      if (index < drops.size()) {
        return IVariable.from(drops.get(index));
      } else {
        return IVariable.from(ItemStack.EMPTY);
      }
    }
    return null;
  }
}
