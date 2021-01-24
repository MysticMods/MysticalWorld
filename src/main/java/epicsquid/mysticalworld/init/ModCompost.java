package epicsquid.mysticalworld.init;

import net.minecraft.block.ComposterBlock;

// TODO: Aubergine Seeds

public class ModCompost {
  public static void init() {
    ComposterBlock.CHANCES.put(ModItems.AUBERGINE.get(), 0.65f);
    ComposterBlock.CHANCES.put(ModItems.STUFFED_AUBERGINE.get(), 1.5f);
    ComposterBlock.CHANCES.put(ModItems.COOKED_AUBERGINE.get(), 0.95f);
    ComposterBlock.CHANCES.put(ModItems.AUBERGINE_SALAD.get(), 0.95f);
    ComposterBlock.CHANCES.put(ModItems.BEETROOT_SALAD.get(), 0.95f);
    ComposterBlock.CHANCES.put(ModItems.CACTUS_DANDELION_SALAD.get(), 0.95f);
    ComposterBlock.CHANCES.put(ModItems.DANDELION_CORNFLOWER_SALAD.get(), 0.95f);
    ComposterBlock.CHANCES.put(ModItems.STEWED_EGGPLANT.get(), 0.95f);
    ComposterBlock.CHANCES.put(ModItems.AUBERGINE_SEEDS.get(), 0.3f);
    ComposterBlock.CHANCES.put(ModItems.ASSORTED_SEEDS.get(), 0.3f);
    ComposterBlock.CHANCES.put(ModItems.COOKED_SEEDS.get(), 0.3f);
    ComposterBlock.CHANCES.put(ModItems.COOKED_BEETROOT.get(), 0.65f);
    ComposterBlock.CHANCES.put(ModItems.COOKED_CARROT.get(), 0.65f);
    ComposterBlock.CHANCES.put(ModItems.SLICED_CARROT.get(), 0.1625f);
  }
}
