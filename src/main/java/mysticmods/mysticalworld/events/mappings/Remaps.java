package mysticmods.mysticalworld.events.mappings;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
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

    ignore("rotten_apple", RemapType.ITEM);
    remapBlock("wild_aubergine_crop", "wild_aubergine");
    remapBlock("cracked_stone", "soft_stone");
    remapBlock("cracked_stone_stairs", "soft_stone_stairs");
    remapBlock("cracked_stone_slab", "soft_stone_slab");
    remapBlock("cracked_stone_wall", "soft_stone_wall");
    remapBlock("cracked_stone_wide_post", "soft_stone_wide_post");
    remapBlock("cracked_stone_small_post", "soft_stone_small_post");
    remapItem("cracked_stone", "soft_stone");
    remapItem("cracked_stone_stairs", "soft_stone_stairs");
    remapItem("cracked_stone_slab", "soft_stone_slab");
    remapItem("cracked_stone_wall", "soft_stone_wall");
    remapItem("cracked_stone_wide_post", "soft_stone_wide_post");
    remapItem("cracked_stone_small_post", "soft_stone_small_post");
    remapBlock("simple_thatch", "thatch");
    remapItem("simple_thatch", "thatch");

    remapBlock("amethyst_block", "sapphire_block");
    remapBlock("amethyst_ore", "sapphire_ore");
    remapBlock("amethyst_stairs", "sapphire_stairs");
    remapBlock("amethyst_slab", "sapphire_slab");
    remapBlock("amethyst_wall", "sapphire_wall");
    remapBlock("amethyst_wide_post", "sapphire_wide_post");
    remapBlock("amethyst_small_post", "sapphire_small_post");
    remapItem("amethyst_block", "sapphire_block");
    remapItem("amethyst_ore", "sapphire_ore");
    remapItem("amethyst_stairs", "sapphire_stairs");
    remapItem("amethyst_slab", "sapphire_slab");
    remapItem("amethyst_wall", "sapphire_wall");
    remapItem("amethyst_wide_post", "sapphire_wide_post");
    remapItem("amethyst_small_post", "sapphire_small_post");

    remapItem("amethyst", "sapphire");
    remapItem("amethyst_axe", "sapphire_axe");
    remapItem("amethyst_boots", "sapphire_boots");
    remapItem("amethyst_chestplate", "sapphire_chestplate");
    remapItem("amethyst_helmet", "sapphire_helmet");
    remapItem("amethyst_hoe", "sapphire_hoe");
    remapItem("amethyst_knife", "sapphire_knife");
    remapItem("amethyst_leggings", "sapphire_leggings");
    remapItem("amethyst_pickaxe", "sapphire_pickaxe");
    remapItem("amethyst_shovel", "sapphire_shovel");
    remapItem("amethyst_spear", "sapphire_spear");
    remapItem("amethyst_sword", "sapphire_sword");

    remapItem("beetle_mask", "beetle_helmet");
  }
}
