package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import epicsquid.mysticalworld.entity.EntityClam;
import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

import java.util.Random;

public class IsEnder implements LootCondition {
  private final boolean inverse;

  public IsEnder(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean testCondition(Random rand, LootContext context) {
    boolean flag;
    Entity looted = context.getLootedEntity();
    if (looted instanceof EntityClam) {
      EntityClam clam = (EntityClam) looted;
      flag = clam.getDataManager().get(EntityClam.ender);
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  public static class Serializer extends LootCondition.Serializer<IsEnder> {
    public Serializer() {
      super(new ResourceLocation("is_ender"), IsEnder.class);
    }

    @Override
    public void serialize(JsonObject json, IsEnder value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsEnder deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsEnder(JsonUtils.getBoolean(json, "inverse", false));
    }
  }
}

