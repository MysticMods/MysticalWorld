package epicsquid.mysticalworld.integration.patchouli.api;

import epicsquid.mysticalworld.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.*;

/* TODO: This class is bad and it should feel bad */
public class StandardDrops {
  public static Map<String, List<ItemStack>> DROPS = new HashMap<>();

  static {
    DROPS.put("beetle", Arrays.asList(new ItemStack(ModItems.CARAPACE), new ItemStack(Items.SLIME_BALL)));
    DROPS.put("deer", Arrays.asList(new ItemStack(ModItems.VENISON), new ItemStack(Items.LEATHER), new ItemStack(ModItems.ANTLERS)));
    DROPS.put("fox", Arrays.asList(new ItemStack(ModItems.PELT)));
    DROPS.put("frog", Arrays.asList(new ItemStack(Items.SLIME_BALL)));
    DROPS.put("sprout", Arrays.asList(new ItemStack(Items.MELON), new ItemStack(ModItems.AUBERGINE), new ItemStack(Items.BEETROOT), new ItemStack(Items.POTATO)));
    DROPS.put("squid", Arrays.asList(new ItemStack(ModItems.RAW_SQUID), new ItemStack(ModItems.INK_BOTTLE)));
    //DROPS.put("endermini", Arrays.asList(new ItemStack(ModItems.UNRIPE_PEARL)));
  }

  public static List<ItemStack> getDrops(String name) {
    return DROPS.getOrDefault(name, new ArrayList<>());
  }
}
