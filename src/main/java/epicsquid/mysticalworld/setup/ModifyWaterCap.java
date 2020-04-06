package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

public class ModifyWaterCap {
  public static Field modifiers = ObfuscationReflectionHelper.findField(Field.class, "modifiers");

  static {
    modifiers.setAccessible(true);
  }

  public static void modify() throws IllegalAccessException {
    Field field = ObfuscationReflectionHelper.findField(EntityClassification.class, "field_75606_e");
    modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    field.set(EntityClassification.WATER_CREATURE, 30);
  }

  public static void modifySquid() throws IllegalAccessException {
    Field field = ObfuscationReflectionHelper.findField(WeightedRandom.Item.class, "field_76292_a");
    modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

    ForgeRegistries.BIOMES.forEach(o -> {
      List<Biome.SpawnListEntry> spawns = o.getSpawns(EntityClassification.WATER_CREATURE);
      spawns.forEach(s -> {
        try {
          field.set(s, s.itemWeight + 6);
        } catch (IllegalAccessException e) {
          MysticalWorld.LOG.error("Unable to modify squid spawn cap in biome " + o.getRegistryName().toString(), e);
        }
      });
    });
  }
}
