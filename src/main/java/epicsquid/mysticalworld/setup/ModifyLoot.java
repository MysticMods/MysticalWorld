/*package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.loot.functions.ApplyBonusModified;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Map;

public class ModifyLoot {
  private static boolean applied = false;

  public static void modify() {
    if (applied) {
      return;
    }

    Map<ResourceLocation, ILootFunction.Serializer<?>> map1 = ObfuscationReflectionHelper.getPrivateValue(LootFunctionManager.class, null, "field_186584_a");
    Map<Class<? extends ILootFunction>, ILootFunction.Serializer<?>> map2 = ObfuscationReflectionHelper.getPrivateValue(LootFunctionManager.class, null, "field_186585_b");

    if (map1 == null || map2 == null) {
      MysticalWorld.LOG.error("Not entire sure how this happened, but we've got a null pointer!", new NullPointerException());
    } else {
      map1.remove(new ResourceLocation("minecraft", "apply_bonus"));
      map2.remove(ApplyBonus.class);

      LootFunctionManager.registerFunction(new ApplyBonusModified.Serializer());
      applied = true;
    }
  }
}*/
