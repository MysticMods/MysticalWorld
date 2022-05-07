package mysticmods.mysticalworld.events.forge;

import com.google.common.collect.Sets;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.loot.Serendipity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class LootHandler {
  @SubscribeEvent
  public static void onLooting(LootingLevelEvent event) {
    DamageSource source = event.getDamageSource();
    if (source != null && source.getEntity() instanceof Player player) {
      event.setLootingLevel(event.getLootingLevel() + Serendipity.calculateAdditional(player.getAttributeValue(ModModifiers.SERENDIPITY.get())));
    }
  }
}
