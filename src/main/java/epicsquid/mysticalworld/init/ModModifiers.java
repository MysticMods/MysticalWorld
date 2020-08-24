package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.modifiers.PlayerModifierRegistry;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class ModModifiers {
  public static final IAttribute BLESSED = PlayerModifierRegistry.registerAttribute(new RangedAttribute(null, "mysticalworld.blessed", 0D, 0D, 10D).setDescription("Causes undead attackers to be set on fire and take fire damage").setShouldWatch(true));

  public static void load () {
  }
}
