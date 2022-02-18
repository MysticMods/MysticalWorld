package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.entity.DeerEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.util.GsonHelper;

public class HasHorns implements LootItemCondition {
  private final boolean inverse;

  public HasHorns(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext lootContext) {
    boolean flag;
    Entity looted = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);
    if (looted instanceof DeerEntity) {
      DeerEntity deer = (DeerEntity) looted;
      flag = deer.getEntityData().get(DeerEntity.hasHorns);
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  @Override
  public LootItemConditionType getType() {
    return ModLoot.HAS_HORNS;
  }

  public static class Serializer implements Serializer<HasHorns> {
    @Override
    public void serialize(JsonObject json, HasHorns value, JsonSerializationContext context) {

      json.addProperty("inverse", value.inverse);
    }

    @Override
    public HasHorns deserialize(JsonObject json, JsonDeserializationContext context) {
      return new HasHorns(GsonHelper.getAsBoolean(json, "inverse", false));
    }
  }

  private static final HasHorns INSTANCE = new HasHorns(false);

  public static LootItemCondition.Builder builder() {
    return () -> INSTANCE;
  }
}

