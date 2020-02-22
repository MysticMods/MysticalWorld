package epicsquid.mysticalworld.integration.endercore;

import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class EndercoreHarvest {
  public static void init() {
    String message = String.format("%s|%s|7|0", ModItems.aubergine_seed.getRegistryName().toString(), ModBlocks.aubergine.getRegistryName().toString());
    FMLInterModComms.sendMessage("endercore", "addRightClickCrop", message);

/*    message = String.format("%s|%s|7|0", Items.POISONOUS_POTATO.getRegistryName().toString(), ModBlocks.poisoned_potato.getRegistryName().toString());   FMLInterModComms.sendMessage("endercore", "addRightClickCrop", message);*/
  }
}
