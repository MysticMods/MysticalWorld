package epicsquid.mysticalworld.proxy;

import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.integration.jer.JERIntegration;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import epicsquid.mysticalworld.world.NBTStructureGenerator;
import epicsquid.mysticalworld.world.OreGenerator;
import net.minecraft.entity.monster.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
  private static ResourceLocation BARROW = new ResourceLocation(MysticalWorld.MODID, "barrow");
  private static ResourceLocation HUT = new ResourceLocation(MysticalWorld.MODID, "hut");

  public void preInit(FMLPreInitializationEvent event) {
    GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
  }

  public void init(FMLInitializationEvent event) {
    ModItems.registerOredict();
    if (Loader.isModLoaded("jeresources")) {
      JERIntegration.init();
    }

    LootConditionManager.registerCondition(new HasHorns.Serializer());
    /*int barrowWeight = ConfigManager.BarrowWeight;
    int hutWeight = ConfigManager.HutWeight;
    if (barrowWeight != 0) {
      GameRegistry.registerWorldGenerator(new NBTStructureGenerator(BARROW,10, () -> {
        switch (Util.rand.nextInt(15)) {
          case 0:
          case 1:
          case 2:
            return EntityHusk.class;
          case 3:
          case 4:
          case 5:
            return EntityStray.class;
          case 6:
          case 7:
          case 8:
          case 9:
            return EntitySkeleton.class;
          default:
            return EntityZombie.class;
        }
      }), barrowWeight);
    }
    if (hutWeight != 0) {
      GameRegistry.registerWorldGenerator(new NBTStructureGenerator(HUT, 6, () -> {
        switch (Util.rand.nextInt(10)) {
          case 0:
            return EntityWitch.class;
          case 1:
          case 2:
            return EntityHusk.class;
          default:
            return EntityZombie.class;
        }
      }), hutWeight);
    }*/
  }

  public void postInit(FMLPostInitializationEvent event) {
  }
}
