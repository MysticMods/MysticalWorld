package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import epicsquid.mysticalworld.init.ModLoot;
import net.minecraft.entity.Entity;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;

public class IsLava implements ILootCondition {
  private final boolean inverse;

  public IsLava(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext context) {
    boolean flag;
    Entity looted = context.get(LootParameters.THIS_ENTITY);
    if (looted instanceof LavaCatEntity) {
      LavaCatEntity cat = (LavaCatEntity) looted;
      flag = cat.getIsLava();
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  @Override
  public LootConditionType func_230419_b_() {
    return ModLoot.IS_LAVA;
  }

  public static class Serializer implements ILootSerializer<IsLava> {
    @Override
    public void serialize(JsonObject json, IsLava value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsLava deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsLava(JSONUtils.getBoolean(json, "inverse", false));
    }
  }

  private static IsLava INSTANCE = new IsLava(false);

  public static ILootCondition.IBuilder builder() {
    return () -> INSTANCE;
  }
}

