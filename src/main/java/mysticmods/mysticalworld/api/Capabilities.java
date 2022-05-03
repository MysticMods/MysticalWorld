package mysticmods.mysticalworld.api;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

// TODO: Re-do cap
public class Capabilities {
  public static final ResourceLocation ANIMAL_COOLDOWN_ID = new ResourceLocation(MysticalWorld.MODID, "animal_cooldown");
  public static final ResourceLocation PLAYER_SHOULDER_ID = new ResourceLocation(MysticalWorld.MODID, "player_shoulder");

  public static final Capability<IAnimalCooldownCapability> ANIMAL_COOLDOWN = CapabilityManager.get(new CapabilityToken<>() {
  });
  public static final Capability<IPlayerShoulderCapability> PLAYER_SHOULDER = CapabilityManager.get(new CapabilityToken<>() {
  });

  public static void load() {
  }
}
