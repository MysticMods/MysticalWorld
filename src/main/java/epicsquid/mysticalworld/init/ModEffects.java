package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.effects.SimpleEffect;
import epicsquid.mysticalworld.effects.SlowRegenerationEffect;
import epicsquid.mysticalworld.effects.WakefulEffect;
import epicsquid.mysticalworld.potions.SupplierPotion;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModEffects {
  public static final RegistryEntry<WakefulEffect> WAKEFUL = REGISTRATE.effect("wakeful", WakefulEffect::new).register();
  public static final RegistryEntry<SlowRegenerationEffect> SLOW_REGEN = REGISTRATE.effect("slow_regeneration", SlowRegenerationEffect::new).register();
  public static final RegistryEntry<Effect> SERENDIPITY = REGISTRATE.effect("serendipity", () -> new SimpleEffect(EffectType.BENEFICIAL, 0x8c348c).addAttributesModifier(ModModifiers.SERENDIPITY.get(), "baf5ced1-927f-46ee-9e02-f4aae6d55316", 1.25, AttributeModifier.Operation.ADDITION))
      .lang("Serendipity")
      .register();

  public static final RegistryEntry<SupplierPotion> SERENDIPITY_POTION = REGISTRATE.simple("serendipity", Potion.class, () -> new SupplierPotion(() -> new EffectInstance(ModEffects.SERENDIPITY.get(), 2 * 20 * 60, 2)));

  public static void load() {
  }
}
