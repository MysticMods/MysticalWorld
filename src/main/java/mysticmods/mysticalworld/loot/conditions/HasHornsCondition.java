package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.entity.DeerEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class HasHornsCondition implements LootItemCondition {
  private final boolean inverse;

  public HasHornsCondition(boolean inverseIn) {
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
    return ModLoot.HAS_HORNS.get();
  }

  public static class HornSerializer implements Serializer<HasHornsCondition> {
    @Override
    public void serialize(JsonObject json, HasHornsCondition value, JsonSerializationContext context) {

      json.addProperty("inverse", value.inverse);
    }

    @Override
    public HasHornsCondition deserialize(JsonObject json, JsonDeserializationContext context) {
      return new HasHornsCondition(GsonHelper.getAsBoolean(json, "inverse", false));
    }
  }

  private static final HasHornsCondition INSTANCE = new HasHornsCondition(false);

  public static LootItemCondition.Builder builder() {
    return () -> INSTANCE;
  }
}

