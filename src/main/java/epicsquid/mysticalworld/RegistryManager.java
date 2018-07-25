package epicsquid.mysticalworld;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.event.RegisterModRecipesEvent;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModRecipes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegistryManager {

  @SubscribeEvent
  public void init(@Nonnull RegisterContentEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    ModBlocks.registerBlocks(event);

    ModItems.registerItems(event);

    ModEntities.registerMobs();
    ModEntities.registerMobSpawn();

  }

  @SubscribeEvent
  public void initRecipes(@Nonnull RegisterModRecipesEvent event) {
    LibRegistry.setActiveMod(MysticalWorld.MODID, MysticalWorld.CONTAINER);

    ModRecipes.initRecipes(event);
  }
}
