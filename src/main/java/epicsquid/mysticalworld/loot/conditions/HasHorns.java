package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

import epicsquid.mysticalworld.entity.EntityDeer;
import net.minecraft.entity.Entity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

public class HasHorns implements LootCondition
{
  private final boolean inverse;

  public HasHorns(boolean inverseIn)
  {
    this.inverse = inverseIn;
  }

  public boolean testCondition(Random rand, LootContext context)
  {
    boolean flag;
    Entity looted = context.getLootedEntity();
    if (looted instanceof EntityDeer) {
      EntityDeer deer = (EntityDeer) looted;
      flag = deer.getDataManager().get(EntityDeer.hasHorns);
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  public static class Serializer extends LootCondition.Serializer<HasHorns>
  {
    public Serializer()
    {
      super(new ResourceLocation("has_horns"), HasHorns.class);
    }

    public void serialize(JsonObject json, HasHorns value, JsonSerializationContext context)
    {
      json.addProperty("inverse", value.inverse);
    }

    public HasHorns deserialize(JsonObject json, JsonDeserializationContext context)
    {
      return new HasHorns(JsonUtils.getBoolean(json, "inverse", false));
    }
  }
}

