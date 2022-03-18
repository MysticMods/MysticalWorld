package mysticmods.mysticalworld.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.configured.ModLoot;
import net.minecraft.core.Holder;
import net.minecraft.tags.Tag;
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

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomPotion extends LootItemConditionalFunction {
  private RandomPotion(LootItemCondition[] conditions) {
    super(conditions);
  }

  public LootItemFunctionType getType() {
    return ModLoot.RANDOM_POTION;
  }

  protected TagKey<Potion> getIgnoreTag() {
    return MWTags.Potions.RANDOM_BLACKLIST;
  }

  public ItemStack run(ItemStack stack, LootContext context) {
    Random random = context.getRandom();

    List<Potion> potions = ForgeRegistries.POTIONS.getValues().stream().filter(potion -> ForgeRegistries.POTIONS.getHolder(potion).map(potionHolder -> potionHolder.is(getIgnoreTag())).orElse(false)).collect(Collectors.toList());
    Potion potion = potions.get(random.nextInt(potions.size()));
    PotionUtils.setPotion(stack, potion);
    return stack;
  }

  public static LootItemConditionalFunction.Builder<?> builder() {
    return simpleBuilder(RandomPotion::new);
  }

  public static class Builder extends LootItemConditionalFunction.Builder<RandomPotion.Builder> {

    protected RandomPotion.Builder getThis() {
      return this;
    }

    public RandomPotion.Builder withEnchantment(Enchantment pEnchantment) {
      return this;
    }

    public LootItemFunction build() {
      return new RandomPotion(this.getConditions());
    }
  }

  public static class Serializer extends LootItemConditionalFunction.Serializer<RandomPotion> {
    public void serialize(JsonObject json, RandomPotion base, JsonSerializationContext context) {
      super.serialize(json, base, context);
    }

    public RandomPotion deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
      return new RandomPotion(conditionsIn);
    }
  }
}

