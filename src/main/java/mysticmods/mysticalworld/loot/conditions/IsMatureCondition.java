package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class IsMatureCondition implements LootItemCondition {
  private final boolean inverse;

  public IsMatureCondition(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext lootContext) {
    boolean flag;
    Entity looted = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);
    if (looted instanceof ClamEntity) {
      ClamEntity clam = (ClamEntity) looted;
      flag = clam.getEntityData().get(ClamEntity.age) >= ConfigManager.CLAM_CONFIG.getMaxAge();
    } else {
      flag = false;
    }

    return flag == !this.inverse;
  }

  @Override
  public LootItemConditionType getType() {
    return ModLoot.IS_MATURE.get();
  }

  public static class MatureSerializer implements Serializer<IsMatureCondition> {
    @Override
    public void serialize(JsonObject json, IsMatureCondition value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsMatureCondition deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsMatureCondition(GsonHelper.getAsBoolean(json, "inverse", false));
    }
  }

  public static Builder builder() {
    return () -> new IsMatureCondition(false);
  }
}

