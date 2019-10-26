package epicsquid.mysticalworld.init;

import net.minecraft.block.ComposterBlock;

public class ModCompost {
  public static void init () {
    ComposterBlock.CHANCES.put(ModItems.AUBERGINE.get(), 0.65f);
    ComposterBlock.CHANCES.put(ModItems.STUFFED_AUBERGINE.get(), 1.5f);
    ComposterBlock.CHANCES.put(ModItems.AUBERGINE_SEEDS.get(), 0.3f);
  }
}
