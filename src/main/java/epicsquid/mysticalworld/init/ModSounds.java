package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;

public class ModSounds {
  public static void initSounds(@Nonnull RegisterContentEvent event) {
    Fox.initSounds(event);
    Sprout.initSounds(event);
    Endermini.initSounds(event);
    Silkworm.initSounds(event);
    Frog.initSounds(event);
    LavaCat.initSounds(event);
    General.initSounds(event);
  }

  public static SoundEvent createSoundEvent(ResourceLocation name) {
    SoundEvent result = new SoundEvent(name);
    result.setRegistryName(name);
    return result;
  }

  public static SoundEvent createSoundEvent(String name) {
    ResourceLocation rl = new ResourceLocation(MysticalWorld.MODID, name);
    SoundEvent result = new SoundEvent(rl);
    result.setRegistryName(rl);
    return result;
  }

  public static class General {
    public static SoundEvent SQUID_MILK, UNRIPE_PEARL, PEARLEPORTER;

    public static void initSounds(@Nonnull RegisterContentEvent event) {
      event.addSound(SQUID_MILK = createSoundEvent("mob.squid.milk"));
      event.addSound(UNRIPE_PEARL = createSoundEvent("item.unripe_pearl.use"));
      event.addSound(PEARLEPORTER = createSoundEvent("item.pearleporter.use"));
    }
  }

  public static class LavaCat {
    public static SoundEvent SIZZLE, AMBIENT, DEATH, HURT, PURR, PURREOW;

    public static void initSounds(@Nonnull RegisterContentEvent event) {
      event.addSound(SIZZLE = createSoundEvent("mob.lava_cat.sizzle"));
      event.addSound(AMBIENT = createSoundEvent("mob.lava_cat.ambient"));
      event.addSound(DEATH = createSoundEvent("mob.lava_cat.death"));
      event.addSound(HURT = createSoundEvent("mob.lava_cat.hurt"));
      event.addSound(PURR = createSoundEvent("mob.lava_cat.purr"));
      event.addSound(PURREOW = createSoundEvent("mob.lava_cat.purreow"));
    }
  }

  public static class Frog {
    public static SoundEvent SLIME;

    public static void initSounds(@Nonnull RegisterContentEvent event) {
      event.addSound(SLIME = createSoundEvent("mob.frog.slime"));
    }
  }

  public static class Silkworm {
    public static SoundEvent USE_EGG, PLOP, AMBIENT, DEATH, HURT, STEP, EAT;

    public static void initSounds(@Nonnull RegisterContentEvent event) {
      event.addSound(USE_EGG = createSoundEvent("mob.silkworm.egg.use"));
      event.addSound(PLOP = createSoundEvent("mob.silkworm.plop"));
      event.addSound(AMBIENT = createSoundEvent("mob.silkworm.ambient"));
      event.addSound(DEATH = createSoundEvent("mob.silkworm.death"));
      event.addSound(HURT = createSoundEvent("mob.silkworm.hurt"));
      event.addSound(STEP = createSoundEvent("mob.silkworm.step"));
      event.addSound(EAT = createSoundEvent("mob.silkworm.eat"));
    }
  }

  public static class Sprout {
    public static SoundEvent AMBIENT;

    public static void initSounds(@Nonnull RegisterContentEvent event) {
      event.addSound(AMBIENT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.sprout.ambient")));
    }
  }

  public static class Fox {
    public static SoundEvent AGGRO, BARK, BITE, DEATH, EAT, IDLE, SLEEP, SNIFF, SPIT;

    public static void initSounds(@Nonnull RegisterContentEvent event) {
      event.addSound(AGGRO = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.aggro"))); /**/
      event.addSound(BARK = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.bark")));
      event.addSound(BITE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.bite")));
      event.addSound(DEATH = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.death"))); /**/
      event.addSound(EAT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.eat"))); /**/
      event.addSound(IDLE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.idle")));
      event.addSound(SLEEP = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.sleep"))); /**/
      event.addSound(SNIFF = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.sniff"))); /**/
      event.addSound(SPIT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.spit"))); /**/
    }
  }

  public static class Endermini {
    public static SoundEvent DEATH, HIT, IDLE, PORTAL, SCREAM, STARE;

    public static void initSounds(@Nonnull RegisterContentEvent event) {
      event.addSound(DEATH = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.death")));
      event.addSound(HIT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.hit")));
      event.addSound(IDLE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.idle")));
      event.addSound(PORTAL = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.portal")));
      event.addSound(SCREAM = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.scream")));
      event.addSound(STARE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.stare")));
    }
  }
}
