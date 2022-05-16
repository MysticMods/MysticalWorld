package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
  // Endermini
  public static final RegistryEntry<SoundEvent> ENDERMINI_DEATH = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.death").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_HIT = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.hit").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_IDLE = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.idle").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_PORTAL = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.portal").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_SCREAM = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.scream").register();
  public static final RegistryEntry<SoundEvent> ENDERMINI_STARE = MysticalWorld.REGISTRATE.soundEvent("mob.endermini.stare").register();

  // Sprout
  public static final RegistryEntry<SoundEvent> SPROUT_AMBIENT = MysticalWorld.REGISTRATE.soundEvent("mob.sprout.ambient").register();

  // Fennec
  public static final RegistryEntry<SoundEvent> FENNEC_AGGRO = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.aggro").register();
  public static final RegistryEntry<SoundEvent> FENNEC_BARK = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.bark").register();
  public static final RegistryEntry<SoundEvent> FENNEC_BITE = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.bite").register();
  public static final RegistryEntry<SoundEvent> FENNEC_DEATH = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.death").register();
  public static final RegistryEntry<SoundEvent> FENNEC_EAT = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.eat").register();
  public static final RegistryEntry<SoundEvent> FENNEC_IDLE = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.idle").register();
  public static final RegistryEntry<SoundEvent> FENNEC_SLEEP = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.sleep").register();
  public static final RegistryEntry<SoundEvent> FENNEC_SNIFF = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.sniff").register();
  public static final RegistryEntry<SoundEvent> FENNEC_SPIT = MysticalWorld.REGISTRATE.soundEvent("mob.fennec.spit").register();

  // Silkworm
  public static final RegistryEntry<SoundEvent> SILKWORM_EGG_USE = MysticalWorld.REGISTRATE.soundEvent("mob.silkworm.egg.use").register();
  public static final RegistryEntry<SoundEvent> SILKWORM_PLOP = MysticalWorld.REGISTRATE.soundEvent("mob.silkworm.plop").register();
  public static final RegistryEntry<SoundEvent> SILKWORM_AMBIENT = MysticalWorld.REGISTRATE.soundEvent("mob.silkworm.ambient").register();
  public static final RegistryEntry<SoundEvent> SILKWORM_DEATH = MysticalWorld.REGISTRATE.soundEvent("mob.silkworm.death").register();
  public static final RegistryEntry<SoundEvent> SILKWORM_HURT = MysticalWorld.REGISTRATE.soundEvent("mob.silkworm.hurt").register();
  public static final RegistryEntry<SoundEvent> SILKWORM_STEP = MysticalWorld.REGISTRATE.soundEvent("mob.silkworm.step").register();
  public static final RegistryEntry<SoundEvent> SILKWORM_EAT = MysticalWorld.REGISTRATE.soundEvent("mob.silkworm.eat").register();

  // Lava cat
  public static final RegistryEntry<SoundEvent> LAVA_CAT_SIZZLE = MysticalWorld.REGISTRATE.soundEvent("mob.lava_cat.sizzle").register();
  public static final RegistryEntry<SoundEvent> LAVA_CAT_AMBIENT = MysticalWorld.REGISTRATE.soundEvent("mob.lava_cat.ambient").register();
  public static final RegistryEntry<SoundEvent> LAVA_CAT_DEATH = MysticalWorld.REGISTRATE.soundEvent("mob.lava_cat.death").register();
  public static final RegistryEntry<SoundEvent> LAVA_CAT_HURT = MysticalWorld.REGISTRATE.soundEvent("mob.lava_cat.hurt").register();
  public static final RegistryEntry<SoundEvent> LAVA_CAT_PURR = MysticalWorld.REGISTRATE.soundEvent("mob.lava_cat.purr").register();
  public static final RegistryEntry<SoundEvent> LAVA_CAT_PURREOW = MysticalWorld.REGISTRATE.soundEvent("mob.lava_cat.purreow").register();

  // Frog
  public static final RegistryEntry<SoundEvent> FROG_SLIME = MysticalWorld.REGISTRATE.soundEvent("mob.frog.slime").register();
  public static final RegistryEntry<SoundEvent> FROG_AMBIENT = MysticalWorld.REGISTRATE.soundEvent("mob.frog.croak").register();

  // Squid
  public static final RegistryEntry<SoundEvent> SQUID_MILK = MysticalWorld.REGISTRATE.soundEvent("mob.squid.milk").register();

  // Unrip pearl
  public static final RegistryEntry<SoundEvent> UNRIPE_PEARL_USE = MysticalWorld.REGISTRATE.soundEvent("item.unripe_pearl.use").register();
  public static final RegistryEntry<SoundEvent> PEARLEPORTER_USE = MysticalWorld.REGISTRATE.soundEvent("item.pearleporter.use").register();

  // Duck
  public static final RegistryEntry<SoundEvent> DUCK_AMBIENT = MysticalWorld.REGISTRATE.soundEvent("mob.duck.quack").register();
  public static final RegistryEntry<SoundEvent> DUCK_SWIM = MysticalWorld.REGISTRATE.soundEvent("mob.duck.swim").register();

  // Deer
  public static final RegistryEntry<SoundEvent> DEER_AMBIENT = MysticalWorld.REGISTRATE.soundEvent("mob.deer.ambient").register();

  public static void load() {
  }
}
