package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.effects.SimpleEffect;
import mysticmods.mysticalworld.effects.SlowRegenerationEffect;
import mysticmods.mysticalworld.effects.WakefulEffect;
import mysticmods.mysticalworld.potions.SupplierPotion;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;

public class ModEffects {
  public static final RegistryEntry<WakefulEffect> WAKEFUL = MysticalWorld.REGISTRATE.effect("wakeful", WakefulEffect::new).register();
  public static final RegistryEntry<SlowRegenerationEffect> SLOW_REGEN = MysticalWorld.REGISTRATE.effect("slow_regeneration", SlowRegenerationEffect::new).register();
  public static final RegistryEntry<Effect> SERENDIPITY = MysticalWorld.REGISTRATE.effect("serendipity", () -> new SimpleEffect(EffectType.BENEFICIAL, 0x8c348c).addAttributesModifier(ModModifiers.SERENDIPITY.get(), "baf5ced1-927f-46ee-9e02-f4aae6d55316", 1.25, AttributeModifier.Operation.ADDITION))
      .lang("Serendipity")
      .register();

  public static final RegistryEntry<SupplierPotion> SERENDIPITY_POTION = MysticalWorld.REGISTRATE.simple("serendipity", Potion.class, () -> new SupplierPotion(() -> new EffectInstance(ModEffects.SERENDIPITY.get(), 2 * 20 * 60, 2)));

  public static void load() {
  }
}
