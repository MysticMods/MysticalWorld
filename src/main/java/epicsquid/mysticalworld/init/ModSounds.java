package epicsquid.mysticalworld.init;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRY;

public class ModSounds {
  public static class Sprout {
    public static RegistryObject<SoundEvent> AMBIENT = REGISTRY.registerSoundEvent("mob.sprout.ambient");
  }

  public static class Fox {
    public static RegistryObject<SoundEvent> AGGRO = REGISTRY.registerSoundEvent("mob.fox.aggro");
    public static RegistryObject<SoundEvent> BARK = REGISTRY.registerSoundEvent("mob.fox.bark");
    public static RegistryObject<SoundEvent> BITE = REGISTRY.registerSoundEvent("mob.fox.bite");
    public static RegistryObject<SoundEvent> DEATH = REGISTRY.registerSoundEvent("mob.fox.death");
    public static RegistryObject<SoundEvent> EAT = REGISTRY.registerSoundEvent("mob.fox.eat");
    public static RegistryObject<SoundEvent> IDLE = REGISTRY.registerSoundEvent("mob.fox.idle");
    public static RegistryObject<SoundEvent> SLEEP = REGISTRY.registerSoundEvent("mob.fox.sleep");
    public static RegistryObject<SoundEvent> SNIFF = REGISTRY.registerSoundEvent("mob.fox.sniff");
    public static RegistryObject<SoundEvent> SPIT = REGISTRY.registerSoundEvent("mob.fox.spit");
  }

  public static class Endermini {
    public static RegistryObject<SoundEvent> DEATH = REGISTRY.registerSoundEvent("mob.endermini.death");
    public static RegistryObject<SoundEvent> HIT = REGISTRY.registerSoundEvent("mob.endermini.hit");
    public static RegistryObject<SoundEvent> IDLE = REGISTRY.registerSoundEvent("mob.endermini.idle");
    public static RegistryObject<SoundEvent> PORTAL = REGISTRY.registerSoundEvent("mob.endermini.portal");
    public static RegistryObject<SoundEvent> SCREAM = REGISTRY.registerSoundEvent("mob.endermini.scream");
    public static RegistryObject<SoundEvent> STARE = REGISTRY.registerSoundEvent("mob.endermini.stare");
  }
}
