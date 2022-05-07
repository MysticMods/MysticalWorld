package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.entity.LavaCatEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class IsLavaCondition implements LootItemCondition {
  private final boolean inverse;

  public IsLavaCondition(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext context) {
    boolean flag;
    Entity looted = context.getParamOrNull(LootContextParams.THIS_ENTITY);
    if (looted instanceof LavaCatEntity) {
      LavaCatEntity cat = (LavaCatEntity) looted;
      flag = cat.getIsLava();
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  @Override
  public LootItemConditionType getType() {
    return ModLoot.IS_LAVA.get();
  }

  public static class LavaSerializer implements Serializer<IsLavaCondition> {
    @Override
    public void serialize(JsonObject json, IsLavaCondition value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsLavaCondition deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsLavaCondition(GsonHelper.getAsBoolean(json, "inverse", false));
    }
  }

  private static final IsLavaCondition INSTANCE = new IsLavaCondition(false);

  public static LootItemCondition.Builder builder() {
    return () -> INSTANCE;
  }
}

