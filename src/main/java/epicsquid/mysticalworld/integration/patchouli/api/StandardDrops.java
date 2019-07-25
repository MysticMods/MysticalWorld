package epicsquid.mysticalworld.integration.patchouli.api;

import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.*;

/* TODO: This class is bad and it should feel bad */
public class StandardDrops {
  public static Map<String, List<ItemStack>> DROPS = new HashMap<>();
  static {
    DROPS.put("beetle", Arrays.asList(new ItemStack(ModItems.carapace), new ItemStack(Items.SLIME_BALL)));
    DROPS.put("deer", Arrays.asList(new ItemStack(ModItems.venison), new ItemStack(Items.LEATHER), new ItemStack(ModItems.antlers)));
    DROPS.put("fox", Arrays.asList(new ItemStack(ModItems.pelt)));
    DROPS.put("frog", Arrays.asList(new ItemStack(Items.SLIME_BALL)));
    DROPS.put("sprout", Arrays.asList(new ItemStack(Items.MELON), new ItemStack(ModItems.aubergine), new ItemStack(Items.BEETROOT), new ItemStack(Items.POTATO)));
    DROPS.put("squid", Arrays.asList(new ItemStack(ModItems.raw_squid), new ItemStack(ModItems.ink_bottle)));
  }

  public static List<ItemStack> getDrops (String name) {
    return DROPS.getOrDefault(name, new ArrayList<>());
  }
}
