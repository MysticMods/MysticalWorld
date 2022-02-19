package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.loot.conditions.*;
import mysticmods.mysticalworld.loot.functions.RandomPotion;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;

public class ModLoot {
  public static final ConfiguredRegistry<LootItemFunctionType> FUNCTION_REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, Registry.LOOT_FUNCTION_TYPE);
  public static final ConfiguredRegistry<LootItemConditionType> CONDITION_REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, Registry.LOOT_CONDITION_TYPE);

  public static final LootItemFunctionType RANDOM_POTION = FUNCTION_REGISTRY.register("random_potion", new LootItemFunctionType(new RandomPotion.Serializer()));

  public static final LootItemConditionType HAS_HORNS = CONDITION_REGISTRY.register("has_horns", new LootItemConditionType(new HasHorns.HornSerializer()));
  public static final LootItemConditionType IS_COLOR = CONDITION_REGISTRY.register("is_color", new LootItemConditionType(new IsColor.ColorSerializer()));
  public static final LootItemConditionType IS_OBSIDIAN = CONDITION_REGISTRY.register("is_lava", new LootItemConditionType(new IsObsidian.ObsidianSerializer()));
  public static final LootItemConditionType IS_LAVA = CONDITION_REGISTRY.register("is_obsidian", new LootItemConditionType(new IsLava.LavaSerializer()));
  public static final LootItemConditionType IS_MATURE = CONDITION_REGISTRY.register("is_mature", new LootItemConditionType(new IsMature.MatureSerializer()));
  public static final LootItemConditionType IS_ENDER = CONDITION_REGISTRY.register("is_ender", new LootItemConditionType(new IsEnder.EnderSerializer()));

  public static void load() {
  }
}
