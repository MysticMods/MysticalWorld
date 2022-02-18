package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.util.GsonHelper;

import net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder;

public class IsEnder implements LootItemCondition {
  private final boolean inverse;

  public IsEnder(boolean inverseIn) {
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
    return ModLoot.IS_ENDER;
  }

  public static class Serializer implements Serializer<IsEnder> {
    @Override
    public void serialize(JsonObject json, IsEnder value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsEnder deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsEnder(GsonHelper.getAsBoolean(json, "inverse", false));
    }
  }

  public static Builder builder() {
    return () -> new IsEnder(false);
  }
}

