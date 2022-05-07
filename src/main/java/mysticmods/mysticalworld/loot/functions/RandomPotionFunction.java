package mysticmods.mysticalworld.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomPotionFunction extends LootItemConditionalFunction {
  private RandomPotionFunction(LootItemCondition[] conditions) {
    super(conditions);
  }

  public LootItemFunctionType getType() {
    return ModLoot.RANDOM_POTION.get();
  }

  protected TagKey<Potion> getIgnoreTag() {
    return MWTags.Potions.RANDOM_BLACKLIST;
  }

  public ItemStack run(ItemStack stack, LootContext context) {
    Random random = context.getRandom();

    Set<Potion> blacklisted = new HashSet<>();
    Registry.POTION.getTagOrEmpty(getIgnoreTag()).forEach(o -> blacklisted.add(o.value()));

    List<Potion> potions = ForgeRegistries.POTIONS.getValues().stream().filter(potion -> !blacklisted.contains(potion)).toList();
    Potion potion = potions.get(random.nextInt(potions.size()));
    PotionUtils.setPotion(stack, potion);
    return stack;
  }

  public static LootItemConditionalFunction.Builder<?> builder() {
    return simpleBuilder(RandomPotionFunction::new);
  }

  public static class Builder extends LootItemConditionalFunction.Builder<RandomPotionFunction.Builder> {

    protected RandomPotionFunction.Builder getThis() {
      return this;
    }

    public RandomPotionFunction.Builder withEnchantment(Enchantment pEnchantment) {
      return this;
    }

    public LootItemFunction build() {
      return new RandomPotionFunction(this.getConditions());
    }
  }

  public static class Serializer extends LootItemConditionalFunction.Serializer<RandomPotionFunction> {
    public void serialize(JsonObject json, RandomPotionFunction base, JsonSerializationContext context) {
      super.serialize(json, base, context);
    }

    public RandomPotionFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
      return new RandomPotionFunction(conditionsIn);
    }
  }
}

