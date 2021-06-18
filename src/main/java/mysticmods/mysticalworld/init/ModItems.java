package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.items.*;
import mysticmods.mysticalworld.items.amethyst.AmethystArmorItem;
import mysticmods.mysticalworld.items.copper.CopperArmorItem;
import mysticmods.mysticalworld.items.lead.LeadArmorItem;
import mysticmods.mysticalworld.items.quicksilver.*;
import mysticmods.mysticalworld.items.silver.*;
import mysticmods.mysticalworld.items.tin.TinArmorItem;
import mysticmods.mysticalworld.items.*;
import mysticmods.mysticalworld.items.quicksilver.*;
import mysticmods.mysticalworld.items.silver.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import noobanidus.libs.noobutil.item.BaseItems;
import noobanidus.libs.noobutil.item.WeaponType;
import noobanidus.libs.noobutil.material.MaterialType;

// TODO: Convert to Registrate

@SuppressWarnings({"unused", "WeakerAccess"})
public class ModItems {

  @FunctionalInterface
  public interface ToolBuilder<V extends Item> {
    V apply(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder);
  }

  @FunctionalInterface
  public interface ArmorBuilder<V extends Item> {
    V apply(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> tool(ToolBuilder<T> builder, WeaponType matType, MaterialType material) {
    return (b) -> builder.apply(material.getItemMaterial(), material.getDamage(matType), material.getSpeed(matType), b);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> sword(ToolBuilder<T> builder, MaterialType material) {
    return tool(builder, WeaponType.SWORD, material);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> spear(ToolBuilder<T> builder, MaterialType material) {
    return tool(builder, WeaponType.SPEAR, material);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> pickaxe(ToolBuilder<T> builder, MaterialType material) {
    return tool(builder, WeaponType.PICKAXE, material);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> axe(ToolBuilder<T> builder, MaterialType material) {
    return tool(builder, WeaponType.AXE, material);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> shovel(ToolBuilder<T> builder, MaterialType material) {
    return tool(builder, WeaponType.SHOVEL, material);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> knife(ToolBuilder<T> builder, MaterialType material) {
    return tool(builder, WeaponType.KNIFE, material);
  }

  private static <T extends Item> NonNullFunction<Item.Properties, T> hoe(ToolBuilder<T> builder, MaterialType material) {
    return tool(builder, WeaponType.HOE, material);
  }

  private static <T extends ArmorItem> NonNullFunction<Item.Properties, T> armor(ArmorBuilder<T> builder, MaterialType material, EquipmentSlotType slot) {
    return (b) -> builder.apply(material.getArmorMaterial(), slot, b);
  }

  private static NonNullFunction<Item.Properties, DyeItem> dyeItem(DyeColor color) {
    return (b) -> new DyeItem(color, b);
  }

  private static <T extends Block> NonNullFunction<Item.Properties, BlockNamedItem> blockNamedItem(RegistryEntry<T> block) {
    return (b) -> new BlockNamedItem(block.get(), b);
  }

  public static RegistryEntry<GuideItem> ENCYCLOPEDIA = MysticalWorld.REGISTRATE.item("encyclopedia", GuideItem::new)
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapelessRecipe(ModItems.ENCYCLOPEDIA.get(), 1)
          .addIngredient(Items.BOOK)
          .addIngredient(MWTags.Items.AUBERGINE)
          .addCriterion("has_aubergine", RegistrateRecipeProvider.hasItem(MWTags.Items.AUBERGINE))
          .build(p))
      .register();

  public static RegistryEntry<Item> CARAPACE = MysticalWorld.REGISTRATE.item("carapace", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.dye(ModItems.CARAPACE, Items.BLUE_DYE.delegate, 1, 2, p))
      .register();

  public static RegistryEntry<Item> PELT = MysticalWorld.REGISTRATE.item("pelt", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItemUnfinished(ModItems.PELT, () -> Items.LEATHER, 1, 1).build(p, new ResourceLocation(MysticalWorld.MODID, "pelt_to_leather")))
      .register();

  public static RegistryEntry<Item> ANTLERS = MysticalWorld.REGISTRATE.item("antlers", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItemUnfinished(ModItems.ANTLERS, () -> Items.BONE_MEAL, 1, 9).build(p, new ResourceLocation(MysticalWorld.MODID, "antlers_to_bonemeal")))
      .register();

  public static RegistryEntry<Item> INK_BOTTLE = MysticalWorld.REGISTRATE.item("ink_bottle", Item::new)
      .properties(o -> o.containerItem(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.dye(ModItems.INK_BOTTLE, Items.BLACK_DYE.delegate, 1, 2, p))
      .register();

  public static RegistryEntry<UnripePearlItem> YOUNG_PEARL = MysticalWorld.REGISTRATE.item("young_pearl", UnripePearlItem::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItemUnfinished(ModItems.YOUNG_PEARL, () -> Items.ENDER_PEARL, 9, 1).build(p, new ResourceLocation(MysticalWorld.MODID, "ender_pearl_from_unripe_pearls")))
      .register();

  public static RegistryEntry<NautilusHornBase.NautilusHorn> NAUTILUS_HORN = MysticalWorld.REGISTRATE.item("nautilus_horn", NautilusHornBase.NautilusHorn::new)
      // KNIFE RECIPE TODO
      .model((ctx, p) -> p.handheld(ModItems.NAUTILUS_HORN))
      .properties(o -> o.maxDamage(32).rarity(Rarity.RARE))
      .register();

  public static RegistryEntry<NautilusHornBase.GlisteringHorn> GLISTERING_HORN = MysticalWorld.REGISTRATE.item("glistering_horn", NautilusHornBase.GlisteringHorn::new)
      .properties(o -> o.maxDamage(64).rarity(Rarity.EPIC))
      .model((ctx, p) -> p.handheld(ModItems.GLISTERING_HORN))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.GLISTERING_HORN.get(), 1)
          .patternLine("XXX")
          .patternLine("XHX")
          .patternLine("XXX")
          .key('X', MWTags.Items.SILVER_INGOT)
          .key('H', ModItems.NAUTILUS_HORN.get())
          .addCriterion("has_horn", RegistrateRecipeProvider.hasItem(ModItems.NAUTILUS_HORN.get()))
          .build(p))
      .model((ctx, p) -> p.withExistingParent(p.name(ModItems.GLISTERING_HORN), "item/handheld").texture("layer0", p.itemTexture(ModItems.NAUTILUS_HORN)))
      .register();

  public static RegistryEntry<AntlerHatItem> ANTLER_HAT = MysticalWorld.REGISTRATE.item("antler_hat", AntlerHatItem::new)
      .properties(o -> o.maxDamage(399).rarity(Rarity.RARE))
      .recipe((o, p) -> ShapedRecipeBuilder.shapedRecipe(o.getEntry(), 1)
          .patternLine("AWA")
          .patternLine("WWW")
          .patternLine("S S")
          .key('A', ModItems.ANTLERS.get())
          .key('W', ItemTags.WOOL)
          .key('S', Tags.Items.STRING)
          .addCriterion("has_antlers", RegistrateRecipeProvider.hasItem(ModItems.ANTLERS.get()))
          .build(p))
      .register();

  public static RegistryEntry<BeetleMaskItem> BEETLE_MASK = MysticalWorld.REGISTRATE.item("beetle_mask", BeetleMaskItem::new)
      .properties(o -> o.maxDamage(399).rarity(Rarity.RARE))
      .recipe((o, p) -> ShapedRecipeBuilder.shapedRecipe(o.getEntry(), 1)
          .patternLine("CWC")
          .patternLine("CWC")
          .patternLine(" S ")
          .key('C', ModItems.CARAPACE.get())
          .key('W', ItemTags.PLANKS)
          .key('S', Tags.Items.RODS_WOODEN)
          .addCriterion("has_carapace", RegistrateRecipeProvider.hasItem(ModItems.CARAPACE.get()))
          .build(p))
      .register();

  public static RegistryEntry<SilkwormEgg> SILKWORM_EGG = MysticalWorld.REGISTRATE.item("silkworm_egg", SilkwormEgg::new).register();

  public static RegistryEntry<Item> SILK_COCOON = MysticalWorld.REGISTRATE.item("silk_cocoon", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItem(ModItems.SILK_COCOON, ModItems.SILK_THREAD, 1, 3, p))
      .register();

  public static RegistryEntry<Item> SILK_THREAD = MysticalWorld.REGISTRATE.item("silk_thread", Item::new)
      .recipe((ctx, p) -> {
        ShapelessRecipeBuilder.shapelessRecipe(Items.STRING, 1)
            .addIngredient(ctx.getEntry())
            .addIngredient(ctx.getEntry())
            .addCriterion("has_silk_thread", RegistrateRecipeProvider.hasItem(ModItems.SILK_THREAD.get()))
            .build(p, "string_from_two_silk_thread");

        // Properly tag Minecraft default recipes
        ShapedRecipeBuilder.shapedRecipe(Items.FISHING_ROD, 1)
            .patternLine("  X")
            .patternLine(" XS")
            .patternLine("X S")
            .key('X', Tags.Items.RODS_WOODEN)
            .key('S', Tags.Items.STRING)
            .addCriterion("has_string", RegistrateRecipeProvider.hasItem(Tags.Items.STRING))
            .build(p);

        ShapedRecipeBuilder.shapedRecipe(Items.SCAFFOLDING, 6)
            .patternLine("XSX")
            .patternLine("X X")
            .patternLine("X X")
            .key('X', Items.BAMBOO)
            .key('S', Tags.Items.STRING)
            .addCriterion("has_bamboo", RegistrateRecipeProvider.hasItem(Items.BAMBOO))
            .build(p);

        // String -> wool
        ShapedRecipeBuilder.shapedRecipe(Blocks.WHITE_WOOL, 1)
            .patternLine("XX")
            .patternLine("XX")
            .key('X', Tags.Items.STRING)
            .addCriterion("has_string", RegistrateRecipeProvider.hasItem(Tags.Items.STRING))
            .build(p);

        // Bow
        ShapedRecipeBuilder.shapedRecipe(Items.BOW, 1)
            .patternLine(" XS")
            .patternLine("X S")
            .patternLine(" XS")
            .key('X', Tags.Items.RODS_WOODEN)
            .key('S', Tags.Items.STRING)
            .addCriterion("has_string", RegistrateRecipeProvider.hasItem(Tags.Items.STRING))
            .build(p);

        // Loom
        ShapedRecipeBuilder.shapedRecipe(Blocks.LOOM, 1)
            .patternLine("SS")
            .patternLine("XX")
            .key('X', ItemTags.PLANKS)
            .key('S', Tags.Items.STRING)
            .addCriterion("has_string", RegistrateRecipeProvider.hasItem(Tags.Items.STRING))
            .build(p);

        // Crossbow
        ShapedRecipeBuilder.shapedRecipe(Items.CROSSBOW, 1)
            .patternLine("XIX")
            .patternLine("STS")
            .patternLine(" X ")
            .key('X', Tags.Items.RODS_WOODEN)
            .key('S', Tags.Items.STRING)
            .key('I', Tags.Items.INGOTS_IRON)
            .key('T', Items.TRIPWIRE_HOOK)
            .addCriterion("has_string", RegistrateRecipeProvider.hasItem(Tags.Items.STRING))
            .addCriterion("has_iron", RegistrateRecipeProvider.hasItem(Tags.Items.INGOTS_IRON))
            .build(p);

        // Lead
        ShapedRecipeBuilder.shapedRecipe(Items.LEAD, 2)
            .patternLine("SS ")
            .patternLine("SB ")
            .patternLine("  S")
            .key('S', Tags.Items.STRING)
            .key('B', Tags.Items.SLIMEBALLS)
            .addCriterion("has_slime", RegistrateRecipeProvider.hasItem(Tags.Items.SLIMEBALLS))
            .build(p);

        // Book
        ShapelessRecipeBuilder.shapelessRecipe(Items.BOOK, 1)
            .addIngredient(Items.PAPER)
            .addIngredient(Items.PAPER)
            .addIngredient(Items.PAPER)
            .addIngredient(Tags.Items.STRING)
            .addCriterion("has_string", RegistrateRecipeProvider.hasItem(Tags.Items.STRING))
            .build(p, new ResourceLocation(MysticalWorld.MODID, "shapeless_book_with_string"));

        ShapedRecipeBuilder.shapedRecipe(Items.BOOK, 1)
            .patternLine("PP")
            .patternLine("PS")
            .key('P', Items.PAPER)
            .key('S', Tags.Items.STRING)
            .addCriterion("has_string", RegistrateRecipeProvider.hasItem(Tags.Items.STRING))
            .build(p, new ResourceLocation(MysticalWorld.MODID, "shaped_book_with_string"));
      })
      .register();

  public static RegistryEntry<Item> SPINDLE = MysticalWorld.REGISTRATE.item("spindle", Item::new)
      .properties(o -> o.maxDamage(64))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.SPINDLE.get(), 1)
          .patternLine(" S ")
          .patternLine("XXX")
          .patternLine(" T ")
          .key('S', net.minecraftforge.common.Tags.Items.RODS_WOODEN)
          .key('X', ItemTags.WOODEN_SLABS)
          .key('T', Items.TRIPWIRE_HOOK)
          .addCriterion("has_slab", RegistrateRecipeProvider.hasItem(ItemTags.WOODEN_SLABS))
          .build(p))
      .register();

  public static RegistryEntry<WaspAttractantItem> WASP_ATTRACTANT = MysticalWorld.REGISTRATE.item("wasp_attractant", WaspAttractantItem::new)
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapelessRecipe(ctx.getEntry(), 4)
          .addIngredient(Items.BONE_MEAL)
          .addIngredient(Ingredient.fromTag(MWTags.Items.SUGARS))
          .addIngredient(Ingredient.fromTag(MWTags.Items.PROTEINS))
          .addCriterion("has_bone_meal", RegistrateRecipeProvider.hasItem(Items.BONE_MEAL))
          .build(p)
      )
      .register();

  public static RegistryEntry<Item> TANNIN_VIAL = MysticalWorld.REGISTRATE.item("tannin_vial", Item::new)
      .properties(o -> o.containerItem(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> {
            ShapelessRecipeBuilder.shapelessRecipe(ctx.getEntry(), 3)
                .addIngredient(ModItems.GALL_APPLE.get())
                .addIngredient(Items.GLASS_BOTTLE)
                .addIngredient(Items.GLASS_BOTTLE)
                .addIngredient(Items.GLASS_BOTTLE)
                .addCriterion("has_gall_apple", RegistrateRecipeProvider.hasItem(ModItems.GALL_APPLE.get()))
                .build(p, new ResourceLocation(MysticalWorld.MODID, "tannin_vials_from_gall_apples"));

            ShapelessRecipeBuilder.shapelessRecipe(Items.LEATHER, 3)
                .addIngredient(ctx.getEntry())
                .addIngredient(Items.ROTTEN_FLESH)
                .addIngredient(Items.ROTTEN_FLESH)
                .addIngredient(Items.ROTTEN_FLESH)
                .addCriterion("has_tannins", RegistrateRecipeProvider.hasItem(ctx.getEntry()))
                .addCriterion("has_rotten_Flesh", RegistrateRecipeProvider.hasItem(Items.ROTTEN_FLESH))
                .build(p, new ResourceLocation(MysticalWorld.MODID, "leather_from_rotten_flesh_x3"));

            ShapelessRecipeBuilder.shapelessRecipe(Items.LEATHER, 2)
                .addIngredient(ctx.getEntry())
                .addIngredient(Items.ROTTEN_FLESH)
                .addIngredient(Items.ROTTEN_FLESH)
                .addCriterion("has_tannins", RegistrateRecipeProvider.hasItem(ctx.getEntry()))
                .addCriterion("has_rotten_Flesh", RegistrateRecipeProvider.hasItem(Items.ROTTEN_FLESH))
                .build(p, new ResourceLocation(MysticalWorld.MODID, "leather_from_rotten_flesh_x2"));

            ShapelessRecipeBuilder.shapelessRecipe(Items.LEATHER, 1)
                .addIngredient(ctx.getEntry())
                .addIngredient(Items.ROTTEN_FLESH)
                .addCriterion("has_tannins", RegistrateRecipeProvider.hasItem(ctx.getEntry()))
                .addCriterion("has_rotten_Flesh", RegistrateRecipeProvider.hasItem(Items.ROTTEN_FLESH))
                .build(p, new ResourceLocation(MysticalWorld.MODID, "leather_from_rotten_flesh_x1"));
          }
      )
      .lang("Vial of Tannin")
      .register();

  public static RegistryEntry<Item> GALL_APPLE = MysticalWorld.REGISTRATE.item("gall_apple", Item::new)
      .register();

  public static RegistryEntry<Item> VENISON = MysticalWorld.REGISTRATE.item("venison", Item::new)
      .properties(o -> o.food(ModFoods.VENISON))
      .recipe((ctx, p) -> {
        MysticalWorld.RECIPES.food(ModItems.VENISON, ModItems.COOKED_VENISON, 0.15f, p);
        MysticalWorld.RECIPES.food(Tags.Items.CROPS_CARROT, ModItems.COOKED_CARROT, 0.15f, p);
        MysticalWorld.RECIPES.food(Tags.Items.CROPS_BEETROOT, ModItems.COOKED_BEETROOT, 0.15f, p);
        MysticalWorld.RECIPES.food(ModItems.ASSORTED_SEEDS, ModItems.COOKED_SEEDS, 0.05f, p);
        MysticalWorld.RECIPES.food(MWTags.Items.EGGPLANT, ModItems.COOKED_AUBERGINE, 0.15f, p);
        MysticalWorld.RECIPES.food(ModItems.RAW_SQUID, ModItems.COOKED_SQUID, 0.15f, p);
      })
      .register();

  public static RegistryEntry<Item> FISH_AND_CHIPS = MysticalWorld.REGISTRATE.item("fish_and_chips", Item::new)
      .properties(o -> o.food(ModFoods.FISH_AND_CHIPS))
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapelessRecipe(ModItems.FISH_AND_CHIPS.get(), 2).addIngredient(Items.BAKED_POTATO).addIngredient(Items.BAKED_POTATO).addIngredient(ModItems.VINEGAR.get()).addIngredient(Ingredient.fromTag(MWTags.Items.COOKED_SEAFOOD)).addCriterion("has_cooked_seafood", RegistrateRecipeProvider.hasItem(MWTags.Items.COOKED_SEAFOOD)).build(p)
      )
      .register();

  public static RegistryEntry<Item> COOKED_VENISON = MysticalWorld.REGISTRATE.item("cooked_venison", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_VENISON))
      .register();

  public static RegistryEntry<BlockNamedItem> AUBERGINE_SEEDS = MysticalWorld.REGISTRATE.item("aubergine_seeds", blockNamedItem(ModBlocks.AUBERGINE_CROP))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItem(ModItems.AUBERGINE, ModItems.AUBERGINE_SEEDS, 1, 1, p))
      .register();

