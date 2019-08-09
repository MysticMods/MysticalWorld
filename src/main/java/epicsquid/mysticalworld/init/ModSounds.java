package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSounds {
	public static void registerSounds(@Nonnull RegistryEvent.Register<SoundEvent> event) {
		Fox.initSounds(event);
		Sprout.initSounds(event);
		Endermini.initSounds(event);
	}

	public static SoundEvent createSoundEvent(ResourceLocation name) {
		SoundEvent result = new SoundEvent(name);
		result.setRegistryName(name);
		return result;
	}

	public static class Sprout {
		public static SoundEvent AMBIENT;

		public static void initSounds(@Nonnull RegistryEvent.Register<SoundEvent> event) {
			event.getRegistry().register(AMBIENT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.sprout.ambient")));
		}
	}

	public static class Fox {
		public static SoundEvent AGGRO, BARK, BITE, DEATH, EAT, IDLE, SLEEP, SNIFF, SPIT;

		public static void initSounds(@Nonnull RegistryEvent.Register<SoundEvent> event) {
			event.getRegistry().register(AGGRO = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.aggro")));
			event.getRegistry().register(BARK = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.bark")));
			event.getRegistry().register(BITE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.bite")));
			event.getRegistry().register(DEATH = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.death")));
			event.getRegistry().register(EAT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.eat")));
			event.getRegistry().register(IDLE = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.idle")));
			event.getRegistry().register(SLEEP = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.sleep")));
			event.getRegistry().register(SNIFF = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.sniff")));
			event.getRegistry().register(SPIT = createSoundEvent(new ResourceLocation(MysticalWorld.MODID, "mob.fox.spit")));
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
