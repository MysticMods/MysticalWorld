package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataSetup {
  @SubscribeEvent
  public static void onDataGen(GatherDataEvent event) {
    if (event.includeServer()) {
      event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
      event.getGenerator().addProvider(new PotionTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
      event.getGenerator().addProvider(new BiomeTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
    }
  }
}
