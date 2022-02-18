package mysticmods.mysticalworld.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

@SuppressWarnings("WeakerAccess")
public class ModFoods {
  public static final FoodProperties FISH_AND_CHIPS = (new FoodProperties.Builder().nutrition(10).meat().saturationMod(0.8f)).build();
  public static final FoodProperties VENISON = (new FoodProperties.Builder().nutrition(3).meat().saturationMod(0.3f)).build();
  public static final FoodProperties COOKED_VENISON = (new FoodProperties.Builder().nutrition(7).meat().saturationMod(0.8f)).build();
  public static final FoodProperties AUBERGINE = (new FoodProperties.Builder().nutrition(4).saturationMod(0.3f)).build();
  public static final FoodProperties COOKED_BEETROOT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.8F).build();
  public static final FoodProperties COOKED_CARROT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
  public static final FoodProperties SLICED_CARROT = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.6F).fast().build();
  public static final FoodProperties COOKED_AUBERGINE = (new FoodProperties.Builder().nutrition(5).saturationMod(0.8f)).build();
  public static final FoodProperties STUFFED_AUBERGINE = (new FoodProperties.Builder().nutrition(10).saturationMod(0.8f)).build();
  public static final FoodProperties RAW_SQUID = (new FoodProperties.Builder().nutrition(1).saturationMod(0.3f)).meat().build();
  public static final FoodProperties COOKED_SQUID = (new FoodProperties.Builder().nutrition(3).saturationMod(0.8f)).meat().build();
  public static final FoodProperties COOKED_SEEDS = (new FoodProperties.Builder().nutrition(1).saturationMod(0.4f)).fast().build();
  public static final FoodProperties EPIC_SQUID = (new FoodProperties.Builder().nutrition(20).saturationMod(1.0f)).meat()
      .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1.0f)
      .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 3000), 1.0f)
      .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 1200), 1.0f)
      .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1.0f)
      .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0f)
      .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0f)
      .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1200, 3), 1.0f)
      .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 1200), 1.0f)
      .alwaysEat().build();

  // Drinks
  public static final FoodProperties APPLE_CORDIAL = (new FoodProperties.Builder().nutrition(1).saturationMod(7.5f)).effect(
      () -> new MobEffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final FoodProperties CACTUS_SYRUP = new FoodProperties.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new MobEffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final FoodProperties DANDELION_CORDIAL = new FoodProperties.Builder().nutrition(1).saturationMod(7.5f).alwaysEat().effect(
      () -> new MobEffectInstance(ModEffects.WAKEFUL.get()), 1f).build();
  public static final FoodProperties LILAC_CORDIAL = new FoodProperties.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new MobEffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final FoodProperties PEONY_CORDIAL = new FoodProperties.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new MobEffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final FoodProperties ROSE_CORDIAL = new FoodProperties.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new MobEffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();
  public static final FoodProperties VINEGAR = new FoodProperties.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new MobEffectInstance(MobEffects.HUNGER, 300, 0, false, false), 1.0f).build();
  public static final FoodProperties VEGETABLE_JUICE = new FoodProperties.Builder().nutrition(1).saturationMod(7.5f).effect(
      () -> new MobEffectInstance(ModEffects.SLOW_REGEN.get(), 1200, 0, false, false), 1f).build();

  // Salads
  public static final FoodProperties AUBERGINE_SALAD = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
  public static final FoodProperties BEETROOT_SALAD = new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).build();
  public static final FoodProperties CACTUS_DANDELION_SALAD = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).build();
  public static final FoodProperties DANDELION_CORNFLOWER_SALAD = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).build();
  public static final FoodProperties STEWED_EGGPLANT = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();
}
