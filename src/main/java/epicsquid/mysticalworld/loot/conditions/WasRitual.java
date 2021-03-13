package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.EntityClam;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;

import java.util.Random;

public class WasRitual implements LootCondition {
  private final boolean inverse;

  public WasRitual(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean testCondition(Random rand, LootContext context) {
    return (context.damageSource != null && context.damageSource.damageType.equals("harvest_damage_ritual")) == !this.inverse;
  }

  public static class Serializer extends LootCondition.Serializer<WasRitual> {
    public Serializer() {
      super(new ResourceLocation("was_ritual"), WasRitual.class);
    }

    @Override
    public void serialize(JsonObject json, WasRitual value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public WasRitual deserialize(JsonObject json, JsonDeserializationContext context) {
      return new WasRitual(JsonUtils.getBoolean(json, "inverse", false));
    }
  }
}

