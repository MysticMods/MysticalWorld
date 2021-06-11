package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.loot.conditions.HasHorns;
import mysticmods.mysticalworld.loot.conditions.IsColor;
import mysticmods.mysticalworld.loot.conditions.IsLava;
import mysticmods.mysticalworld.loot.conditions.IsObsidian;
import net.minecraft.loot.LootConditionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ModLoot {
  public static final LootConditionType HAS_HORNS = new LootConditionType(new HasHorns.Serializer());
  public static final LootConditionType IS_COLOR = new LootConditionType(new IsColor.Serializer());
  public static final LootConditionType IS_OBSIDIAN = new LootConditionType(new IsObsidian.Serializer());
  public static final LootConditionType IS_LAVA = new LootConditionType(new IsLava.Serializer());

  public static void load() {
    Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MysticalWorld.MODID, "has_horns"), HAS_HORNS);
    Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MysticalWorld.MODID, "is_color"), IS_COLOR);
    Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MysticalWorld.MODID, "is_lava"), IS_LAVA);
    Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(MysticalWorld.MODID, "is_obsidian"), IS_OBSIDIAN);
  }
}
