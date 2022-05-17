package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.loot.conditions.*;
import mysticmods.mysticalworld.loot.functions.RandomPotionFunction;
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

  public static final RegistryObject<LootItemFunctionType> RANDOM_POTION = FUNCTIONS.register("random_potion", () -> new LootItemFunctionType(new RandomPotionFunction.Serializer()));

  public static final RegistryObject<LootItemConditionType> HAS_HORNS = CONDITIONS.register("has_horns", () -> new LootItemConditionType(new HasHornsCondition.HornSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_OBSIDIAN = CONDITIONS.register("is_lava", () -> new LootItemConditionType(new IsObsidianCondition.ObsidianSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_LAVA = CONDITIONS.register("is_obsidian", () -> new LootItemConditionType(new IsLavaCondition.LavaSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_MATURE = CONDITIONS.register("is_mature", () -> new LootItemConditionType(new IsMatureCondition.MatureSerializer()));
  public static final RegistryObject<LootItemConditionType> IS_ENDER = CONDITIONS.register("is_ender", () -> new LootItemConditionType(new IsEnderCondition.Serializer()));
  public static final RegistryObject<LootItemConditionType> BLOCK_TAG = CONDITIONS.register("block_tag", () -> new LootItemConditionType(new BlockTagCondition.Serializer()));

  public static void register(IEventBus bus) {
    FUNCTIONS.register(bus);
    CONDITIONS.register(bus);
  }

  @SubscribeEvent
  public static void registerRecipes(RegistryEvent.Register<RecipeSerializer<?>> event) {
    CraftingHelper.register(new ResourceLocation(MysticalWorld.MODID, "excluding_ingredient"), ExcludingIngredient.Serializer.INSTANCE);
  }
}
