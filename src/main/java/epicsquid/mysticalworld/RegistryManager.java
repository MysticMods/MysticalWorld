package epicsquid.mysticalworld;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import epicsquid.mysticalworld.init.*;
import epicsquid.mysticalworld.item.metals.Metal;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegistryManager {

  @SubscribeEvent
  public void init(@Nonnull RegisterContentEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    ModBlocks.registerBlocks(event);

    ModItems.registerItems(event);

    ModSounds.initSounds(event);

    Metal.registerMetals(event);

    ModEntities.registerMobs();
    ModEntities.registerMobSpawn();

  }

  @SubscribeEvent
  public void initRecipes(@Nonnull RegisterModRecipesEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    ModRecipes.initRecipes(event);
  }

  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void registerOredict(@Nonnull RegistryEvent.Register<Item> event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);
    
    Metal.registerOreDict();
  }
}
