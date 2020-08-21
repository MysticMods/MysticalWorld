package epicsquid.mysticalworld.events.mappings;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class Remaps {
  public static ResourceLocation IGNORE = new ResourceLocation(MysticalWorld.MODID, "ignore_remap");

  public static Map<ResourceLocation, ResourceLocation> remappedItems = new HashMap<>();
  public static Map<ResourceLocation, ResourceLocation> remappedBlocks = new HashMap<>();
  public static Map<ResourceLocation, ResourceLocation> remappedEntities = new HashMap<>();

  public enum RemapType {
    ITEM("item", () -> remappedItems), BLOCK("block", () -> remappedBlocks), ENTITY("entity", () -> remappedEntities);

    private String name;
    private Supplier<Map<ResourceLocation, ResourceLocation>> map;

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

  public static void remapItem(String o, String n) {
    remap(o, n, RemapType.ITEM);
  }

  public static void remapBlock(String o, String n) {
    remap(o, n, RemapType.BLOCK);
  }

  public static void remapEntity(String o, String n) {
    remap(o, n, RemapType.ENTITY);
  }

  public static void remapItemEvent(RegistryEvent.MissingMappings<Item> event) {
    for (RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getAllMappings()) {
      ResourceLocation remap = remappedItems.get(mapping.key);
      if (remap != null && remap.equals(IGNORE)) {
        mapping.ignore();
      } else if (remap != null) {
        mapping.remap(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(remap)));
      }
    }
  }

  public static void remapBlockEvent(RegistryEvent.MissingMappings<Block> event) {
    for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getAllMappings()) {
      ResourceLocation remap = remappedBlocks.get(mapping.key);
      if (remap != null && remap.equals(IGNORE)) {
        mapping.ignore();
      } else if (remap != null) {
        mapping.remap(Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(remap)));
      }
    }
  }

  public static void remapEntityEvent(RegistryEvent.MissingMappings<EntityType<?>> event) {
    for (RegistryEvent.MissingMappings.Mapping<EntityType<?>> mapping : event.getAllMappings()) {
      ResourceLocation remap = remappedEntities.get(mapping.key);
      if (remap != null && remap.equals(IGNORE)) {
        mapping.ignore();
      } else if (remap != null) {
        mapping.remap(Objects.requireNonNull(ForgeRegistries.ENTITIES.getValue(remap)));
      }
    }
  }

  //
  // Actual remaps go here
  //
  static {
    ignore("drowned_head", RemapType.ITEM);
    ignore("stray_head", RemapType.ITEM);
    ignore("husk_head", RemapType.ITEM);
    ignore("villager_head", RemapType.ITEM);
    ignore("pillager_head", RemapType.ITEM);
    ignore("zombie_pigman_head", RemapType.ITEM);
    ignore("zombie_villager_head", RemapType.ITEM);

    ignore("drowned_head", RemapType.BLOCK);
    ignore("stray_head", RemapType.BLOCK);
    ignore("husk_head", RemapType.BLOCK);
    ignore("villager_head", RemapType.BLOCK);
    ignore("pillager_head", RemapType.BLOCK);
    ignore("zombie_pigman_head", RemapType.BLOCK);
    ignore("zombie_villager_head", RemapType.BLOCK);

    ignore("drowned_wall_head", RemapType.BLOCK);
    ignore("stray_wall_head", RemapType.BLOCK);
    ignore("husk_wall_head", RemapType.BLOCK);
    ignore("villager_wall_head", RemapType.BLOCK);
    ignore("pillager_wall_head", RemapType.BLOCK);
    ignore("zombie_pigman_wall_head", RemapType.BLOCK);
    ignore("zombie_villager_wall_head", RemapType.BLOCK);
  }
}
