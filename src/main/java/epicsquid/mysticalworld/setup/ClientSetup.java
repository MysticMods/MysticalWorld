package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.player.ShoulderRenderer;
import epicsquid.mysticalworld.entity.render.*;
import epicsquid.mysticalworld.events.CapabilityHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    ((IReloadableResourceManager) Minecraft.getInstance().getResourceManager()).addReloadListener(new ModelHolder());
    ModelHolder.init();

    RenderingRegistry.registerEntityRenderingHandler(BeetleEntity.class, new BeetleRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(FoxEntity.class, new FoxRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(FrogEntity.class, new FrogRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(SproutEntity.class, new SproutRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(DeerEntity.class, new DeerRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(EnderminiEntity.class, new EnderminiRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(OwlEntity.class, new OwlRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(LavaCatEntity.class, new LavaCatRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(SilkwormEntity.class, new SilkwormRenderer.Factory());
  }

  public static void registerListeners () {
    MinecraftForge.EVENT_BUS.addListener(ShoulderRenderer::onRenderHand);
    MinecraftForge.EVENT_BUS.addListener(ShoulderRenderer::onRenderPlayerPost);
  }
}
