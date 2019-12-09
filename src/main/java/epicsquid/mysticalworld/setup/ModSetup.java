package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityStorage;
import epicsquid.mysticalworld.client.data.MWBlockstateProvider;
import epicsquid.mysticalworld.client.data.MWItemModelProvider;
import epicsquid.mysticalworld.client.data.MWLangProvider;
import epicsquid.mysticalworld.data.MWBlockTagsProvider;
import epicsquid.mysticalworld.data.MWItemTagsProvider;
import epicsquid.mysticalworld.data.MWLootTableProvider;
import epicsquid.mysticalworld.data.MWRecipeProvider;
import epicsquid.mysticalworld.events.*;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import epicsquid.mysticalworld.loot.conditions.IsColor;
import epicsquid.mysticalworld.loot.conditions.IsLava;
import epicsquid.mysticalworld.loot.conditions.IsObsidian;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.potions.PotionRecipes;
import epicsquid.mysticalworld.world.OreGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Field;
import java.util.List;

public class ModSetup {
  public ModSetup() {
  }

  public void init(FMLCommonSetupEvent event) {
    Networking.init();
    CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
    CapabilityManager.INSTANCE.register(PlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);

    LootConditionManager.registerCondition(new HasHorns.Serializer());
    LootConditionManager.registerCondition(new IsColor.Serializer());
    LootConditionManager.registerCondition(new IsLava.Serializer());
    LootConditionManager.registerCondition(new IsObsidian.Serializer());

    OreGen.registerOreGeneration();
    PotionRecipes.registerRecipes();

    try {
      ModifyWaterCap.modify();
    } catch (IllegalAccessException e) {
      MysticalWorld.LOG.error("Unable to modify water cap", e);
    }
  }

  public void serverStarting(FMLServerStartedEvent event) {
    try {
      ModifyWaterCap.modifySquid();
    } catch (IllegalAccessException e) {
      MysticalWorld.LOG.error("Unable to modify squid spawn cap", e);
    }
  }

  @SuppressWarnings("Duplicates")
  public void registerListeners() {
    MinecraftForge.EVENT_BUS.addListener(LeafHandler::onBlockDrops);
    MinecraftForge.EVENT_BUS.addListener(DamageHandler::onAttackDamage);
    MinecraftForge.EVENT_BUS.addListener(EntityHandler::onEntityInteract);
    MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onDeath);
    MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onRightClickBlock);
    MinecraftForge.EVENT_BUS.addListener(LootHandler::onLootLoad);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::attachCapability);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onSquidMilked);

    DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientSetup::registerListeners);
  }

  public void gatherData(GatherDataEvent event) {
    DataGenerator gen = event.getGenerator();
    if (event.includeClient()) {
      gen.addProvider(new MWBlockstateProvider(gen, event.getExistingFileHelper()));
      gen.addProvider(new MWItemModelProvider(gen, event.getExistingFileHelper()));
      gen.addProvider(new MWLangProvider(gen));
    }
    if (event.includeServer()) {
      gen.addProvider(new MWLootTableProvider(gen));
      gen.addProvider(new MWBlockTagsProvider(gen));
      gen.addProvider(new MWItemTagsProvider(gen));
      gen.addProvider(new MWRecipeProvider(gen));
    }
  }
}
