package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.init.deferred.ModItems;
import net.minecraft.world.level.block.ComposterBlock;

// TODO: Is there a new way to do compostables?
public class ModCompost {
  public static void init() {
    ComposterBlock.COMPOSTABLES.put(ModItems.AUBERGINE.get(), 0.65f);
    ComposterBlock.COMPOSTABLES.put(ModItems.STUFFED_AUBERGINE.get(), 1.5f);
    ComposterBlock.COMPOSTABLES.put(ModItems.COOKED_AUBERGINE.get(), 0.95f);
    ComposterBlock.COMPOSTABLES.put(ModItems.AUBERGINE_SALAD.get(), 0.95f);
    ComposterBlock.COMPOSTABLES.put(ModItems.BEETROOT_SALAD.get(), 0.95f);
    ComposterBlock.COMPOSTABLES.put(ModItems.CACTUS_DANDELION_SALAD.get(), 0.95f);
    ComposterBlock.COMPOSTABLES.put(ModItems.DANDELION_CORNFLOWER_SALAD.get(), 0.95f);
    ComposterBlock.COMPOSTABLES.put(ModItems.STEWED_EGGPLANT.get(), 0.95f);
    ComposterBlock.COMPOSTABLES.put(ModItems.AUBERGINE_SEEDS.get(), 0.3f);
    ComposterBlock.COMPOSTABLES.put(ModItems.ASSORTED_SEEDS.get(), 0.3f);
    ComposterBlock.COMPOSTABLES.put(ModItems.COOKED_SEEDS.get(), 0.3f);
    ComposterBlock.COMPOSTABLES.put(ModItems.COOKED_BEETROOT.get(), 0.65f);
    ComposterBlock.COMPOSTABLES.put(ModItems.COOKED_CARROT.get(), 0.65f);
    ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_CARROT.get(), 0.1625f);
  }
}
