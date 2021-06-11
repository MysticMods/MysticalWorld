package mysticmods.mysticalworld.setup;

import mysticmods.mysticalworld.loot.functions.ApplyBonusModified;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.functions.LootFunctionManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;


public class ModifyLoot {
  private static boolean applied = false;

  public static void modify() {
    if (applied) {
      return;
    }

    LootFunctionManager.APPLY_BONUS = Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation("minecraft", "apply_bonus"), new LootFunctionType(new ApplyBonusModified.Serializer()));
    applied = true;
  }
}
