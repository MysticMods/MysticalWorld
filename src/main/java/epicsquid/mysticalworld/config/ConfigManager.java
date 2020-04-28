package epicsquid.mysticalworld.config;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.world.WorldGeneratorTrees;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MysticalWorld.MODID)
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ConfigManager {
  @SubscribeEvent
  public static void syncConfig(ConfigChangedEvent.OnConfigChangedEvent event) {
    if (event.getModID().equals(MysticalWorld.MODID)) {
      net.minecraftforge.common.config.ConfigManager.sync(MysticalWorld.MODID, Config.Type.INSTANCE);
      WorldGeneratorTrees.invalidTypes.clear();
    }
  }

  public static int safeInt(int incoming) {
    return Math.max(1, incoming);
  }

  @Config.Comment(("Grant the Patchouli-based Guide Book when first joining the server"))
  public static boolean GiveBook = true;

  @Config.Comment(("Inject some items from Mystical World into dungeon & other loot chests"))
  public static boolean InjectLoot = true;

  @Config.Comment(("Enable squid drops"))
  public static boolean InjectSquid = true;

  @Config.Comment(("Minimum number of pulls for injected loot"))
  public static int InjectMinimum = 1;

  @Config.Comment(("Maximum nubmer of pulls for injected loot"))
  public static int InjectMaximum = 1;

  @Config.Comment(("Controls the spawning of mobs in Mystical World and dependent mods."))
  public static ConfigMysticalWorldMobs mobs = new ConfigMysticalWorldMobs();

  public static class ConfigMysticalWorldMobs {

    @Config.Comment(("Enable the spawning of Deer."))
    public boolean spawnDeer = true;

    @Config.Comment(("Enable the spawning of Fox."))
    public boolean spawnFox = true;

    @Config.Comment(("Enable the spawning of Beetle."))
    public boolean spawnBeetle = true;

    @Config.Comment(("Enable the spawning of Frog."))
    public boolean spawnFrog = true;

    @Config.Comment(("Enable the spawning of Sprouts."))
    @Config.Ignore
    public boolean spawnSprout = true;

    @Config.Comment(("Enable the spawning of Endermini."))
    public boolean spawnEndermini = true;

    @Config.Comment(("Enable the spawning of Owls."))
    public boolean spawnOwl = true;

    @Config.Comment(("Enable the spawning of Lava Cats"))
    public boolean spawnLavaCat = true;
  }

  @Config.Comment(("Controls the spawn settings of Deer"))
  public static ConfigMysticalWorldDeer deer = new ConfigMysticalWorldDeer();
  @Config.Comment(("Controls the spawn settings of Foxes"))
  public static ConfigMysticalWorldFox fox = new ConfigMysticalWorldFox();
  @Config.Comment(("Controls the spawn settings of Beetle"))
  public static ConfigMysticalWorldBeetle beetle = new ConfigMysticalWorldBeetle();
  @Config.Comment(("Controls the spawn settings of Frog"))
  public static ConfigMysticalWorldFrog frog = new ConfigMysticalWorldFrog();
  @Config.Comment(("Controls the spawn settings of Sprout"))
  public static ConfigMysticalWorldSprout sprout = new ConfigMysticalWorldSprout();
  @Config.Comment(("Controls the spawn settings of Endermini"))
  public static ConfigMysticalWorldEndermini endermini = new ConfigMysticalWorldEndermini();
  @Config.Comment(("Controls the spawn settings of owls"))
  public static ConfigMysticalWorldOwl owl = new ConfigMysticalWorldOwl();
  @Config.Comment(("Controls the spawn settings of silkworms"))
  public static ConfigMysticalWorldSilkworm silkworm = new ConfigMysticalWorldSilkworm();
  @Config.Comment(("Controls the spawn settings of lava cats"))
  public static ConfigMysticalWorldLavaCat lavaCat = new ConfigMysticalWorldLavaCat();

  public static class ConfigMysticalWorldLavaCat {
    @Config.Comment(("Spawn rate of lava cats"))
    public int rate = 2;

    @Config.Comment(("Min number of sprouts to spawn in a group"))
    public int min = 1;

    @Config.Comment(("Maximum number of sprouts to spawn in a group"))
    public int max = 1;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"NETHER"};
  }

  public static class ConfigMysticalWorldSprout {
    @Config.Comment(("Spawn rate of sprouts"))
    public int rate = 2;

    @Config.Comment(("Min number of sprouts to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of sprouts to spawn in a group"))
    public int max = 4;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"JUNGLE", "LUSH", "MAGICAL", "RIVER", "FOREST", "BEACH"};
  }

  public static class ConfigMysticalWorldEndermini {
    @Config.Comment(("Spawn rate of enderminis in the End"))
    public int rate = 50;

    @Config.Comment(("Min number of enderminis to spawn in a group"))
    public int min = 1;

    @Config.Comment(("Maximum number of enderminis to spawn in a group"))
    public int max = 1;
  }

  public static class ConfigMysticalWorldDeer {
    @Config.Comment(("Spawn rate of deer"))
    public int rate = 6;

    @Config.Comment(("Min number of deer to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of deer to spawn in a group"))
    public int max = 4;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"FOREST", "COLD", "CONIFEROUS", "PLAINS"};
  }

  public static class ConfigMysticalWorldFox {
    @Config.Comment(("Spawn rate of fox"))
    public int rate = 4;

    @Config.Comment(("Min number of fox to spawn in a group"))
    public int min = 1;

    @Config.Comment(("Maximum number of fox to spawn in a group"))
    public int max = 3;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"FOREST", "COLD", "CONIFEROUS"};
  }

  public static class ConfigMysticalWorldBeetle {
    @Config.Comment(("Spawn rate of beetle"))
    public int rate = 5;

    @Config.Comment(("Min number of beetles to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of beetles to spawn in a group"))
    public int max = 4;

    @Config.Comment(("Biome types to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"SWAMP", "JUNGLE", "FOREST", "PLAINS"};
  }

  public static class ConfigMysticalWorldFrog {
    @Config.Comment(("Spawn rate of frog"))
    public int rate = 6;

    @Config.Comment(("Min number of frogs to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of frogs to spawn in a group"))
    public int max = 4;

    @Config.Comment(("How often slime balls are dropped; set to -1 to disable slime ball dropping"))
    public int slimeTime = 18000;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"SWAMP", "JUNGLE", "BEACH", "RIVER"};
  }

  public static class ConfigMysticalWorldOwl {
    @Config.Comment(("Spawn rate of owls"))
    public int rate = 9;

    @Config.Comment(("Min number of owls to spawn in a group"))
    public int min = 1;

    @Config.Comment(("Maximum number of frogs to spawn in a group"))
    public int max = 3;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"CONIFEROUS", "SPOOKY", "MAGICAL", "FOREST", "MOUNTAIN", "SNOWY"};
  }

  public static class ConfigMysticalWorldSilkworm {
    @Config.Comment(("Set to false to disable silkworms entirely"))
    public boolean enabled = true;

    @Config.Comment(("Set to false to disable silkworm eggs from dropping when leaves are broken"))
    public boolean leafDrops = true;

    @Config.Comment(("Specify a chance (1 in X) for silkworm eggs to drop when leaves are broken"))
    public int leafDropChance = 55;

    @Config.Comment(("Specify a chance (1 in X) for silkworm eggs used to successfully spawn a silkworm"))
    public int successChance = 2;

    @Config.Comment(("Specify a chance (1 in X) per tick for a silkworm to grow (default 40, around 3-4 minutes per)"))
    public int growthChance = 85;

    @Config.Comment(("Specify maximum number of additional potential extra growth calls when fed leaves by hand"))
    public int additionalGrowth = 5;

    @Config.Comment(("Specify initial minimum number of growth calls when fed leaves by hand"))
    public int initialGrowth = 3;

    @Config.Comment(("Max number of silk cocoons that can be spawned"))
    public int maxCocoons = 5;
  }

  @Config.Comment(("Minimum distance between Barrow structures. Set to -1 to disable."))
  @Config.RangeInt(min = -1)
  public static int BarrowDistance = 400;

  @Config.Comment(("List of dimensions the barrow structure should spawn in"))
  public static int[] BarrowSpawnWhitelist = new int[]{0};

  @Config.Comment(("List of dimensions the barrow structure should not spawn in"))
  public static int[] BarrowSpawnBlacklist = new int[]{};

  @Config.Comment(("Mininmum distance between Hut structures. Set to -1 to disable."))
  @Config.RangeInt(min = -1)
  public static int HutDistance = 400;

  @Config.Comment(("List of dimensions the hut structure should spawn in"))
  public static int[] HutSpawnWhitelist = new int[]{0};

  @Config.Comment(("List of dimensions the hut structure should not spawn in"))
  public static int[] HutSpawnBlacklist = new int[]{};

  @Config.Comment(("Spawn options for burn trees"))
  @Config.Name("Burnt Trees")
  public static ConfigMysticalWorldBurntTrees burntTrees = new ConfigMysticalWorldBurntTrees();

  public static class ConfigMysticalWorldBurntTrees {
    @Config.Comment("Chance (1 in X) of burnt trees spawning; set to -1 to disable")
    @Config.Name("Spawn chance")
    @Config.RangeInt(min = -1)
    public int chance = 200;

    @Config.Comment("Attempts to spawn a tree per chunk")
    @Config.Name("Spawn attempts")
    @Config.RangeInt(min = 1)
    public int attempts = 80;

    @Config.Comment("Excluded biomes types. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|")
    @Config.Name("Excluded biome types")
    public String[] excludedBiomes = new String[]{"VOID", "END", "WATER", "BEACH", "MESA", "MUSHROOM", "NETHER", "OCEAN", "RIVER"};

    @Config.Comment(("List of dimensions the trees should spawn in"))
    public static int[] whitelist = new int[]{0};

    @Config.Comment(("List of dimensions the trees should not spawn in"))
    public static int[] blacklist = new int[]{};
  }

  @Config.Comment(("Mystical world gold dusts"))
  public static ConfigMysticalWorldGold gold = new ConfigMysticalWorldGold();

  public static class ConfigMysticalWorldGold {
    @Config.Comment(("Enable Tiny Dusts for Gold"))
    public boolean enableTinyDusts = true;

    @Config.Comment(("Enable Dusts for Gold"))
    public boolean enableDusts = true;
  }

  @Config.Comment(("Mystical world iron dusts"))
  public static ConfigMysticalWorldIron iron = new ConfigMysticalWorldIron();

  public static class ConfigMysticalWorldIron {
    @Config.Comment(("Enable Tiny Dusts for Iron"))
    public boolean enableTinyDusts = true;

    @Config.Comment(("Enable Dusts for Iron"))
    public boolean enableDusts = true;
  }

  @Config.Comment(("Mystical world gems and their components"))
  public static ConfigMysticalWorldAmethyst amethyst = new ConfigMysticalWorldAmethyst();

  public static class ConfigMysticalWorldAmethyst {
    @Config.Comment(("Enabled Amethyst"))
    public boolean enableAmethyst = true;

    @Config.Comment(("Enable blocks of Amethyst"))
    public boolean enableBlocks = true;

    @Config.Comment(("Enable gems of Amethyst"))
    public boolean enableGems = true;

    @Config.Comment(("Enable Amethyst ore"))
    public boolean enableOres = true;
  }

  @Config.Comment(("Mystical world copper options"))
  public static ConfigMysticalWorldCopper copper = new ConfigMysticalWorldCopper();

  @Config.Comment(("Mystical world silver options"))
  public static ConfigMysticalWorldSilver silver = new ConfigMysticalWorldSilver();

  public static class ConfigMysticalWorldCopper {
    @Config.Comment(("Enable Copper"))
    public boolean enableCopper = true;

    @Config.Comment(("Enable Tiny Dusts for Copper"))
    public boolean enableTinyDusts = true;

    @Config.Comment(("Enable Dusts for Copper"))
    public boolean enableDusts = true;

    @Config.Comment(("Enable Ores for Copper"))
    public boolean enableOres = true;

    @Config.Comment(("Enable Blocks for Copper"))
    public boolean enableBlocks = true;

    @Config.Comment(("Enable Ingots for Copper"))
    public boolean enableIngots = true;

    @Config.Comment(("Enable Nuggets for Copper"))
    public boolean enableNuggets = true;
  }

  public static class ConfigMysticalWorldSilver {
    @Config.Comment(("Enable Silver"))
    public boolean enableSilver = true;

    @Config.Comment(("Enable Tiny Dusts for Silver"))
    public boolean enableTinyDusts = true;

    @Config.Comment(("Enable Dusts for Silver"))
    public boolean enableDusts = true;

    @Config.Comment(("Enable Ores for Silver"))
    public boolean enableOres = true;

    @Config.Comment(("Enable Blocks for Silver"))
    public boolean enableBlocks = true;

    @Config.Comment(("Enable Ingots for Silver"))
    public boolean enableIngots = true;

    @Config.Comment(("Enable Nuggets for Silver"))
    public boolean enableNuggets = true;
  }

  @Config.Comment("Tool/Weapon Enablind")
  public static ConfigMysticalWorldToolEnable toolEnable = new ConfigMysticalWorldToolEnable();

  public static class ConfigMysticalWorldToolEnable {
    @Config.Comment("Set to false to disable silver tool recipes entirely")
    @Config.Name("Enable silver tools")
    public boolean enableSilver = true;

    @Config.Comment("Set to false to disable copper tool recipes entirely")
    @Config.Name("Enable copper tools")
    public boolean enableCopper = true;

    @Config.Comment("Set to false to disable amethyst tool recipes entirely")
    @Config.Name("Enable amethyst tools")
    public boolean enableAmethyst = true;

    @Config.Comment("Settings for individual Amethyst tool recipes")
    @Config.Name("Amethyst Tool recipes")
    public AmethystTools amethystTools = new AmethystTools();

    @Config.Comment("Settings for individual Copper tool recipes")
    @Config.Name("Copper Tool recipes")
    public CopperTools copperTools = new CopperTools();

    @Config.Comment("Settings for individual Silver tool recipes")
    @Config.Name("Silver Tool recipes")
    public SilverTools silverTools = new SilverTools();

    public static class AmethystTools {
      @Config.Comment("Set to false to disable the amethyst axe recipe")
      @Config.Name("Enable amethyst axe")
      public boolean amethystAxe = true;

      @Config.Comment("Set to false to disable the amethyst hoe recipe")
      @Config.Name("Enable amethyst hoe")
      public boolean amethystHoe = true;

      @Config.Comment("Set to false to disable the amethyst knife recipe")
      @Config.Name("Enable amethyst knife")
      public boolean amethystKnife = true;

      @Config.Comment("Set to false to disable the amethyst pickaxe recipe")
      @Config.Name("Enable amethyst pickaxe")
      public boolean amethystPickaxe = true;

      @Config.Comment("Set to false to disable the amethyst shovel recipe")
      @Config.Name("Enable amethyst shovel")
      public boolean amethystShovel = true;

      @Config.Comment("Set to false to disable the amethyst sword recipe")
      @Config.Name("Enable amethyst sword")
      public boolean amethystSword = true;
    }

    public static class CopperTools {
      @Config.Comment("Set to false to disable the copper axe recipe")
      @Config.Name("Enable copper axe")
      public boolean copperAxe = true;

      @Config.Comment("Set to false to disable the copper hoe recipe")
      @Config.Name("Enable copper hoe")
      public boolean copperHoe = true;

      @Config.Comment("Set to false to disable the copper knife recipe")
      @Config.Name("Enable copper knife")
      public boolean copperKnife = true;

      @Config.Comment("Set to false to disable the copper pickaxe recipe")
      @Config.Name("Enable copper pickaxe")
      public boolean copperPickaxe = true;

      @Config.Comment("Set to false to disable the copper shovel recipe")
      @Config.Name("Enable copper shovel")
      public boolean copperShovel = true;

      @Config.Comment("Set to false to disable the copper sword recipe")
      @Config.Name("Enable copper sword")
      public boolean copperSword = true;
    }

    public static class SilverTools {
      @Config.Comment("Set to false to disable the silver axe recipe")
      @Config.Name("Enable silver axe")
      public boolean silverAxe = true;

      @Config.Comment("Set to false to disable the silver hoe recipe")
      @Config.Name("Enable silver hoe")
      public boolean silverHoe = true;

      @Config.Comment("Set to false to disable the silver knife recipe")
      @Config.Name("Enable silver knife")
      public boolean silverKnife = true;

      @Config.Comment("Set to false to disable the silver pickaxe recipe")
      @Config.Name("Enable silver pickaxe")
      public boolean silverPickaxe = true;

      @Config.Comment("Set to false to disable the silver shovel recipe")
      @Config.Name("Enable silver shovel")
      public boolean silverShovel = true;

      @Config.Comment("Set to false to disable the silver sword recipe")
      @Config.Name("Enable silver sword")
      public boolean silverSword = true;
    }
  }

  @Config.Comment("Ore Debugging Options")
  public static ConfigMysticalWorldOreDebugging oreDebugging = new ConfigMysticalWorldOreDebugging();

  public static class ConfigMysticalWorldOreDebugging {
    @Config.Comment("Enable debugging at all. Specific ores will need to be enabled individually before any messages will be printed.")
    @Config.Name("Enable Debugging")
    public boolean enableDebugging = false;

    @Config.Comment("Enable debugging to the standard log file. Useful if you do not generate or have access to debug.log. WARNING: WILL OVERLY INFLATE YOUR STANDARD LOG FILE. ONLY ENABLE IF NEEDED.")
    @Config.Name("Enable Info Debugging (ONLY IF NEEDED)")
    public boolean enableInfoDebugging = false;

    @Config.Comment("Enable the debugging of Amethyst Ore")
    @Config.Name("Debug Amethyst Ore")
    public boolean debugAmethyst = false;

    @Config.Comment("Enable the debugging of Copper Ore")
    @Config.Name("Debug Copper Ore")
    public boolean debugCopper = false;

    @Config.Comment("Enable the debugging of Silver Ore")
    @Config.Name("Debug Silver Ore")
    public boolean debugSilver = false;
  }

  @Config.Comment(("Controls ore generation for Mystical World Ores."))
  public static ConfigMysticalWorldOreGen oreGen = new ConfigMysticalWorldOreGen();

  public static class ConfigMysticalWorldOreGen {
    @Config.Comment(("List of dimensions to spawn ore in (default 0)"))
    public String[] dimensions = new String[]{
        "0"
    };

    @Config.Ignore
    private IntOpenHashSet spawnDimensions = null;

    public IntOpenHashSet getSpawnDimensions() {
      if (spawnDimensions == null) {
        spawnDimensions = new IntOpenHashSet();
        for (String dim : dimensions) {
          spawnDimensions.add(Integer.parseInt(dim));
        }
      }

      return spawnDimensions;
    }

    @Config.Comment(("The amount of copper veins to generate per chunk. Set to 0 to disable."))
    public int copperPerChunk = 6;

    @Config.Comment(("The maximum size of a copper ore vein."))
    public int copperVeinSize = 12;

    @Config.Comment(("The highest a copper ore vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int copperMaxY = 64;

    @Config.Comment(("The lowest a copper of vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int copperMinY = 0;

    @Config.Comment(("The amount of amethyst veins to generate per chunk. Set to 0 to disable."))
    public int amethystPerChunk = 2;

    @Config.Comment(("The maximum size of an amethyst ore vein."))
    public int amethystVeinSize = 5;

    @Config.Comment(("The highest an amethyst ore vein can generate"))
    @RangeInt(min = 0, max = 255)
    public int amethystMaxY = 32;

    @Config.Comment(("The lowest an amethsyt ore vein can generate"))
    @RangeInt(min = 0, max = 255)
    public int amethystMinY = 5;

    @Config.Comment(("The amount of silver veins to generate per chunk. Set to 0 to disable."))
    public int silverPerChunk = 4;

    @Config.Comment(("The maximum size of a silver ore vein."))
    public int silverVeinSize = 8;

    @Config.Comment(("The highest a silver ore vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int silverMaxY = 28;

    @Config.Comment(("The lowest a silver of vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int silverMinY = 0;
  }
}
