package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.api.IPlayerShoulderCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityStorage;
import epicsquid.mysticalworld.events.*;
import epicsquid.mysticalworld.init.ModCompost;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import epicsquid.mysticalworld.loot.conditions.IsColor;
import epicsquid.mysticalworld.loot.conditions.IsLava;
import epicsquid.mysticalworld.loot.conditions.IsObsidian;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.potions.PotionRecipes;
import epicsquid.mysticalworld.world.OreGen;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

public class ModSetup {
  public ModSetup() {
  }

  public void init(FMLCommonSetupEvent event) {
    ModCompost.init();
    ModEntities.registerEntities();
    Networking.INSTANCE.registerMessages();
    CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
    CapabilityManager.INSTANCE.register(IPlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);

    OreGen.registerOreGeneration();
    PotionRecipes.registerRecipes();

/*    try {
      ModifyWaterCap.modify();
    } catch (IllegalAccessException e) {
      MysticalWorld.LOG.error("Unable to modify water cap", e);
    }*/
  }

  public void serverStarting(FMLServerStartedEvent event) {
    try {
      ModifyWaterCap.modifySquid();
    } catch (IllegalAccessException e) {
      MysticalWorld.LOG.error("Unable to modify squid spawn cap", e);
    }
  }

  public void serverAboutToStart(FMLServerAboutToStartEvent event) {
    ModifyLoot.modify();
  }

  @SuppressWarnings("Duplicates")
  public void registerListeners() {
    MinecraftForge.EVENT_BUS.addListener(LeafHandler::onBlockDrops);
    MinecraftForge.EVENT_BUS.addListener(DamageHandler::onAttackDamage);
    MinecraftForge.EVENT_BUS.addListener(EntityHandler::onEntityInteract);
    MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onDeath);
    MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onRightClickBlock);
    MinecraftForge.EVENT_BUS.addListener(LootHandler::onLootLoad);
    MinecraftForge.EVENT_BUS.addListener(LootHandler::onLooting);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::attachCapability);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onSquidMilked);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onPlayerJoin);

    DistExecutor.runWhenOn(Dist.CLIENT, () -> ClientSetup::registerListeners);
  }
}
