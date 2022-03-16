package mysticmods.mysticalworld.potions;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModEffects;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import noobanidus.libs.noobutil.ingredient.LazyIngredient;

public class PotionRecipes {
  public static void registerRecipes() {

    BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), new LazyIngredient(() -> Ingredient.of(ModItems.ANTLERS.get())), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH));
    BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), new LazyIngredient(() -> Ingredient.of(MWTags.Items.resolve(new ResourceLocation("forge", "gems/sapphire")))), PotionUtils.setPotion(new ItemStack(Items.POTION), ModEffects.SERENDIPITY_POTION.get()));
    BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), new LazyIngredient(() -> Ingredient.of(ModBlocks.STONEPETAL.get())), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING));
  }
}
