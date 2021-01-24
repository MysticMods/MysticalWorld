package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber
public class MappingHandler {

  @SubscribeEvent
  public static void onMissingBlock(RegistryEvent.MissingMappings<Block> event) {
    for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getAllMappings()) {
      if (mapping.key.getNamespace().equals("mysticalworld")) {
        if (mapping.key.getPath().equals("poisoned_potato_crop")) {
          mapping.remap(Blocks.POTATOES);
        }
        if (mapping.key.getPath().equals("slime_puddle")) {
          mapping.remap(ModBlocks.slime_eggs);
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
  public static void onMissingEntity(RegistryEvent.MissingMappings<EntityEntry> event) {
    for (RegistryEvent.MissingMappings.Mapping<EntityEntry> mapping : event.getAllMappings()) {
      if (mapping.key.getNamespace().equals("mysticalworld")) {
        if (mapping.key.getPath().equals("entity_lurker")) {
          mapping.ignore();
        }
      }
    }
  }

  @SubscribeEvent
  public static void onMissingItem(RegistryEvent.MissingMappings<Item> event) {
    for (RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getAllMappings()) {
      if (mapping.key.getNamespace().equals("mysticalworld")) {
        switch (mapping.key.getPath()) {
          case "cooked_apple":
            mapping.remap(Items.APPLE);
            break;
          case "slime_puddle":
            mapping.remap(Item.getItemFromBlock(ModBlocks.slime_eggs));
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
