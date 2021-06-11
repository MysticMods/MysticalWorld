package mysticmods.mysticalworld.loot.functions;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.loot.Serendipity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.LootFunctionManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ApplyBonusModified extends LootFunction {
  private static final Map<ResourceLocation, ApplyBonusModified.IFormulaDeserializer> field_215875_a = Maps.newHashMap();
  private final Enchantment enchantment;
  private final ApplyBonusModified.IFormula field_215877_d;

  private ApplyBonusModified(ILootCondition[] p_i51246_1_, Enchantment p_i51246_2_, ApplyBonusModified.IFormula p_i51246_3_) {
    super(p_i51246_1_);
    this.enchantment = p_i51246_2_;
    this.field_215877_d = p_i51246_3_;
  }

  @Override
  public LootFunctionType getFunctionType() {
    return LootFunctionManager.APPLY_BONUS;
  }

  @Override
  public Set<LootParameter<?>> getRequiredParameters() {
    return ImmutableSet.of(LootParameters.TOOL);
  }

  @Override
  public ItemStack doApply(ItemStack p_215859_1_, LootContext context) {
    ItemStack lvt_3_1_ = context.get(LootParameters.TOOL);
    if (lvt_3_1_ != null) {
      int lvt_4_1_ = EnchantmentHelper.getEnchantmentLevel(this.enchantment, lvt_3_1_);
      if (this.enchantment == Enchantments.FORTUNE) {
        Entity entity = context.get(LootParameters.THIS_ENTITY);
        if (entity instanceof PlayerEntity) {
          double serendipity = ((PlayerEntity) entity).getAttributeValue(ModModifiers.SERENDIPITY.get());
          lvt_4_1_ += Serendipity.calculateAdditional(serendipity);
        }
      }
      int lvt_5_1_ = this.field_215877_d.func_216204_a(context.getRandom(), p_215859_1_.getCount(), lvt_4_1_);
      p_215859_1_.setCount(lvt_5_1_);
    }

    return p_215859_1_;
  }

  public static Builder<?> binomialWithBonusCount(Enchantment p_215870_0_, float p_215870_1_, int p_215870_2_) {
    return builder((p_215864_3_) -> new ApplyBonusModified(p_215864_3_, p_215870_0_, new BinomialWithBonusCountFormula(p_215870_2_, p_215870_1_)));
  }

  public static Builder<?> oreDrops(Enchantment p_215869_0_) {
    return builder((p_215866_1_) -> new ApplyBonusModified(p_215866_1_, p_215869_0_, new OreDropsFormula()));
  }

  public static Builder<?> uniformBonusCount(Enchantment p_215871_0_) {
    return builder((p_215872_1_) -> new ApplyBonusModified(p_215872_1_, p_215871_0_, new UniformBonusCountFormula(1)));
  }

  public static Builder<?> uniformBonusCount(Enchantment p_215865_0_, int p_215865_1_) {
    return builder((p_215868_2_) -> new ApplyBonusModified(p_215868_2_, p_215865_0_, new UniformBonusCountFormula(p_215865_1_)));
  }

  static {
    field_215875_a.put(ApplyBonusModified.BinomialWithBonusCountFormula.field_216211_a, ApplyBonusModified.BinomialWithBonusCountFormula::func_216210_a);
    field_215875_a.put(ApplyBonusModified.OreDropsFormula.field_216206_a, ApplyBonusModified.OreDropsFormula::func_216205_a);
    field_215875_a.put(ApplyBonusModified.UniformBonusCountFormula.field_216208_a, ApplyBonusModified.UniformBonusCountFormula::func_216207_a);
  }

  @SuppressWarnings("deprecation")
  public static class Serializer extends net.minecraft.loot.LootFunction.Serializer<ApplyBonusModified> {
    public Serializer() {
    }

    @Override
    public void serialize(JsonObject p_230424_1_, ApplyBonusModified p_230424_2_, JsonSerializationContext p_230424_3_) {
      super.serialize(p_230424_1_, p_230424_2_, p_230424_3_);
      p_230424_1_.addProperty("enchantment", Registry.ENCHANTMENT.getKey(p_230424_2_.enchantment).toString());
      p_230424_1_.addProperty("formula", p_230424_2_.field_215877_d.func_216203_a().toString());
      JsonObject lvt_4_1_ = new JsonObject();
      p_230424_2_.field_215877_d.func_216202_a(lvt_4_1_, p_230424_3_);
      if (lvt_4_1_.size() > 0) {
        p_230424_1_.add("parameters", lvt_4_1_);
      }

    }

    @Override
    public ApplyBonusModified deserialize(JsonObject p_186530_1_, JsonDeserializationContext p_186530_2_, ILootCondition[] p_186530_3_) {
      ResourceLocation lvt_4_1_ = new ResourceLocation(JSONUtils.getString(p_186530_1_, "enchantment"));
      Enchantment lvt_5_1_ = Registry.ENCHANTMENT.getOptional(lvt_4_1_).orElseThrow(() -> new JsonParseException("Invalid enchantment id: " + lvt_4_1_));
      ResourceLocation lvt_6_1_ = new ResourceLocation(JSONUtils.getString(p_186530_1_, "formula"));
      ApplyBonusModified.IFormulaDeserializer lvt_7_1_ = ApplyBonusModified.field_215875_a.get(lvt_6_1_);
      if (lvt_7_1_ == null) {
        throw new JsonParseException("Invalid formula id: " + lvt_6_1_);
      } else {
        ApplyBonusModified.IFormula lvt_8_2_;
        if (p_186530_1_.has("parameters")) {
          lvt_8_2_ = lvt_7_1_.deserialize(JSONUtils.getJsonObject(p_186530_1_, "parameters"), p_186530_2_);
        } else {
          lvt_8_2_ = lvt_7_1_.deserialize(new JsonObject(), p_186530_2_);
        }

        return new ApplyBonusModified(p_186530_3_, lvt_5_1_, lvt_8_2_);
      }
    }
  }

  static final class OreDropsFormula implements ApplyBonusModified.IFormula {
    public static final ResourceLocation field_216206_a = new ResourceLocation("ore_drops");

    private OreDropsFormula() {
    }

    @Override
    public int func_216204_a(Random p_216204_1_, int p_216204_2_, int p_216204_3_) {
      if (p_216204_3_ > 0) {
        int lvt_4_1_ = p_216204_1_.nextInt(p_216204_3_ + 2) - 1;
        if (lvt_4_1_ < 0) {
          lvt_4_1_ = 0;
        }

        return p_216204_2_ * (lvt_4_1_ + 1);
      } else {
        return p_216204_2_;
      }
    }

    @Override
    public void func_216202_a(JsonObject p_216202_1_, JsonSerializationContext p_216202_2_) {
    }

    public static ApplyBonusModified.IFormula func_216205_a(JsonObject p_216205_0_, JsonDeserializationContext p_216205_1_) {
      return new ApplyBonusModified.OreDropsFormula();
    }

    @Override
    public ResourceLocation func_216203_a() {
      return field_216206_a;
    }
  }

  static final class UniformBonusCountFormula implements ApplyBonusModified.IFormula {
    public static final ResourceLocation field_216208_a = new ResourceLocation("uniform_bonus_count");
    private final int bonusMultiplier;

    public UniformBonusCountFormula(int p_i50981_1_) {
      this.bonusMultiplier = p_i50981_1_;
    }

    @Override
    public int func_216204_a(Random p_216204_1_, int p_216204_2_, int p_216204_3_) {
      return p_216204_2_ + p_216204_1_.nextInt(this.bonusMultiplier * p_216204_3_ + 1);
    }

    @Override
    public void func_216202_a(JsonObject p_216202_1_, JsonSerializationContext p_216202_2_) {
      p_216202_1_.addProperty("bonusMultiplier", this.bonusMultiplier);
    }

    public static ApplyBonusModified.IFormula func_216207_a(JsonObject p_216207_0_, JsonDeserializationContext p_216207_1_) {
      int lvt_2_1_ = JSONUtils.getInt(p_216207_0_, "bonusMultiplier");
      return new ApplyBonusModified.UniformBonusCountFormula(lvt_2_1_);
    }

    @Override
    public ResourceLocation func_216203_a() {
      return field_216208_a;
    }
  }

  static final class BinomialWithBonusCountFormula implements ApplyBonusModified.IFormula {
    public static final ResourceLocation field_216211_a = new ResourceLocation("binomial_with_bonus_count");
    private final int extra;
    private final float probability;

    public BinomialWithBonusCountFormula(int p_i50983_1_, float p_i50983_2_) {
      this.extra = p_i50983_1_;
      this.probability = p_i50983_2_;
    }

    @Override
    public int func_216204_a(Random p_216204_1_, int p_216204_2_, int p_216204_3_) {
      for (int lvt_4_1_ = 0; lvt_4_1_ < p_216204_3_ + this.extra; ++lvt_4_1_) {
        if (p_216204_1_.nextFloat() < this.probability) {
          ++p_216204_2_;
        }
      }

      return p_216204_2_;
    }

    @Override
    public void func_216202_a(JsonObject p_216202_1_, JsonSerializationContext p_216202_2_) {
      p_216202_1_.addProperty("extra", this.extra);
      p_216202_1_.addProperty("probability", this.probability);
    }

    public static ApplyBonusModified.IFormula func_216210_a(JsonObject p_216210_0_, JsonDeserializationContext p_216210_1_) {
      int lvt_2_1_ = JSONUtils.getInt(p_216210_0_, "extra");
      float lvt_3_1_ = JSONUtils.getFloat(p_216210_0_, "probability");
      return new ApplyBonusModified.BinomialWithBonusCountFormula(lvt_2_1_, lvt_3_1_);
    }

    @Override
    public ResourceLocation func_216203_a() {
      return field_216211_a;
    }
  }

  interface IFormulaDeserializer {
    ApplyBonusModified.IFormula deserialize(JsonObject var1, JsonDeserializationContext var2);
  }

  interface IFormula {
    int func_216204_a(Random var1, int var2, int var3);

    void func_216202_a(JsonObject var1, JsonSerializationContext var2);

    ResourceLocation func_216203_a();
  }
}
