package epicsquid.mysticalworld.events;

import com.google.common.collect.Sets;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
@SuppressWarnings("unused")
public class LootHandler {
	private static Set<ResourceLocation> tables = Sets.newHashSet(LootTables.CHESTS_SIMPLE_DUNGEON, LootTables.CHESTS_ABANDONED_MINESHAFT, LootTables.CHESTS_DESERT_PYRAMID, LootTables.CHESTS_JUNGLE_TEMPLE, LootTables.CHESTS_WOODLAND_MANSION);

	@SubscribeEvent
	public static void onLootLoad(LootTableLoadEvent event) {
//    if (ConfigManager.InjectLoot) {
//      RandomValueRange range = new RandomValueRange(ConfigManager.InjectMinimum, ConfigManager.InjectMaximum);
		if (tables.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(MysticalWorld.MODID, "chests/inject")).weight(1).quality(0)).build();
			event.getTable().addPool(pool);
		}
//    }
	}
}
