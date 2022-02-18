package mysticmods.mysticalworld.potions;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SupplierPotion extends Potion {
  private final ImmutableList<Supplier<MobEffectInstance>> effectsSupplier;
  private List<MobEffectInstance> instanceList = null;

  @SafeVarargs
  public SupplierPotion(Supplier<MobEffectInstance>... effectsIn) {
    this(null, effectsIn);
  }

  @SafeVarargs
  public SupplierPotion(@Nullable String baseNameIn, Supplier<MobEffectInstance>... effectsIn) {
    super(baseNameIn, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN));
    this.effects = ImmutableList.of();
    this.effectsSupplier = ImmutableList.copyOf(effectsIn);
  }

  @Override
  public List<MobEffectInstance> getEffects() {
    if (instanceList == null) {
      instanceList = effectsSupplier.stream().map(Supplier::get).collect(Collectors.toList());
    }
    return this.instanceList;
  }

  @Override
  public boolean hasInstantEffects() {
    if (!effectsSupplier.isEmpty()) {
      for (MobEffectInstance effectinstance : this.getEffects()) {
        if (effectinstance.getEffect().isInstantenous()) {
          return true;
        }
      }
    }

    return false;
  }
}
