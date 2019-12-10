package epicsquid.mysticalworld.loot;

import epicsquid.mysticallib.util.Util;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class Serendipity {
  public static int calculateAdditional(IAttributeInstance serendipity) {
    int i = 0;
    serendipity.setBaseValue(1);
    double val = serendipity.getValue() - 1.0;
    while (val > 0) {
      if (Util.rand.nextDouble() < val) {
        i++;
      }
      val--;
    }
    return i;
  }
}
