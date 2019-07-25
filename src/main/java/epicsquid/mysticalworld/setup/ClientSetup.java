package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.blocks.PaintedChestTileEntity;
import epicsquid.mysticalworld.blocks.PaintedChestTileEntityRenderer;
import epicsquid.mysticalworld.entity.BeetleEntity;
import epicsquid.mysticalworld.entity.DeerEntity;
import epicsquid.mysticalworld.entity.FoxEntity;
import epicsquid.mysticalworld.entity.FrogEntity;
import epicsquid.mysticalworld.entity.SproutEntity;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.render.BeetleRenderer;
import epicsquid.mysticalworld.entity.render.DeerRenderer;
import epicsquid.mysticalworld.entity.render.FoxRenderer;
import epicsquid.mysticalworld.entity.render.FrogRenderer;
import epicsquid.mysticalworld.entity.render.SproutRenderer;
import epicsquid.mysticalworld.init.ModContainers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

		ScreenManager.registerFactory(ModContainers.RED_PAINTED_CHEST, ChestScreen::new);
		ClientRegistry.bindTileEntitySpecialRenderer(PaintedChestTileEntity.class, new PaintedChestTileEntityRenderer());
	}
}
