package epicsquid.mysticalworld.loot;

import noobanidus.libs.noobutil.util.MathUtil;

public class Serendipity {
  public static int calculateAdditional(double val) {
    int i = 0;
    while (val > 0) {
      if (MathUtil.rand.nextDouble() < val) {
        i++;
      }
      val--;
    }
    return i;
  }
}
