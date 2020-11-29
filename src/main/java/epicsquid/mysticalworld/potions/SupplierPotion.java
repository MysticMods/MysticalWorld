package epicsquid.mysticalworld.potions;

import com.google.common.collect.ImmutableList;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SupplierPotion extends Potion {
  private final ImmutableList<Supplier<EffectInstance>> effectsSupplier;
  private List<EffectInstance> instanceList = null;

  @SafeVarargs
  public SupplierPotion(Supplier<EffectInstance>... effectsIn) {
    this(null, effectsIn);
  }

  @SafeVarargs
  public SupplierPotion(@Nullable String baseNameIn, Supplier<EffectInstance>... effectsIn) {
    super(baseNameIn, new EffectInstance(Effects.SLOWNESS));
    this.effects = ImmutableList.of();
    this.effectsSupplier = ImmutableList.copyOf(effectsIn);
  }

  @Override
  public List<EffectInstance> getEffects() {
    if (instanceList == null) {
      instanceList = effectsSupplier.stream().map(Supplier::get).collect(Collectors.toList());
    }
    return this.instanceList;
  }

  @Override
  public boolean hasInstantEffect() {
    if (!effectsSupplier.isEmpty()) {
      for (EffectInstance effectinstance : this.getEffects()) {
        if (effectinstance.getPotion().isInstant()) {
          return true;
        }
      }
    }

    return false;
  }
}
