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

public class IsObsidian implements LootItemCondition {
  private final boolean inverse;

  public IsObsidian(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext context) {
    boolean flag;
    Entity looted = context.getParamOrNull(LootContextParams.THIS_ENTITY);
    if (looted instanceof LavaCatEntity) {
      LavaCatEntity cat = (LavaCatEntity) looted;
      flag = !cat.getIsLava();
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  @Override
  public LootItemConditionType getType() {
    return ModLoot.IS_OBSIDIAN.get();
  }

  public static class ObsidianSerializer implements Serializer<IsObsidian> {
    @Override
    public void serialize(JsonObject json, IsObsidian value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsObsidian deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsObsidian(GsonHelper.getAsBoolean(json, "inverse", false));
    }
  }

  private static final IsObsidian INSTANCE = new IsObsidian(false);

  public static LootItemCondition.Builder builder() {
    return () -> INSTANCE;
  }
}

