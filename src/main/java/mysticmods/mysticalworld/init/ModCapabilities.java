package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.capability.IAnimalCooldownCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= MysticalWorld.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
  public static final ResourceLocation ANIMAL_COOLDOWN_ID = new ResourceLocation(MysticalWorld.MODID, "animal_cooldown");

  public static final Capability<IAnimalCooldownCapability> ANIMAL_COOLDOWN = CapabilityManager.get(new CapabilityToken<>() {
  });

  @SubscribeEvent
  public static void onRegisterCapabilities (RegisterCapabilitiesEvent event) {
    event.register(IAnimalCooldownCapability.class);
  }
}
