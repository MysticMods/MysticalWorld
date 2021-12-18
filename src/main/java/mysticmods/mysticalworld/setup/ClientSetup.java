package mysticmods.mysticalworld.setup;

import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.render.*;
import mysticmods.mysticalworld.init.ModBlocks;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.libs.noobutil.setup.ShadedClientSetup;

@SuppressWarnings("deprecation")
public class ClientSetup {
  public static void init(FMLClientSetupEvent event) {
    ModelHolder.init();
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
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.DUCK.get(), new DuckRenderer.Factory());

    event.enqueueWork(() -> {
      RenderType rendertype = RenderType.cutoutMipped();
      RenderTypeLookup.setRenderLayer(ModBlocks.AUBERGINE_CROP.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.WILD_AUBERGINE.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.THATCH.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.STONEPETAL.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_STONEPETAL.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_UNCANNY_MUSHROOM.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.WILD_WART.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.ANYWHERE_BROWN_MUSHROOM.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.ANYWHERE_RED_MUSHROOM.get(), rendertype);
      RenderTypeLookup.setRenderLayer(ModBlocks.UNCANNY_MUSHROOM.get(), rendertype);

      ShadedClientSetup.init(event);

      // TODO: Fix this
      ItemColors c = Minecraft.getInstance().getItemColors();
      for (RegistryEntry<? extends LazySpawnEggItem<?>> egg : ModEntities.SPAWN_EGGS) {
        c.register((a, layer) -> egg.get().getColor(layer), egg.get());
      }
    });
  }
}
