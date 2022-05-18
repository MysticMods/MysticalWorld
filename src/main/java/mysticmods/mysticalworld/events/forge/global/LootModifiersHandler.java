package mysticmods.mysticalworld.events.forge.global;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.loot.modifiers.AddLootTableModifier;
import mysticmods.mysticalworld.loot.modifiers.GrassDropModifier;
import mysticmods.mysticalworld.loot.modifiers.SilkwormEggModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// Information for determining how to do the following derived from ImmersiveEngineering from the file:
// https://github.com/BluSunrize/ImmersiveEngineering/blob/1.15/src/main/java/blusunrize/immersiveengineering/common/util/loot/GrassDrops.java
// While the code is not specifically copied from that, credit for the solution belongs to the Immersive Engineering team.
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LootModifiersHandler {
  @SubscribeEvent
  public static void registerModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
    event.getRegistry().register(new GrassDropModifier.Serializer().setRegistryName(MysticalWorld.MODID, "aubergine_seed_drops"));
    event.getRegistry().register(new SilkwormEggModifier.Serializer().setRegistryName(MysticalWorld.MODID, "silkworm_egg_drops"));
    event.getRegistry().register(new AddLootTableModifier.Serializer().setRegistryName(MysticalWorld.MODID, "add_loot_table"));
  }
}
