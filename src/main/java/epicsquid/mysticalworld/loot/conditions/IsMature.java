package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.EntityClam;
import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

import java.util.Random;

public class IsMature implements LootCondition {
  private final boolean inverse;

  public IsMature(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean testCondition(Random rand, LootContext context) {
    boolean flag;
    Entity looted = context.getLootedEntity();
    if (looted instanceof EntityClam) {
      EntityClam clam = (EntityClam) looted;
      flag = clam.getDataManager().get(EntityClam.age) >= ConfigManager.clam.maturity;
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  public static class Serializer extends LootCondition.Serializer<IsMature> {
    public Serializer() {
      super(new ResourceLocation("is_mature"), IsMature.class);
    }

    @Override
    public void serialize(JsonObject json, IsMature value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsMature deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsMature(JsonUtils.getBoolean(json, "inverse", false));
    }
  }
}

