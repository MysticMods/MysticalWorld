package epicsquid.mysticalworld.events;

import com.google.common.collect.Sets;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
@SuppressWarnings("unused")
public class LootHandler {
  private static Set<ResourceLocation> tables = Sets.newHashSet(LootTableList.CHESTS_SIMPLE_DUNGEON, LootTableList.CHESTS_ABANDONED_MINESHAFT, LootTableList.CHESTS_DESERT_PYRAMID, LootTableList.CHESTS_JUNGLE_TEMPLE, LootTableList.CHESTS_WOODLAND_MANSION);

  @SubscribeEvent
  public static void onLootLoad(LootTableLoadEvent event) {
    if (ConfigManager.InjectLoot) {
      RandomValueRange range = new RandomValueRange(ConfigManager.InjectMinimum, ConfigManager.InjectMaximum);
      if (tables.contains(event.getName())) {
        LootPool pool = new LootPool(new LootEntry[]{
            new LootEntryTable(new ResourceLocation(MysticalWorld.MODID, "chests/inject"), 1, 0, new LootCondition[0], "MysticalWorld")
        }, new LootCondition[]{}, range, range, "MysticalWorld");
        event.getTable().addPool(pool);
      }
    }
    if (ConfigManager.InjectSquid) {
      if (event.getName() == LootTableList.ENTITIES_SQUID) {
        RandomValueRange range = new RandomValueRange(0, 2);
        RandomValueRange range2 = new RandomValueRange(0);
        LootPool pool = new LootPool(new LootEntry[]{
            new LootEntryTable(new ResourceLocation(MysticalWorld.MODID, "entity/squid_inject"), 100, 0, new LootCondition[0], "MysticalWorld")
        }, new LootCondition[]{}, range, range2, "MysticalWorld");
        event.getTable().addPool(pool);
      }
    }
  }

  @SubscribeEvent
  public static void onLootDrop (LivingDropsEvent event) {

  }
}
