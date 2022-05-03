package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.loot.conditions.*;
import mysticmods.mysticalworld.loot.functions.RandomPotion;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.libs.noobutil.ingredient.ExcludingIngredient;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModLoot {
  private static final DeferredRegister<LootItemFunctionType> FUNCTIONS = DeferredRegister.create(Registry.LOOT_FUNCTION_REGISTRY, MysticalWorld.MODID);
  private static final DeferredRegister<LootItemConditionType> CONDITIONS = DeferredRegister.create(Registry.LOOT_ITEM_REGISTRY, MysticalWorld.MODID);

  public static final RegistryObject<LootItemFunctionType> RANDOM_POTION = FUNCTIONS.register("random_potion", () -> new LootItemFunctionType(new RandomPotion.Serializer()));

  public static final RegistryObject<LootItemConditionType> HAS_HORNS = CONDITIONS.register("has_horns", () -> new LootItemConditionType(new HasHorns.HornSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_COLOR = CONDITIONS.register("is_color", () -> new LootItemConditionType(new IsColor.ColorSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_OBSIDIAN = CONDITIONS.register("is_lava", () -> new LootItemConditionType(new IsObsidian.ObsidianSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_LAVA = CONDITIONS.register("is_obsidian", () -> new LootItemConditionType(new IsLava.LavaSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_MATURE = CONDITIONS.register("is_mature", () -> new LootItemConditionType(new IsMature.MatureSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_ENDER = CONDITIONS.register("is_ender", () -> new LootItemConditionType(new IsEnder.EnderSerializer()));

  public static void register(IEventBus bus) {
    FUNCTIONS.register(bus);
    CONDITIONS.register(bus);
  }

  @SubscribeEvent
  public static void registerRecipes(RegistryEvent.Register<RecipeSerializer<?>> event) {
    CraftingHelper.register(new ResourceLocation(MysticalWorld.MODID, "excluding_ingredient"), ExcludingIngredient.Serializer.INSTANCE);
  }
}
