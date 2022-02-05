package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.entity.Entity;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;

public class IsEnder implements ILootCondition {
  private final boolean inverse;

  public IsEnder(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext lootContext) {
    boolean flag;
    Entity looted = lootContext.getParamOrNull(LootParameters.THIS_ENTITY);
    if (looted instanceof ClamEntity) {
      ClamEntity clam = (ClamEntity) looted;
      flag = clam.getEntityData().get(ClamEntity.isEnder);
    } else {
      flag = false;
    }

    return flag == !this.inverse;
  }

  @Override
  public LootConditionType getType() {
    return ModLoot.IS_ENDER;
  }

  public static class Serializer implements ILootSerializer<IsEnder> {
    @Override
    public void serialize(JsonObject json, IsEnder value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsEnder deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsEnder(JSONUtils.getAsBoolean(json, "inverse", false));
    }
  }

  public static IBuilder builder() {
    return () -> new IsEnder(false);
  }
}

