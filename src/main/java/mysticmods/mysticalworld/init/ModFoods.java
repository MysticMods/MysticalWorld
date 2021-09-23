package mysticmods.mysticalworld.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

@SuppressWarnings("WeakerAccess")
public class ModFoods {
  public static final Food FISH_AND_CHIPS = (new Food.Builder().nutrition(10).meat().saturationMod(0.8f)).build();
  public static final Food VENISON = (new Food.Builder().nutrition(3).meat().saturationMod(0.3f)).build();
  public static final Food COOKED_VENISON = (new Food.Builder().nutrition(7).meat().saturationMod(0.8f)).build();
  public static final Food AUBERGINE = (new Food.Builder().nutrition(4).saturationMod(0.3f)).build();
  public static final Food COOKED_BEETROOT = (new Food.Builder()).nutrition(4).saturationMod(0.8F).build();
  public static final Food COOKED_CARROT = (new Food.Builder()).nutrition(4).saturationMod(0.6F).build();
  public static final Food SLICED_CARROT = (new Food.Builder()).nutrition(1).saturationMod(0.6F).fast().build();
  public static final Food COOKED_AUBERGINE = (new Food.Builder().nutrition(5).saturationMod(0.8f)).build();
  public static final Food STUFFED_AUBERGINE = (new Food.Builder().nutrition(10).saturationMod(0.8f)).build();
  public static final Food RAW_SQUID = (new Food.Builder().nutrition(1).saturationMod(0.3f)).meat().build();
  public static final Food COOKED_SQUID = (new Food.Builder().nutrition(3).saturationMod(0.8f)).meat().build();
  public static final Food COOKED_SEEDS = (new Food.Builder().nutrition(1).saturationMod(0.4f)).fast().build();
  public static final Food EPIC_SQUID = (new Food.Builder().nutrition(20).saturationMod(1.0f)).meat()
      .effect(() -> new EffectInstance(Effects.REGENERATION, 600, 1), 1.0f)
      .effect(() -> new EffectInstance(Effects.NIGHT_VISION, 3000), 1.0f)
      .effect(() -> new EffectInstance(Effects.INVISIBILITY, 1200), 1.0f)
      .effect(() -> new EffectInstance(Effects.DIG_SPEED, 1200, 1), 1.0f)
      .effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 1200, 0), 1.0f)
      .effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0), 1.0f)
      .effect(() -> new EffectInstance(Effects.ABSORPTION, 1200, 3), 1.0f)
      .effect(() -> new EffectInstance(Effects.WATER_BREATHING, 1200), 1.0f)
      .alwaysEat().build();

  // Drinks
  public static final Food APPLE_CORDIAL = (new Food.Builder().nutrition(1).saturationMod(7.5f)).effect(
      () -> new EffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final Food CACTUS_SYRUP = new Food.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new EffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final Food DANDELION_CORDIAL = new Food.Builder().nutrition(1).saturationMod(7.5f).alwaysEat().effect(
      () -> new EffectInstance(ModEffects.WAKEFUL.get()), 1f).build();
  public static final Food LILAC_CORDIAL = new Food.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new EffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final Food PEONY_CORDIAL = new Food.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new EffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final Food ROSE_CORDIAL = new Food.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new EffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final Food VINEGAR = new Food.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new EffectInstance(Effects.HUNGER, 300, 0, false, false), 1.0f).build();
  public static final Food VEGETABLE_JUICE = new Food.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new EffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();

  // Salads
  public static final Food AUBERGINE_SALAD = new Food.Builder().nutrition(5).saturationMod(0.4F).build();
  public static final Food BEETROOT_SALAD = new Food.Builder().nutrition(2).saturationMod(0.6F).build();
  public static final Food CACTUS_DANDELION_SALAD = new Food.Builder().nutrition(3).saturationMod(0.4F).build();
  public static final Food DANDELION_CORNFLOWER_SALAD = new Food.Builder().nutrition(3).saturationMod(0.4F).build();
  public static final Food STEWED_EGGPLANT = new Food.Builder().nutrition(6).saturationMod(0.8F).build();
}
