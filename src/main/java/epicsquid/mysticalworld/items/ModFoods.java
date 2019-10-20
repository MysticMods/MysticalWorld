package epicsquid.mysticalworld.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {

  public static final Food VENISON = (new Food.Builder().hunger(3).meat().saturation(0.3f)).build();
  public static final Food COOKED_VENISON = (new Food.Builder().hunger(7).meat().saturation(0.8f)).build();
  public static final Food AUBERGINE = (new Food.Builder().hunger(4).saturation(0.3f)).build();
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
      .build();
}
