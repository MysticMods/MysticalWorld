package epicsquid.mysticalworld.setup;

import com.google.common.collect.Maps;
import epicsquid.mysticalworld.api.IPlayerShoulderCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityStorage;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.events.CapabilityHandler;
import epicsquid.mysticalworld.events.DamageHandler;
import epicsquid.mysticalworld.events.EntityHandler;
import epicsquid.mysticalworld.events.LootHandler;
import epicsquid.mysticalworld.init.ModCompost;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModModifiers;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.potions.PotionRecipes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import noobanidus.libs.noobutil.setup.ShadedCommonSetup;

import java.util.HashMap;

@SuppressWarnings("deprecation")
public class CommonSetup {
  public CommonSetup() {
  }

  public void init(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      ModCompost.init();
      ModEntities.registerEntities();
      Networking.INSTANCE.registerMessages();
      CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
      CapabilityManager.INSTANCE.register(IPlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);

      ShadedCommonSetup.init(event);
      PotionRecipes.registerRecipes();

      GlobalEntityTypeAttributes.put(ModEntities.BEETLE.get(), BeetleEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.DEER.get(), DeerEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.FROG.get(), FrogEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SILVER_FOX.get(), SilverFoxEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SPROUT.get(), SproutEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.ENDERMINI.get(), EnderminiEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.LAVA_CAT.get(), LavaCatEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.OWL.get(), OwlEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SILKWORM.get(), SilkwormEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.HELL_SPROUT.get(), HellSproutEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SPIRIT_BEETLE.get(), SpiritBeetleEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SPIRIT_DEER.get(), SpiritDeerEntity.attributes().create());

      AttributeModifierMap map = GlobalEntityTypeAttributes.getAttributesForEntity(EntityType.PLAYER);
      HashMap<Attribute, ModifiableAttributeInstance> attributeMap = new HashMap<>(map.attributeMap);
      attributeMap.put(ModModifiers.SERENDIPITY.get(), new ModifiableAttributeInstance(ModModifiers.SERENDIPITY.get(), (modifier) -> {}));
      map.attributeMap = attributeMap;
    });
  }

  public void serverStarting(FMLServerStartedEvent event) {
  }

  public void serverAboutToStart(FMLServerAboutToStartEvent event) {
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
    MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityHandler::attachCapability);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onSquidMilked);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onPlayerJoin);
  }

  public void loadComplete(FMLLoadCompleteEvent event) {
    ModifyLoot.modify();
  }
}
