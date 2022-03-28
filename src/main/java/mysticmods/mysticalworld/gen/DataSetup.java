package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModRecipes;
import mysticmods.mysticalworld.init.configured.ModLoot;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import noobanidus.libs.noobutil.recipe.UniqueShapelessRecipe;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataSetup {
  @SubscribeEvent
  public static void onDataGen(GatherDataEvent event) {
    if (event.includeServer()) {
      UniqueShapelessRecipe.setStoredSerializer(ModRecipes.UNIQUE_SHAPELESS_RECIPE.get());

      event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
      event.getGenerator().addProvider(new PotionTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
    }
  }
}
