package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class IsEnderCondition implements LootItemCondition {
  private final boolean inverse;

  public IsEnderCondition(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext lootContext) {
    boolean flag;
    Entity looted = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);
    if (looted instanceof ClamEntity) {
      ClamEntity clam = (ClamEntity) looted;
      flag = clam.getEntityData().get(ClamEntity.isEnder);
    } else {
      flag = false;
    }

    return flag == !this.inverse;
  }

  @Override
  public LootItemConditionType getType() {
    return ModLoot.IS_ENDER.get();
  }

  public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<IsEnderCondition> {
    @Override
    public void serialize(JsonObject json, IsEnderCondition value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsEnderCondition deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsEnderCondition(GsonHelper.getAsBoolean(json, "inverse", false));
    }
  }

  public static Builder builder() {
    return () -> new IsEnderCondition(false);
  }
}

