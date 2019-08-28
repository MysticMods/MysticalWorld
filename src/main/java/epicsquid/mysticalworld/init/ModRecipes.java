package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.materials.Gem;
import epicsquid.mysticalworld.materials.Metal;
import jeresources.config.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

import static epicsquid.mysticalworld.init.ModItems.pelt;

public class ModRecipes {

  private static ResourceLocation getRL(@Nonnull String s) {
    return new ResourceLocation(MysticalWorld.MODID + ":" + s);
  }

  public static void initRecipes(@Nonnull RegisterModRecipesEvent event) {
    // TODO: Make this a JSON recipe
    // registerShapeless(event.getRegistry(), "pelt", new ItemStack(Items.LEATHER, 1), new ItemStack(pelt, 1), new ItemStack(pelt, 1));

    for (Metal metal : Metal.values()) {
      if (metal == Metal.silver) {
        if (metal.hasGrindables()) {
          if (ConfigManager.silver.enableDusts && metal.getIngot() != null) {
            GameRegistry.addSmelting(metal.getDust(), new ItemStack(metal.getIngot(), 1), metal.getExperience());
          }
        }
        if (metal.hasOre() && ConfigManager.silver.enableOres && metal.getIngot() != null) {
          GameRegistry.addSmelting(metal.getOre(), new ItemStack(metal.getIngot(), 1), metal.getExperience());
        }
      } else if (metal == Metal.copper) {
        if (metal.hasGrindables()) {
          if (ConfigManager.copper.enableDusts && metal.getIngot() != null) {
            GameRegistry.addSmelting(metal.getDust(), new ItemStack(metal.getIngot(), 1), metal.getExperience());
          }
        }
        if (metal.hasOre() && ConfigManager.copper.enableOres && metal.getIngot() != null) {
          GameRegistry.addSmelting(metal.getOre(), new ItemStack(metal.getIngot(), 1), metal.getExperience());
        }
      }
    }

    if (ConfigManager.gold.enableDusts) {
      GameRegistry.addSmelting(ModItems.gold_dust, new ItemStack(Items.GOLD_INGOT), 0.25f);
    }
    if (ConfigManager.iron.enableDusts) {
      GameRegistry.addSmelting(ModItems.iron_dust, new ItemStack(Items.IRON_INGOT), 0.25f);
    }

    GameRegistry.addSmelting(new ItemStack(ModItems.venison, 1), new ItemStack(ModItems.cooked_venison), 0.1f);
    GameRegistry.addSmelting(ModItems.aubergine, new ItemStack(ModItems.cooked_aubergine), 0.125f);
    GameRegistry.addSmelting(ModItems.raw_squid, new ItemStack(ModItems.cooked_squid, 2), 0.125f);
    GameRegistry.addSmelting(ModBlocks.wet_mud_block, new ItemStack(ModBlocks.mud_block), 0.125f);
    GameRegistry.addSmelting(ModBlocks.wet_mud_brick, new ItemStack(ModBlocks.mud_brick), 0.125f);
    GameRegistry.addSmelting(ModBlocks.charred_log, new ItemStack(Items.COAL, 1, 1), 0.15f);
  }
}
