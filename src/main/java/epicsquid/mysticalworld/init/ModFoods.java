package epicsquid.mysticalworld.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

@SuppressWarnings("WeakerAccess")
public class ModFoods {
  public static final Food VENISON = (new Food.Builder().hunger(3).meat().saturation(0.3f)).build();
  public static final Food COOKED_VENISON = (new Food.Builder().hunger(7).meat().saturation(0.8f)).build();
  public static final Food AUBERGINE = (new Food.Builder().hunger(4).saturation(0.3f)).build();
  public static final Food COOKED_BEETROOT = (new Food.Builder()).hunger(4).saturation(0.8F).build();
  public static final Food COOKED_CARROT = (new Food.Builder()).hunger(4).saturation(0.6F).build();
  public static final Food SLICED_CARROT = (new Food.Builder()).hunger(1).saturation(0.6F).fastToEat().build();
  public static final Food COOKED_AUBERGINE = (new Food.Builder().hunger(5).saturation(0.8f)).build();
  public static final Food STUFFED_AUBERGINE = (new Food.Builder().hunger(10).saturation(0.8f)).build();
  public static final Food RAW_SQUID = (new Food.Builder().hunger(1).saturation(0.3f)).meat().build();
  public static final Food COOKED_SQUID = (new Food.Builder().hunger(3).saturation(0.8f)).meat().build();
  public static final Food EPIC_SQUID = (new Food.Builder().hunger(20).saturation(1.0f)).meat()
      .effect(new EffectInstance(Effects.REGENERATION, 600, 1), 1.0f)
      .effect(new EffectInstance(Effects.NIGHT_VISION, 3000), 1.0f)
      .effect(new EffectInstance(Effects.INVISIBILITY, 1200), 1.0f)
      .effect(new EffectInstance(Effects.HASTE, 1200, 1), 1.0f)
      .effect(new EffectInstance(Effects.RESISTANCE, 1200, 0), 1.0f)
      .effect(new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0), 1.0f)
      .effect(new EffectInstance(Effects.ABSORPTION, 1200, 3), 1.0f)
      .effect(new EffectInstance(Effects.WATER_BREATHING, 1200), 1.0f)
      .setAlwaysEdible().build();

  // Drinks
  public static final Food APPLE_CORDIAL = (new Food.Builder().hunger(1).saturation(0.9f)).setAlwaysEdible().effect(
      new EffectInstance(Effects.SPEED, 100), 1.0f).build();
  public static final Food CACTUS_SYRUP = new Food.Builder().hunger(1).saturation(0.9f).setAlwaysEdible().build();
  public static final Food DANDELION_CORDIAL = new Food.Builder().hunger(1).saturation(0.9f).setAlwaysEdible().build();
  public static final Food LILAC_CORDIAL = new Food.Builder().hunger(1).saturation(0.9f).setAlwaysEdible().build();
  public static final Food PEONY_CORDIAL = new Food.Builder().hunger(1).saturation(0.9f).setAlwaysEdible().build();
  public static final Food ROSE_CORDIAL = new Food.Builder().hunger(1).saturation(0.9f).setAlwaysEdible().build();
  public static final Food VINEGAR = new Food.Builder().hunger(1).saturation(0.9f).setAlwaysEdible().build();
  public static final Food VEGETABLE_JUICE = new Food.Builder().hunger(1).saturation(0.9f).setAlwaysEdible().build();

  // Salads
  public static final Food AUBERGINE_SALAD = buildStew(6);
  public static final Food BEETROOT_SALAD = buildStew(6);
  public static final Food CACTUS_DANDELION_SALAD = buildStew(6);
  public static final Food DANDELION_CORNFLOWER_SALAD = buildStew(6);
  public static final Food STEWED_EGGPLANT = buildStew(8);

   private static Food buildStew(int hunger) {
      return (new Food.Builder()).hunger(hunger).saturation(0.6F).build();
   }
}
