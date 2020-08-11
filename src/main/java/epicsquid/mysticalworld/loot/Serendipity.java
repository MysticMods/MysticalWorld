package epicsquid.mysticalworld.loot;

import net.minecraft.entity.ai.attributes.IAttributeInstance;
import noobanidus.libs.noobutil.util.MathUtil;

public class Serendipity {
  public static int calculateAdditional(IAttributeInstance serendipity) {
    int i = 0;
    serendipity.setBaseValue(1);
    double val = serendipity.getValue() - 1.0;
    while (val > 0) {
      if (MathUtil.rand.nextDouble() < val) {
        i++;
      }
      val--;
    }
    return i;
  }
}
