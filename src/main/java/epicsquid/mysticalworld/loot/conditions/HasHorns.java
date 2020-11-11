package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import epicsquid.mysticalworld.entity.DeerEntity;
import epicsquid.mysticalworld.init.ModLoot;
import net.minecraft.entity.Entity;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;

public class HasHorns implements ILootCondition {
  private final boolean inverse;

  public HasHorns(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext lootContext) {
    boolean flag;
    Entity looted = lootContext.get(LootParameters.THIS_ENTITY);
    if (looted instanceof DeerEntity) {
      DeerEntity deer = (DeerEntity) looted;
      flag = deer.getDataManager().get(DeerEntity.hasHorns);
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  @Override
  public LootConditionType func_230419_b_() {
    return ModLoot.HAS_HORNS;
  }

  public static class Serializer implements ILootSerializer<HasHorns> {
    @Override
    public void serialize(JsonObject json, HasHorns value, JsonSerializationContext context) {

      json.addProperty("inverse", value.inverse);
    }

    @Override
    public HasHorns deserialize(JsonObject json, JsonDeserializationContext context) {
      return new HasHorns(JSONUtils.getBoolean(json, "inverse", false));
    }
  }

/*  private static HasHorns INSTANCE = new HasHorns(false);

  public static ILootCondition.IBuilder builder() {
    return () -> INSTANCE;
  }*/
}

