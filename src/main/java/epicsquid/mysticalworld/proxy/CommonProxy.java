package epicsquid.mysticalworld.proxy;

import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.integration.jer.JERIntegration;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import epicsquid.mysticalworld.world.StructureGenerator;
import epicsquid.mysticalworld.world.OreGenerator;
import epicsquid.mysticalworld.world.WorldGeneratorTrees;
import net.minecraft.entity.monster.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
  private static ResourceLocation BARROW = new ResourceLocation(MysticalWorld.MODID, "barrow");
  private static ResourceLocation HUT = new ResourceLocation(MysticalWorld.MODID, "hut");

  private StructureGenerator hutGenerator;
  private StructureGenerator barrowGenerator;
  private WorldGeneratorTrees treeGenerator;

  public void preInit(FMLPreInitializationEvent event) {
    GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
  }

  public void init(FMLInitializationEvent event) {
    ModItems.registerOredict();
    if (Loader.isModLoaded("jeresources")) {
      JERIntegration.init();
    }

    LootConditionManager.registerCondition(new HasHorns.Serializer());
    if (ConfigManager.BarrowDistance != -1) {
      GameRegistry.registerWorldGenerator(barrowGenerator = new StructureGenerator(BARROW,10, () -> {
        switch (Util.rand.nextInt(6)) {
          case 0:
          case 1:
            return EntitySkeleton.class;
          default:
            return EntityZombie.class;
        }
      }, ConfigManager.BarrowDistance), 400);
    }

    if (ConfigManager.HutDistance != -1) {
      GameRegistry.registerWorldGenerator(hutGenerator = new StructureGenerator(HUT, 6, () -> {
        if (Util.rand.nextInt(4) == 0) {
          return EntityWitch.class;
        }
        return EntityZombie.class;
      }, ConfigManager.HutDistance), 400);
    }

    GameRegistry.registerWorldGenerator(treeGenerator = new WorldGeneratorTrees(), 400);
  }

  public void postInit(FMLPostInitializationEvent event) {
  }
}
