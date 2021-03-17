package epicsquid.mysticalworld.integration.patchouli.api;

import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.init.Blocks;
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
    DROPS.put("endermini", Arrays.asList(new ItemStack(ModItems.unripe_pearl)));
    DROPS.put("owl", Arrays.asList(new ItemStack(Items.FEATHER)));
    DROPS.put("lava_cat", Arrays.asList(new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.COBBLESTONE)));
    DROPS.put("silkworm", Arrays.asList(new ItemStack(ModItems.silkworm_egg)));
    DROPS.put("hell_sprout", Arrays.asList(new ItemStack(Items.NETHER_WART)));
    DROPS.put("clam", Arrays.asList(new ItemStack(ModItems.pearl), new ItemStack(Items.ENDER_PEARL)));
  }

  public static List<ItemStack> getDrops(String name) {
    return DROPS.getOrDefault(name, new ArrayList<>());
  }
}
