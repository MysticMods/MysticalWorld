package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.items.*;
import mysticmods.mysticalworld.items.copper.CopperArmorItem;
import mysticmods.mysticalworld.items.lead.LeadArmorItem;
import mysticmods.mysticalworld.items.orichalcum.*;
import mysticmods.mysticalworld.items.sapphire.SapphireArmorItem;
import mysticmods.mysticalworld.items.silver.*;
import mysticmods.mysticalworld.items.tin.TinArmorItem;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import noobanidus.libs.noobutil.data.generator.ItemGenerator;
import noobanidus.libs.noobutil.ingredient.ExcludingIngredient;
import noobanidus.libs.noobutil.item.BaseItems;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ModItems {
  public static RegistryEntry<GuideItem> ENCYCLOPEDIA = MysticalWorld.REGISTRATE.item("encyclopedia", GuideItem::new)
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ModItems.ENCYCLOPEDIA.get(), 1)
          .requires(Items.BOOK)
          .requires(MWTags.Items.AUBERGINE)
          .unlockedBy("has_aubergine", RegistrateRecipeProvider.has(MWTags.Items.AUBERGINE))
          .save(p))
      .register();

  public static RegistryEntry<Item> CARAPACE = MysticalWorld.REGISTRATE.item("carapace", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.dye(ModItems.CARAPACE, Items.BLUE_DYE.delegate, 1, 2, p))
      .tag(MWTags.Items.CARAPACE)
      .register();

  public static RegistryEntry<Item> PELT = MysticalWorld.REGISTRATE.item("pelt", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItemUnfinished(ModItems.PELT, () -> Items.LEATHER, 1, 1).save(p, new ResourceLocation(MysticalWorld.MODID, "pelt_to_leather")))
      .register();

  public static RegistryEntry<Item> ANTLERS = MysticalWorld.REGISTRATE.item("antlers", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItemUnfinished(ModItems.ANTLERS, () -> Items.BONE_MEAL, 1, 9).save(p, new ResourceLocation(MysticalWorld.MODID, "antlers_to_bonemeal")))
      .register();

  public static RegistryEntry<Item> INK_BOTTLE = MysticalWorld.REGISTRATE.item("ink_bottle", Item::new)
      .properties(o -> o.craftRemainder(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.dye(ModItems.INK_BOTTLE, Items.BLACK_DYE.delegate, 1, 2, p))
      .register();

  public static RegistryEntry<UnripePearlItem> YOUNG_PEARL = MysticalWorld.REGISTRATE.item("young_pearl", UnripePearlItem::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItemUnfinished(ModItems.YOUNG_PEARL, () -> Items.ENDER_PEARL, 9, 1).save(p, new ResourceLocation(MysticalWorld.MODID, "ender_pearl_from_unripe_pearls")))
      .register();

  public static RegistryEntry<NautilusHornBase.NautilusHorn> NAUTILUS_HORN = MysticalWorld.REGISTRATE.item("nautilus_horn", NautilusHornBase.NautilusHorn::new)
      .model((ctx, p) -> p.handheld(ModItems.NAUTILUS_HORN))
      .properties(o -> o.durability(32).rarity(Rarity.RARE))
      .register();

  public static RegistryEntry<NautilusHornBase.GlisteringHorn> GLISTERING_HORN = MysticalWorld.REGISTRATE.item("glistering_horn", NautilusHornBase.GlisteringHorn::new)
      .properties(o -> o.durability(64).rarity(Rarity.EPIC))
      .model((ctx, p) -> p.handheld(ModItems.GLISTERING_HORN))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.GLISTERING_HORN.get(), 1)
          .pattern("XXX")
          .pattern("XHX")
          .pattern("XXX")
          .define('X', MWTags.Items.SILVER_INGOT)
          .define('H', ModItems.NAUTILUS_HORN.get())
          .unlockedBy("has_horn", RegistrateRecipeProvider.has(ModItems.NAUTILUS_HORN.get()))
          .save(p))
      .model((ctx, p) -> p.withExistingParent(p.name(ModItems.GLISTERING_HORN), "item/handheld").texture("layer0", p.itemTexture(ModItems.NAUTILUS_HORN)))
      .register();

  public static RegistryEntry<PearleporterItem> PEARLEPORTER = MysticalWorld.REGISTRATE.item("pearleporter", PearleporterItem::new)
      .properties(o -> o.durability(211).rarity(Rarity.EPIC))
      .model((ctx, p) -> p.handheld(ModItems.PEARLEPORTER))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ModItems.PEARLEPORTER.get(), 1)
            .pattern(" GP")
            .pattern(" SG")
            .pattern("S  ")
            .define('G', Tags.Items.NUGGETS_GOLD)
            .define('P', MWTags.Items.PEARL_GEM)
            .define('S', Tags.Items.RODS_WOODEN)
            .unlockedBy("has_pearl", RegistrateRecipeProvider.has(ModItems.PEARL_GEM.get()))
            .save(p);
      })
      .register();

  public static RegistryEntry<ClamBucketItem> CLAM_BUCKET = MysticalWorld.REGISTRATE.item("clam_bucket", (p) -> new ClamBucketItem(() -> Fluids.WATER, p))
      .properties(o -> o.stacksTo(1))
      .register();

  public static RegistryEntry<AntlerHatItem> ANTLER_HAT = MysticalWorld.REGISTRATE.item("antler_hat", AntlerHatItem::new)
      .properties(o -> o.durability(399).rarity(Rarity.RARE))
      .recipe((o, p) -> ShapedRecipeBuilder.shaped(o.getEntry(), 1)
          .pattern("AWA")
          .pattern("WWW")
          .pattern("S S")
          .define('A', ModItems.ANTLERS.get())
          .define('W', ItemTags.WOOL)
          .define('S', Tags.Items.STRING)
          .unlockedBy("has_antlers", RegistrateRecipeProvider.has(ModItems.ANTLERS.get()))
          .save(p))
      .register();

  public static RegistryEntry<BeetleArmorItem> BEETLE_HELMET = MysticalWorld.REGISTRATE.item("beetle_helmet", (b) -> new BeetleArmorItem(b, EquipmentSlot.HEAD))
      .properties(o -> o.rarity(Rarity.RARE))
      .recipe((o, p) -> ShapedRecipeBuilder.shaped(o.getEntry(), 1)
          .pattern("CCC")
          .pattern("C C")
          .define('C', MWTags.Items.CARAPACE)
          .unlockedBy("has_carapace", RegistrateRecipeProvider.has(MWTags.Items.CARAPACE))
          .save(p))
      .register();

  public static RegistryEntry<BeetleArmorItem> BEETLE_CHESTPLATE = MysticalWorld.REGISTRATE.item("beetle_chestplate", (b) -> new BeetleArmorItem(b, EquipmentSlot.CHEST))
      .properties(o -> o.rarity(Rarity.RARE))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
          .pattern("C C")
          .pattern("CCC")
          .pattern("CCC")
          .define('C', MWTags.Items.CARAPACE)
          .unlockedBy("has_carapace", RegistrateRecipeProvider.has(MWTags.Items.CARAPACE))
          .save(p))
      .register();

  public static RegistryEntry<BeetleArmorItem> BEETLE_LEGGINGS = MysticalWorld.REGISTRATE.item("beetle_leggings", (b) -> new BeetleArmorItem(b, EquipmentSlot.LEGS))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
          .pattern("CCC")
          .pattern("C C")
          .pattern("C C")
          .define('C', MWTags.Items.CARAPACE)
          .unlockedBy("has_carapace", RegistrateRecipeProvider.has(MWTags.Items.CARAPACE))
          .save(p))
      .register();

  public static RegistryEntry<BeetleArmorItem> BEETLE_BOOTS = MysticalWorld.REGISTRATE.item("beetle_boots", (b) -> new BeetleArmorItem(b, EquipmentSlot.FEET))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
          .pattern("C C")
          .pattern("C C")
          .define('C', MWTags.Items.CARAPACE)
          .unlockedBy("has_carapace", RegistrateRecipeProvider.has(MWTags.Items.CARAPACE))
          .save(p))
      .register();

  public static RegistryEntry<SilkwormEgg> SILKWORM_EGG = MysticalWorld.REGISTRATE.item("silkworm_egg", SilkwormEgg::new).register();

  public static RegistryEntry<Item> SILK_COCOON = MysticalWorld.REGISTRATE.item("silk_cocoon", Item::new)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.singleItem(ModItems.SILK_COCOON, ModItems.SILK_THREAD, 1, 3, p))
      .register();

  public static RegistryEntry<Item> SILK_THREAD = MysticalWorld.REGISTRATE.item("silk_thread", Item::new)
      .recipe((ctx, p) -> {
        ShapelessRecipeBuilder.shapeless(Items.STRING, 1)
            .requires(ctx.getEntry())
            .requires(ctx.getEntry())
            .unlockedBy("has_silk_thread", RegistrateRecipeProvider.has(ModItems.SILK_THREAD.get()))
            .save(p, "string_from_two_silk_thread");

        // Properly tag Minecraft default recipes
        ShapedRecipeBuilder.shaped(Items.FISHING_ROD, 1)
            .pattern("  X")
            .pattern(" XS")
            .pattern("X S")
            .define('X', Tags.Items.RODS_WOODEN)
            .define('S', Tags.Items.STRING)
            .unlockedBy("has_string", RegistrateRecipeProvider.has(Tags.Items.STRING))
            .save(p);

        ShapedRecipeBuilder.shaped(Items.SCAFFOLDING, 6)
            .pattern("XSX")
            .pattern("X X")
            .pattern("X X")
            .define('X', Items.BAMBOO)
            .define('S', Tags.Items.STRING)
            .unlockedBy("has_bamboo", RegistrateRecipeProvider.has(Items.BAMBOO))
            .save(p);

        // String -> wool
        ShapedRecipeBuilder.shaped(Blocks.WHITE_WOOL, 1)
            .pattern("XX")
            .pattern("XX")
            .define('X', Tags.Items.STRING)
            .unlockedBy("has_string", RegistrateRecipeProvider.has(Tags.Items.STRING))
            .save(p);

        // Bow
        ShapedRecipeBuilder.shaped(Items.BOW, 1)
            .pattern(" XS")
            .pattern("X S")
            .pattern(" XS")
            .define('X', Tags.Items.RODS_WOODEN)
            .define('S', Tags.Items.STRING)
            .unlockedBy("has_string", RegistrateRecipeProvider.has(Tags.Items.STRING))
            .save(p);

        // Loom
        ShapedRecipeBuilder.shaped(Blocks.LOOM, 1)
            .pattern("SS")
            .pattern("XX")
            .define('X', ItemTags.PLANKS)
            .define('S', Tags.Items.STRING)
            .unlockedBy("has_string", RegistrateRecipeProvider.has(Tags.Items.STRING))
            .save(p);

        // Crossbow
        ShapedRecipeBuilder.shaped(Items.CROSSBOW, 1)
            .pattern("XIX")
            .pattern("STS")
            .pattern(" X ")
            .define('X', Tags.Items.RODS_WOODEN)
            .define('S', Tags.Items.STRING)
            .define('I', Tags.Items.INGOTS_IRON)
            .define('T', Items.TRIPWIRE_HOOK)
            .unlockedBy("has_string", RegistrateRecipeProvider.has(Tags.Items.STRING))
            .unlockedBy("has_iron", RegistrateRecipeProvider.has(Tags.Items.INGOTS_IRON))
            .save(p);

        // Lead
        ShapedRecipeBuilder.shaped(Items.LEAD, 2)
            .pattern("SS ")
            .pattern("SB ")
            .pattern("  S")
            .define('S', Tags.Items.STRING)
            .define('B', Tags.Items.SLIMEBALLS)
            .unlockedBy("has_slime", RegistrateRecipeProvider.has(Tags.Items.SLIMEBALLS))
            .save(p);

        // Book
        ShapelessRecipeBuilder.shapeless(Items.BOOK, 1)
            .requires(Items.PAPER)
            .requires(Items.PAPER)
            .requires(Items.PAPER)
            .requires(Tags.Items.STRING)
            .unlockedBy("has_string", RegistrateRecipeProvider.has(Tags.Items.STRING))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "shapeless_book_with_string"));

        ShapedRecipeBuilder.shaped(Items.BOOK, 1)
            .pattern("PP")
            .pattern("PS")
            .define('P', Items.PAPER)
            .define('S', Tags.Items.STRING)
            .unlockedBy("has_string", RegistrateRecipeProvider.has(Tags.Items.STRING))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "shaped_book_with_string"));
      })
      .register();

  public static RegistryEntry<Item> SPINDLE = MysticalWorld.REGISTRATE.item("spindle", Item::new)
      .properties(o -> o.durability(64))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.SPINDLE.get(), 1)
          .pattern(" S ")
          .pattern("XXX")
          .pattern(" T ")
          .define('S', net.minecraftforge.common.Tags.Items.RODS_WOODEN)
          .define('X', ItemTags.WOODEN_SLABS)
          .define('T', Items.TRIPWIRE_HOOK)
          .unlockedBy("has_slab", RegistrateRecipeProvider.has(ItemTags.WOODEN_SLABS))
          .save(p))
      .register();

  public static RegistryEntry<WaspAttractantItem> WASP_ATTRACTANT = MysticalWorld.REGISTRATE.item("wasp_attractant", WaspAttractantItem::new)
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 4)
          .requires(Items.BONE_MEAL)
          .requires(Ingredient.of(MWTags.Items.SUGARS))
          .requires(Ingredient.of(MWTags.Items.PROTEINS))
          .unlockedBy("has_bone_meal", RegistrateRecipeProvider.has(Items.BONE_MEAL))
          .save(p)
      )
      .register();

  public static RegistryEntry<Item> TANNIN_VIAL = MysticalWorld.REGISTRATE.item("tannin_vial", Item::new)
      .properties(o -> o.craftRemainder(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> {
            ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 3)
                .requires(ModItems.GALL_APPLE.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_gall_apple", RegistrateRecipeProvider.has(ModItems.GALL_APPLE.get()))
                .save(p, new ResourceLocation(MysticalWorld.MODID, "tannin_vials_from_gall_apples"));

            ShapelessRecipeBuilder.shapeless(Items.LEATHER, 3)
                .requires(ctx.getEntry())
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.ROTTEN_FLESH)
                .unlockedBy("has_tannins", RegistrateRecipeProvider.has(ctx.getEntry()))
                .unlockedBy("has_rotten_Flesh", RegistrateRecipeProvider.has(Items.ROTTEN_FLESH))
                .save(p, new ResourceLocation(MysticalWorld.MODID, "leather_from_rotten_flesh_x3"));

            ShapelessRecipeBuilder.shapeless(Items.LEATHER, 2)
                .requires(ctx.getEntry())
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.ROTTEN_FLESH)
                .unlockedBy("has_tannins", RegistrateRecipeProvider.has(ctx.getEntry()))
                .unlockedBy("has_rotten_Flesh", RegistrateRecipeProvider.has(Items.ROTTEN_FLESH))
                .save(p, new ResourceLocation(MysticalWorld.MODID, "leather_from_rotten_flesh_x2"));

            ShapelessRecipeBuilder.shapeless(Items.LEATHER, 1)
                .requires(ctx.getEntry())
                .requires(Items.ROTTEN_FLESH)
                .unlockedBy("has_tannins", RegistrateRecipeProvider.has(ctx.getEntry()))
                .unlockedBy("has_rotten_Flesh", RegistrateRecipeProvider.has(Items.ROTTEN_FLESH))
                .save(p, new ResourceLocation(MysticalWorld.MODID, "leather_from_rotten_flesh_x1"));
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
          ShapelessRecipeBuilder.shapeless(ModItems.FISH_AND_CHIPS.get(), 2).requires(Items.BAKED_POTATO).requires(Items.BAKED_POTATO).requires(ModItems.VINEGAR.get()).requires(Ingredient.of(MWTags.Items.COOKED_SEAFOOD)).unlockedBy("has_cooked_seafood", RegistrateRecipeProvider.has(MWTags.Items.COOKED_SEAFOOD)).save(p)
      )
      .register();

  public static RegistryEntry<Item> COOKED_VENISON = MysticalWorld.REGISTRATE.item("cooked_venison", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_VENISON))
      .register();

  public static RegistryEntry<ItemNameBlockItem> AUBERGINE_SEEDS = MysticalWorld.REGISTRATE.item("aubergine_seeds", ItemGenerator.blockNamedItem(ModBlocks.AUBERGINE_CROP))
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
            .addCriterion("has_carrot", p.has(Tags.Items.CROPS_CARROT))
            .addCriterion("has_knives", p.has(MWTags.Items.KNIVES))
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
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ModItems.STUFFED_AUBERGINE.get(), 1).requires(ModItems.COOKED_AUBERGINE.get()).requires(ExcludingIngredient.create(MWTags.Items.VEGETABLES, ModItems.AUBERGINE.get())).requires(ExcludingIngredient.create(MWTags.Items.VEGETABLES, ModItems.AUBERGINE.get())).requires(ExcludingIngredient.create(MWTags.Items.COOKED_VEGETABLES, ModItems.COOKED_AUBERGINE.get())).unlockedBy("has_cooked_aubergine", RegistrateRecipeProvider.has(ModItems.COOKED_AUBERGINE.get())).save(p))
      .register();

  public static RegistryEntry<Item> RAW_SQUID = MysticalWorld.REGISTRATE.item("raw_squid", Item::new)
      .properties(o -> o.food(ModFoods.RAW_SQUID))
      .register();

  public static RegistryEntry<Item> COOKED_SQUID = MysticalWorld.REGISTRATE.item("cooked_squid", Item::new)
      .properties(o -> o.food(ModFoods.COOKED_SQUID))
      .register();

  public static RegistryEntry<BaseItems.EffectItem> EPIC_SQUID = MysticalWorld.REGISTRATE.item("epic_squid", BaseItems.EffectItem::new)
      .properties(o -> o.food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.EPIC_SQUID.get(), 2)
          .pattern("CAC")
          .pattern("AEA")
          .pattern("CAC")
          .define('C', ModItems.COOKED_SQUID.get())
          .define('A', MWTags.Items.GEMS)
          .define('E', net.minecraftforge.common.Tags.Items.GEMS_EMERALD)
          .unlockedBy("has_squid", RegistrateRecipeProvider.has(ModItems.COOKED_SQUID.get()))
          .save(p))
      .register();

  public static NonNullFunction<Item.Properties, TooltipDrinkItem> tooltipDrink(String translationKey) {
    return (b) -> new TooltipDrinkItem(b, translationKey);
  }

  // Drinkies
  // TODO: More tags
  public static RegistryEntry<TooltipDrinkItem> APPLE_CORDIAL = MysticalWorld.REGISTRATE.item("apple_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.APPLE_CORDIAL).craftRemainder(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.APPLE_CORDIAL, Items.APPLE))
      .register();

  public static RegistryEntry<TooltipDrinkItem> CACTUS_SYRUP = MysticalWorld.REGISTRATE.item("cactus_syrup", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.CACTUS_SYRUP).craftRemainder(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.CACTUS_SYRUP, Items.CACTUS))
      .register();

  public static RegistryEntry<TooltipDrinkItem> DANDELION_CORDIAL = MysticalWorld.REGISTRATE.item("dandelion_cordial", tooltipDrink("mysticalworld.drinks.wakefulness"))
      .properties(o -> o.food(ModFoods.DANDELION_CORDIAL).craftRemainder(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.DANDELION_CORDIAL, Items.DANDELION))
      .register();

  public static RegistryEntry<TooltipDrinkItem> LILAC_CORDIAL = MysticalWorld.REGISTRATE.item("lilac_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.LILAC_CORDIAL).craftRemainder(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.LILAC_CORDIAL, Items.LILAC))
      .register();

  public static RegistryEntry<TooltipDrinkItem> PEONY_CORDIAL = MysticalWorld.REGISTRATE.item("peony_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.PEONY_CORDIAL).craftRemainder(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.PEONY_CORDIAL, Items.PEONY))
      .register();

  public static RegistryEntry<TooltipDrinkItem> ROSE_CORDIAL = MysticalWorld.REGISTRATE.item("rose_cordial", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.ROSE_CORDIAL).craftRemainder(Items.GLASS_BOTTLE))
      .recipe(MysticalWorld.RECIPES.cordial(() -> ModItems.ROSE_CORDIAL, Items.ROSE_BUSH))
      .register();

  public static RegistryEntry<TooltipDrinkItem> VINEGAR = MysticalWorld.REGISTRATE.item("vinegar", tooltipDrink("mysticalworld.drinks.sour"))
      .properties(o -> o.food(ModFoods.VINEGAR).craftRemainder(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.VINEGAR.get(), 6)
          .pattern("BBB")
          .pattern("PPP")
          .pattern("BBB")
          .define('P', Items.SEA_PICKLE)
          .define('B', Items.GLASS_BOTTLE)
          .unlockedBy("has_sea_pickle", RegistrateRecipeProvider.has(Items.SEA_PICKLE))
          .save(p))
      .register();

  public static RegistryEntry<TooltipDrinkItem> VEGETABLE_JUICE = MysticalWorld.REGISTRATE.item("vegetable_juice", tooltipDrink("mysticalworld.drinks.slow_regen"))
      .properties(o -> o.food(ModFoods.VEGETABLE_JUICE).craftRemainder(Items.GLASS_BOTTLE))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.VEGETABLE_JUICE.get(), 4)
          .pattern("ARC")
          .pattern("BPB")
          .pattern("BWB")
          .define('A', MWTags.Items.AUBERGINE)
          .define('R', Items.BEETROOT)
          .define('C', Items.CARROT)
          .define('P', Items.APPLE)
          .define('B', Items.GLASS_BOTTLE)
          .define('W', Items.WATER_BUCKET)
          .unlockedBy("has_aubergine", RegistrateRecipeProvider.has(MWTags.Items.AUBERGINE))
          .unlockedBy("has_beetroot", RegistrateRecipeProvider.has(Items.BEETROOT))
          .unlockedBy("has_carrot", RegistrateRecipeProvider.has(Items.CARROT))
          .unlockedBy("has_apple", RegistrateRecipeProvider.has(Items.APPLE))
          .save(p))
      .register();

  // Salads
  public static RegistryEntry<BaseItems.BowlItem> AUBERGINE_SALAD = MysticalWorld.REGISTRATE.item("aubergine_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.AUBERGINE_SALAD).craftRemainder(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.AUBERGINE_SALAD.get(), 3)
          .pattern("AAA")
          .pattern("KKK")
          .pattern("BBB")
          .define('A', MWTags.Items.AUBERGINE)
          .define('B', Items.BOWL)
          .define('K', Items.KELP)
          .unlockedBy("has_aubergine", RegistrateRecipeProvider.has(MWTags.Items.AUBERGINE))
          .unlockedBy("has_kelp", RegistrateRecipeProvider.has(Items.KELP))
          .save(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> BEETROOT_SALAD = MysticalWorld.REGISTRATE.item("beetroot_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.BEETROOT_SALAD).craftRemainder(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.BEETROOT_SALAD.get(), 3)
          .pattern("AAA")
          .pattern("KKK")
          .pattern("BBB")
          .define('A', Items.BEETROOT)
          .define('B', Items.BOWL)
          .define('K', Items.KELP)
          .unlockedBy("has_beetroot", RegistrateRecipeProvider.has(Items.BEETROOT))
          .unlockedBy("has_kelp", RegistrateRecipeProvider.has(Items.KELP))
          .save(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> CACTUS_DANDELION_SALAD = MysticalWorld.REGISTRATE.item("cactus_dandelion_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.CACTUS_DANDELION_SALAD).craftRemainder(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.CACTUS_DANDELION_SALAD.get(), 3)
          .pattern("DCD")
          .pattern("CDC")
          .pattern("BBB")
          .define('D', Items.DANDELION)
          .define('C', Items.CACTUS)
          .define('B', Items.BOWL)
          .unlockedBy("has_dandelion", RegistrateRecipeProvider.has(Items.DANDELION))
          .unlockedBy("has_cactus", RegistrateRecipeProvider.has(Items.CACTUS))
          .save(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> DANDELION_CORNFLOWER_SALAD = MysticalWorld.REGISTRATE.item("dandelion_cornflower_salad", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.DANDELION_CORNFLOWER_SALAD).craftRemainder(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.DANDELION_CORNFLOWER_SALAD.get(), 3)
          .pattern("CDC")
          .pattern("DCD")
          .pattern("BBB")
          .define('D', Items.DANDELION)
          .define('C', Items.CORNFLOWER)
          .define('B', Items.BOWL)
          .unlockedBy("has_dandelion", RegistrateRecipeProvider.has(Items.DANDELION))
          .unlockedBy("has_cornflower", RegistrateRecipeProvider.has(Items.CORNFLOWER))
          .save(p))
      .register();

  public static RegistryEntry<BaseItems.BowlItem> STEWED_EGGPLANT = MysticalWorld.REGISTRATE.item("stewed_eggplant", BaseItems.BowlItem::new)
      .properties(o -> o.food(ModFoods.STEWED_EGGPLANT).craftRemainder(Items.BOWL))
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModItems.STEWED_EGGPLANT.get(), 3)
          .pattern("AAA")
          .pattern("MLM")
          .pattern("BBB")
          .define('A', ModItems.COOKED_AUBERGINE.get())
          .define('B', Items.BOWL)
          .define('L', Items.ALLIUM)
          .define('M', Ingredient.of(Items.RED_MUSHROOM, Items.BROWN_MUSHROOM))
          .unlockedBy("has_cooked_aubergine", RegistrateRecipeProvider.has(ModItems.COOKED_AUBERGINE.get()))
          .save(p))
      .register();

  // Ingots/gems
  public static RegistryEntry<Item> PEARL_GEM = MysticalWorld.REGISTRATE.item("lustrous_pearl", Item::new)
      .tag(MWTags.Items.PEARL_GEM)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.PEARL_BLOCK, () -> ModItems.PEARL_GEM, MWTags.Items.PEARL_BLOCK, MWTags.Items.PEARL_GEM, null, null, null, null))
      .register();
  public static RegistryEntry<Item> SAPPHIRE_GEM = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName(), Item::new)
      .tag(MWTags.Items.SAPPHIRE_GEM)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.SAPPHIRE_BLOCK, () -> ModItems.SAPPHIRE_GEM, MWTags.Items.SAPPHIRE_BLOCK, MWTags.Items.SAPPHIRE_GEM, MWTags.Items.SAPPHIRE_ORE, null, null, null))
      .register();
  public static RegistryEntry<Item> COPPER_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getIngotName(), Item::new)
      .tag(MWTags.Items.COPPER_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.COPPER_BLOCK, () -> ModItems.COPPER_INGOT, MWTags.Items.COPPER_BLOCK, MWTags.Items.COPPER_INGOT, MWTags.Items.COPPER_ORE, () -> ModItems.COPPER_NUGGET, MWTags.Items.COPPER_NUGGET, MWTags.Items.COPPER_DUST))
      .register();
  public static RegistryEntry<Item> LEAD_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getIngotName(), Item::new)
      .tag(MWTags.Items.LEAD_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.LEAD_BLOCK, () -> ModItems.LEAD_INGOT, MWTags.Items.LEAD_BLOCK, MWTags.Items.LEAD_INGOT, MWTags.Items.LEAD_ORE, () -> ModItems.LEAD_NUGGET, MWTags.Items.LEAD_NUGGET, MWTags.Items.LEAD_DUST))
      .register();
  public static RegistryEntry<Item> ORICHALCUM_INGOT = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getIngotName(), Item::new)
      .tag(MWTags.Items.ORICHALCUM_INGOT)
      .recipe(MysticalWorld.RECIPES.storage(() -> ModBlocks.ORICHALCUM_BLOCK, () -> ModItems.ORICHALCUM_INGOT, MWTags.Items.ORICHALCUM_BLOCK, MWTags.Items.ORICHALCUM_INGOT, null /* It has no ore */, () -> ModItems.ORICHALCUM_NUGGET, MWTags.Items.ORICHALCUM_NUGGET, MWTags.Items.ORICHALCUM_DUST))
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
        MysticalWorld.RECIPES.recycle(MWTags.Items.ORICHALCUM, ModItems.ORICHALCUM_NUGGET, 0.15f, p);
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
  public static RegistryEntry<Item> ORICHALCUM_NUGGET = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.nuggetName(), Item::new)
      .tag(MWTags.Items.ORICHALCUM_NUGGET)
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
  public static RegistryEntry<Item> ORICHALCUM_DUST = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.dustName(), Item::new)
      .tag(MWTags.Items.ORICHALCUM_DUST)
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

  // Sapphire Tools
  public static RegistryEntry<AxeItem> SAPPHIRE_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_axe", ItemGenerator.axe(AxeItem::new, ModMaterials.SAPPHIRE))
      .model((ctx, p) -> p.handheld(ModItems.SAPPHIRE_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> SAPPHIRE_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_hoe", ItemGenerator.hoe(HoeItem::new, ModMaterials.SAPPHIRE))
      .model((ctx, p) -> p.handheld(ModItems.SAPPHIRE_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> SAPPHIRE_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.SAPPHIRE))
      .model((ctx, p) -> p.handheld(ModItems.SAPPHIRE_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> SAPPHIRE_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_pickaxe", ItemGenerator.pickaxe(PickaxeItem::new, ModMaterials.SAPPHIRE))
      .model((ctx, p) -> p.handheld(ModItems.SAPPHIRE_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> SAPPHIRE_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_shovel", ItemGenerator.shovel(ShovelItem::new, ModMaterials.SAPPHIRE))
      .model((ctx, p) -> p.handheld(ModItems.SAPPHIRE_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> SAPPHIRE_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_sword", ItemGenerator.sword(SwordItem::new, ModMaterials.SAPPHIRE))
      .model((ctx, p) -> p.handheld(ModItems.SAPPHIRE_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> SAPPHIRE_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.SAPPHIRE))
      .model((ctx, p) -> p.handheld(ModItems.SAPPHIRE_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.SAPPHIRE_SWORD, ModItems.SAPPHIRE_SPEAR, null, p)).register();

  // Cactus
  public static RegistryEntry<AxeItem> CACTUS_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_axe", ItemGenerator.axe(AxeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(() -> Items.CACTUS, ModItems.CACTUS_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> CACTUS_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_hoe", ItemGenerator.hoe(HoeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(() -> Items.CACTUS, ModItems.CACTUS_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> CACTUS_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(() -> Items.CACTUS, ModItems.CACTUS_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> CACTUS_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_pickaxe", ItemGenerator.pickaxe(PickaxeItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(() -> Items.CACTUS, ModItems.CACTUS_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> CACTUS_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_shovel", ItemGenerator.shovel(ShovelItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(() -> Items.CACTUS, ModItems.CACTUS_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> CACTUS_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_sword", ItemGenerator.sword(SwordItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(() -> Items.CACTUS, ModItems.CACTUS_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> CACTUS_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.CACTUS.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.CACTUS))
      .model((ctx, p) -> p.handheld(ModItems.CACTUS_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.CACTUS_SWORD, ModItems.CACTUS_SPEAR, null, p)).register();

  // COPPER
  public static RegistryEntry<AxeItem> COPPER_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_axe", ItemGenerator.axe(AxeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.COPPER_INGOT, ModItems.COPPER_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> COPPER_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_hoe", ItemGenerator.hoe(HoeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.COPPER_INGOT, ModItems.COPPER_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> COPPER_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.COPPER_INGOT, ModItems.COPPER_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> COPPER_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_pickaxe", ItemGenerator.pickaxe(PickaxeItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.COPPER_INGOT, ModItems.COPPER_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> COPPER_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_shovel", ItemGenerator.shovel(ShovelItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.COPPER_INGOT, ModItems.COPPER_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> COPPER_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_sword", ItemGenerator.sword(SwordItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.COPPER_INGOT, ModItems.COPPER_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> COPPER_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.COPPER))
      .model((ctx, p) -> p.handheld(ModItems.COPPER_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.COPPER_SWORD, ModItems.COPPER_SPEAR, null, p)).register();

  // LEAD
  public static RegistryEntry<AxeItem> LEAD_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_axe", ItemGenerator.axe(AxeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.LEAD_INGOT, ModItems.LEAD_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> LEAD_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_hoe", ItemGenerator.hoe(HoeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.LEAD_INGOT, ModItems.LEAD_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> LEAD_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.LEAD_INGOT, ModItems.LEAD_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> LEAD_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_pickaxe", ItemGenerator.pickaxe(PickaxeItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.LEAD_INGOT, ModItems.LEAD_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> LEAD_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_shovel", ItemGenerator.shovel(ShovelItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.LEAD_INGOT, ModItems.LEAD_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> LEAD_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_sword", ItemGenerator.sword(SwordItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.LEAD_INGOT, ModItems.LEAD_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> LEAD_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.LEAD))
      .model((ctx, p) -> p.handheld(ModItems.LEAD_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.LEAD_SWORD, ModItems.LEAD_SPEAR, null, p)).register();

  // ORICHALCUM
  public static RegistryEntry<OrichalcumAxeItem> ORICHALCUM_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_axe", ItemGenerator.axe(OrichalcumAxeItem::new, ModMaterials.ORICHALCUM))
      .model((ctx, p) -> p.handheld(ModItems.ORICHALCUM_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_AXE, null, p)).register();
  public static RegistryEntry<OrichalcumHoeItem> ORICHALCUM_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_hoe", ItemGenerator.hoe(OrichalcumHoeItem::new, ModMaterials.ORICHALCUM))
      .model((ctx, p) -> p.handheld(ModItems.ORICHALCUM_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_HOE, null, p)).register();
  public static RegistryEntry<OrichalcumKnifeItem> ORICHALCUM_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_knife", ItemGenerator.knife(OrichalcumKnifeItem::new, ModMaterials.ORICHALCUM))
      .model((ctx, p) -> p.handheld(ModItems.ORICHALCUM_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_KNIFE, null, p)).register();
  public static RegistryEntry<OrichalcumPickaxeItem> ORICHALCUM_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_pickaxe", ItemGenerator.pickaxe(OrichalcumPickaxeItem::new, ModMaterials.ORICHALCUM))
      .model((ctx, p) -> p.handheld(ModItems.ORICHALCUM_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_PICKAXE, null, p)).register();
  public static RegistryEntry<OrichalcumShovelItem> ORICHALCUM_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_shovel", ItemGenerator.shovel(OrichalcumShovelItem::new, ModMaterials.ORICHALCUM))
      .model((ctx, p) -> p.handheld(ModItems.ORICHALCUM_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_SHOVEL, null, p)).register();
  public static RegistryEntry<OrichalcumSwordItem> ORICHALCUM_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_sword", ItemGenerator.sword(OrichalcumSwordItem::new, ModMaterials.ORICHALCUM))
      .model((ctx, p) -> p.handheld(ModItems.ORICHALCUM_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_SWORD, null, p)).register();
  public static RegistryEntry<OrichalcumSpearItem> ORICHALCUM_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_spear", ItemGenerator.spear(OrichalcumSpearItem::new, ModMaterials.ORICHALCUM))
      .model((ctx, p) -> p.handheld(ModItems.ORICHALCUM_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.ORICHALCUM_SWORD, ModItems.ORICHALCUM_SPEAR, null, p)).register();

  // SILVER
  public static RegistryEntry<SilverAxeItem> SILVER_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_axe", ItemGenerator.axe(SilverAxeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.SILVER_INGOT, ModItems.SILVER_AXE, null, p)).register();
  public static RegistryEntry<SilverHoeItem> SILVER_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_hoe", ItemGenerator.hoe(SilverHoeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.SILVER_INGOT, ModItems.SILVER_HOE, null, p)).register();
  public static RegistryEntry<SilverKnifeItem> SILVER_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_knife", ItemGenerator.knife(SilverKnifeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.SILVER_INGOT, ModItems.SILVER_KNIFE, null, p)).register();
  public static RegistryEntry<SilverPickaxeItem> SILVER_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_pickaxe", ItemGenerator.pickaxe(SilverPickaxeItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.SILVER_INGOT, ModItems.SILVER_PICKAXE, null, p)).register();
  public static RegistryEntry<SilverShovelItem> SILVER_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_shovel", ItemGenerator.shovel(SilverShovelItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.SILVER_INGOT, ModItems.SILVER_SHOVEL, null, p)).register();
  public static RegistryEntry<SilverSwordItem> SILVER_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_sword", ItemGenerator.sword(SilverSwordItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.SILVER_INGOT, ModItems.SILVER_SWORD, null, p)).register();
  public static RegistryEntry<SilverSpearItem> SILVER_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_spear", ItemGenerator.spear(SilverSpearItem::new, ModMaterials.SILVER))
      .model((ctx, p) -> p.handheld(ModItems.SILVER_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.SILVER_SWORD, ModItems.SILVER_SPEAR, null, p)).register();

  // TIN
  public static RegistryEntry<AxeItem> TIN_AXE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_axe", ItemGenerator.axe(AxeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_AXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.axe(MWTags.Items.TIN_INGOT, ModItems.TIN_AXE, null, p)).register();
  public static RegistryEntry<HoeItem> TIN_HOE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_hoe", ItemGenerator.hoe(HoeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_HOE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.hoe(MWTags.Items.TIN_INGOT, ModItems.TIN_HOE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> TIN_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(MWTags.Items.TIN_INGOT, ModItems.TIN_KNIFE, null, p)).register();
  public static RegistryEntry<PickaxeItem> TIN_PICKAXE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_pickaxe", ItemGenerator.pickaxe(PickaxeItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_PICKAXE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.pickaxe(MWTags.Items.TIN_INGOT, ModItems.TIN_PICKAXE, null, p)).register();
  public static RegistryEntry<ShovelItem> TIN_SHOVEL = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_shovel", ItemGenerator.shovel(ShovelItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_SHOVEL))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.shovel(MWTags.Items.TIN_INGOT, ModItems.TIN_SHOVEL, null, p)).register();
  public static RegistryEntry<SwordItem> TIN_SWORD = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_sword", ItemGenerator.sword(SwordItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_SWORD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.sword(MWTags.Items.TIN_INGOT, ModItems.TIN_SWORD, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> TIN_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.TIN))
      .model((ctx, p) -> p.handheld(ModItems.TIN_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(ModItems.TIN_SWORD, ModItems.TIN_SPEAR, null, p)).register();

  // VANILLA
  // Knives
  public static RegistryEntry<BaseItems.KnifeItem> STONE_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.STONE.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.STONE))
      .model((ctx, p) -> p.handheld(ModItems.STONE_KNIFE))
      .recipe((ctx, p) -> {
        MysticalWorld.RECIPES.knife(Tags.Items.STONE, ModItems.STONE_KNIFE, null, p);
        MysticalWorld.RECIPES.knife(Tags.Items.COBBLESTONE, ModItems.STONE_KNIFE, null, p);
      }).register();
  public static RegistryEntry<BaseItems.KnifeItem> WOODEN_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.WOODEN.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.WOODEN))
      .model((ctx, p) -> p.handheld(ModItems.WOODEN_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(ItemTags.PLANKS, ModItems.WOODEN_KNIFE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> DIAMOND_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.DIAMOND.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.DIAMOND))
      .model((ctx, p) -> p.handheld(ModItems.DIAMOND_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(Tags.Items.GEMS_DIAMOND, ModItems.DIAMOND_KNIFE, null, p)).register();
  public static RegistryEntry<BaseItems.KnifeItem> GOLD_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.GOLD.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.GOLD))
      .model((ctx, p) -> p.handheld(ModItems.GOLD_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(Tags.Items.INGOTS_GOLD, ModItems.GOLD_KNIFE, null, p))
      .tag(ItemTags.PIGLIN_LOVED)
      .register();
  public static RegistryEntry<BaseItems.KnifeItem> IRON_KNIFE = MysticalWorld.REGISTRATE.item(ModMaterials.IRON.getInternalName() + "_knife", ItemGenerator.knife(BaseItems.KnifeItem::new, ModMaterials.IRON))
      .model((ctx, p) -> p.handheld(ModItems.IRON_KNIFE))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.knife(Tags.Items.INGOTS_IRON, ModItems.IRON_KNIFE, null, p)).register();

  // Spears
  public static RegistryEntry<ModifiedSpearItem> STONE_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.STONE.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.STONE))
      .model((ctx, p) -> p.handheld(ModItems.STONE_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.STONE_SWORD, ModItems.STONE_SPEAR, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> WOODEN_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.WOODEN.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.WOODEN))
      .model((ctx, p) -> p.handheld(ModItems.WOODEN_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.WOODEN_SWORD, ModItems.WOODEN_SPEAR, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> DIAMOND_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.DIAMOND.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.DIAMOND))
      .model((ctx, p) -> p.handheld(ModItems.DIAMOND_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.DIAMOND_SWORD, ModItems.DIAMOND_SPEAR, null, p)).register();
  public static RegistryEntry<ModifiedSpearItem> GOLD_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.GOLD.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.GOLD))
      .model((ctx, p) -> p.handheld(ModItems.GOLD_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.GOLDEN_SWORD, ModItems.GOLD_SPEAR, null, p))
      .tag(ItemTags.PIGLIN_LOVED)
      .register();
  public static RegistryEntry<ModifiedSpearItem> IRON_SPEAR = MysticalWorld.REGISTRATE.item(ModMaterials.IRON.getInternalName() + "_spear", ItemGenerator.spear(ModifiedSpearItem::new, ModMaterials.IRON))
      .model((ctx, p) -> p.handheld(ModItems.IRON_SPEAR))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.spear(Items.IRON_SWORD, ModItems.IRON_SPEAR, null, p)).register();

  // Armors
  public static RegistryEntry<SapphireArmorItem> SAPPHIRE_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_helmet", ItemGenerator.armor(SapphireArmorItem::new, ModMaterials.SAPPHIRE, EquipmentSlot.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_HELMET, null, p))
      .register();
  public static RegistryEntry<SapphireArmorItem> SAPPHIRE_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_chestplate", ItemGenerator.armor(SapphireArmorItem::new, ModMaterials.SAPPHIRE, EquipmentSlot.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<SapphireArmorItem> SAPPHIRE_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_leggings", ItemGenerator.armor(SapphireArmorItem::new, ModMaterials.SAPPHIRE, EquipmentSlot.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<SapphireArmorItem> SAPPHIRE_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.SAPPHIRE.getInternalName() + "_boots", ItemGenerator.armor(SapphireArmorItem::new, ModMaterials.SAPPHIRE, EquipmentSlot.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.SAPPHIRE_GEM, ModItems.SAPPHIRE_BOOTS, null, p))
      .register();

  // COPPER
  public static RegistryEntry<CopperArmorItem> COPPER_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_helmet", ItemGenerator.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlot.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.COPPER_INGOT, ModItems.COPPER_HELMET, null, p))
      .register();
  public static RegistryEntry<CopperArmorItem> COPPER_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_chestplate", ItemGenerator.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlot.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.COPPER_INGOT, ModItems.COPPER_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<CopperArmorItem> COPPER_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_leggings", ItemGenerator.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlot.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.COPPER_INGOT, ModItems.COPPER_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<CopperArmorItem> COPPER_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.COPPER.getInternalName() + "_boots", ItemGenerator.armor(CopperArmorItem::new, ModMaterials.COPPER, EquipmentSlot.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.COPPER_INGOT, ModItems.COPPER_BOOTS, null, p))
      .register();

  // LEAD
  public static RegistryEntry<LeadArmorItem> LEAD_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_helmet", ItemGenerator.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlot.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.LEAD_INGOT, ModItems.LEAD_HELMET, null, p))
      .register();
  public static RegistryEntry<LeadArmorItem> LEAD_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_chestplate", ItemGenerator.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlot.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.LEAD_INGOT, ModItems.LEAD_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<LeadArmorItem> LEAD_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_leggings", ItemGenerator.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlot.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.LEAD_INGOT, ModItems.LEAD_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<LeadArmorItem> LEAD_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.LEAD.getInternalName() + "_boots", ItemGenerator.armor(LeadArmorItem::new, ModMaterials.LEAD, EquipmentSlot.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.LEAD_INGOT, ModItems.LEAD_BOOTS, null, p))
      .register();

  // ORICHALCUM
  public static RegistryEntry<OrichalcumArmorItem> ORICHALCUM_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_helmet", ItemGenerator.armor(OrichalcumArmorItem::new, ModMaterials.ORICHALCUM, EquipmentSlot.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_HELMET, null, p))
      .register();
  public static RegistryEntry<OrichalcumArmorItem> ORICHALCUM_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_chestplate", ItemGenerator.armor(OrichalcumArmorItem::new, ModMaterials.ORICHALCUM, EquipmentSlot.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<OrichalcumArmorItem> ORICHALCUM_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_leggings", ItemGenerator.armor(OrichalcumArmorItem::new, ModMaterials.ORICHALCUM, EquipmentSlot.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<OrichalcumArmorItem> ORICHALCUM_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.ORICHALCUM.getInternalName() + "_boots", ItemGenerator.armor(OrichalcumArmorItem::new, ModMaterials.ORICHALCUM, EquipmentSlot.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.ORICHALCUM_INGOT, ModItems.ORICHALCUM_BOOTS, null, p))
      .register();

  // SILVER
  public static RegistryEntry<SilverArmorItem> SILVER_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_helmet", ItemGenerator.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlot.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.SILVER_INGOT, ModItems.SILVER_HELMET, null, p))
      .register();
  public static RegistryEntry<SilverArmorItem> SILVER_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_chestplate", ItemGenerator.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlot.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.SILVER_INGOT, ModItems.SILVER_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<SilverArmorItem> SILVER_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_leggings", ItemGenerator.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlot.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.SILVER_INGOT, ModItems.SILVER_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<SilverArmorItem> SILVER_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.SILVER.getInternalName() + "_boots", ItemGenerator.armor(SilverArmorItem::new, ModMaterials.SILVER, EquipmentSlot.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.SILVER_INGOT, ModItems.SILVER_BOOTS, null, p))
      .register();

  // TIN
  public static RegistryEntry<TinArmorItem> TIN_HELMET = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_helmet", ItemGenerator.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlot.HEAD))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.helmet(MWTags.Items.TIN_INGOT, ModItems.TIN_HELMET, null, p))
      .register();
  public static RegistryEntry<TinArmorItem> TIN_CHESTPLATE = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_chestplate", ItemGenerator.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlot.CHEST))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.chest(MWTags.Items.TIN_INGOT, ModItems.TIN_CHESTPLATE, null, p))
      .register();
  public static RegistryEntry<TinArmorItem> TIN_LEGGINGS = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_leggings", ItemGenerator.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlot.LEGS))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.legs(MWTags.Items.TIN_INGOT, ModItems.TIN_LEGGINGS, null, p))
      .register();
  public static RegistryEntry<TinArmorItem> TIN_BOOTS = MysticalWorld.REGISTRATE.item(ModMaterials.TIN.getInternalName() + "_boots", ItemGenerator.armor(TinArmorItem::new, ModMaterials.TIN, EquipmentSlot.FEET))
      .recipe((ctx, p) -> MysticalWorld.RECIPES.boots(MWTags.Items.TIN_INGOT, ModItems.TIN_BOOTS, null, p))
      .register();

  public static void load() {
  }
}
