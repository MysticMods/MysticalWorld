package mysticmods.mysticalworld.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModLoot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootFunction;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tags.ITag;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomPotion extends LootFunction {
  private RandomPotion(ILootCondition[] conditions) {
    super(conditions);
  }

  public LootFunctionType getFunctionType() {
    return ModLoot.RANDOM_POTION;
  }

  protected ITag.INamedTag<Potion> getIgnoreTag() {
    return MWTags.Potions.RANDOM_BLACKLIST;
  }

  public ItemStack doApply(ItemStack stack, LootContext context) {
    Random random = context.getRandom();

    List<Potion> potions = ForgeRegistries.POTION_TYPES.getValues().stream().filter(potion -> !potion.isIn(getIgnoreTag())).collect(Collectors.toList());
    Potion potion = potions.get(random.nextInt(potions.size()));
    PotionUtils.addPotionToItemStack(stack, potion);
    return stack;
  }

  public static LootFunction.Builder<?> builder() {
    return builder(RandomPotion::new);
  }

  public static class Builder extends LootFunction.Builder<RandomPotion.Builder> {

    protected RandomPotion.Builder doCast() {
      return this;
    }

    public RandomPotion.Builder func_237424_a_(Enchantment p_237424_1_) {
      return this;
    }

    public ILootFunction build() {
      return new RandomPotion(this.getConditions());
    }
  }

  public static class Serializer extends LootFunction.Serializer<RandomPotion> {
    public void serialize(JsonObject json, RandomPotion base, JsonSerializationContext context) {
      super.serialize(json, base, context);
    }

    public RandomPotion deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
      return new RandomPotion(conditionsIn);
    }
  }
}

