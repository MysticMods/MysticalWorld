package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import java.util.UUID;

import static mysticmods.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: No Registrate conversion

public class ModModifiers {
  public static final UUID BLESSED_MODIFIER = UUID.fromString("fa1a6f2d-c5c0-4a10-8eb8-44ec9f0765ca");
  public static final UUID SMITE_MODIFIER = UUID.fromString("6dfb72af-627d-47e1-b8b0-4c99ea590f2e");
  public static final UUID SERENDIPITY_MODIFIER = UUID.fromString("3e0b0175-9e90-4c11-b004-9535f399deac");
  public static final UUID CARAPAX_MODIFIER = UUID.fromString("027b3040-1881-4d13-945c-8d6133cccb1e");

  public static final RegistryEntry<Attribute> BLESSED = REGISTRATE.simple("blessed", Attribute.class, () -> new RangedAttribute("mysticalworld.blessed", 0D, 0D, 10D).setSyncable(true));
  public static final RegistryEntry<Attribute> SMITE = REGISTRATE.simple("smite", Attribute.class, () -> new RangedAttribute("mysticalworld.smite", 0D, 0D, 5D).setSyncable(true));
  public static final RegistryEntry<Attribute> SERENDIPITY = REGISTRATE.simple("serendipity", Attribute.class, () -> new RangedAttribute("mysticalworld.serendipity", 0D, 0D, 10D).setSyncable(true));
  public static final RegistryEntry<Attribute> CARAPAX = REGISTRATE.simple("carapax", Attribute.class, () -> new RangedAttribute("mysticalworld.carapax", 0D, 0D, 10D).setSyncable(true));

  public static void load() {

  }
}
