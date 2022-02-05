package mysticmods.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.entity.ClamEntity;
import mysticmods.mysticalworld.entity.SproutEntity;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.entity.Entity;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;

public class IsMature implements ILootCondition {
  private final boolean inverse;

  public IsMature(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext lootContext) {
    boolean flag;
    Entity looted = lootContext.getParamOrNull(LootParameters.THIS_ENTITY);
    if (looted instanceof ClamEntity) {
      ClamEntity clam = (ClamEntity) looted;
      flag = clam.getEntityData().get(ClamEntity.age) >= ConfigManager.CLAM_CONFIG.getMaxAge();
    } else {
      flag = false;
    }

    return flag == !this.inverse;
  }

  @Override
  public LootConditionType getType() {
    return ModLoot.IS_MATURE;
  }

  public static class Serializer implements ILootSerializer<IsMature> {
    @Override
    public void serialize(JsonObject json, IsMature value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsMature deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsMature(JSONUtils.getAsBoolean(json, "inverse", false));
    }
  }

  public static IBuilder builder() {
    return () -> new IsMature(false);
  }
}

