package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	@SubscribeEvent
	public static void init(FMLClientSetupEvent event) {
		((IReloadableResourceManager) Minecraft.getInstance().getResourceManager()).addReloadListener(new ModelHolder());
		ModelHolder.init();

		RenderingRegistry.registerEntityRenderingHandler(BeetleEntity.class, new BeetleRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(FoxEntity.class, new FoxRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(FrogEntity.class, new FrogRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(SproutEntity.class, new SproutRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(DeerEntity.class, new DeerRenderer.Factory());
	}
}
