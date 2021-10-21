package mysticmods.mysticalworld.events;

import com.google.common.collect.Sets;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.loot.Serendipity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;

import java.util.Set;

@SuppressWarnings("unused")
public class LootHandler {
  private static final Set<ResourceLocation> tables = Sets.newHashSet(LootTables.SIMPLE_DUNGEON, LootTables.ABANDONED_MINESHAFT, LootTables.DESERT_PYRAMID, LootTables.JUNGLE_TEMPLE, LootTables.WOODLAND_MANSION);
  private static final ResourceLocation squid_table = new ResourceLocation("minecraft", "entities/squid");

  public static void onLootLoad(LootTableLoadEvent event) {
    if (tables.contains(event.getName())) {
      event.getTable().addPool(
          LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(MysticalWorld.MODID, "chests/inject")).setWeight(1).setQuality(0)).name("mystical_world_chest_injection").build());
    }

    if (event.getName().equals(squid_table)) {
      event.getTable().addPool(LootPool.lootPool().add(TableLootEntry.lootTableReference(new ResourceLocation(MysticalWorld.MODID, "entities/squid_inject"))).name("mystical_world_squid_injection").build());
    }
  }

  public static void onLooting(LootingLevelEvent event) {
    DamageSource source = event.getDamageSource();
    if (source != null && source.getEntity() != null && source.getEntity() instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) source.getEntity();

      int looting = event.getLootingLevel();

      event.setLootingLevel(looting + Serendipity.calculateAdditional(player.getAttributeValue(ModModifiers.SERENDIPITY.get())));
    }
  }
}
