package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.util.SoundEvent;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;
// TODO: Convert to Registrate

public class ModSounds {
  public static final RegistryEntry<SoundEvent> ENDERMINI_DEATH = REGISTRATE.soundEvent("mob.endermini.death").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_HIT = REGISTRATE.soundEvent("mob.endermini.hit").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_IDLE = REGISTRATE.soundEvent("mob.endermini.idle").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_PORTAL = REGISTRATE.soundEvent("mob.endermini.portal").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_SCREAM = REGISTRATE.soundEvent("mob.endermini.scream").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_STARE = REGISTRATE.soundEvent("mob.endermini.stare").register();
  public static final RegistryEntry<SoundEvent> SPROUT_AMBIENT = REGISTRATE.soundEvent("mob.sprout.ambient").register();
  public static final RegistryEntry<SoundEvent> FOX_AGGRO = REGISTRATE.soundEvent("mob.fox.aggro").register();
  public static final RegistryEntry<SoundEvent> FOX_BARK = REGISTRATE.soundEvent("mob.fox.bark").register();
  public static final RegistryEntry<SoundEvent> FOX_BITE = REGISTRATE.soundEvent("mob.fox.bite").register();
  public static final RegistryEntry<SoundEvent> FOX_DEATH = REGISTRATE.soundEvent("mob.fox.death").register();
  public static final RegistryEntry<SoundEvent> FOX_EAT = REGISTRATE.soundEvent("mob.fox.eat").register();
  public static final RegistryEntry<SoundEvent> FOX_IDLE = REGISTRATE.soundEvent("mob.fox.idle").register();
  public static final RegistryEntry<SoundEvent> FOX_SLEEP = REGISTRATE.soundEvent("mob.fox.sleep").register();
  public static final RegistryEntry<SoundEvent> FOX_SNIFF = REGISTRATE.soundEvent("mob.fox.sniff").register();
  public static final RegistryEntry<SoundEvent> FOX_SPIT = REGISTRATE.soundEvent("mob.fox.spit").register();

  public static void load() {
  }
}
