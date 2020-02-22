package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class MappingHandler {

  @SubscribeEvent
  public static void onMissingBlock (RegistryEvent.MissingMappings<Block> event) {
    for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getAllMappings()) {
      if (mapping.key.getNamespace().equals("mysticalworld")) {
        if (mapping.key.getPath().equals("poisoned_potato_crop")) {
          mapping.remap(Blocks.POTATOES);
        }
      } else {
        if (!mapping.key.getNamespace().equals("roots")) continue;

        if (mapping.key.getPath().equals("aubergine_crop")) {
          mapping.remap(ModBlocks.aubergine);
        }
      }
    }
  }

  @SubscribeEvent
  public static void onMissingItem (RegistryEvent.MissingMappings<Item> event) {
    for (RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getAllMappings()) {
      if (mapping.key.getNamespace().equals("mysticalworld")) {
        switch (mapping.key.getPath()) {
          case "cooked_apple":
            mapping.remap(Items.APPLE);
            break;
        }
      } else {

        if (!mapping.key.getNamespace().equals("roots")) continue;

        switch (mapping.key.getPath()) {
          case "aubergine_seed":
            mapping.remap(ModItems.aubergine_seed);
            break;
          case "stuffed_aubergine":
            mapping.remap(ModItems.stuffed_aubergine);
            break;
          case "cooked_aubergine":
            mapping.remap(ModItems.cooked_aubergine);
            break;
          case "aubergine":
            mapping.remap(ModItems.aubergine);
            break;
          default:
            continue;
        }
      }
    }
  }
}
