package mysticmods.mysticalworld.init.deferred;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModModifiers {
  private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MysticalWorld.MODID);

  public static void register(IEventBus bus) {
    ATTRIBUTES.register(bus);
  }

  public static final RegistryObject<Attribute> BLESSED = ATTRIBUTES.register("blessed", () -> new RangedAttribute("mysticalworld.blessed", 0D, 0D, 10D).setSyncable(true));
  public static final RegistryObject<Attribute> SMITE = ATTRIBUTES.register("smite", () -> new RangedAttribute("mysticalworld.smite", 0D, 0D, 5D).setSyncable(true));
  public static final RegistryObject<Attribute> SERENDIPITY = ATTRIBUTES.register("serendipity", () -> new RangedAttribute("mysticalworld.serendipity", 0D, 0D, 10D).setSyncable(true));
  public static final RegistryObject<Attribute> CARAPAX = ATTRIBUTES.register("carapax", () -> new RangedAttribute("mysticalworld.carapax", 0D, 0D, 10D).setSyncable(true));
}
