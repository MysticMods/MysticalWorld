package epicsquid.mysticalworld.charm;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.world.World;

public class CharmEffect {
  private Enchantment replacement;
  private String name;

  public CharmEffect(Enchantment replacement, String name) {
    this.replacement = replacement;
    this.name = name;
  }

  public Enchantment getReplacement() {
    return replacement;
  }

  public String getName() {
    return "mysticalworld.charm.effect." + name;
  }

  public String getTranslatedName(int level) {
    String s = I18n.format(this.getName());

    return level == 1 && this.replacement.getMaxLevel() == 1 ? s : s + " " + I18n.format("enchantment.level." + level);
  }
}
