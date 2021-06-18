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
import mysticmods.mysticalworld.init.*;
import mysticmods.mysticalworld.potions.PotionRecipes;
import mysticmods.mysticalworld.recipe.ingredients.SeedIngredient;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import noobanidus.libs.noobutil.setup.ShadedCommonSetup;

import java.util.Arrays;

@SuppressWarnings("deprecation")
public class CommonSetup {
  public static void init(FMLCommonSetupEvent event) {
    MysticalWorld.STONE_PLANT = PlantType.get("stone");
    CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
    CapabilityManager.INSTANCE.register(IPlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);
    /*    Networking.INSTANCE.registerMessages();*/

    event.enqueueWork(() -> {
      ModLoot.load();
      ModEntities.registerEntities();
      ShadedCommonSetup.init(event);
      ModStructures.setupStructures();
      ConfiguredStructures.registerStructures();
      CraftingHelper.register(new ResourceLocation(MysticalWorld.MODID, "seeds"), SeedIngredient.Serializer.INSTANCE);
      ModCompost.init();

      PotionRecipes.registerRecipes();

      ChickenEntity.TEMPTATION_ITEMS = Ingredient.merge(Arrays.asList(ChickenEntity.TEMPTATION_ITEMS, Ingredient.fromItems(ModItems.AUBERGINE_SEEDS.get())));

      FireBlock fire = (FireBlock) Blocks.FIRE;

      fire.setFireInfo(ModBlocks.THATCH.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_FENCE.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_FENCE_GATE.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_SLAB.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_SMALL_POST.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_WIDE_POST.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_STAIRS.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_WALL.get(), 5, 20);
      fire.setFireInfo(ModBlocks.SIMPLE_THATCH.get(), 5, 20);
      fire.setFireInfo(ModBlocks.CHARRED_PLANKS.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_LOG.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_FENCE.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_FENCE_GATE.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_SLAB.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_SMALL_POST.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_STAIRS.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_WALL.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_WIDE_POST.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_WOOD.get(), 1, 1);
      ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.STONEPETAL.getId(), () -> ModBlocks.POTTED_STONEPETAL.get());

      ConfiguredStructures.REGISTRY.registration();
      ModFeatures.generateFeatures();
      ModFeatures.REGISTRY.registration();

      SpawnEggItem.EGGS.remove(null);
      ModEntities.SPAWN_EGGS.forEach(o -> SpawnEggItem.EGGS.put(o.get().getType(null), o.get()));
    });
  }

  public static void serverStarting(FMLServerStartedEvent event) {
  }

  public static void serverAboutToStart(FMLServerAboutToStartEvent event) {
  }

  @SuppressWarnings("Duplicates")
  public static void registerListeners() {
    MinecraftForge.EVENT_BUS.addListener(DamageHandler::onAttackDamage);
    /*    MinecraftForge.EVENT_BUS.addListener(EntityHandler::onEntityInteract);*/
    // TODO: Temporarily disabled
    //MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onDeath);
    //MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onRightClickBlock);
    /*    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onPlayerJoin);*/
    MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, true, LootHandler::onLootLoad);
    MinecraftForge.EVENT_BUS.addListener(LootHandler::onLooting);
    MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityHandler::attachCapability);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onSquidMilked);
  }

  public static void loadComplete(FMLLoadCompleteEvent event) {
    event.enqueueWork(() -> {
      ModifyLoot.modify();
    });
  }
}
