package mysticmods.mysticalworld.init.deferred;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModFoods;
import mysticmods.mysticalworld.init.ModMaterials;
import mysticmods.mysticalworld.init.deferred.data.ItemData;
import mysticmods.mysticalworld.items.*;
import mysticmods.mysticalworld.items.copper.CopperArmorItem;
import mysticmods.mysticalworld.items.lead.LeadArmorItem;
import mysticmods.mysticalworld.items.orichalcum.*;
import mysticmods.mysticalworld.items.sapphire.SapphireArmorItem;
import mysticmods.mysticalworld.items.silver.*;
import mysticmods.mysticalworld.items.tin.TinArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.libs.noobutil.item.BaseItems;
import noobanidus.libs.noobutil.item.WeaponType;

import java.util.function.Supplier;

public class ModItems {
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Item.class, MysticalWorld.MODID);

  public static void register(IEventBus modBus) {
    ITEMS.register(modBus);
  }

  public static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
    RegistryObject<T> result = ITEMS.register(name, item);
    if (MysticalWorld.DATA_GEN) {
      ItemData.storeItem(result);
    }
    return result;
  }

  protected static Item.Properties p() {
    return new Item.Properties().tab(MysticalWorld.ITEM_GROUP);
  }

  public static final RegistryObject<GuideItem> ENCYCLOPEDIA = register("encyclopedia", () -> new GuideItem(p()));

  public static final RegistryObject<Item> CARAPACE = register("carapace", () -> new Item(p()));

  public static final RegistryObject<Item> PELT = register("pelt", () -> new Item(p()));

  public static final RegistryObject<Item> ANTLERS = register("antlers", () -> new Item(p()));

  public static final RegistryObject<Item> INK_BOTTLE = register("ink_bottle", () -> new Item(p().craftRemainder(Items.GLASS_BOTTLE)));

  public static final RegistryObject<UnripePearlItem> YOUNG_PEARL = register("young_pearl", () -> new UnripePearlItem(p()));

  public static final RegistryObject<NautilusHornBase.NautilusHorn> NAUTILUS_HORN = register("nautilus_horn", () -> new NautilusHornBase.NautilusHorn(p().durability(32).rarity(Rarity.RARE)));

  public static final RegistryObject<NautilusHornBase.GlisteringHorn> GLISTERING_HORN = register("glistering_horn", () -> new NautilusHornBase.GlisteringHorn(p().durability(64).rarity(Rarity.EPIC)));

  public static final RegistryObject<PearleporterItem> PEARLEPORTER = register("pearleporter", () -> new PearleporterItem(p().durability(211).rarity(Rarity.EPIC)));

  public static final RegistryObject<ClamBucketItem> CLAM_BUCKET = register("clam_bucket", () -> new ClamBucketItem(() -> Fluids.WATER, p().stacksTo(1)));

  public static final RegistryObject<SilkwormEgg> SILKWORM_EGG = register("silkworm_egg", () -> new SilkwormEgg(p()));

  public static final RegistryObject<Item> SILK_COCOON = register("silk_cocoon", () -> new Item(p()));

  public static final RegistryObject<Item> SILK_THREAD = register("silk_thread", () -> new Item(p()));

  public static final RegistryObject<Item> SPINDLE = register("spindle", () -> new Item(p().durability(64)));

  public static final RegistryObject<Item> TANNIN_VIAL = register("tannin_vial", () -> new Item(p().craftRemainder(Items.GLASS_BOTTLE)));

  public static final RegistryObject<Item> GALL_APPLE = register("gall_apple", () -> new Item(p()));

  // Seeds
  public static final RegistryObject<WaspAttractantItem> WASP_ATTRACTANT = register("wasp_attractant", () -> new WaspAttractantItem(p()));

  public static final RegistryObject<ItemNameBlockItem> AUBERGINE_SEEDS = register("aubergine_seeds", () -> new ItemNameBlockItem(ModBlocks.AUBERGINE_CROP.get(), p()));

  public static final RegistryObject<Item> ASSORTED_SEEDS = register("assorted_seeds", () -> new Item(p()));


  // FOOD

  public static final RegistryObject<Item> VENISON = register("venison", () -> new Item(p().food(ModFoods.VENISON)));

  public static final RegistryObject<Item> RAW_SQUID = register("raw_squid", () -> new Item(p().food(ModFoods.RAW_SQUID)));

  public static final RegistryObject<BaseItems.FastFoodItem> SLICED_CARROT = register("sliced_carrot", () -> new BaseItems.FastFoodItem(p().food(ModFoods.SLICED_CARROT)));

  public static final RegistryObject<Item> AUBERGINE = register("aubergine", () -> new Item(p().food(ModFoods.AUBERGINE)));

  public static final RegistryObject<Item> COOKED_SQUID = register("cooked_squid", () -> new Item(p().food(ModFoods.COOKED_SQUID)));

  public static final RegistryObject<BaseItems.EffectItem> EPIC_SQUID = register("epic_squid", () -> new BaseItems.EffectItem(p().food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC)));

  public static final RegistryObject<Item> COOKED_VENISON = register("cooked_venison", () -> new Item(p().food(ModFoods.COOKED_VENISON)));

  public static final RegistryObject<Item> FISH_AND_CHIPS = register("fish_and_chips", () -> new Item(p().food(ModFoods.FISH_AND_CHIPS)));

  public static final RegistryObject<BaseItems.FastFoodItem> COOKED_SEEDS = register("cooked_seeds", () -> new BaseItems.FastFoodItem(p().food(ModFoods.COOKED_SEEDS)));

  public static final RegistryObject<Item> COOKED_BEETROOT = register("cooked_beetroot", () -> new Item(p().food(ModFoods.COOKED_BEETROOT)));

  public static final RegistryObject<Item> COOKED_CARROT = register("cooked_carrot", () -> new Item(p().food(ModFoods.COOKED_CARROT)));

  public static final RegistryObject<Item> COOKED_AUBERGINE = register("cooked_aubergine", () -> new Item(p().food(ModFoods.COOKED_AUBERGINE)));

  public static final RegistryObject<Item> STUFFED_AUBERGINE = register("stuffed_aubergine", () -> new Item(p().food(ModFoods.STUFFED_AUBERGINE)));

  public static final RegistryObject<BaseItems.BowlItem> AUBERGINE_SALAD = register("aubergine_salad", () -> new BaseItems.BowlItem(p().food(ModFoods.AUBERGINE_SALAD).craftRemainder(Items.BOWL)));

  public static final RegistryObject<BaseItems.BowlItem> BEETROOT_SALAD = register("beetroot_salad", () -> new BaseItems.BowlItem(p().food(ModFoods.BEETROOT_SALAD).craftRemainder(Items.BOWL)));

  public static final RegistryObject<BaseItems.BowlItem> CACTUS_DANDELION_SALAD = register("cactus_dandelion_salad", () -> new BaseItems.BowlItem(p().food(ModFoods.CACTUS_DANDELION_SALAD).craftRemainder(Items.BOWL)));

  public static final RegistryObject<BaseItems.BowlItem> DANDELION_CORNFLOWER_SALAD = register("dandelion_cornflower_salad", () -> new BaseItems.BowlItem(p().food(ModFoods.DANDELION_CORNFLOWER_SALAD).craftRemainder(Items.BOWL)));

  public static final RegistryObject<BaseItems.BowlItem> STEWED_EGGPLANT = register("stewed_eggplant", () -> new BaseItems.BowlItem(p().food(ModFoods.STEWED_EGGPLANT).craftRemainder(Items.BOWL)));

  // DRINKS

  public static final RegistryObject<TooltipDrinkItem> APPLE_CORDIAL = register("apple_cordial", () -> new TooltipDrinkItem(p().food(ModFoods.APPLE_CORDIAL).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.slow_regen"));

  public static final RegistryObject<TooltipDrinkItem> CACTUS_SYRUP = register("cactus_syrup", () -> new TooltipDrinkItem(p().food(ModFoods.CACTUS_SYRUP).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.slow_regen"));

  public static final RegistryObject<TooltipDrinkItem> DANDELION_CORDIAL = register("dandelion_cordial", () -> new TooltipDrinkItem(p().food(ModFoods.DANDELION_CORDIAL).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.wakefulness"));

  public static final RegistryObject<TooltipDrinkItem> LILAC_CORDIAL = register("lilac_cordial", () -> new TooltipDrinkItem(p().food(ModFoods.LILAC_CORDIAL).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.slow_regen"));

  public static final RegistryObject<TooltipDrinkItem> PEONY_CORDIAL = register("peony_cordial", () -> new TooltipDrinkItem(p().food(ModFoods.PEONY_CORDIAL).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.slow_regen"));

  public static final RegistryObject<TooltipDrinkItem> ROSE_CORDIAL = register("rose_cordial", () -> new TooltipDrinkItem(p().food(ModFoods.ROSE_CORDIAL).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.slow_regen"));

  public static final RegistryObject<TooltipDrinkItem> VEGETABLE_JUICE = register("vegetable_juice", () -> new TooltipDrinkItem(p().food(ModFoods.VEGETABLE_JUICE).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.slow_regen"));

  public static final RegistryObject<TooltipDrinkItem> VINEGAR = register("vinegar", () -> new TooltipDrinkItem(p().food(ModFoods.VINEGAR).craftRemainder(Items.GLASS_BOTTLE), "mysticalworld.drinks.sour"));

  // INGOTS ETC

  public static final RegistryObject<Item> LUSTROUS_PEARL = register("lustrous_pearl", () -> new Item(p()));

  public static final RegistryObject<Item> SAPPHIRE_GEM = register(ModMaterials.SAPPHIRE.getInternalName(), () -> new Item(p()));

  public static final RegistryObject<Item> LEAD_INGOT = register(ModMaterials.LEAD.getInternalName(), () -> new Item(p()));

  public static final RegistryObject<Item> ORICHALCUM_INGOT = register(ModMaterials.ORICHALCUM.getInternalName(), () -> new Item(p()));

  public static final RegistryObject<Item> TIN_INGOT = register(ModMaterials.TIN.getInternalName(), () -> new Item(p()));

  public static final RegistryObject<Item> SILVER_INGOT = register(ModMaterials.SILVER.getInternalName(), () -> new Item(p()));

  // NUGGETS

  public static final RegistryObject<Item> COPPER_NUGGET = register(ModMaterials.COPPER.nuggetName(), () -> new Item(p()));

  public static final RegistryObject<Item> LEAD_NUGGET = register(ModMaterials.LEAD.nuggetName(), () -> new Item(p()));

  public static final RegistryObject<Item> ORICHALCUM_NUGGET = register(ModMaterials.ORICHALCUM.nuggetName(), () -> new Item(p()));

  public static final RegistryObject<Item> TIN_NUGGET = register(ModMaterials.TIN.nuggetName(), () -> new Item(p()));

  public static final RegistryObject<Item> SILVER_NUGGET = register(ModMaterials.SILVER.nuggetName(), () -> new Item(p()));

  // DUSTS

  public static final RegistryObject<Item> COPPER_DUST = register(ModMaterials.COPPER.dustName(), () -> new Item(p()));

  public static final RegistryObject<Item> LEAD_DUST = register(ModMaterials.LEAD.dustName(), () -> new Item(p()));

  public static final RegistryObject<Item> ORICHALCUM_DUST = register(ModMaterials.ORICHALCUM.dustName(), () -> new Item(p()));

  public static final RegistryObject<Item> TIN_DUST = register(ModMaterials.TIN.dustName(), () -> new Item(p()));

  public static final RegistryObject<Item> SILVER_DUST = register(ModMaterials.SILVER.dustName(), () -> new Item(p()));

  public static final RegistryObject<Item> GOLD_DUST = register(ModMaterials.GOLD.dustName(), () -> new Item(p()));

  public static final RegistryObject<Item> IRON_DUST = register(ModMaterials.IRON.dustName(), () -> new Item(p()));

  // SAPPHIRE TOOLS

  public static final RegistryObject<AxeItem> SAPPHIRE_AXE = register(ModMaterials.SAPPHIRE.getInternalName() + "_axe", () -> new AxeItem(ModMaterials.SAPPHIRE.getItemMaterial(), ModMaterials.SAPPHIRE.getDamage(WeaponType.AXE), ModMaterials.SAPPHIRE.getSpeed(WeaponType.AXE), p()));

  public static final RegistryObject<HoeItem> SAPPHIRE_HOE = register(ModMaterials.SAPPHIRE.getInternalName() + "_hoe", () -> new HoeItem(ModMaterials.SAPPHIRE.getItemMaterial(), ModMaterials.SAPPHIRE.getDamage(WeaponType.HOE), ModMaterials.SAPPHIRE.getSpeed(WeaponType.HOE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> SAPPHIRE_KNIFE = register(ModMaterials.SAPPHIRE.getInternalName() + "_knife", () -> new BaseItems.KnifeItem(ModMaterials.SAPPHIRE.getItemMaterial(), ModMaterials.SAPPHIRE.getDamage(WeaponType.KNIFE), ModMaterials.SAPPHIRE.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<PickaxeItem> SAPPHIRE_PICKAXE = register(ModMaterials.SAPPHIRE.getInternalName() + "_pickaxe", () -> new PickaxeItem(ModMaterials.SAPPHIRE.getItemMaterial(), ModMaterials.SAPPHIRE.getDamage(WeaponType.PICKAXE), ModMaterials.SAPPHIRE.getSpeed(WeaponType.PICKAXE), p()));

  public static final RegistryObject<ShovelItem> SAPPHIRE_SHOVEL = register(ModMaterials.SAPPHIRE.getInternalName() + "_shovel", () -> new ShovelItem(ModMaterials.SAPPHIRE.getItemMaterial(), ModMaterials.SAPPHIRE.getDamage(WeaponType.SHOVEL), ModMaterials.SAPPHIRE.getSpeed(WeaponType.SHOVEL), p()));

  public static final RegistryObject<SwordItem> SAPPHIRE_SWORD = register(ModMaterials.SAPPHIRE.getInternalName() + "_sword", () -> new SwordItem(ModMaterials.SAPPHIRE.getItemMaterial(), ModMaterials.SAPPHIRE.getDamage(WeaponType.SWORD), ModMaterials.SAPPHIRE.getSpeed(WeaponType.SWORD), p()));

  // CACTUS

  public static final RegistryObject<AxeItem> CACTUS_AXE = register(ModMaterials.CACTUS.getInternalName() + "_axe", () -> new AxeItem(ModMaterials.CACTUS.getItemMaterial(), ModMaterials.CACTUS.getDamage(WeaponType.AXE), ModMaterials.CACTUS.getSpeed(WeaponType.AXE), p()));

  public static final RegistryObject<HoeItem> CACTUS_HOE = register(ModMaterials.CACTUS.getInternalName() + "_hoe", () -> new HoeItem(ModMaterials.CACTUS.getItemMaterial(), ModMaterials.CACTUS.getDamage(WeaponType.HOE), ModMaterials.CACTUS.getSpeed(WeaponType.HOE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> CACTUS_KNIFE = register(ModMaterials.CACTUS.getInternalName() + "_knife", () -> new BaseItems.KnifeItem(ModMaterials.CACTUS.getItemMaterial(), ModMaterials.CACTUS.getDamage(WeaponType.KNIFE), ModMaterials.CACTUS.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<PickaxeItem> CACTUS_PICKAXE = register(ModMaterials.CACTUS.getInternalName() + "_pickaxe", () -> new PickaxeItem(ModMaterials.CACTUS.getItemMaterial(), ModMaterials.CACTUS.getDamage(WeaponType.PICKAXE), ModMaterials.CACTUS.getSpeed(WeaponType.PICKAXE), p()));

  public static final RegistryObject<ShovelItem> CACTUS_SHOVEL = register(ModMaterials.CACTUS.getInternalName() + "_shovel", () -> new ShovelItem(ModMaterials.CACTUS.getItemMaterial(), ModMaterials.CACTUS.getDamage(WeaponType.SHOVEL), ModMaterials.CACTUS.getSpeed(WeaponType.SHOVEL), p()));

  public static final RegistryObject<SwordItem> CACTUS_SWORD = register(ModMaterials.CACTUS.getInternalName() + "_sword", () -> new SwordItem(ModMaterials.CACTUS.getItemMaterial(), ModMaterials.CACTUS.getDamage(WeaponType.SWORD), ModMaterials.CACTUS.getSpeed(WeaponType.SWORD), p()));

  // COPPER

  public static final RegistryObject<AxeItem> COPPER_AXE = register(ModMaterials.COPPER.getInternalName() + "_axe", () -> new AxeItem(ModMaterials.COPPER.getItemMaterial(), ModMaterials.COPPER.getDamage(WeaponType.AXE), ModMaterials.COPPER.getSpeed(WeaponType.AXE), p()));

  public static final RegistryObject<HoeItem> COPPER_HOE = register(ModMaterials.COPPER.getInternalName() + "_hoe", () -> new HoeItem(ModMaterials.COPPER.getItemMaterial(), ModMaterials.COPPER.getDamage(WeaponType.HOE), ModMaterials.COPPER.getSpeed(WeaponType.HOE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> COPPER_KNIFE = register(ModMaterials.COPPER.getInternalName() + "_knife", () -> new BaseItems.KnifeItem(ModMaterials.COPPER.getItemMaterial(), ModMaterials.COPPER.getDamage(WeaponType.KNIFE), ModMaterials.COPPER.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<PickaxeItem> COPPER_PICKAXE = register(ModMaterials.COPPER.getInternalName() + "_pickaxe", () -> new PickaxeItem(ModMaterials.COPPER.getItemMaterial(), ModMaterials.COPPER.getDamage(WeaponType.PICKAXE), ModMaterials.COPPER.getSpeed(WeaponType.PICKAXE), p()));

  public static final RegistryObject<ShovelItem> COPPER_SHOVEL = register(ModMaterials.COPPER.getInternalName() + "_shovel", () -> new ShovelItem(ModMaterials.COPPER.getItemMaterial(), ModMaterials.COPPER.getDamage(WeaponType.SHOVEL), ModMaterials.COPPER.getSpeed(WeaponType.SHOVEL), p()));

  public static final RegistryObject<SwordItem> COPPER_SWORD = register(ModMaterials.COPPER.getInternalName() + "_sword", () -> new SwordItem(ModMaterials.COPPER.getItemMaterial(), ModMaterials.COPPER.getDamage(WeaponType.SWORD), ModMaterials.COPPER.getSpeed(WeaponType.SWORD), p()));

  // LEAD

  public static final RegistryObject<AxeItem> LEAD_AXE = register(ModMaterials.LEAD.getInternalName() + "_axe", () -> new AxeItem(ModMaterials.LEAD.getItemMaterial(), ModMaterials.LEAD.getDamage(WeaponType.AXE), ModMaterials.LEAD.getSpeed(WeaponType.AXE), p()));

  public static final RegistryObject<HoeItem> LEAD_HOE = register(ModMaterials.LEAD.getInternalName() + "_hoe", () -> new HoeItem(ModMaterials.LEAD.getItemMaterial(), ModMaterials.LEAD.getDamage(WeaponType.HOE), ModMaterials.LEAD.getSpeed(WeaponType.HOE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> LEAD_KNIFE = register(ModMaterials.LEAD.getInternalName() + "_knife", () -> new BaseItems.KnifeItem(ModMaterials.LEAD.getItemMaterial(), ModMaterials.LEAD.getDamage(WeaponType.KNIFE), ModMaterials.LEAD.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<PickaxeItem> LEAD_PICKAXE = register(ModMaterials.LEAD.getInternalName() + "_pickaxe", () -> new PickaxeItem(ModMaterials.LEAD.getItemMaterial(), ModMaterials.LEAD.getDamage(WeaponType.PICKAXE), ModMaterials.LEAD.getSpeed(WeaponType.PICKAXE), p()));

  public static final RegistryObject<ShovelItem> LEAD_SHOVEL = register(ModMaterials.LEAD.getInternalName() + "_shovel", () -> new ShovelItem(ModMaterials.LEAD.getItemMaterial(), ModMaterials.LEAD.getDamage(WeaponType.SHOVEL), ModMaterials.LEAD.getSpeed(WeaponType.SHOVEL), p()));

  public static final RegistryObject<SwordItem> LEAD_SWORD = register(ModMaterials.LEAD.getInternalName() + "_sword", () -> new SwordItem(ModMaterials.LEAD.getItemMaterial(), ModMaterials.LEAD.getDamage(WeaponType.SWORD), ModMaterials.LEAD.getSpeed(WeaponType.SWORD), p()));

  // ORICHALCUM

  public static final RegistryObject<OrichalcumAxeItem> ORICHALCUM_AXE = register(ModMaterials.ORICHALCUM.getInternalName() + "_axe", () -> new OrichalcumAxeItem(ModMaterials.ORICHALCUM.getItemMaterial(), ModMaterials.ORICHALCUM.getDamage(WeaponType.AXE), ModMaterials.ORICHALCUM.getSpeed(WeaponType.AXE), p()));

  public static final RegistryObject<OrichalcumHoeItem> ORICHALCUM_HOE = register(ModMaterials.ORICHALCUM.getInternalName() + "_hoe", () -> new OrichalcumHoeItem(ModMaterials.ORICHALCUM.getItemMaterial(), ModMaterials.ORICHALCUM.getDamage(WeaponType.HOE), ModMaterials.ORICHALCUM.getSpeed(WeaponType.HOE), p()));

  public static final RegistryObject<OrichalcumKnifeItem> ORICHALCUM_KNIFE = register(ModMaterials.ORICHALCUM.getInternalName() + "_knife", () -> new OrichalcumKnifeItem(ModMaterials.ORICHALCUM.getItemMaterial(), ModMaterials.ORICHALCUM.getDamage(WeaponType.KNIFE), ModMaterials.ORICHALCUM.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<OrichalcumPickaxeItem> ORICHALCUM_PICKAXE = register(ModMaterials.ORICHALCUM.getInternalName() + "_pickaxe", () -> new OrichalcumPickaxeItem(ModMaterials.ORICHALCUM.getItemMaterial(), ModMaterials.ORICHALCUM.getDamage(WeaponType.PICKAXE), ModMaterials.ORICHALCUM.getSpeed(WeaponType.PICKAXE), p()));

  public static final RegistryObject<OrichalcumShovelItem> ORICHALCUM_SHOVEL = register(ModMaterials.ORICHALCUM.getInternalName() + "_shovel", () -> new OrichalcumShovelItem(ModMaterials.ORICHALCUM.getItemMaterial(), ModMaterials.ORICHALCUM.getDamage(WeaponType.SHOVEL), ModMaterials.ORICHALCUM.getSpeed(WeaponType.SHOVEL), p()));

  public static final RegistryObject<OrichalcumSwordItem> ORICHALCUM_SWORD = register(ModMaterials.ORICHALCUM.getInternalName() + "_sword", () -> new OrichalcumSwordItem(ModMaterials.ORICHALCUM.getItemMaterial(), ModMaterials.ORICHALCUM.getDamage(WeaponType.SWORD), ModMaterials.ORICHALCUM.getSpeed(WeaponType.SWORD), p()));

  // SILVER

  public static final RegistryObject<SilverAxeItem> SILVER_AXE = register(ModMaterials.SILVER.getInternalName() + "_axe", () -> new SilverAxeItem(ModMaterials.SILVER.getItemMaterial(), ModMaterials.SILVER.getDamage(WeaponType.AXE), ModMaterials.SILVER.getSpeed(WeaponType.AXE), p()));

  public static final RegistryObject<SilverHoeItem> SILVER_HOE = register(ModMaterials.SILVER.getInternalName() + "_hoe", () -> new SilverHoeItem(ModMaterials.SILVER.getItemMaterial(), ModMaterials.SILVER.getDamage(WeaponType.HOE), ModMaterials.SILVER.getSpeed(WeaponType.HOE), p()));

  public static final RegistryObject<SilverKnifeItem> SILVER_KNIFE = register(ModMaterials.SILVER.getInternalName() + "_knife", () -> new SilverKnifeItem(ModMaterials.SILVER.getItemMaterial(), ModMaterials.SILVER.getDamage(WeaponType.KNIFE), ModMaterials.SILVER.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<SilverPickaxeItem> SILVER_PICKAXE = register(ModMaterials.SILVER.getInternalName() + "_pickaxe", () -> new SilverPickaxeItem(ModMaterials.SILVER.getItemMaterial(), ModMaterials.SILVER.getDamage(WeaponType.PICKAXE), ModMaterials.SILVER.getSpeed(WeaponType.PICKAXE), p()));

  public static final RegistryObject<SilverShovelItem> SILVER_SHOVEL = register(ModMaterials.SILVER.getInternalName() + "_shovel", () -> new SilverShovelItem(ModMaterials.SILVER.getItemMaterial(), ModMaterials.SILVER.getDamage(WeaponType.SHOVEL), ModMaterials.SILVER.getSpeed(WeaponType.SHOVEL), p()));

  public static final RegistryObject<SilverSwordItem> SILVER_SWORD = register(ModMaterials.SILVER.getInternalName() + "_sword", () -> new SilverSwordItem(ModMaterials.SILVER.getItemMaterial(), ModMaterials.SILVER.getDamage(WeaponType.SWORD), ModMaterials.SILVER.getSpeed(WeaponType.SWORD), p()));

  // TIN

  public static final RegistryObject<AxeItem> TIN_AXE = register(ModMaterials.TIN.getInternalName() + "_axe", () -> new AxeItem(ModMaterials.TIN.getItemMaterial(), ModMaterials.TIN.getDamage(WeaponType.AXE), ModMaterials.TIN.getSpeed(WeaponType.AXE), p()));

  public static final RegistryObject<HoeItem> TIN_HOE = register(ModMaterials.TIN.getInternalName() + "_hoe", () -> new HoeItem(ModMaterials.TIN.getItemMaterial(), ModMaterials.TIN.getDamage(WeaponType.HOE), ModMaterials.TIN.getSpeed(WeaponType.HOE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> TIN_KNIFE = register(ModMaterials.TIN.getInternalName() + "_knife", () -> new BaseItems.KnifeItem(ModMaterials.TIN.getItemMaterial(), ModMaterials.TIN.getDamage(WeaponType.KNIFE), ModMaterials.TIN.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<PickaxeItem> TIN_PICKAXE = register(ModMaterials.TIN.getInternalName() + "_pickaxe", () -> new PickaxeItem(ModMaterials.TIN.getItemMaterial(), ModMaterials.TIN.getDamage(WeaponType.PICKAXE), ModMaterials.TIN.getSpeed(WeaponType.PICKAXE), p()));

  public static final RegistryObject<ShovelItem> TIN_SHOVEL = register(ModMaterials.TIN.getInternalName() + "_shovel", () -> new ShovelItem(ModMaterials.TIN.getItemMaterial(), ModMaterials.TIN.getDamage(WeaponType.SHOVEL), ModMaterials.TIN.getSpeed(WeaponType.SHOVEL), p()));

  public static final RegistryObject<SwordItem> TIN_SWORD = register(ModMaterials.TIN.getInternalName() + "_sword", () -> new SwordItem(ModMaterials.TIN.getItemMaterial(), ModMaterials.TIN.getDamage(WeaponType.SWORD), ModMaterials.TIN.getSpeed(WeaponType.SWORD), p()));

  // VANILLA KNIVES

  public static final RegistryObject<BaseItems.KnifeItem> WOODEN_KNIFE = register("wooden_knife", () -> new BaseItems.KnifeItem(ModMaterials.WOODEN.getItemMaterial(), ModMaterials.WOODEN.getDamage(WeaponType.KNIFE), ModMaterials.WOODEN.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> STONE_KNIFE = register("stone_knife", () -> new BaseItems.KnifeItem(ModMaterials.STONE.getItemMaterial(), ModMaterials.STONE.getDamage(WeaponType.KNIFE), ModMaterials.STONE.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> IRON_KNIFE = register("iron_knife", () -> new BaseItems.KnifeItem(ModMaterials.IRON.getItemMaterial(), ModMaterials.IRON.getDamage(WeaponType.KNIFE), ModMaterials.IRON.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> GOLD_KNIFE = register("gold_knife", () -> new BaseItems.KnifeItem(ModMaterials.GOLD.getItemMaterial(), ModMaterials.GOLD.getDamage(WeaponType.KNIFE), ModMaterials.GOLD.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> DIAMOND_KNIFE = register("diamond_knife", () -> new BaseItems.KnifeItem(ModMaterials.DIAMOND.getItemMaterial(), ModMaterials.DIAMOND.getDamage(WeaponType.KNIFE), ModMaterials.DIAMOND.getSpeed(WeaponType.KNIFE), p()));

  public static final RegistryObject<BaseItems.KnifeItem> NETHERITE_KNIFE = register("netherite_knife", () -> new BaseItems.KnifeItem(ModMaterials.NETHERITE.getItemMaterial(), ModMaterials.NETHERITE.getDamage(WeaponType.KNIFE), ModMaterials.NETHERITE.getSpeed(WeaponType.KNIFE), p()));

  // ARMOR

  // SAPPHIRE

  public static final RegistryObject<SapphireArmorItem> SAPPHIRE_HELMET = register(ModMaterials.SAPPHIRE.getInternalName() + "_helmet", () -> new SapphireArmorItem(ModMaterials.SAPPHIRE.getArmorMaterial(), EquipmentSlot.HEAD, p()));

  public static final RegistryObject<SapphireArmorItem> SAPPHIRE_CHESTPLATE = register(ModMaterials.SAPPHIRE.getInternalName() + "_chestplate", () -> new SapphireArmorItem(ModMaterials.SAPPHIRE.getArmorMaterial(), EquipmentSlot.CHEST, p()));

  public static final RegistryObject<SapphireArmorItem> SAPPHIRE_LEGGINGS = register(ModMaterials.SAPPHIRE.getInternalName() + "_leggings", () -> new SapphireArmorItem(ModMaterials.SAPPHIRE.getArmorMaterial(), EquipmentSlot.LEGS, p()));

  public static final RegistryObject<SapphireArmorItem> SAPPHIRE_BOOTS = register(ModMaterials.SAPPHIRE.getInternalName() + "_boots", () -> new SapphireArmorItem(ModMaterials.SAPPHIRE.getArmorMaterial(), EquipmentSlot.FEET, p()));

  // COPPER

  public static final RegistryObject<CopperArmorItem> COPPER_HELMET = register(ModMaterials.COPPER.getInternalName() + "_helmet", () -> new CopperArmorItem(ModMaterials.COPPER.getArmorMaterial(), EquipmentSlot.HEAD, p()));

  public static final RegistryObject<CopperArmorItem> COPPER_CHESTPLATE = register(ModMaterials.COPPER.getInternalName() + "_chestplate", () -> new CopperArmorItem(ModMaterials.COPPER.getArmorMaterial(), EquipmentSlot.CHEST, p()));

  public static final RegistryObject<CopperArmorItem> COPPER_LEGGINGS = register(ModMaterials.COPPER.getInternalName() + "_leggings", () -> new CopperArmorItem(ModMaterials.COPPER.getArmorMaterial(), EquipmentSlot.LEGS, p()));

  public static final RegistryObject<CopperArmorItem> COPPER_BOOTS = register(ModMaterials.COPPER.getInternalName() + "_boots", () -> new CopperArmorItem(ModMaterials.COPPER.getArmorMaterial(), EquipmentSlot.FEET, p()));

  // LEAD

  public static final RegistryObject<LeadArmorItem> LEAD_HELMET = register(ModMaterials.LEAD.getInternalName() + "_helmet", () -> new LeadArmorItem(ModMaterials.LEAD.getArmorMaterial(), EquipmentSlot.HEAD, p()));

  public static final RegistryObject<LeadArmorItem> LEAD_CHESTPLATE = register(ModMaterials.LEAD.getInternalName() + "_chestplate", () -> new LeadArmorItem(ModMaterials.LEAD.getArmorMaterial(), EquipmentSlot.CHEST, p()));

  public static final RegistryObject<LeadArmorItem> LEAD_LEGGINGS = register(ModMaterials.LEAD.getInternalName() + "_leggings", () -> new LeadArmorItem(ModMaterials.LEAD.getArmorMaterial(), EquipmentSlot.LEGS, p()));

  public static final RegistryObject<LeadArmorItem> LEAD_BOOTS = register(ModMaterials.LEAD.getInternalName() + "_boots", () -> new LeadArmorItem(ModMaterials.LEAD.getArmorMaterial(), EquipmentSlot.FEET, p()));

  // ORICHALCUM

  public static final RegistryObject<OrichalcumArmorItem> ORICHALCUM_HELMET = register(ModMaterials.ORICHALCUM.getInternalName() + "_helmet", () -> new OrichalcumArmorItem(ModMaterials.ORICHALCUM.getArmorMaterial(), EquipmentSlot.HEAD, p()));

  public static final RegistryObject<OrichalcumArmorItem> ORICHALCUM_CHESTPLATE = register(ModMaterials.ORICHALCUM.getInternalName() + "_chestplate", () -> new OrichalcumArmorItem(ModMaterials.ORICHALCUM.getArmorMaterial(), EquipmentSlot.CHEST, p()));

  public static final RegistryObject<OrichalcumArmorItem> ORICHALCUM_LEGGINGS = register(ModMaterials.ORICHALCUM.getInternalName() + "_leggings", () -> new OrichalcumArmorItem(ModMaterials.ORICHALCUM.getArmorMaterial(), EquipmentSlot.LEGS, p()));

  public static final RegistryObject<OrichalcumArmorItem> ORICHALCUM_BOOTS = register(ModMaterials.ORICHALCUM.getInternalName() + "_boots", () -> new OrichalcumArmorItem(ModMaterials.ORICHALCUM.getArmorMaterial(), EquipmentSlot.FEET, p()));

  // SILVER

  public static final RegistryObject<SilverArmorItem> SILVER_HELMET = register(ModMaterials.SILVER.getInternalName() + "_helmet", () -> new SilverArmorItem(ModMaterials.SILVER.getArmorMaterial(), EquipmentSlot.HEAD, p()));

  public static final RegistryObject<SilverArmorItem> SILVER_CHESTPLATE = register(ModMaterials.SILVER.getInternalName() + "_chestplate", () -> new SilverArmorItem(ModMaterials.SILVER.getArmorMaterial(), EquipmentSlot.CHEST, p()));

  public static final RegistryObject<SilverArmorItem> SILVER_LEGGINGS = register(ModMaterials.SILVER.getInternalName() + "_leggings", () -> new SilverArmorItem(ModMaterials.SILVER.getArmorMaterial(), EquipmentSlot.LEGS, p()));

  public static final RegistryObject<SilverArmorItem> SILVER_BOOTS = register(ModMaterials.SILVER.getInternalName() + "_boots", () -> new SilverArmorItem(ModMaterials.SILVER.getArmorMaterial(), EquipmentSlot.FEET, p()));

  // TIN

  public static final RegistryObject<TinArmorItem> TIN_HELMET = register(ModMaterials.TIN.getInternalName() + "_helmet", () -> new TinArmorItem(ModMaterials.TIN.getArmorMaterial(), EquipmentSlot.HEAD, p()));

  public static final RegistryObject<TinArmorItem> TIN_CHESTPLATE = register(ModMaterials.TIN.getInternalName() + "_chestplate", () -> new TinArmorItem(ModMaterials.TIN.getArmorMaterial(), EquipmentSlot.CHEST, p()));

  public static final RegistryObject<TinArmorItem> TIN_LEGGINGS = register(ModMaterials.TIN.getInternalName() + "_leggings", () -> new TinArmorItem(ModMaterials.TIN.getArmorMaterial(), EquipmentSlot.LEGS, p()));

  public static final RegistryObject<TinArmorItem> TIN_BOOTS = register(ModMaterials.TIN.getInternalName() + "_boots", () -> new TinArmorItem(ModMaterials.TIN.getArmorMaterial(), EquipmentSlot.FEET, p()));


  // ARMOR

  // TODO: Durability?
  public static final RegistryObject<AntlerHatItem> ANTLER_HAT = register("antler_hat", () -> new AntlerHatItem(p().durability(399).rarity(Rarity.RARE)));

  public static final RegistryObject<BeetleArmorItem> BEETLE_CHESTPLATE = register("beetle_chestplate", () -> new BeetleArmorItem(p().rarity(Rarity.RARE), EquipmentSlot.CHEST));

  public static final RegistryObject<BeetleArmorItem> BEETLE_LEGGINGS = register("beetle_leggings", () -> new BeetleArmorItem(p().rarity(Rarity.RARE), EquipmentSlot.LEGS));

  public static final RegistryObject<BeetleArmorItem> BEETLE_BOOTS = register("beetle_boots", () -> new BeetleArmorItem(p().rarity(Rarity.RARE), EquipmentSlot.FEET));

  public static final RegistryObject<BeetleArmorItem> BEETLE_HELMET = register("beetle_helmet", () -> new BeetleArmorItem(p().rarity(Rarity.RARE), EquipmentSlot.HEAD));
}
