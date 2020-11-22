package epicsquid.mysticalworld.setup;

import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.entity.render.*;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.libs.noobutil.setup.ShadedClientSetup;

@SuppressWarnings("deprecation")
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    event.enqueueWork(() -> {
      RenderType rendertype = RenderType.getCutoutMipped();
      RenderTypeLookup.setRenderLayer(ModBlocks.AUBERGINE_CROP.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.THATCH.get(), rendertype);

      ShadedClientSetup.init(event);
      ((IReloadableResourceManager) Minecraft.getInstance().getResourceManager()).addReloadListener(new ModelHolder());
      ModelHolder.init();

      // TODO: Fix this
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.BEETLE.get(), new BeetleRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.SILVER_FOX.get(), new FoxRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.FROG.get(), new FrogRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPROUT.get(), new SproutRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.DEER.get(), new DeerRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENDERMINI.get(), new EnderminiRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.OWL.get(), new OwlRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.LAVA_CAT.get(), new LavaCatRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.SILKWORM.get(), new SilkwormRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.HELL_SPROUT.get(), new HellSproutRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPIRIT_DEER.get(), new SpiritDeerRenderer.Factory());
      RenderingRegistry.registerEntityRenderingHandler(ModEntities.SPIRIT_BEETLE.get(), new SpiritBeetleRenderer.Factory());

      ItemColors c = Minecraft.getInstance().getItemColors();
      for (RegistryEntry<? extends LazySpawnEggItem<?>> egg : ModEntities.SPAWN_EGGS) {
        c.register((a, layer) -> egg.get().getColor(layer), egg.get());
      }

      SpawnEggItem.EGGS.remove(null);
      ModEntities.SPAWN_EGGS.forEach(o -> SpawnEggItem.EGGS.put(o.get().getType(null), o.get()));
    });

    // TODO: Cutout, etc, blocks
  }

  public static void registerListeners() {

  }
}
