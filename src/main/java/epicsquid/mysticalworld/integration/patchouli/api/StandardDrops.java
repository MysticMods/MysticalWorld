package epicsquid.mysticalworld.integration.patchouli.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class StandardDrops {
  private static Map<String, List<ResourceLocation>> DROPS = new HashMap<>();
  private static Map<String, List<ItemStack>> DROP_ITEMS = new HashMap<>();

  static {
    DROPS.put("beetle", Arrays.asList(new ResourceLocation("mysticalworld", "carapace"), new ResourceLocation("minecraft", "slime_ball")));
    DROPS.put("deer", Arrays.asList(new ResourceLocation("minecraft", "leather"), new ResourceLocation("mysticalworld", "venison"), new ResourceLocation("mysticalworld", "antlers")));
    DROPS.put("endermini", Arrays.asList(new ResourceLocation("mysticalworld", "young_pearl")));
    DROPS.put("frog", Arrays.asList(new ResourceLocation("minecraft", "slime_ball")));
    DROPS.put("lava_cat", Arrays.asList(new ResourceLocation("minecraft", "obsidian"), new ResourceLocation("minecraft", "cobblestone")));
    DROPS.put("owl", Arrays.asList(new ResourceLocation("minecraft", "feather")));
    DROPS.put("silkworm", Arrays.asList(new ResourceLocation("mysticalworld", "silkworm_egg")));
    DROPS.put("silver_fox", Arrays.asList(new ResourceLocation("mysticalworld", "pelt")));
    DROPS.put("sprout", Arrays.asList(new ResourceLocation("minecraft", "melon_slice"), new ResourceLocation("mysticalworld", "aubergine"), new ResourceLocation("minecraft", "beetroot"), new ResourceLocation("minecraft", "potato")));
    DROPS.put("hell_sprout", Arrays.asList(new ResourceLocation("minecraft", "nether_wart")));
    DROPS.put("squid", Arrays.asList(new ResourceLocation("mysticalworld", "raw_squid")));
  }

  public static List<ItemStack> getDrops(String name) {
    return DROP_ITEMS.computeIfAbsent(name, (s) -> {
      List<ItemStack> result = new ArrayList<>();
      List<ResourceLocation> data = DROPS.get(name);
      if (data != null && !data.isEmpty()) {
        for (ResourceLocation rl : data) {
          Item item = ForgeRegistries.ITEMS.getValue(rl);
          if (item != null) {
            result.add(new ItemStack(item));
          }
        }
      }

      return result;
    });
  }
}
