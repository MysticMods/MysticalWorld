package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.util.SoundEvent;
// TODO: Convert to Registrate

public class ModSounds {
  public static final RegistryEntry<SoundEvent> ENDERMINI_DEATH = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.death").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_HIT = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.hit").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_IDLE = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.idle").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_PORTAL = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.portal").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_SCREAM = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.scream").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_STARE = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.stare").register();
  public static final RegistryEntry<SoundEvent> SPROUT_AMBIENT = MysticalWorld.REGISTRATE.soundEvent("mob.sprout.ambient").register();
  public static final RegistryEntry<SoundEvent> FOX_AGGRO = MysticalWorld.REGISTRATE.soundEvent("mob.fox.aggro").register();
  public static final RegistryEntry<SoundEvent> FOX_BARK = MysticalWorld.REGISTRATE.soundEvent("mob.fox.bark").register();
  public static final RegistryEntry<SoundEvent> FOX_BITE = MysticalWorld.REGISTRATE.soundEvent("mob.fox.bite").register();
  public static final RegistryEntry<SoundEvent> FOX_DEATH = MysticalWorld.REGISTRATE.soundEvent("mob.fox.death").register();
  public static final RegistryEntry<SoundEvent> FOX_EAT = MysticalWorld.REGISTRATE.soundEvent("mob.fox.eat").register();
  public static final RegistryEntry<SoundEvent> FOX_IDLE = MysticalWorld.REGISTRATE.soundEvent("mob.fox.idle").register();
  public static final RegistryEntry<SoundEvent> FOX_SLEEP = MysticalWorld.REGISTRATE.soundEvent("mob.fox.sleep").register();
  public static final RegistryEntry<SoundEvent> FOX_SNIFF = MysticalWorld.REGISTRATE.soundEvent("mob.fox.sniff").register();
  public static final RegistryEntry<SoundEvent> FOX_SPIT = MysticalWorld.REGISTRATE.soundEvent("mob.fox.spit").register();

  public static void load() {
  }
}
