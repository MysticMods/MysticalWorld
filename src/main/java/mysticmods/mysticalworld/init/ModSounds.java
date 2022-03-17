package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
  private static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MysticalWorld.MODID);

  public static void register(IEventBus bus) {
    SOUND_EVENT.register(bus);
  }

  // ENDERMINI
  public static final RegistryObject<SoundEvent> ENDERMINI_DEATH = SOUND_EVENT.register("mobs.endermini.death", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.endermini.death")));
  public static final RegistryObject<SoundEvent> ENDERMINI_HIT = SOUND_EVENT.register("mobs.endermini.hit", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.endermini.hit")));
  public static final RegistryObject<SoundEvent> ENDERMINI_IDLE = SOUND_EVENT.register("mobs.endermini.idle", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.endermini.idle")));
  public static final RegistryObject<SoundEvent> ENDERMINI_PORTAL = SOUND_EVENT.register("mobs.endermini.portal", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.endermini.portal")));
  public static final RegistryObject<SoundEvent> ENDERMINI_SCREAM = SOUND_EVENT.register("mobs.endermini.scream", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.endermini.scream")));
  public static final RegistryObject<SoundEvent> ENDERMINI_STARE = SOUND_EVENT.register("mobs.endermini.stare", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.endermini.stare")));

  // Sprout
  public static final RegistryObject<SoundEvent> SPROUT_AMBIENT = SOUND_EVENT.register("mobs.sprout.ambient", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.sprout.ambient")));

  // Fox
  public static final RegistryObject<SoundEvent> FOX_AGGRO = SOUND_EVENT.register("mobs.fox.aggro", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.aggro")));
  public static final RegistryObject<SoundEvent> FOX_BARK = SOUND_EVENT.register("mobs.fox.bark", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.bark")));
  public static final RegistryObject<SoundEvent> FOX_BITE = SOUND_EVENT.register("mobs.fox.bite", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.bite")));
  public static final RegistryObject<SoundEvent> FOX_DEATH = SOUND_EVENT.register("mobs.fox.death", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.death")));
  public static final RegistryObject<SoundEvent> FOX_EAT = SOUND_EVENT.register("mobs.fox.eat", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.eat")));
  public static final RegistryObject<SoundEvent> FOX_IDLE = SOUND_EVENT.register("mobs.fox.idle", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.idle")));
  public static final RegistryObject<SoundEvent> FOX_SLEEP = SOUND_EVENT.register("mobs.fox.sleep", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.sleep")));
  public static final RegistryObject<SoundEvent> FOX_SNIFF = SOUND_EVENT.register("mobs.fox.sniff", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.sniff")));
  public static final RegistryObject<SoundEvent> FOX_SPIT = SOUND_EVENT.register("mobs.fox.spit", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.fox.spit")));

  // Silkworm
  public static final RegistryObject<SoundEvent> SILKWORM_EGG_USE = SOUND_EVENT.register("mobs.silkworm.egg_use", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.silkworm.egg_use")));
  public static final RegistryObject<SoundEvent> SILKWORM_PLOP = SOUND_EVENT.register("mobs.silkworm.plop", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.silkworm.plop")));
  public static final RegistryObject<SoundEvent> SILKWORM_AMBIENT = SOUND_EVENT.register("mobs.silkworm.ambient", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.silkworm.ambient")));
  public static final RegistryObject<SoundEvent> SILKWORM_DEATH = SOUND_EVENT.register("mobs.silkworm.death", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.silkworm.death")));
  public static final RegistryObject<SoundEvent> SILKWORM_HURT = SOUND_EVENT.register("mobs.silkworm.hurt", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.silkworm.hurt")));
  public static final RegistryObject<SoundEvent> SILKWORM_STEP = SOUND_EVENT.register("mobs.silkworm.step", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.silkworm.step")));
  public static final RegistryObject<SoundEvent> SILKWORM_EAT = SOUND_EVENT.register("mobs.silkworm.eat", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.silkworm.eat")));

  // Lava cat
  public static final RegistryObject<SoundEvent> LAVA_CAT_SIZZLE = SOUND_EVENT.register("mobs.lava_cat.sizzle", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.lava_cat.sizzle")));
  public static final RegistryObject<SoundEvent> LAVA_CAT_AMBIENT = SOUND_EVENT.register("mobs.lava_cat.ambient", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.lava_cat.ambient")));
  public static final RegistryObject<SoundEvent> LAVA_CAT_DEATH = SOUND_EVENT.register("mobs.lava_cat.death", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.lava_cat.death")));
  public static final RegistryObject<SoundEvent> LAVA_CAT_HURT = SOUND_EVENT.register("mobs.lava_cat.hurt", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.lava_cat.hurt")));
  public static final RegistryObject<SoundEvent> LAVA_CAT_PURR = SOUND_EVENT.register("mobs.lava_cat.purr", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.lava_cat.purr")));
  public static final RegistryObject<SoundEvent> LAVA_CAT_PURREOW = SOUND_EVENT.register("mobs.lava_cat.purreow", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.lava_cat.purreow")));

  // Frog
  public static final RegistryObject<SoundEvent> FROG_SLIME = SOUND_EVENT.register("mobs.frog.slime", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.frog.slime")));
  public static final RegistryObject<SoundEvent> FROG_AMBIENT = SOUND_EVENT.register("mobs.frog.ambient", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.frog.ambient")));

  // Squid
  public static final RegistryObject<SoundEvent> SQUID_MILK = SOUND_EVENT.register("mobs.squid.milk", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.squid.milk")));

  // Unrip pearl
  public static final RegistryObject<SoundEvent> UNRIPE_PEARL_USE = SOUND_EVENT.register("mobs.unripe_pearl.use", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.unripe_pearl.use")));
  public static final RegistryObject<SoundEvent> PEARLEPORTER_USE = SOUND_EVENT.register("mobs.pearl_porter.use", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.pearl_porter.use")));

  // Duck
  public static final RegistryObject<SoundEvent> DUCK_AMBIENT = SOUND_EVENT.register("mobs.duck.ambient", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.duck.ambient")));
  public static final RegistryObject<SoundEvent> DUCK_SWIM = SOUND_EVENT.register("mobs.duck.swim", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.duck.swim")));

  // Deer
  public static final RegistryObject<SoundEvent> DEER_AMBIENT = SOUND_EVENT.register("mobs.deer.ambient", () -> new SoundEvent(new ResourceLocation(MysticalWorld.MODID, "mobs.deer.ambient")));
}
