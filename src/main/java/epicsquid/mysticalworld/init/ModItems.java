package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.items.EffectItem;
import epicsquid.mysticalworld.items.ModFoods;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MysticalWorld.MODID)
public class ModItems {
  public static RegistryObject<Item> CARAPACE = ModRegistries.registerItem("carapace", ModRegistries.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<Item> PELT = ModRegistries.registerItem("pelt", ModRegistries.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<Item> ANTLERS = ModRegistries.registerItem("antlers", ModRegistries.item(Item::new, ModRegistries.SIG));
  public static RegistryObject<DyeItem> INK_BOTTLE = ModRegistries.registerItem("ink_Bottle", ModRegistries.dyeItem(DyeColor.BLACK, ModRegistries.SIG));

  public static RegistryObject<Item> VENISON = ModRegistries.registerItem("venison", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.VENISON)));
  public static RegistryObject<Item> COOKED_VENISON = ModRegistries.registerItem("cooked_venison", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_VENISON)));

  //public static RegistryObject<BlockNamedItem> AUBERGINE_SEEDS = ModRegistries.registerItem("aubergine_seeds", ModRegistries.item(

  public static RegistryObject<Item> AUBERGINE = ModRegistries.registerItem("aubergine", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.AUBERGINE)));
  public static RegistryObject<Item> COOKED_AUBERGINE = ModRegistries.registerItem("cooked_aubergine", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_AUBERGINE)));
  public static RegistryObject<Item> STUFFED_AUBERGINE = ModRegistries.registerItem("stuffed_aubergine", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.STUFFED_AUBERGINE)));

  public static RegistryObject<Item> RAW_SQUID = ModRegistries.registerItem("raw_squid", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.RAW_SQUID)));
  public static RegistryObject<Item> COOKED_SQUID = ModRegistries.registerItem("cooked_squid", ModRegistries.item(Item::new, ModRegistries.foodProp(ModFoods.COOKED_SQUID)));
  public static RegistryObject<EffectItem> EPIC_SQUID = ModRegistries.registerItem("epic_squid", ModRegistries.item(EffectItem::new, () -> new Item.Properties().group(MysticalWorld.ITEM_GROUP).food(ModFoods.EPIC_SQUID).rarity(Rarity.EPIC)));
}
