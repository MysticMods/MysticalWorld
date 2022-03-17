package mysticmods.mysticalworld.init.deferred;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.effects.SimpleEffect;
import mysticmods.mysticalworld.effects.SlowRegenerationEffect;
import mysticmods.mysticalworld.effects.WakefulEffect;
import mysticmods.mysticalworld.potions.SupplierPotion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
  private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MysticalWorld.MODID);
  private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MysticalWorld.MODID);

  public static void register(IEventBus bus) {
    EFFECTS.register(bus);
    POTIONS.register(bus);
  }

  public static final RegistryObject<WakefulEffect> WAKEFUL = EFFECTS.register("wakeful", WakefulEffect::new);
  public static final RegistryObject<SlowRegenerationEffect> SLOW_REGEN = EFFECTS.register("slow_regeneration", SlowRegenerationEffect::new);
  public static final RegistryObject<MobEffect> SERENDIPITY = EFFECTS.register("serendipity", () -> new SimpleEffect(MobEffectCategory.BENEFICIAL, 0x8c348c).addAttributeModifier(ModModifiers.SERENDIPITY.get(), "baf5ced1-927f-46ee-9e02-f4aae6d55316", 1.25, AttributeModifier.Operation.ADDITION));

  public static final RegistryObject<SupplierPotion> SERENDIPITY_POTION = POTIONS.register("serendipity", () -> new SupplierPotion(() -> new MobEffectInstance(SERENDIPITY.get(), 3600)));
}
