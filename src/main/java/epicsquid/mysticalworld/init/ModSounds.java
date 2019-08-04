package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;

public class ModSounds {
  public static void initSounds (@Nonnull RegisterContentEvent event) {
    Fox.initSounds(event);
    Sprout.initSounds(event);
    Endermini.initSounds(event);
  }

  public static SoundEvent createSoundEvent (ResourceLocation name) {
    SoundEvent result = new SoundEvent(name);
    result.setRegistryName(name);
    return result;
  }

  public static class Sprout {
    public static SoundEvent AMBIENT;

    public static void initSounds (@Nonnull RegisterContentEvent event) {
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

    public static void initSounds (@Nonnull RegisterContentEvent event) {
      event.addSound(DEATH = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.death")));
      event.addSound(HIT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.hit")));
      event.addSound(IDLE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.idle")));
      event.addSound(PORTAL = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.portal")));
      event.addSound(SCREAM = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.scream")));
      event.addSound(STARE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.endermini.stare")));
    }
  }
}
