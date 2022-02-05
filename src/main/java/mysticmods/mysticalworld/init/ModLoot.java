package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.loot.conditions.*;
import mysticmods.mysticalworld.loot.functions.RandomPotion;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;

public class ModLoot {
  public static final ConfiguredRegistry<LootFunctionType> FUNCTION_REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, Registry.LOOT_FUNCTION_TYPE);
  public static final ConfiguredRegistry<LootConditionType> CONDITION_REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, Registry.LOOT_CONDITION_TYPE);

  public static final LootFunctionType RANDOM_POTION = FUNCTION_REGISTRY.register("random_potion", new LootFunctionType(new RandomPotion.Serializer()));

  public static final LootConditionType HAS_HORNS = CONDITION_REGISTRY.register("has_horns", new LootConditionType(new HasHorns.Serializer()));
  public static final LootConditionType IS_COLOR = CONDITION_REGISTRY.register("is_color", new LootConditionType(new IsColor.Serializer()));
  public static final LootConditionType IS_OBSIDIAN = CONDITION_REGISTRY.register("is_lava", new LootConditionType(new IsObsidian.Serializer()));
  public static final LootConditionType IS_LAVA = CONDITION_REGISTRY.register("is_obsidian", new LootConditionType(new IsLava.Serializer()));
  public static final LootConditionType IS_MATURE = CONDITION_REGISTRY.register("is_mature", new LootConditionType(new IsMature.Serializer()));
  public static final LootConditionType IS_ENDER = CONDITION_REGISTRY.register("is_ender", new LootConditionType(new IsEnder.Serializer()));

  public static void load() {
  }
}
