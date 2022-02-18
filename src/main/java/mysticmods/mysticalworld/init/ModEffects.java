package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.effects.SimpleEffect;
import mysticmods.mysticalworld.effects.SlowRegenerationEffect;
import mysticmods.mysticalworld.effects.WakefulEffect;
import mysticmods.mysticalworld.potions.SupplierPotion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.alchemy.Potion;

public class ModEffects {
  public static final RegistryEntry<WakefulEffect> WAKEFUL = MysticalWorld.REGISTRATE.effect("wakeful", WakefulEffect::new).register();
  public static final RegistryEntry<SlowRegenerationEffect> SLOW_REGEN = MysticalWorld.REGISTRATE.effect("slow_regeneration", SlowRegenerationEffect::new).register();
  public static final RegistryEntry<MobEffect> SERENDIPITY = MysticalWorld.REGISTRATE.effect("serendipity", () -> new SimpleEffect(MobEffectCategory.BENEFICIAL, 0x8c348c).addAttributeModifier(ModModifiers.SERENDIPITY.get(), "baf5ced1-927f-46ee-9e02-f4aae6d55316", 1.25, AttributeModifier.Operation.ADDITION))
      .lang("Serendipity")
      .register();

  public static final RegistryEntry<SupplierPotion> SERENDIPITY_POTION = MysticalWorld.REGISTRATE.simple("serendipity", Potion.class, () -> new SupplierPotion(() -> new MobEffectInstance(ModEffects.SERENDIPITY.get(), 2 * 20 * 60, 2)));

  public static void load() {
  }
}
