package epicsquid.mysticalworld.recipes;

import com.google.gson.JsonObject;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

@SuppressWarnings("unused")
public class ConfigurableRecipesFactory implements IConditionFactory {
  @Override
  public BooleanSupplier parse(JsonContext context, JsonObject json) {
    String key = JsonUtils.getString(json, "config");

    switch (key) {
      case "copper": return () -> ConfigManager.toolEnable.enableCopper;
      case "amethyst": return () -> ConfigManager.toolEnable.enableAmethyst;
      case "silver": return () -> ConfigManager.toolEnable.enableSilver;
      case "copper_axe": return () -> ConfigManager.toolEnable.copperTools.copperAxe;
      case "copper_hoe": return () -> ConfigManager.toolEnable.copperTools.copperHoe;
      case "copper_knife": return () -> ConfigManager.toolEnable.copperTools.copperKnife;
      case "copper_pickaxe":  return () -> ConfigManager.toolEnable.copperTools.copperPickaxe;
      case "copper_shovel":  return () -> ConfigManager.toolEnable.copperTools.copperShovel;
      case "copper_sword":  return () -> ConfigManager.toolEnable.copperTools.copperSword;
      case "silver_axe": return () -> ConfigManager.toolEnable.silverTools.silverAxe;
      case "silver_hoe": return () -> ConfigManager.toolEnable.silverTools.silverHoe;
      case "silver_knife": return () -> ConfigManager.toolEnable.silverTools.silverKnife;
      case "silver_pickaxe":  return () -> ConfigManager.toolEnable.silverTools.silverPickaxe;
      case "silver_shovel":  return () -> ConfigManager.toolEnable.silverTools.silverShovel;
      case "silver_sword":  return () -> ConfigManager.toolEnable.silverTools.silverSword;
      case "amethyst_axe": return () -> ConfigManager.toolEnable.amethystTools.amethystAxe;
      case "amethyst_hoe": return () -> ConfigManager.toolEnable.amethystTools.amethystHoe;
      case "amethyst_knife": return () -> ConfigManager.toolEnable.amethystTools.amethystKnife;
      case "amethyst_pickaxe":  return () -> ConfigManager.toolEnable.amethystTools.amethystPickaxe;
      case "amethyst_shovel":  return () -> ConfigManager.toolEnable.amethystTools.amethystShovel;
      case "amethyst_sword":  return () -> ConfigManager.toolEnable.amethystTools.amethystSword;
      default: return () -> false;
    }
  }
}
