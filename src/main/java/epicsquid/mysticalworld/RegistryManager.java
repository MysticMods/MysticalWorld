package epicsquid.mysticalworld;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import epicsquid.mysticalworld.init.*;
import epicsquid.mysticalworld.materials.Materials;
import epicsquid.mysticalworld.materials.Tools;
import epicsquid.mysticalworld.network.PacketHandler;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class RegistryManager {

  @SubscribeEvent
  public void init(@Nonnull RegisterContentEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    ModBlocks.registerBlocks(event);
    Materials.initMaterials(event);
    ModItems.registerItems(event);

    ModSounds.initSounds(event);
    Tools.registerTools(event);

    ModEntities.registerMobs();
    ModEntities.registerMobSpawn();

    PacketHandler.registerMessages();
  }

  @SubscribeEvent
  public void initRecipes(@Nonnull RegisterModRecipesEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    ModRecipes.initRecipes(event);
  }

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void registerOredict(@Nonnull RegistryEvent.Register<Item> event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    ModItems.registerOredict();
    Materials.registerOreDictionary();
  }
}
