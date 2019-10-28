package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.item.ItemFoodBase;
import epicsquid.mysticallib.item.ItemKnifeBase;
import epicsquid.mysticallib.item.ItemSeedBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticallib.util.MirrorHelper;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.integration.roots.Knives;
import epicsquid.mysticalworld.item.ItemCharm;
import epicsquid.mysticalworld.item.ItemSilkwormEgg;
import epicsquid.mysticalworld.item.ItemUnripePearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ModItems {

  // All mod items
  public static Item iron_dust, iron_dust_tiny, gold_dust, gold_dust_tiny, carapace, pelt, antlers, venison, cooked_venison, ink_bottle;

  public static Item amethyst_knife, copper_knife, silver_knife;

  public static Item aubergine, aubergine_seed, cooked_aubergine, stuffed_aubergine, cooked_apple, raw_squid, cooked_squid, epic_squid, unripe_pearl;

  public static Item silk_cocoon, silk_thread, spindle, silkworm_egg;

  public static Item poisoned_potato;

  public static Item charm;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {
    Item.ToolMaterial copper = EnumHelper.addToolMaterial("mysticalworld:copper", 2, 200, 4.0f, 2.0f, 7);
    Item.ToolMaterial silver = EnumHelper.addToolMaterial("mysticalworld:silver", 2, 175, 6.0f, 2.5f, 18);
    Item.ToolMaterial amethyst = EnumHelper.addToolMaterial("mysticalworld:amethyst", 3, 1561, 8.0f, 3.0f, 12);
    MaterialTypes.addMaterial("mysticalworld:copper", copper, 2.5f, -1.5f);
    MaterialTypes.addMaterial("mysticalworld:silver", silver, 2.5f, -1.0f);
    MaterialTypes.addMaterial("mysticalworld:amethyst", amethyst, 3f, -1.0f);

    // Mob Drops
    event.addItem(carapace = new ItemBase("carapace").setCreativeTab(MysticalWorld.tab));

    event.addItem(pelt = new ItemBase("pelt").setCreativeTab(MysticalWorld.tab));
    event.addItem(iron_dust = new ItemBase("iron_dust").setCreativeTab(MysticalWorld.tab));
    event.addItem(iron_dust_tiny = new ItemBase("iron_dust_tiny").setCreativeTab(MysticalWorld.tab));
    event.addItem(gold_dust = new ItemBase("gold_dust").setCreativeTab(MysticalWorld.tab));
    event.addItem(gold_dust_tiny = new ItemBase("gold_dust_tiny").setCreativeTab(MysticalWorld.tab));
    event.addItem(antlers = new ItemBase("antlers").setCreativeTab(MysticalWorld.tab));
    event.addItem(venison = new ItemFoodBase("venison", 3, true).setCreativeTab(MysticalWorld.tab));
    event.addItem(raw_squid = new ItemFoodBase("raw_squid", 1, false).setCreativeTab(MysticalWorld.tab));
    event.addItem(cooked_venison = new ItemFoodBase("cooked_venison", 7, true).setCreativeTab(MysticalWorld.tab));
    event.addItem(cooked_squid = new ItemFoodBase("cooked_squid", 3, false).setCreativeTab(MysticalWorld.tab));
    event.addItem(epic_squid = new ItemFoodBase("epic_squid", 20, false) {
      @Override
      public boolean hasEffect(ItemStack stack) {
        return true;
      }

      @Override
      public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
      }

      @Override
      protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
          player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 1));
          player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 3000));
          player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 1200));
          player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 1));
          player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 0));
          player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1200, 0));
          player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 3));
        }
      }
    }.setAlwaysEdible().setCreativeTab(MysticalWorld.tab));
    event.addItem(ink_bottle = new ItemBase("ink_bottle").setCreativeTab(MysticalWorld.tab).setContainerItem(Items.GLASS_BOTTLE));
    event.addItem(unripe_pearl = new ItemUnripePearl("unripe_pearl").setCreativeTab(MysticalWorld.tab));

    if (!Loader.isModLoaded("roots") || !Knives.initKnives(event)) {
      event.addItem(amethyst_knife = new ItemKnifeBase("amethyst_knife", MaterialTypes.material("mysticalworld:amethyst")).setCreativeTab(MysticalWorld.tab));
      event.addItem(copper_knife = new ItemKnifeBase("copper_knife", MaterialTypes.material("mysticalworld:copper")).setCreativeTab(MysticalWorld.tab));
      event.addItem(silver_knife = new ItemKnifeBase("silver_knife", MaterialTypes.material("mysticalworld:silver")).setCreativeTab(MysticalWorld.tab));
    }

    event.addItem(aubergine_seed = new ItemSeedBase("aubergine_seed", ModBlocks.aubergine, Blocks.FARMLAND).setCreativeTab(MysticalWorld.tab));
    event.addItem(aubergine = new ItemFoodBase("aubergine", 4, false).setCreativeTab(MysticalWorld.tab));
    event.addItem(cooked_aubergine = new ItemFoodBase("cooked_aubergine", 5, false).setCreativeTab(MysticalWorld.tab));
    event.addItem(stuffed_aubergine = new ItemFoodBase("stuffed_aubergine", 10, false).setCreativeTab(MysticalWorld.tab));
    event.addItem(cooked_apple = new ItemFoodBase("cooked_apple", 4, 0.3f, false).setCreativeTab(MysticalWorld.tab));
    event.addItem(poisoned_potato = new ItemSeedFood(2, 0.3f, ModBlocks.poisoned_potato, Blocks.FARMLAND).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.6F).setTranslationKey("potatoPoisonous").setRegistryName(new ResourceLocation("minecraft", "poisonous_potato")));

    if (!MirrorHelper.setStaticFinalValue(Items.class, "field_151170_bI", poisoned_potato)) {
      MysticalWorld.logger.error("Failed to override poisonous potato");
    }

    event.addItem(silk_cocoon = new ItemBase("silk_cocoon").setCreativeTab(MysticalWorld.tab));
    event.addItem(silk_thread = new ItemBase("silk_thread").setCreativeTab(MysticalWorld.tab));
    event.addItem(spindle = new ItemBase("spindle").setCreativeTab(MysticalWorld.tab).setMaxStackSize(1).setMaxDamage(64));
    event.addItem(silkworm_egg = new ItemSilkwormEgg("silkworm_egg").setCreativeTab(MysticalWorld.tab));

    //event.addItem(charm = new ItemCharm("charm").setCreativeTab(MysticalWorld.tab).setMaxStackSize(1));

    registerSeedDrops();
  }

  /**
   * Register item oredicts here
   */
  public static void registerOredict() {
    OreDictionary.registerOre("dyeBlack", ink_bottle);
    OreDictionary.registerOre("logWood", ModBlocks.charred_log);
    OreDictionary.registerOre("plankWood", ModBlocks.charred_planks);
    OreDictionary.registerOre("slabWood", ModBlocks.charred_slab);
    OreDictionary.registerOre("stairWood", ModBlocks.charred_stair);
    OreDictionary.registerOre("fenceWood", ModBlocks.charred_fence);

    // Harvestcraft & VFP compat

    OreDictionary.registerOre("cropAubergine", ModItems.aubergine);
    OreDictionary.registerOre("cropEggplant", ModItems.aubergine);
    OreDictionary.registerOre("foodCalamaricooked", ModItems.cooked_squid);
    OreDictionary.registerOre("foodCalamariraw", ModItems.raw_squid);
    OreDictionary.registerOre("foodEggplant", ModItems.aubergine);
    OreDictionary.registerOre("foodGoodMeat", ModItems.raw_squid);
    OreDictionary.registerOre("foodGoodMeat", ModItems.venison);
    OreDictionary.registerOre("foodGoodMeatCooked", ModItems.cooked_squid);
    OreDictionary.registerOre("foodGoodMeatCooked", ModItems.cooked_venison);
    OreDictionary.registerOre("foodGrilledeggplant", ModItems.cooked_aubergine);
    OreDictionary.registerOre("foodNativeGameMeatCooked", ModItems.cooked_venison);
    OreDictionary.registerOre("foodProteinCooked", ModItems.cooked_squid);
    OreDictionary.registerOre("foodProteinCooked", ModItems.cooked_venison);
    OreDictionary.registerOre("foodRedMeat", ModItems.venison);
    OreDictionary.registerOre("foodRedMeatCooked", ModItems.cooked_venison);
    OreDictionary.registerOre("foodVenisoncooked", ModItems.cooked_venison);
    OreDictionary.registerOre("foodVenisonraw", ModItems.venison);
    OreDictionary.registerOre("ingredientChowderFill", ModItems.cooked_squid);
    OreDictionary.registerOre("ingredientKebabAdventure", ModItems.raw_squid);
    OreDictionary.registerOre("ingredientKebabAdventure", ModItems.venison);
    OreDictionary.registerOre("ingredientKebabMain", ModItems.aubergine);
    OreDictionary.registerOre("ingredientKebabSeafood", ModItems.cooked_squid);
    OreDictionary.registerOre("ingredientVegNugget", ModItems.aubergine);
    OreDictionary.registerOre("listAllfishcooked", ModItems.cooked_squid);
    OreDictionary.registerOre("listAllfishfresh", ModItems.raw_squid);
    OreDictionary.registerOre("listAllfishraw", ModItems.raw_squid);
    OreDictionary.registerOre("listAllmeatcooked", ModItems.cooked_venison);
    OreDictionary.registerOre("listAllmeatraw", ModItems.venison);
    OreDictionary.registerOre("listAllseed", ModItems.aubergine_seed);
    OreDictionary.registerOre("listAllveggie", ModItems.aubergine);
    OreDictionary.registerOre("listAllvenisoncooked", ModItems.cooked_venison);
    OreDictionary.registerOre("listAllvenisonraw", ModItems.venison);

    OreDictionary.registerOre("string", ModItems.silk_thread);
  }

  private static void registerSeedDrops() {
    MinecraftForge.addGrassSeed(new ItemStack(aubergine_seed, 1), 5);
  }
}
