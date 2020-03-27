package epicsquid.mysticalworld.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.SproutEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class IsColor implements ILootCondition {
  private final boolean inverse;
  private final int variant;

  public IsColor(boolean inverseIn, String color) {
    this.inverse = inverseIn;
    this.variant = SproutEntity.StringToVariant(color);
  }

  @Override
  public boolean test(LootContext lootContext) {
    int variant;
    Entity looted = lootContext.get(LootParameters.THIS_ENTITY);
    if (looted instanceof SproutEntity) {
      SproutEntity sprout = (SproutEntity) looted;
      variant = sprout.getDataManager().get(SproutEntity.variant);
    } else {
      variant = 0;
    }

    boolean flag = variant == this.variant;

    return flag == !this.inverse;
  }

  public static class Serializer extends AbstractSerializer<IsColor> {
    public Serializer() {
      super(new ResourceLocation(MysticalWorld.MODID, "is_color"), IsColor.class);
    }

    @Override
    public void serialize(JsonObject json, IsColor value, JsonSerializationContext context) {
      json.addProperty("inverse", value.inverse);
      json.addProperty("color", SproutEntity.VariantToString(value.variant));
    }

    @Override
    public IsColor deserialize(JsonObject json, JsonDeserializationContext context) {
      return new IsColor(JSONUtils.getBoolean(json, "inverse", false), JSONUtils.getString(json, "color", "green"));
    }
  }

  public static ILootCondition.IBuilder builder(String variant) {
    return () -> new IsColor(false, variant);
  }
}

