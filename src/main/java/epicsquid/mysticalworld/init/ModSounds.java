package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;

public class ModSounds {
  public static void initSounds (@Nonnull RegisterContentEvent event) {
    Fox.initSounds(event);
  }

  public static SoundEvent createSoundEvent (ResourceLocation name) {
    SoundEvent result = new SoundEvent(name);
    result.setRegistryName(name);
    return result;
  }

  public static class Fox {
    public static SoundEvent AGGRO, BARK, BITE, DEATH, EAT, IDLE, SLEEP, SNIFF, SPIT;

    public static void initSounds (@Nonnull RegisterContentEvent event) {
      event.addSound(AGGRO = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.aggro"))); /**/
      event.addSound(BARK = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.bark")));
      event.addSound(BITE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.bite")));
      event.addSound(DEATH = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.death"))); /**/
      event.addSound(EAT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.eat"))); /**/
      event.addSound(IDLE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.idle")));
      event.addSound(SLEEP = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.sleep"))); /**/
      event.addSound(SNIFF = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.sniff"))); /**/
      event.addSound(SPIT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.spit"))); /**/

      /*     public static final SoundEvent ENTITY_FOX_AGGRO = register("entity.fox.aggro"); = AGGRO
    public static final SoundEvent ENTITY_FOX_AMBIENT = register("entity.fox.ambient");
    public static final SoundEvent ENTITY_FOX_BITE = register("entity.fox.bite"); = BITE
    public static final SoundEvent ENTITY_FOX_DEATH = register("entity.fox.death"); = DEATH
    public static final SoundEvent ENTITY_FOX_EAT = register("entity.fox.eat"); = EAT
    public static final SoundEvent ENTITY_FOX_HURT = register("entity.fox.hurt");
    public static final SoundEvent ENTITY_FOX_SCREECH = register("entity.fox.screech");
    public static final SoundEvent ENTITY_FOX_SLEEP = register("entity.fox.sleep"); = SLEEP
    public static final SoundEvent ENTITY_FOX_SNIFF = register("entity.fox.sniff"); = SNIFF
    public static final SoundEvent ENTITY_FOX_SPIT = register("entity.fox.spit") = SPIT */
    }
  }
}
