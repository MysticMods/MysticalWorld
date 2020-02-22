package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.materials.Materials;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

public class ModRecipes {
  public static void initRecipes(@Nonnull RegisterModRecipesEvent event) {
    if (ConfigManager.silver.enableSilver) {
      if (ConfigManager.silver.enableDusts) {
        GameRegistry.addSmelting(Materials.silver.getDust(), new ItemStack(Materials.silver.getItem(), 1), Materials.silver.getExperience());
      }
      if (ConfigManager.silver.enableOres) {
        GameRegistry.addSmelting(Materials.silver.getOre(), new ItemStack(Materials.silver.getItem(), 1), Materials.silver.getExperience());
      }
    }

    if (ConfigManager.copper.enableCopper) {
      if (ConfigManager.copper.enableDusts) {
        GameRegistry.addSmelting(Materials.copper.getDust(), new ItemStack(Materials.copper.getItem(), 1), Materials.copper.getExperience());
      }
      if (ConfigManager.copper.enableOres) {
        GameRegistry.addSmelting(Materials.copper.getOre(), new ItemStack(Materials.copper.getItem(), 1), Materials.copper.getExperience());
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
/*    GameRegistry.addSmelting(Items.APPLE, new ItemStack(ModItems.cooked_apple), 0.125f);*/
    GameRegistry.addSmelting(ModItems.raw_squid, new ItemStack(ModItems.cooked_squid, 2), 0.125f);
    GameRegistry.addSmelting(ModBlocks.wet_mud_block, new ItemStack(ModBlocks.mud_block), 0.125f);
    GameRegistry.addSmelting(ModBlocks.wet_mud_brick, new ItemStack(ModBlocks.mud_brick), 0.125f);
    GameRegistry.addSmelting(ModBlocks.charred_log, new ItemStack(Items.COAL, 1, 1), 0.15f);
  }
}
