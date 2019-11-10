package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.client.data.MWBlockstateProvider;
import epicsquid.mysticalworld.client.data.MWItemModelProvider;
import epicsquid.mysticalworld.client.data.MWLangProvider;
import epicsquid.mysticalworld.data.MWBlockTagsProvider;
import epicsquid.mysticalworld.data.MWItemTagsProvider;
import epicsquid.mysticalworld.data.MWLootTableProvider;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import epicsquid.mysticalworld.world.OreGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class ModSetup {
  public ModSetup() {
  }

  public void init(FMLCommonSetupEvent event) {
    CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);

    LootConditionManager.registerCondition(new HasHorns.Serializer());

    OreGen.registerOreGeneration();
  }

  public void gatherData (GatherDataEvent event) {
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
    }
  }
}