  public static RegistryEntry<Item> ASSORTED_SEEDS = MysticalWorld.REGISTRATE.item("assorted_seeds", Item::new)
      .register();

  public static RegistryEntry<BaseItems.FastFoodItem> COOKED_SEEDS = MysticalWorld.REGISTRATE.item("cooked_seeds", BaseItems.FastFoodItem::new)
      .properties(o -> o.food(ModFoods.COOKED_SEEDS))
      .register();

  public static RegistryEntry<Item> COOKED_BEETROOT = MysticalWorld.REGISTRATE.item("cooked_beetroot", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_BEETROOT))
      .register();

  public static RegistryEntry<BaseItems.FastFoodItem> SLICED_CARROT = MysticalWorld.REGISTRATE.item("sliced_carrot", BaseItems.FastFoodItem::new)
      .properties(o -> o.food(ModFoods.SLICED_CARROT))
/*      .recipe((ctx, p) -> {
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SLICED_CARROT.get(), 4)
            .addIngredient(Tags.Items.CROPS_CARROT)
            .addIngredient(MWTags.Items.KNIVES)
            .addCriterion("has_carrot", p.hasItem(Tags.Items.CROPS_CARROT))
            .addCriterion("has_knives", p.hasItem(MWTags.Items.KNIVES))
            .build(p);
      })*/
      .register();

