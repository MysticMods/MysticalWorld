package mysticmods.mysticalworld.events.mod;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.*;
import mysticmods.mysticalworld.network.Networking;
import mysticmods.mysticalworld.potions.PotionRecipes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import noobanidus.libs.noobutil.recipe.UniqueShapelessRecipe;
import noobanidus.libs.noobutil.setup.ShadedCommonSetup;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {
  @SubscribeEvent
  public static void init(FMLCommonSetupEvent event) {
    MysticalWorld.STONE_PLANT = PlantType.get("stone");
    Networking.INSTANCE.registerMessages();

    event.enqueueWork(() -> {
      ModEntities.registerEntities();
      ShadedCommonSetup.init(event);

      ModCompost.init();

      PotionRecipes.registerRecipes();

      Chicken.FOOD_ITEMS = CompoundIngredient.of(Chicken.FOOD_ITEMS, Ingredient.of(ModItems.AUBERGINE_SEEDS.get()));

      // So, this is ugly, but at the same time, implementing flammable fences/etc blocks is kind of annoying so I'm happy to leave it like this.
      FireBlock fire = (FireBlock) Blocks.FIRE;

      fire.setFlammable(ModBlocks.THATCH_FENCE.get(), 5, 20);
      fire.setFlammable(ModBlocks.THATCH_FENCE_GATE.get(), 5, 20);
      fire.setFlammable(ModBlocks.THATCH_SLAB.get(), 5, 20);
      fire.setFlammable(ModBlocks.THATCH_SMALL_POST.get(), 5, 20);
      fire.setFlammable(ModBlocks.THATCH_WIDE_POST.get(), 5, 20);
      fire.setFlammable(ModBlocks.THATCH_STAIRS.get(), 5, 20);
      fire.setFlammable(ModBlocks.THATCH_WALL.get(), 5, 20);
      fire.setFlammable(ModBlocks.THATCH.get(), 5, 20);
      fire.setFlammable(ModBlocks.CHARRED_PLANKS.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_LOG.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_FENCE.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_FENCE_GATE.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_SLAB.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_SMALL_POST.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_STAIRS.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_WALL.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_WIDE_POST.get(), 1, 1);
      fire.setFlammable(ModBlocks.CHARRED_WOOD.get(), 1, 1);
      ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.STONEPETAL.getId(), () -> ModBlocks.POTTED_STONEPETAL.get());
    });
  }

  @SubscribeEvent
  public static void registerRecipes(RegistryEvent.Register<RecipeSerializer<?>> event) {
    UniqueShapelessRecipe.setStoredSerializer(ModRecipes.UNIQUE_SHAPELESS_RECIPE.get());
  }
}
