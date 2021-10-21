package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.entity.Entity;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;

public class IsObsidian implements ILootCondition {
  private final boolean inverse;

  public IsObsidian(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext context) {
    boolean flag;
    Entity looted = context.getParamOrNull(LootParameters.THIS_ENTITY);
    if (looted instanceof LavaCatEntity) {
      LavaCatEntity cat = (LavaCatEntity) looted;
      flag = !cat.getIsLava();
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  @Override
  public LootConditionType getType() {
    return ModLoot.IS_OBSIDIAN;
  }

  public static class Serializer implements ILootSerializer<IsObsidian> {
    @Override
    public void serialize(JsonObject json, IsObsidian value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsObsidian deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsObsidian(JSONUtils.getAsBoolean(json, "inverse", false));
    }
  }

  private static final IsObsidian INSTANCE = new IsObsidian(false);

  public static ILootCondition.IBuilder builder() {
    return () -> INSTANCE;
  }
}