  public static RegistryEntry<Item> COOKED_CARROT = MysticalWorld.REGISTRATE.item("cooked_carrot", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_CARROT))
      .register();

  public static RegistryEntry<Item> AUBERGINE = MysticalWorld.REGISTRATE.item("aubergine", Item::new)
      .properties(o -> o.food(ModFoods.AUBERGINE))
      .register();

  public static RegistryEntry<Item> COOKED_AUBERGINE = MysticalWorld.REGISTRATE.item("cooked_aubergine", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_AUBERGINE))
      .register();

  public static RegistryEntry<Item> STUFFED_AUBERGINE = MysticalWorld.REGISTRATE.item("stuffed_aubergine", Item::new)
      .properties(o -> o.food(ModFoods.STUFFED_AUBERGINE))
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapelessRecipe(ModItems.STUFFED_AUBERGINE.get(), 1).addIngredient(ModItems.COOKED_AUBERGINE.get()).addIngredient(MWTags.Items.VEGETABLES).addIngredient(MWTags.Items.VEGETABLES).addIngredient(MWTags.Items.COOKED_VEGETABLES).addCriterion("has_cooked_aubergine", RegistrateRecipeProvider.hasItem(ModItems.COOKED_AUBERGINE.get())).build(p))
      .register();

  public static RegistryEntry<Item> RAW_SQUID = MysticalWorld.REGISTRATE.item("raw_squid", Item::new)
      .properties(o -> o.food(ModFoods.RAW_SQUID))
      .register();

  public static RegistryEntry<Item> COOKED_SQUID = MysticalWorld.REGISTRATE.item("cooked_squid", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_SQUID))
      .register();

  public static RegistryEntry<BaseItems.EffectItem> EPIC_SQUID = MysticalWorld.REGISTRATE.item("epic_squid", BaseItems.EffectItem::new)
      .properties(o -> o.food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.EPIC_SQUID.get(), 2)
          .patternLine("CAC")
          .patternLine("AEA")
          .patternLine("CAC")
          .key('C', ModItems.COOKED_SQUID.get())
          .key('A', MWTags.Items.GEMS)
          .key('E', net.minecraftforge.common.Tags.Items.GEMS_EMERALD)
          .addCriterion("has_squid", RegistrateRecipeProvider.hasItem(ModItems.COOKED_SQUID.get()))
          .build(p))
      .register();

  public static NonNullFunction<Item.Properties, TooltipDrinkItem> tooltipDrink(String translationKey) {
    return (b) -> new TooltipDrinkItem(b, translationKey);
  }

  // Drinkies
  // TODO: More tags
  public static RegistryEntry<TooltipDrinkItem> APPLE_CORDIAL = MysticalWorld.REGISTRATE.item("apple_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.APPLE_CORDIAL).containerItem(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.APPLE_CORDIAL, Items.APPLE))
      .register();

  public static RegistryEntry<TooltipDrinkItem> CACTUS_SYRUP = MysticalWorld.REGISTRATE.item("cactus_syrup", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.CACTUS_SYRUP).containerItem(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.CACTUS_SYRUP, Items.CACTUS))
      .register();

  public static RegistryEntry<TooltipDrinkItem> DANDELION_CORDIAL = MysticalWorld.REGISTRATE.item("dandelion_cordial", tooltipDrink("mysticalworld.drinks.wakefulness"))
      .properties(o -> o.food(ModFoods.DANDELION_CORDIAL).containerItem(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.DANDELION_CORDIAL, Items.DANDELION))
      .register();

  public static RegistryEntry<TooltipDrinkItem> LILAC_CORDIAL = MysticalWorld.REGISTRATE.item("lilac_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.LILAC_CORDIAL).containerItem(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.LILAC_CORDIAL, Items.LILAC))
      .register();

  public static RegistryEntry<TooltipDrinkItem> PEONY_CORDIAL = MysticalWorld.REGISTRATE.item("peony_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.PEONY_CORDIAL).containerItem(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.PEONY_CORDIAL, Items.PEONY))
      .register();

  public static RegistryEntry<TooltipDrinkItem> ROSE_CORDIAL = MysticalWorld.REGISTRATE.item("rose_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.ROSE_CORDIAL).containerItem(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.ROSE_CORDIAL, Items.ROSE_BUSH))
      .register();

  public static RegistryEntry<TooltipDrinkItem> VINEGAR = MysticalWorld.REGISTRATE.item("vinegar", tooltipDrink("mysticalworld.drinks.sour"))
      .properties(o -> o.food(ModFoods.VINEGAR).containerItem(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.VINEGAR.get(), 6)
          .patternLine("BBB")
          .patternLine("PPP")
          .patternLine("BBB")
          .key('P', Items.SEA_PICKLE)
          .key('B', Items.GLASS_BOTTLE)
          .addCriterion("has_sea_pickle", RegistrateRecipeProvider.hasItem(Items.SEA_PICKLE))
          .build(p))
      .register();

  public static RegistryEntry<TooltipDrinkItem> VEGETABLE_JUICE = MysticalWorld.REGISTRATE.item("vegetable_juice", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.VEGETABLE_JUICE).containerItem(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.VEGETABLE_JUICE.get(), 4)
          .patternLine("ARC")
          .patternLine("BPB")
          .patternLine("BWB")
          .key('A', MWTags.Items.AUBERGINE)
          .key('R', Items.BEETROOT)
          .key('C', Items.CARROT)
          .key('P', Items.APPLE)
          .key('B', Items.GLASS_BOTTLE)
          .key('W', Items.WATER_BUCKET)
          .addCriterion("has_aubergine", RegistrateRecipeProvider.hasItem(MWTags.Items.AUBERGINE))
          .addCriterion("has_beetroot", RegistrateRecipeProvider.hasItem(Items.BEETROOT))
          .addCriterion("has_carrot", RegistrateRecipeProvider.hasItem(Items.CARROT))
          .addCriterion("has_apple", RegistrateRecipeProvider.hasItem(Items.APPLE))
          .build(p))
      .register();

  // Salads
  public static RegistryEntry<BaseItems.BowlItem> AUBERGINE_SALAD = MysticalWorld.REGISTRATE.item("aubergine_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.AUBERGINE_SALAD).containerItem(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.AUBERGINE_SALAD.get(), 3)
          .patternLine("AAA")
          .patternLine("KKK")
          .patternLine("BBB")
          .key('A', MWTags.Items.AUBERGINE)
          .key('B', Items.BOWL)
          .key('K', Items.KELP)
          .addCriterion("has_aubergine", RegistrateRecipeProvider.hasItem(MWTags.Items.AUBERGINE))
          .addCriterion("has_kelp", RegistrateRecipeProvider.hasItem(Items.KELP))
          .build(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> BEETROOT_SALAD = MysticalWorld.REGISTRATE.item("beetroot_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.BEETROOT_SALAD).containerItem(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.BEETROOT_SALAD.get(), 3)
          .patternLine("AAA")
          .patternLine("KKK")
          .patternLine("BBB")
          .key('A', Items.BEETROOT)
          .key('B', Items.BOWL)
          .key('K', Items.KELP)
          .addCriterion("has_beetroot", RegistrateRecipeProvider.hasItem(Items.BEETROOT))
          .addCriterion("has_kelp", RegistrateRecipeProvider.hasItem(Items.KELP))
          .build(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> CACTUS_DANDELION_SALAD = MysticalWorld.REGISTRATE.item("cactus_dandelion_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.CACTUS_DANDELION_SALAD).containerItem(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.CACTUS_DANDELION_SALAD.get(), 3)
          .patternLine("DCD")
          .patternLine("CDC")
          .patternLine("BBB")
          .key('D', Items.DANDELION)
          .key('C', Items.CACTUS)
          .key('B', Items.BOWL)
          .addCriterion("has_dandelion", RegistrateRecipeProvider.hasItem(Items.DANDELION))
          .addCriterion("has_cactus", RegistrateRecipeProvider.hasItem(Items.CACTUS))
          .build(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> DANDELION_CORNFLOWER_SALAD = MysticalWorld.REGISTRATE.item("dandelion_cornflower_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.DANDELION_CORNFLOWER_SALAD).containerItem(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.DANDELION_CORNFLOWER_SALAD.get(), 3)
          .patternLine("CDC")
          .patternLine("DCD")
          .patternLine("BBB")
          .key('D', Items.DANDELION)
          .key('C', Items.CORNFLOWER)
          .key('B', Items.BOWL)
          .addCriterion("has_dandelion", RegistrateRecipeProvider.hasItem(Items.DANDELION))
          .addCriterion("has_cornflower", RegistrateRecipeProvider.hasItem(Items.CORNFLOWER))
          .build(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> STEWED_EGGPLANT = MysticalWorld.REGISTRATE.item("stewed_eggplant", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.STEWED_EGGPLANT).containerItem(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shapedRecipe(ModItems.STEWED_EGGPLANT.get(), 3)
          .patternLine("AAA")
          .patternLine("MLM")
          .patternLine("BBB")
          .key('A', ModItems.COOKED_AUBERGINE.get())
          .key('B', Items.BOWL)
          .key('L', Items.ALLIUM)
          .key('M', Ingredient.fromItems(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM))
          .addCriterion("has_cooked_aubergine", RegistrateRecipeProvider.hasItem(ModItems.COOKED_AUBERGINE.get()))
          .build(p))
      .register();

  // Ingots/gems
  public static RegistryEntry<Item> PEARL_GEM = MysticalWorld.REGISTRATE.item("lustrous_pearl", Item::new)
      .tag(MWTags.Items.PEARL_GEM)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.PEARL_BLOCK, () -> ModItems.PEARL_GEM, MWTags.Items.PEARL_BLOCK, MWTags.Items.PEARL_GEM, null, null, null, null))
      .register();
  public static RegistryEntry<Item> AMETHYST_GEM = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName(), Item::new)
      .tag(MWTags.Items.AMETHYST_GEM)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.AMETHYST_BLOCK, () -> ModItems.AMETHYST_GEM, MWTags.Items.AMETHYST_BLOCK, MWTags.Items.AMETHYST_GEM, MWTags.Items.AMETHYST_ORE, null, null, null))
      .register();
  public static RegistryEntry<Item> COPPER_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getIngotName(), Item::new)
      .tag(MWTags.Items.COPPER_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.COPPER_BLOCK, () -> ModItems.COPPER_INGOT, MWTags.Items.COPPER_BLOCK, MWTags.Items.COPPER_INGOT, MWTags.Items.COPPER_ORE, () -> ModItems.COPPER_NUGGET, MWTags.Items.COPPER_NUGGET, MWTags.Items.COPPER_DUST))
      .register();
  public static RegistryEntry<Item> LEAD_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getIngotName(), Item::new)
      .tag(MWTags.Items.LEAD_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.LEAD_BLOCK, () -> ModItems.LEAD_INGOT, MWTags.Items.LEAD_BLOCK, MWTags.Items.LEAD_INGOT, MWTags.Items.LEAD_ORE, () -> ModItems.LEAD_NUGGET, MWTags.Items.LEAD_NUGGET, MWTags.Items.LEAD_DUST))
      .register();
  public static RegistryEntry<Item> QUICKSILVER_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getIngotName(), Item::new)
      .tag(MWTags.Items.QUICKSILVER_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.QUICKSILVER_BLOCK, () -> ModItems.QUICKSILVER_INGOT, MWTags.Items.QUICKSILVER_BLOCK, MWTags.Items.QUICKSILVER_INGOT, MWTags.Items.QUICKSILVER_ORE, () -> ModItems.QUICKSILVER_NUGGET, MWTags.Items.QUICKSILVER_NUGGET, MWTags.Items.QUICKSILVER_DUST))
      .register();
  public static RegistryEntry<Item> SILVER_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getIngotName(), Item::new)
      .tag(MWTags.Items.SILVER_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.SILVER_BLOCK, () -> ModItems.SILVER_INGOT, MWTags.Items.SILVER_BLOCK, MWTags.Items.SILVER_INGOT, MWTags.Items.SILVER_ORE, () -> ModItems.SILVER_NUGGET, MWTags.Items.SILVER_NUGGET, MWTags.Items.SILVER_DUST))
      .register();
  public static RegistryEntry<Item> TIN_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getIngotName(), Item::new)
      .tag(MWTags.Items.TIN_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.TIN_BLOCK, () -> ModItems.TIN_INGOT, MWTags.Items.TIN_BLOCK, MWTags.Items.TIN_INGOT, MWTags.Items.TIN_ORE, () -> ModItems.TIN_NUGGET, MWTags.Items.TIN_NUGGET, MWTags.Items.TIN_DUST))
      .register();

  // Nuggets
  public static RegistryEntry<Item> COPPER_NUGGET = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.nuggetName(), Item::new)
      .tag(MWTags.Items.COPPER_NUGGET)
      .recipe((ctx, p) -> {
        MysticalWorld.RECIPES.recycle(MWTags.Items.SILVER_ITEMS, ModItems.SILVER_NUGGET, 0.15f, p);
        MysticalWorld.RECIPES.recycle(MWTags.Items.COPPER_ITEMS, ModItems.COPPER_NUGGET, 0.15f, p);
        MysticalWorld.RECIPES.recycle(MWTags.Items.QUICKSILVER_ITEMS, ModItems.QUICKSILVER_NUGGET, 0.15f, p);
        MysticalWorld.RECIPES.recycle(MWTags.Items.TIN_ITEMS, ModItems.TIN_NUGGET, 0.15f, p);
        MysticalWorld.RECIPES.recycle(MWTags.Items.LEAD_ITEMS, ModItems.LEAD_NUGGET, 0.15f, p);
        MysticalWorld.RECIPES.recycle(ModItems.GOLD_KNIFE, () -> Items.GOLD_NUGGET, 0.15f, MysticalWorld.MODID, p);
        MysticalWorld.RECIPES.recycle(ModItems.IRON_KNIFE, () -> Items.IRON_NUGGET, 0.15f, MysticalWorld.MODID, p);
        MysticalWorld.RECIPES.recycle(ModItems.GOLD_SPEAR, () -> Items.GOLD_NUGGET, 0.15f, MysticalWorld.MODID, p);
        MysticalWorld.RECIPES.recycle(ModItems.IRON_SPEAR, () -> Items.IRON_NUGGET, 0.15f, MysticalWorld.MODID, p);
      })
      .register();
  public static RegistryEntry<Item> LEAD_NUGGET = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.nuggetName(), Item::new)
      .tag(MWTags.Items.LEAD_NUGGET)
      .register();
  public static RegistryEntry<Item> QUICKSILVER_NUGGET = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.nuggetName(), Item::new)
      .tag(MWTags.Items.QUICKSILVER_NUGGET)
      .register();
  public static RegistryEntry<Item> SILVER_NUGGET = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.nuggetName(), Item::new)
      .tag(MWTags.Items.SILVER_NUGGET)
      .register();
  public static RegistryEntry<Item> TIN_NUGGET = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.nuggetName(), Item::new)
      .tag(MWTags.Items.TIN_NUGGET)
      .register();

  // Dusts
  public static RegistryEntry<Item> COPPER_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.dustName(), Item::new)
      .tag(MWTags.Items.COPPER_DUST)
      .register();
  public static RegistryEntry<Item> LEAD_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.dustName(), Item::new)
      .tag(MWTags.Items.LEAD_DUST)
      .register();
  public static RegistryEntry<Item> QUICKSILVER_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.dustName(), Item::new)
      .tag(MWTags.Items.QUICKSILVER_DUST)
      .register();
  public static RegistryEntry<Item> SILVER_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.dustName(), Item::new)
      .tag(MWTags.Items.SILVER_DUST)
      .register();
  public static RegistryEntry<Item> TIN_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.dustName(), Item::new)
      .tag(MWTags.Items.TIN_DUST)
      .register();
  public static RegistryEntry<Item> GOLD_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.GOLD.dustName(), Item::new)
      .tag(MWTags.Items.GOLD_DUST)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.dust(MWTags.Items.GOLD_DUST, () -> Items.GOLD_INGOT, 0.125f, p))
      .register();
  public static RegistryEntry<Item> IRON_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.IRON.dustName(), Item::new)
      .tag(MWTags.Items.IRON_DUST)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.dust(MWTags.Items.IRON_DUST, () -> Items.IRON_INGOT, 0.125f, p))
      .register();

  // Amethyst Tools
  public static RegistryEntry<AxeItem> AMETHYST_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_axe", axe(AxeItem::new, ModMaterials.AMETHYST))
      .model((ctx, p) -> p.handheld(ModItems.AMETHYST_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> AMETHYST_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_hoe", hoe(HoeItem::new, ModMaterials.AMETHYST))
      .model((ctx, p) -> p.handheld(ModItems.AMETHYST_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> AMETHYST_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.AMETHYST))
      .model((ctx, p) -> p.handheld(ModItems.AMETHYST_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> AMETHYST_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, ModMaterials.AMETHYST))
      .model((ctx, p) -> p.handheld(ModItems.AMETHYST_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> AMETHYST_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_shovel", shovel(ShovelItem::new, ModMaterials.AMETHYST))
      .model((ctx, p) -> p.handheld(ModItems.AMETHYST_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> AMETHYST_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_sword", sword(SwordItem::new, ModMaterials.AMETHYST))
      .model((ctx, p) -> p.handheld(ModItems.AMETHYST_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> AMETHYST_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.AMETHYST))
      .model((ctx, p) -> p.handheld(ModItems.AMETHYST_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.AMETHYST_SWORD, ModItems.AMETHYST_SPEAR, null, p)).register();

  // Cactus
  public static RegistryEntry<AxeItem> CACTUS_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_axe", axe(AxeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(() -> Items.CACTUS, ModItems.CACTUS_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> CACTUS_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_hoe", hoe(HoeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(() -> Items.CACTUS, ModItems.CACTUS_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> CACTUS_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(() -> Items.CACTUS, ModItems.CACTUS_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> CACTUS_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(() -> Items.CACTUS, ModItems.CACTUS_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> CACTUS_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_shovel", shovel(ShovelItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(() -> Items.CACTUS, ModItems.CACTUS_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> CACTUS_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_sword", sword(SwordItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(() -> Items.CACTUS, ModItems.CACTUS_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> CACTUS_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.CACTUS_SWORD, ModItems.CACTUS_SPEAR, null, p)).register();

  // COPPER
  public static RegistryEntry<AxeItem> COPPER_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_axe", axe(AxeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.COPPER_INGOT, ModItems.COPPER_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> COPPER_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_hoe", hoe(HoeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.COPPER_INGOT, ModItems.COPPER_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> COPPER_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.COPPER_INGOT, ModItems.COPPER_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> COPPER_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.COPPER_INGOT, ModItems.COPPER_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> COPPER_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_shovel", shovel(ShovelItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.COPPER_INGOT, ModItems.COPPER_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> COPPER_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_sword", sword(SwordItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.COPPER_INGOT, ModItems.COPPER_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> COPPER_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.COPPER_SWORD, ModItems.COPPER_SPEAR, null, p)).register();

  // LEAD
  public static RegistryEntry<AxeItem> LEAD_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_axe", axe(AxeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.LEAD_INGOT, ModItems.LEAD_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> LEAD_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_hoe", hoe(HoeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.LEAD_INGOT, ModItems.LEAD_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> LEAD_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.LEAD_INGOT, ModItems.LEAD_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> LEAD_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.LEAD_INGOT, ModItems.LEAD_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> LEAD_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_shovel", shovel(ShovelItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.LEAD_INGOT, ModItems.LEAD_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> LEAD_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_sword", sword(SwordItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.LEAD_INGOT, ModItems.LEAD_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> LEAD_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.LEAD_SWORD, ModItems.LEAD_SPEAR, null, p)).register();

  // QUICKSILVER
  public static RegistryEntry<QuicksilverAxeItem> QUICKSILVER_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_axe", axe(QuicksilverAxeItem::new, ModMaterials.QUICKSILVER))
      .model((ctx, p) -> p.handheld(ModItems.QUICKSILVER_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_AXE, null, p)).register();
  public static RegistryEntry<QuicksilverHoeItem> QUICKSILVER_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_hoe", hoe(QuicksilverHoeItem::new, ModMaterials.QUICKSILVER))
      .model((ctx, p) -> p.handheld(ModItems.QUICKSILVER_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_HOE, null, p)).register();
  public static RegistryEntry<QuicksilverKnifeItem> QUICKSILVER_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_knife", knife(QuicksilverKnifeItem::new, ModMaterials.QUICKSILVER))
      .model((ctx, p) -> p.handheld(ModItems.QUICKSILVER_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_KNIFE, null, p)).register();
  public static RegistryEntry<QuicksilverPickaxeItem> QUICKSILVER_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_pickaxe", pickaxe(QuicksilverPickaxeItem::new, ModMaterials.QUICKSILVER))
      .model((ctx, p) -> p.handheld(ModItems.QUICKSILVER_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_PICKAXE, null, p)).register();
  public static RegistryEntry<QuicksilverShovelItem> QUICKSILVER_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_shovel", shovel(QuicksilverShovelItem::new, ModMaterials.QUICKSILVER))
      .model((ctx, p) -> p.handheld(ModItems.QUICKSILVER_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_SHOVEL, null, p)).register();
  public static RegistryEntry<QuicksilverSwordItem> QUICKSILVER_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_sword", sword(QuicksilverSwordItem::new, ModMaterials.QUICKSILVER))
      .model((ctx, p) -> p.handheld(ModItems.QUICKSILVER_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_SWORD, null, p)).register();
  public static RegistryEntry<QuicksilverSpearItem> QUICKSILVER_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_spear", spear(QuicksilverSpearItem::new, ModMaterials.QUICKSILVER))
      .model((ctx, p) -> p.handheld(ModItems.QUICKSILVER_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.QUICKSILVER_SWORD, ModItems.QUICKSILVER_SPEAR, null, p)).register();

  // SILVER
  public static RegistryEntry<SilverAxeItem> SILVER_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_axe", axe(SilverAxeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.SILVER_INGOT, ModItems.SILVER_AXE, null, p)).register();
  public static RegistryEntry<SilverHoeItem> SILVER_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_hoe", hoe(SilverHoeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.SILVER_INGOT, ModItems.SILVER_HOE, null, p)).register();
  public static RegistryEntry<SilverKnifeItem> SILVER_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_knife", knife(SilverKnifeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.SILVER_INGOT, ModItems.SILVER_KNIFE, null, p)).register();
  public static RegistryEntry<SilverPickaxeItem> SILVER_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_pickaxe", pickaxe(SilverPickaxeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.SILVER_INGOT, ModItems.SILVER_PICKAXE, null, p)).register();
  public static RegistryEntry<SilverShovelItem> SILVER_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_shovel", shovel(SilverShovelItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.SILVER_INGOT, ModItems.SILVER_SHOVEL, null, p)).register();
  public static RegistryEntry<SilverSwordItem> SILVER_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_sword", sword(SilverSwordItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.SILVER_INGOT, ModItems.SILVER_SWORD, null, p)).register();
  public static RegistryEntry<SilverSpearItem> SILVER_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_spear", spear(SilverSpearItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.SILVER_SWORD, ModItems.SILVER_SPEAR, null, p)).register();

  // TIN
  public static RegistryEntry<AxeItem> TIN_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_axe", axe(AxeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.TIN_INGOT, ModItems.TIN_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> TIN_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_hoe", hoe(HoeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.TIN_INGOT, ModItems.TIN_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> TIN_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.TIN_INGOT, ModItems.TIN_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> TIN_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_pickaxe", pickaxe(PickaxeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.TIN_INGOT, ModItems.TIN_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> TIN_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_shovel", shovel(ShovelItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.TIN_INGOT, ModItems.TIN_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> TIN_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_sword", sword(SwordItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.TIN_INGOT, ModItems.TIN_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> TIN_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.TIN_SWORD, ModItems.TIN_SPEAR, null, p)).register();

  // VANILLA
  // Knives
  public static RegistryEntry<BaseItems.KnifeItem> STONE_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.STONE.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.STONE))
      .model((ctx, p) -> p.handheld(ModItems.STONE_KNIFE))
      .recipe((ctx, p) -> {
        MysticalWorld.RECIPES.knife(Tags.Items.STONE, ModItems.STONE_KNIFE, null, p);
        MysticalWorld.RECIPES.knife(Tags.Items.COBBLESTONE, ModItems.STONE_KNIFE, null, p);
      }).register();
  public static RegistryEntry<BaseItems.KnifeItem> WOODEN_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.WOODEN.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.WOODEN))
      .model((ctx, p) -> p.handheld(ModItems.WOODEN_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(ItemTags.PLANKS, ModItems.WOODEN_KNIFE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> DIAMOND_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.DIAMOND.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.DIAMOND))
      .model((ctx, p) -> p.handheld(ModItems.DIAMOND_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(Tags.Items.GEMS_DIAMOND, ModItems.DIAMOND_KNIFE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> GOLD_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.GOLD.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.GOLD))
      .model((ctx, p) -> p.handheld(ModItems.GOLD_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(Tags.Items.INGOTS_GOLD, ModItems.GOLD_KNIFE, null, p))
      .tag(ItemTags.PIGLIN_LOVED)
      .register();
  public static RegistryEntry<BaseItems.KnifeItem> IRON_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.IRON.getInternalName() + "_knife", knife(BaseItems.KnifeItem::new, ModMaterials.IRON))
      .model((ctx, p) -> p.handheld(ModItems.IRON_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(Tags.Items.INGOTS_IRON, ModItems.IRON_KNIFE, null, p)).register();

  // Spears
  public static RegistryEntry<ModifiedSpearItem> STONE_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.STONE.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.STONE))
      .model((ctx, p) -> p.handheld(ModItems.STONE_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.STONE_SWORD, ModItems.STONE_SPEAR, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> WOODEN_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.WOODEN.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.WOODEN))
      .model((ctx, p) -> p.handheld(ModItems.WOODEN_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.WOODEN_SWORD, ModItems.WOODEN_SPEAR, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> DIAMOND_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.DIAMOND.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.DIAMOND))
      .model((ctx, p) -> p.handheld(ModItems.DIAMOND_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.DIAMOND_SWORD, ModItems.DIAMOND_SPEAR, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> GOLD_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.GOLD.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.GOLD))
      .model((ctx, p) -> p.handheld(ModItems.GOLD_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.GOLDEN_SWORD, ModItems.GOLD_SPEAR, null, p))
      .tag(ItemTags.PIGLIN_LOVED)
      .register();
  public static RegistryEntry<ModifiedSpearItem> IRON_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.IRON.getInternalName() + "_spear", spear(ModifiedSpearItem::new, ModMaterials.IRON))
      .model((ctx, p) -> p.handheld(ModItems.IRON_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.IRON_SWORD, ModItems.IRON_SPEAR, null, p)).register();

  // Armors
  public static RegistryEntry<AmethystArmorItem> AMETHYST_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_helmet", armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_HELMET, null, p))
      .register();
  public static RegistryEntry<AmethystArmorItem> AMETHYST_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_chestplate", armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<AmethystArmorItem> AMETHYST_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_leggings", armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<AmethystArmorItem> AMETHYST_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.AMETHYST.getInternalName() + "_boots", armor(AmethystArmorItem::new, ModMaterials.AMETHYST, EquipmentSlotType.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.AMETHYST_GEM, ModItems.AMETHYST_BOOTS, null, p))
      .register();

  // COPPER
  public static RegistryEntry<CopperArmorItem> COPPER_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_helmet", armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.COPPER_INGOT, ModItems.COPPER_HELMET, null, p))
      .register();
  public static RegistryEntry<CopperArmorItem> COPPER_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_chestplate", armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.COPPER_INGOT, ModItems.COPPER_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<CopperArmorItem> COPPER_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_leggings", armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.COPPER_INGOT, ModItems.COPPER_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<CopperArmorItem> COPPER_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_boots", armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlotType.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.COPPER_INGOT, ModItems.COPPER_BOOTS, null, p))
      .register();

  // LEAD
  public static RegistryEntry<LeadArmorItem> LEAD_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_helmet", armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.LEAD_INGOT, ModItems.LEAD_HELMET, null, p))
      .register();
  public static RegistryEntry<LeadArmorItem> LEAD_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_chestplate", armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.LEAD_INGOT, ModItems.LEAD_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<LeadArmorItem> LEAD_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_leggings", armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.LEAD_INGOT, ModItems.LEAD_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<LeadArmorItem> LEAD_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_boots", armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlotType.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.LEAD_INGOT, ModItems.LEAD_BOOTS, null, p))
      .register();

  // QUICKSILVER
  public static RegistryEntry<QuicksilverArmorItem> QUICKSILVER_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_helmet", armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_HELMET, null, p))
      .register();
  public static RegistryEntry<QuicksilverArmorItem> QUICKSILVER_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_chestplate", armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<QuicksilverArmorItem> QUICKSILVER_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_leggings", armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<QuicksilverArmorItem> QUICKSILVER_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.QUICKSILVER.getInternalName() + "_boots", armor(QuicksilverArmorItem::new, ModMaterials.QUICKSILVER, EquipmentSlotType.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.QUICKSILVER_INGOT, ModItems.QUICKSILVER_BOOTS, null, p))
      .register();

  // SILVER
  public static RegistryEntry<SilverArmorItem> SILVER_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_helmet", armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.SILVER_INGOT, ModItems.SILVER_HELMET, null, p))
      .register();
  public static RegistryEntry<SilverArmorItem> SILVER_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_chestplate", armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.SILVER_INGOT, ModItems.SILVER_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<SilverArmorItem> SILVER_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_leggings", armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.SILVER_INGOT, ModItems.SILVER_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<SilverArmorItem> SILVER_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_boots", armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlotType.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.SILVER_INGOT, ModItems.SILVER_BOOTS, null, p))
      .register();

  // TIN
  public static RegistryEntry<TinArmorItem> TIN_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_helmet", armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.TIN_INGOT, ModItems.TIN_HELMET, null, p))
      .register();
  public static RegistryEntry<TinArmorItem> TIN_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_chestplate", armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.TIN_INGOT, ModItems.TIN_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<TinArmorItem> TIN_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_leggings", armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.TIN_INGOT, ModItems.TIN_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<TinArmorItem> TIN_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_boots", armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlotType.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.TIN_INGOT, ModItems.TIN_BOOTS, null, p))
      .register();

  public static void load() {
  }
}