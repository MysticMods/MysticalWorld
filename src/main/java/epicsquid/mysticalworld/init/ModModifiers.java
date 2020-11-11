package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import java.util.UUID;

// TODO: No Registrate conversion
import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModModifiers {
  public static final UUID BLESSED_MODIFIER = UUID.fromString("fa1a6f2d-c5c0-4a10-8eb8-44ec9f0765ca");
  public static final UUID SMITE_MODIFIER = UUID.fromString("6dfb72af-627d-47e1-b8b0-4c99ea590f2e");
  public static final UUID SERENDIPITY_MODIFIER = UUID.fromString("3e0b0175-9e90-4c11-b004-9535f399deac");

  public static final RegistryEntry<Attribute> BLESSED = REGISTRATE.simple("blessed", Attribute.class, () -> new RangedAttribute("mysticalworld.blessed", 0D, 0D, 10D).setShouldWatch(true));
  public static final RegistryEntry<Attribute> SMITE = REGISTRATE.simple("smite", Attribute.class, () -> new RangedAttribute("mysticalworld.smite", 0D, 0D, 5D).setShouldWatch(true));
  public static final RegistryEntry<Attribute> SERENDIPITY = REGISTRATE.simple("serendipity", Attribute.class, () -> new RangedAttribute("mysticalworld.serendipity", 1D, 1D, 10D).setShouldWatch(true));

  public static void load() {

  }
}
