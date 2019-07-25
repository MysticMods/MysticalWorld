package epicsquid.mysticalworld.config;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.fml.common.Mod;

@Config(modid = MysticalWorld.MODID)
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ConfigManager {

  @Config.Comment(("Inject some items from Mystical World into dungeon & other loot chests"))
  public static boolean InjectLoot = true;

  @Config.Comment(("Enable squid drops"))
  public static boolean InjectSquid = true;

  @Config.Comment(("Minimum number of pulls for injected loot"))
  public static int InjectMinimum = 1;

  @Config.Comment(("Maximum nubmer of pulls for injected loot"))
  public static int InjectMaximum = 1;

  @Config.Comment(("Mystical world content modules. These modules are applied before any other config options."))
  public static ConfigMysticalWorldModules modules = new ConfigMysticalWorldModules();

  public static class ConfigMysticalWorldModules {

    @Config.Comment(("Enable Roots Content"))
    public boolean rootsModuleEnabled = true;

    @Config.Comment(("Enable Embers Content"))
    public boolean embersModuleEnabled = true;

    @Config.Comment(("Enable Mystical World Content"))
    public boolean mysticalWorldModuleEnabled = true;

    @Config.Comment(("Enable Solar Content"))
    public boolean solarModuleEnabled = true;
  }

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

    @Config.Comment(("Enable the spawning of Sprouts"))
    public boolean spawnSprout = true;
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

  public static class ConfigMysticalWorldSprout  {
    @Config.Comment(("Spawn rate of sprouts"))
    public int rate = 2;

    @Config.Comment(("Min number of sprouts to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of sprouts to spawn in a group"))
    public int max = 4;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"JUNGLE", "LUSH", "MAGICAL", "RIVER", "FOREST", "BEACH"};
  }

  public static class ConfigMysticalWorldDeer  {
    @Config.Comment(("Spawn rate of deer"))
    public int rate = 6;

    @Config.Comment(("Min number of deer to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of deer to spawn in a group"))
    public int max = 4;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"FOREST", "COLD", "CONIFEROUS", "PLAINS"};
  }

  public static class ConfigMysticalWorldFox  {
    @Config.Comment(("Spawn rate of fox"))
    public int rate = 4;

    @Config.Comment(("Min number of fox to spawn in a group"))
    public int min = 1;

    @Config.Comment(("Maximum number of fox to spawn in a group"))
    public int max = 3;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"FOREST", "COLD", "CONIFEROUS"};
  }

  public static class ConfigMysticalWorldBeetle  {
    @Config.Comment(("Spawn rate of beetle"))
    public int rate = 5;

    @Config.Comment(("Min number of beetles to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of beetles to spawn in a group"))
    public int max = 4;

    @Config.Comment(("Biome types to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"SWAMP", "JUNGLE", "FOREST", "PLAINS"};
  }

  public static class ConfigMysticalWorldFrog  {
    @Config.Comment(("Spawn rate of frog"))
    public int rate = 6;

    @Config.Comment(("Min number of frogs to spawn in a group"))
    public int min = 2;

    @Config.Comment(("Maximum number of frogs to spawn in a group"))
    public int max = 4;

    @Config.Comment(("Biomes to spawn in. List consisting of elements from: |SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID|"))
    public String[] biomes = new String[]{"SWAMP", "JUNGLE", "BEACH", "RIVER"};
  }

  @Config.Comment(("Minimum distance between Barrow structures. Set to -1 to disable."))
  @Config.RangeInt(min = 0)
  public static int BarrowDistance = 400;

  @Config.Comment(("Mininmum distance between Hut structures. Set to -1 to disable."))
  @Config.RangeInt(min = 0)
  public static int HutDistance = 400;

  @Config.Comment(("Mystical world gems and their components"))
  public static ConfigMysticalWorldGems gems = new ConfigMysticalWorldGems();

  public static class ConfigMysticalWorldGems {
    @Config.Comment(("Enabled Amethyst"))
    public boolean enableAmethyst = true;

    @Config.Comment(("Enable blocks"))
    public boolean enableBlocks = true;

    @Config.Comment(("Enable gems"))
    public boolean enableGems = true;

    @Config.Comment(("Enable ore"))
    public boolean enableOres = true;
  }

  @Config.Comment(("Mystical world metals and their components."))
  public static ConfigMysticalWorldMetals metals = new ConfigMysticalWorldMetals();

  public static class ConfigMysticalWorldMetals {

    @Config.Comment(("Enable Tiny Dusts for Metals"))
    public boolean enableTinyDusts = true;

    @Config.Comment(("Enable Dusts for Metals"))
    public boolean enableDusts = true;

    @Config.Comment(("Enable Ores for Metals"))
    public boolean enableOres = true;

    @Config.Comment(("Enable Blocks for Metals"))
    public boolean enableBlocks = true;

    @Config.Comment(("Enable Ingots for Metals"))
    public boolean enableIngots = true;

    @Config.Comment(("Enable Nuggets for Metals"))
    public boolean enableNuggets = true;

    @Config.Comment(("Enable Copper"))
    public boolean enableCopper = true;

    /*@Config.Comment(("Enable Tin"))
    public boolean enableTin = true;*/

    @Config.Comment(("Enable Silver"))
    public boolean enableSilver = true;

    /*@Config.Comment(("Enable Lead"))
    public boolean enableLead = true;

    @Config.Comment(("Enable Nickel"))
    public boolean enableNickel = true;

    @Config.Comment(("Enable Aluminum"))
    public boolean enableAluminum = true;

    @Config.Comment(("Enable Zinc"))
    public boolean enableZinc = true;

    @Config.Comment(("Enable Invar"))
    public boolean enableInvar = true;

    @Config.Comment(("Enable Electrum"))
    public boolean enableElectrum = true;

    @Config.Comment(("Enable Brass"))
    public boolean enableBrass = true;

    @Config.Comment(("Enable Bronze"))
    public boolean enableBronze = true;

    @Config.Comment(("Enable Dawnstone"))
    public boolean enableDawnstone = true;

    @Config.Comment(("Enable Sooty Iron"))
    public boolean enableSootyIron = true;*/
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

    /*@Config.Comment(("The amount of tin veins to generate per chunk. Set to 0 to disable."))
    public int tinPerChunk = 6;

    @Config.Comment(("The maximum size of a tin ore vein."))
    public int tinVeinSize = 8;

    @Config.Comment(("The highest a tin ore vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int tinMaxY = 48;

    @Config.Comment(("The lowest a tin of vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int tinMinY = 0;*/

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

    /*@Config.Comment(("The amount of lead veins to generate per chunk. Set to 0 to disable."))
    public int leadPerChunk = 4;

    @Config.Comment(("The maximum size of a lead ore vein."))
    public int leadVeinSize = 8;

    @Config.Comment(("The highest a lead ore vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int leadMaxY = 28;

    @Config.Comment(("The lowest a lead of vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int leadMinY = 0;

    @Config.Comment(("The amount of nickel veins to generate per chunk. Set to 0 to disable."))
    public int nickelPerChunk = 4;

    @Config.Comment(("The maximum size of a nickel ore vein."))
    public int nickelVeinSize = 8;

    @Config.Comment(("The highest a nickel ore vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int nickelMaxY = 24;

    @Config.Comment(("The lowest a nickel of vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int nickelMinY = 0;

    @Config.Comment(("The amount of aluminum veins to generate per chunk. Set to 0 to disable."))
    public int aluminumPerChunk = 4;

    @Config.Comment(("The maximum size of a aluminum ore vein."))
    public int aluminumVeinSize = 8;

    @Config.Comment(("The highest a aluminum ore vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int aluminumMaxY = 58;

    @Config.Comment(("The lowest a aluminum of vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int aluminumMinY = 0;

    @Config.Comment(("The amount of zinc veins to generate per chunk. Set to 0 to disable."))
    public int zincPerChunk = 4;

    @Config.Comment(("The maximum size of a zinc ore vein."))
    public int zincVeinSize = 8;

    @Config.Comment(("The highest a zinc ore vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int zincMaxY = 32;

    @Config.Comment(("The lowest a zinc of vein can generate."))
    @RangeInt(min = 0, max = 255)
    public int zincMinY = 0;*/
  }
}
