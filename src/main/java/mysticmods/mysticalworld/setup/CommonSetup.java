package mysticmods.mysticalworld.setup;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.api.IPlayerShoulderCapability;
import mysticmods.mysticalworld.capability.AnimalCooldownCapability;
import mysticmods.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import mysticmods.mysticalworld.capability.PlayerShoulderCapability;
import mysticmods.mysticalworld.capability.PlayerShoulderCapabilityStorage;
import mysticmods.mysticalworld.events.CapabilityHandler;
import mysticmods.mysticalworld.events.DamageHandler;
import mysticmods.mysticalworld.events.LootHandler;
import mysticmods.mysticalworld.init.*;
import mysticmods.mysticalworld.network.Networking;
import mysticmods.mysticalworld.potions.PotionRecipes;
import mysticmods.mysticalworld.recipe.ingredients.SeedIngredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import noobanidus.libs.noobutil.ingredient.ExcludingIngredient;
import noobanidus.libs.noobutil.recipe.UniqueShapelessRecipe;
import noobanidus.libs.noobutil.setup.ShadedCommonSetup;

import java.util.Arrays;

@SuppressWarnings("deprecation")
public class CommonSetup {
  public static void init(FMLCommonSetupEvent event) {
    MysticalWorld.STONE_PLANT = PlantType.get("stone");
    CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
    CapabilityManager.INSTANCE.register(IPlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);
    Networking.INSTANCE.registerMessages();

    event.enqueueWork(() -> {
      ModLoot.load();
      ModEntities.registerEntities();
      ShadedCommonSetup.init(event);
      ModStructures.setupStructures();
      ConfiguredStructures.registerStructures();
      CraftingHelper.register(new ResourceLocation(MysticalWorld.MODID, "seeds"), SeedIngredient.Serializer.INSTANCE);
      CraftingHelper.register(new ResourceLocation(MysticalWorld.MODID, "excluding_ingredient"), ExcludingIngredient.Serializer.INSTANCE);
      UniqueShapelessRecipe.setStoredSerializer(ModRecipes.UNIQUE_SHAPELESS_RECIPE.get());
      ModCompost.init();

      PotionRecipes.registerRecipes();

      Chicken.FOOD_ITEMS = Ingredient.merge(Arrays.asList(Chicken.FOOD_ITEMS, Ingredient.of(ModItems.AUBERGINE_SEEDS.get())));

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
      ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.UNCANNY_MUSHROOM.getId(), () -> ModBlocks.POTTED_UNCANNY_MUSHROOM.get());

      ConfiguredFeatures.REGISTRY.registration();
      ConfiguredStructures.REGISTRY.registration();
      ModFeatures.generateFeatures();
      ModFeatures.REGISTRY.registration();
      ModLoot.FUNCTION_REGISTRY.registration();
      ModLoot.CONDITION_REGISTRY.registration();

      SpawnEggItem.BY_ID.remove(null);
      //noinspection unchecked
      ModEntities.SPAWN_EGGS.forEach(o -> SpawnEggItem.BY_ID.put(o.get().getType(null), o.get()));
    });
  }

  public static void serverStarting(FMLServerStartedEvent event) {
  }

  public static void serverAboutToStart(FMLServerAboutToStartEvent event) {
  }

  @SuppressWarnings("Duplicates")
  public static void registerListeners() {
    MinecraftForge.EVENT_BUS.addListener(DamageHandler::onAttackDamage);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onPlayerJoin);
    MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, true, LootHandler::onLootLoad);
    MinecraftForge.EVENT_BUS.addListener(LootHandler::onLooting);
    MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityHandler::attachCapability);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onSquidMilked);
  }

  public static void loadComplete(FMLLoadCompleteEvent event) {
    event.enqueueWork(() -> {
    });
  }
}
