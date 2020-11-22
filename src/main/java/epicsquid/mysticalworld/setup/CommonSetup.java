package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.api.IPlayerShoulderCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityStorage;
import epicsquid.mysticalworld.events.CapabilityHandler;
import epicsquid.mysticalworld.events.DamageHandler;
import epicsquid.mysticalworld.events.EntityHandler;
import epicsquid.mysticalworld.events.LootHandler;
import epicsquid.mysticalworld.init.ModCompost;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModFeatures;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.potions.PotionRecipes;
import epicsquid.mysticalworld.world.OreGen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import noobanidus.libs.noobutil.setup.ShadedCommonSetup;

@SuppressWarnings("deprecation")
public class CommonSetup {
  public CommonSetup() {
  }

  public void init(FMLCommonSetupEvent event) {
    DeferredWorkQueue.runLater(() -> {
      ModCompost.init();
      ModEntities.registerEntities();
      Networking.INSTANCE.registerMessages();
      CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
      CapabilityManager.INSTANCE.register(IPlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);

      ShadedCommonSetup.init(event);
/*      OreGen.registerOreGeneration();*/
      PotionRecipes.registerRecipes();


    });

  }

  public void serverStarting(FMLServerStartedEvent event) {
  }

  public void serverAboutToStart(FMLServerAboutToStartEvent event) {
/*    ModifyLoot.modify();*/
  }

  @SuppressWarnings("Duplicates")
  public void registerListeners() {
    MinecraftForge.EVENT_BUS.addListener(DamageHandler::onAttackDamage);
    MinecraftForge.EVENT_BUS.addListener(EntityHandler::onEntityInteract);
    // TODO: Temporarily disabled
    //MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onDeath);
    //MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onRightClickBlock);
    MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, true, LootHandler::onLootLoad);
    MinecraftForge.EVENT_BUS.addListener(LootHandler::onLooting);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::attachCapability);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onSquidMilked);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onPlayerJoin);
  }

  public void loadComplete(FMLLoadCompleteEvent event) {
/*    ModFeatures.loadComplete();*/
  }
}
