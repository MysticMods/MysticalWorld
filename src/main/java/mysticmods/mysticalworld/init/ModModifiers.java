package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static mysticmods.mysticalworld.MysticalWorld.REGISTRATE;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModModifiers {
  public static final RegistryEntry<Attribute> BLESSED = REGISTRATE.simple("blessed", Registry.ATTRIBUTE_REGISTRY, () -> new RangedAttribute("mysticalworld.blessed", 0D, 0D, 10D).setSyncable(true));
  public static final RegistryEntry<Attribute> SMITE = REGISTRATE.simple("smite", Registry.ATTRIBUTE_REGISTRY, () -> new RangedAttribute("mysticalworld.smite", 0D, 0D, 5D).setSyncable(true));
  public static final RegistryEntry<Attribute> SERENDIPITY = REGISTRATE.simple("serendipity", Registry.ATTRIBUTE_REGISTRY, () -> new RangedAttribute("mysticalworld.serendipity", 0D, 0D, 10D).setSyncable(true));
  public static final RegistryEntry<Attribute> CARAPAX = REGISTRATE.simple("carapax", Registry.ATTRIBUTE_REGISTRY, () -> new RangedAttribute("mysticalworld.carapax", 0D, 0D, 10D).setSyncable(true));

  public static void load() {
  }

  @SubscribeEvent
  public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
    event.add(EntityType.PLAYER, BLESSED.get());
    event.add(EntityType.PLAYER, SMITE.get());
    event.add(EntityType.PLAYER, SERENDIPITY.get());
  }
}
