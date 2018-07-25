package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

import static epicsquid.mysticalworld.init.ModItems.carapace;
import static epicsquid.mysticalworld.init.ModItems.pelt;

public class ModRecipes {

  private static ResourceLocation getRL(@Nonnull String s) {
    return new ResourceLocation(MysticalWorld.MODID + ":" + s);
  }

  private static void registerShapeless(@Nonnull IForgeRegistry<IRecipe> registry, @Nonnull String name, @Nonnull ItemStack result, Object... ingredients) {
    registry.register(new ShapelessOreRecipe(getRL(name), result, ingredients).setRegistryName(getRL(name)));
  }

  public static void initRecipes(@Nonnull RegisterModRecipesEvent event) {
    registerShapeless(event.getRegistry(), "pelt", new ItemStack(Items.LEATHER, 1), new ItemStack(pelt, 1), new ItemStack(pelt, 1));
    registerShapeless(event.getRegistry(), "carapace", new ItemStack(Items.DYE, 1, 4), new ItemStack(carapace, 1));
  }
}
