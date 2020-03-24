package epicsquid.mysticalworld.init;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRY;

// TODO: Convert to Registrate

public class ModSounds {
  public static final RegistryObject<SoundEvent> ENDERMINI_DEATH = REGISTRY.registerSoundEvent("mob.endermini.death");
  public static final RegistryObject<SoundEvent> ENDERMINI_HIT = REGISTRY.registerSoundEvent("mob.endermini.hit");
  public static final RegistryObject<SoundEvent> ENDERMINI_IDLE = REGISTRY.registerSoundEvent("mob.endermini.idle");
  public static final RegistryObject<SoundEvent> ENDERMINI_PORTAL = REGISTRY.registerSoundEvent("mob.endermini.portal");
  public static final RegistryObject<SoundEvent> ENDERMINI_SCREAM = REGISTRY.registerSoundEvent("mob.endermini.scream");
  public static final RegistryObject<SoundEvent> ENDERMINI_STARE = REGISTRY.registerSoundEvent("mob.endermini.stare");
  public static final RegistryObject<SoundEvent> SPROUT_AMBIENT = REGISTRY.registerSoundEvent("mob.sprout.ambient");
  public static final RegistryObject<SoundEvent> FOX_AGGRO = REGISTRY.registerSoundEvent("mob.fox.aggro");
  public static final RegistryObject<SoundEvent> FOX_BARK = REGISTRY.registerSoundEvent("mob.fox.bark");
  public static final RegistryObject<SoundEvent> FOX_BITE = REGISTRY.registerSoundEvent("mob.fox.bite");
  public static final RegistryObject<SoundEvent> FOX_DEATH = REGISTRY.registerSoundEvent("mob.fox.death");
  public static final RegistryObject<SoundEvent> FOX_EAT = REGISTRY.registerSoundEvent("mob.fox.eat");
  public static final RegistryObject<SoundEvent> FOX_IDLE = REGISTRY.registerSoundEvent("mob.fox.idle");
  public static final RegistryObject<SoundEvent> FOX_SLEEP = REGISTRY.registerSoundEvent("mob.fox.sleep");
  public static final RegistryObject<SoundEvent> FOX_SNIFF = REGISTRY.registerSoundEvent("mob.fox.sniff");
  public static final RegistryObject<SoundEvent> FOX_SPIT = REGISTRY.registerSoundEvent("mob.fox.spit");

  public static void load () {

  }
}
