package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.configured.ModLoot;
import mysticmods.mysticalworld.init.deferred.ModRecipes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import noobanidus.libs.noobutil.recipe.UniqueShapelessRecipe;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataSetup {
  @SubscribeEvent
  public static void onDataGen(GatherDataEvent event) {
    if (event.includeServer()) {
      ModLoot.load();
      ModLoot.CONDITION_REGISTRY.registration();
      ModLoot.FUNCTION_REGISTRY.registration();
      UniqueShapelessRecipe.setStoredSerializer(ModRecipes.UNIQUE_SHAPELESS_RECIPE.get());
      event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
      event.getGenerator().addProvider(new PotionTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
      MWBlockTagGenerator blockTagGenerator = new MWBlockTagGenerator(event.getGenerator(), event.getExistingFileHelper());
      event.getGenerator().addProvider(blockTagGenerator);
      event.getGenerator().addProvider(new MWItemTagGenerator(event.getGenerator(), blockTagGenerator, event.getExistingFileHelper()));
      event.getGenerator().addProvider(new MWBlockStateGenerator(event.getGenerator(), event.getExistingFileHelper()));
      event.getGenerator().addProvider(new MWLootGenerator(event.getGenerator()));
      event.getGenerator().addProvider(new MWItemModelGenerator(event.getGenerator(), event.getExistingFileHelper()));
      event.getGenerator().addProvider(new MWLangGenerator(event.getGenerator()));
    }
  }
}
