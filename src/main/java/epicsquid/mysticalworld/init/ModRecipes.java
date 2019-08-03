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
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

import static epicsquid.mysticalworld.init.ModItems.pelt;

public class ModRecipes {

  private static ResourceLocation getRL(@Nonnull String s) {
    return new ResourceLocation(MysticalWorld.MODID + ":" + s);
  }

  private static void registerShapeless(@Nonnull IForgeRegistry<IRecipe> registry, @Nonnull String name, @Nonnull ItemStack result, Object... ingredients) {
    registry.register(new ShapelessOreRecipe(getRL(name), result, ingredients).setRegistryName(getRL(name)));
  }

  private static void registerShaped(@Nonnull IForgeRegistry<IRecipe> registry, @Nonnull String name, @Nonnull ItemStack result, Object... ingredients) {
    registry.register(new ShapedOreRecipe(getRL(name), result, ingredients).setRegistryName(getRL(name)));
  }

  public static void initRecipes(@Nonnull RegisterModRecipesEvent event) {
    registerShapeless(event.getRegistry(), "pelt", new ItemStack(Items.LEATHER, 1), new ItemStack(pelt, 1), new ItemStack(pelt, 1));

    // Iron and Gold Dust Recipes
    if (ConfigManager.metals.enableDusts && ConfigManager.metals.enableTinyDusts) {
      registerCompressionRecipe(event.getRegistry(), "dustIron", "dustTinyIron", ModItems.iron_dust, ModItems.iron_dust_tiny);
      registerCompressionRecipe(event.getRegistry(), "dustGold", "dustTinyGold", ModItems.gold_dust, ModItems.gold_dust_tiny);
    }

    for (Metal metal : Metal.values()) {
      if (metal.hasGrindables()) {
        // Tiny Dust <-> Dust
        if (ConfigManager.metals.enableDusts && ConfigManager.metals.enableTinyDusts) {
          registerCompressionRecipe(event.getRegistry(), "dust" + metal.getOredictNameSuffix(), "dustTiny" + metal.getOredictNameSuffix(), metal.getDust(), metal.getDustTiny());
          GameRegistry.addSmelting(metal.getDust(), new ItemStack(metal.getIngot(), 1), metal.getExperience());
        }
      }
      // Nugget <-> Ingot
      if (ConfigManager.metals.enableIngots && ConfigManager.metals.enableNuggets) {
        registerCompressionRecipe(event.getRegistry(), "ingot" + metal.getOredictNameSuffix(), "nugget" +
            metal.getOredictNameSuffix(), metal.getIngot(), metal.getNugget());
        // Ingot <-> Block
        registerCompressionRecipe(event.getRegistry(), "block" + metal.getOredictNameSuffix(), "ingot" +
            metal.getOredictNameSuffix(), metal.getBlock(), metal.getIngot());
      }

      if (metal.hasOre() && ConfigManager.metals.enableOres) {
        GameRegistry.addSmelting(metal.getOre(), new ItemStack(metal.getIngot(), 1), metal.getExperience());
      }
    }

    for (Gem gem : Gem.values()) {
      // Ingot <-> Block
      if (ConfigManager.gems.enableBlocks && ConfigManager.gems.enableGems) {
        registerCompressionRecipe(event.getRegistry(), "block" + gem.getOredictNameSuffix(), "gem" +
            gem.getOredictNameSuffix(), gem.getBlock(), gem.getGem());
      }
    }

    GameRegistry.addSmelting(new ItemStack(ModItems.venison, 1), new ItemStack(ModItems.cooked_venison), 0.1f);
    GameRegistry.addSmelting(ModItems.aubergine, new ItemStack(ModItems.cooked_aubergine), 0.125f);
    GameRegistry.addSmelting(ModItems.raw_squid, new ItemStack(ModItems.cooked_squid, 2), 0.125f);
    GameRegistry.addSmelting(ModBlocks.wet_mud_block, new ItemStack(ModBlocks.mud_block), 0.125f);
    GameRegistry.addSmelting(ModBlocks.wet_mud_brick, new ItemStack(ModBlocks.mud_brick), 0.125f);
    GameRegistry.addSmelting(ModBlocks.charred_log, new ItemStack(Items.COAL, 1, 1), 0.15f);
  }

  /**
   * Used to register a recipe that has both compression and decompression (e.g. nugget to ingot and back)
   */
  private static void registerCompressionRecipe(@Nonnull IForgeRegistry<IRecipe> registry, @Nonnull String oredictCompressed, @Nonnull String oredictDecompressed,
                                                @Nonnull Item itemCompressed, @Nonnull Item itemDecompressed) {
    // Compression
    registerShaped(registry, oredictCompressed + "Compression", new ItemStack(itemCompressed, 1),
        "XXX",
        "XXX",
        "XXX",
        'X', oredictDecompressed);
    // Decompression
    registerShapeless(registry, oredictDecompressed + "Decompression", new ItemStack(itemDecompressed, 9), oredictCompressed);
  }

  private static void registerCompressionRecipe(@Nonnull IForgeRegistry<IRecipe> registry, @Nonnull String oredictCompressed, @Nonnull String oredictDecompressed,
                                                @Nonnull Block blockCompressed, @Nonnull Item itemDecompressed) {
    // Compression
    registerShaped(registry, oredictCompressed + "Compression", new ItemStack(blockCompressed, 1),
        "XXX",
        "XXX",
        "XXX",
        'X', oredictDecompressed);
    // Decompression
    registerShapeless(registry, oredictDecompressed + "Decompression", new ItemStack(itemDecompressed, 9), oredictCompressed);
  }
}
