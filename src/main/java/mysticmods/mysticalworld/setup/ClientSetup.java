package mysticmods.mysticalworld.setup;

import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.player.layer.ShoulderRenderLayer;
import mysticmods.mysticalworld.client.render.*;
import mysticmods.mysticalworld.init.ModBlocks;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.libs.noobutil.setup.ShadedClientSetup;
import noobanidus.libs.shoulders.client.bootstrap.Bootstrap;

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
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLAM.get(), new ClamRenderer.Factory());

    event.enqueueWork(() -> {
      RenderType rendertype = RenderType.cutoutMipped();
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.AUBERGINE_CROP.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_AUBERGINE.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.THATCH.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.STONEPETAL.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_STONEPETAL.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_UNCANNY_MUSHROOM.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_WART.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANYWHERE_BROWN_MUSHROOM.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANYWHERE_RED_MUSHROOM.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNCANNY_MUSHROOM.get(), rendertype);

      ShadedClientSetup.init(event);
      Bootstrap.init(Minecraft.getInstance());

      Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().values().forEach(o -> o.addLayer(new ShoulderRenderLayer<>(o)));

      // TODO: Fix this
      ItemColors c = Minecraft.getInstance().getItemColors();
      for (RegistryEntry<? extends LazySpawnEggItem<?>> egg : ModEntities.SPAWN_EGGS) {
        c.register((a, layer) -> egg.get().getColor(layer), egg.get());
      }
    });
  }
}
