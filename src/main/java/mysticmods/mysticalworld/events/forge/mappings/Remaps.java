package mysticmods.mysticalworld.events.forge.mappings;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class Remaps {
  public static ResourceLocation IGNORE = new ResourceLocation(MysticalWorld.MODID, "ignore_remap");

  public static Map<ResourceLocation, ResourceLocation> remappedItems = new HashMap<>();
  public static Map<ResourceLocation, ResourceLocation> remappedBlocks = new HashMap<>();
  public static Map<ResourceLocation, ResourceLocation> remappedEntities = new HashMap<>();

  public enum RemapType {
    ITEM("item", () -> remappedItems), BLOCK("block", () -> remappedBlocks), ENTITY("entity", () -> remappedEntities);

    private final String name;
    private final Supplier<Map<ResourceLocation, ResourceLocation>> map;

    public String getName() {
      return name;
    }

    public Supplier<Map<ResourceLocation, ResourceLocation>> getMap() {
      return map;
    }

    RemapType(String name, Supplier<Map<ResourceLocation, ResourceLocation>> map) {
      this.name = name;
      this.map = map;
    }
  }

  private static void ignore(String oldName, RemapType type) {
    remap(oldName, "ignore_remap", type);
  }

  public static void remapItem(String o, String n) {
    remap(o, n, RemapType.ITEM);
  }

  public static void remapBlock(String o, String n) {
    remap(o, n, RemapType.BLOCK);
  }

  public static void remapEntity(String o, String n) {
    remap(o, n, RemapType.ENTITY);
  }

  private static void remap(String oldName, String newName, RemapType type) {
    Map<ResourceLocation, ResourceLocation> remapper = type.getMap().get();
    String mapType = type.getName();
    ResourceLocation oldRL = new ResourceLocation(MysticalWorld.MODID, oldName);
    ResourceLocation newRL = new ResourceLocation(MysticalWorld.MODID, newName);
    if (remapper.containsKey(oldRL)) {
      MysticalWorld.LOG.error("Invalid " + mapType + " remap: " + oldRL.toString() + " already exists and remaps to " + remapper.get(oldRL).toString() + ", cannot also remap to " + newRL.toString(), new IllegalStateException("Duplicate " + mapType + " remap"));
    }
    remapper.put(oldRL, newRL);
  }

  @SubscribeEvent
  public static void remapItemEvent(RegistryEvent.MissingMappings<Item> event) {
    for (RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getAllMappings()) {
      ResourceLocation remap = remappedItems.get(mapping.key);
      if (remap != null && remap.equals(IGNORE)) {
        mapping.ignore();
      } else if (remap != null) {
        Item remapped = ForgeRegistries.ITEMS.getValue(remap);
        if (remapped == null || remapped == Items.AIR) {
          MysticalWorld.LOG.error("Attempted remapping is invalid: " + mapping.key + " is remapped to " + remap + " but that item doesn't exist!");
        } else {
          mapping.remap(remapped);
          MysticalWorld.LOG.info("Remapped item: " + mapping.key + " to " + remap);
        }
      }
    }
  }

  @SubscribeEvent
  public static void remapBlockEvent(RegistryEvent.MissingMappings<Block> event) {
    for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getAllMappings()) {
      ResourceLocation remap = remappedBlocks.get(mapping.key);
      if (remap != null && remap.equals(IGNORE)) {
        mapping.ignore();
      } else if (remap != null) {
        Block remapped = ForgeRegistries.BLOCKS.getValue(remap);
        if (remapped == null || remapped == Blocks.AIR) {
          MysticalWorld.LOG.error("Attempted remapping is invalid: " + mapping.key + " is remapped to " + remap + " but that block doesn't exist!");
        } else {
          mapping.remap(remapped);
          MysticalWorld.LOG.info("Remapped block: " + mapping.key + " to " + remap);
        }
      }
    }
  }

  @SubscribeEvent
  public static void remapEntityEvent(RegistryEvent.MissingMappings<EntityType<?>> event) {
    for (RegistryEvent.MissingMappings.Mapping<EntityType<?>> mapping : event.getAllMappings()) {
      ResourceLocation remap = remappedEntities.get(mapping.key);
      if (remap != null && remap.equals(IGNORE)) {
        mapping.ignore();
      } else if (remap != null) {
        EntityType<?> remapped = ForgeRegistries.ENTITIES.getValue(remap);
        if (remapped == null) {
          MysticalWorld.LOG.error("Attempted remapping is invalid: " + mapping.key + " is remapped to " + remap + " but that entity doesn't exist!");
        } else {
          mapping.remap(remapped);
          MysticalWorld.LOG.info("Remapped entity: " + mapping.key + " to " + remap);
        }
      }
    }
  }

  //
  // Actual remaps go here
  //
  static {
  }
}
