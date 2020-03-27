package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.LavaCatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class IsObsidian implements ILootCondition {
  private final boolean inverse;

  public IsObsidian(boolean inverseIn) {
    this.inverse = inverseIn;
  }

  @Override
  public boolean test(LootContext context) {
    boolean flag;
    Entity looted = context.get(LootParameters.THIS_ENTITY);
    if (looted instanceof LavaCatEntity) {
      LavaCatEntity cat = (LavaCatEntity) looted;
      flag = !cat.getIsLava();
    } else {
      flag = false;
    }
    return flag == !this.inverse;
  }

  public static class Serializer extends ILootCondition.AbstractSerializer<IsObsidian> {
    public Serializer() {
      super(new ResourceLocation(MysticalWorld.MODID, "is_obsidian"), IsObsidian.class);
    }

    @Override
    public void serialize(JsonObject json, IsObsidian value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
    }

    @Override
    public IsObsidian deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsObsidian(JSONUtils.getBoolean(json, "inverse", false));
    }
  }

  private static IsObsidian INSTANCE = new IsObsidian(false);

  public static ILootCondition.IBuilder builder() {
    return () -> INSTANCE;
  }
}

