package mysticmods.mysticalworld.events.mod;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.gen.BiomeTagGenerator;
import mysticmods.mysticalworld.gen.LootTableGenerator;
import mysticmods.mysticalworld.gen.PotionTagGenerator;
import mysticmods.mysticalworld.init.ModRecipes;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.noobutil.recipe.UniqueShapelessRecipe;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataSetup {
	@SubscribeEvent
	public static void onDataGen(GatherDataEvent event) {
		UniqueShapelessRecipe.setStoredSerializer(ModRecipes.UNIQUE_SHAPELESS_RECIPE.get());
		event.getGenerator().addProvider(event.includeServer(), new LootTableGenerator(event.getGenerator()));
		event.getGenerator().addProvider(event.includeServer(), new PotionTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeServer(), new BiomeTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
	}
}
