package epicsquid.mysticalworld.items;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;

import java.util.Map;
import java.util.function.Supplier;

public interface IModifiable {
  Map<IAttribute, AttributeModifier> getModifiers ();

  default AttributeModifier getOrCreateModifier(IAttribute attribute, Supplier<AttributeModifier> supplier) {
    AttributeModifier mod = getModifiers().get(attribute);
    if (mod == null) {
      mod = supplier.get();
      getModifiers().put(attribute, mod);
    }
    return mod;
  }
}
