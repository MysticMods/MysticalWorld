package mysticmods.mysticalworld.events.mod;

import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.client.model.BeetleModel;
import mysticmods.mysticalworld.client.model.ClamModel;
import mysticmods.mysticalworld.client.model.DeerModel;
import mysticmods.mysticalworld.client.model.DuckModel;
import mysticmods.mysticalworld.client.model.EnderminiModel;
import mysticmods.mysticalworld.client.model.FrogModel;
import mysticmods.mysticalworld.client.model.HellSproutModel;
import mysticmods.mysticalworld.client.model.LavaCatModel;
import mysticmods.mysticalworld.client.model.ModelHolder;
import mysticmods.mysticalworld.client.model.OwlModel;
import mysticmods.mysticalworld.client.model.SilkwormModel;
import mysticmods.mysticalworld.client.model.SilverFoxModel;
import mysticmods.mysticalworld.client.model.SproutModel;
import mysticmods.mysticalworld.client.render.BeetleRenderer;
import mysticmods.mysticalworld.client.render.ClamRenderer;
import mysticmods.mysticalworld.client.render.DeerRenderer;
import mysticmods.mysticalworld.client.render.DuckRenderer;
import mysticmods.mysticalworld.client.render.EnderminiRenderer;
import mysticmods.mysticalworld.client.render.FrogRenderer;
import mysticmods.mysticalworld.client.render.HellSproutRenderer;
import mysticmods.mysticalworld.client.render.LavaCatRenderer;
import mysticmods.mysticalworld.client.render.OwlRenderer;
import mysticmods.mysticalworld.client.render.SilkwormRenderer;
import mysticmods.mysticalworld.client.render.SilverFoxRenderer;
import mysticmods.mysticalworld.client.render.SproutRenderer;
import mysticmods.mysticalworld.init.ModBlocks;
import mysticmods.mysticalworld.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import noobanidus.libs.noobutil.setup.ShadedClientSetup;

@Mod.EventBusSubscriber(modid= MysticalWorld.MODID, bus= Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public class ClientSetup {
  @SubscribeEvent
  public static void init(FMLClientSetupEvent event) {
    ModelHolder.init();
/*    RenderingRegistry.registerEntityRenderingHandler(ModEntities.FROG.get(), new FrogRenderer.Factory());
    RenderingRegistry.registerEntityRenderingHandler(ModEntities.OWL.get(), new OwlRenderer.Factory());*/

    event.enqueueWork(() -> {
      RenderType rendertype = RenderType.cutoutMipped();
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.AUBERGINE_CROP.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_AUBERGINE.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.THATCH.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.STONEPETAL.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_STONEPETAL.get(), rendertype);
/*      ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_UNCANNY_MUSHROOM.get(), rendertype);*/
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_WART.get(), rendertype);
/*      ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANYWHERE_BROWN_MUSHROOM.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANYWHERE_RED_MUSHROOM.get(), rendertype);
      ItemBlockRenderTypes.setRenderLayer(ModBlocks.UNCANNY_MUSHROOM.get(), rendertype);*/

      ShadedClientSetup.init(event);
      /*      Bootstrap.init(Minecraft.getInstance());*/

      /*      Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().values().forEach(o -> o.addLayer(new ShoulderRenderLayer<>(o)));*/

      // TODO: Fix this
      ItemColors c = Minecraft.getInstance().getItemColors();
      for (RegistryEntry<? extends LazySpawnEggItem<?>> egg : ModEntities.SPAWN_EGGS) {
        c.register((a, layer) -> egg.get().getColor(layer), egg.get());
      }
    });
  }

  @SubscribeEvent
  public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(ModEntities.BEETLE.get(), BeetleRenderer::new);
    event.registerEntityRenderer(ModEntities.CLAM.get(), ClamRenderer::new);
    event.registerEntityRenderer(ModEntities.DEER.get(), DeerRenderer::new);
    event.registerEntityRenderer(ModEntities.DUCK.get(), DuckRenderer::new);
    event.registerEntityRenderer(ModEntities.ENDERMINI.get(), EnderminiRenderer::new);
    event.registerEntityRenderer(ModEntities.FROG.get(), FrogRenderer::new);
    event.registerEntityRenderer(ModEntities.HELL_SPROUT.get(), HellSproutRenderer::new);
    event.registerEntityRenderer(ModEntities.LAVA_CAT.get(), LavaCatRenderer::new);
    event.registerEntityRenderer(ModEntities.OWL.get(), OwlRenderer::new);
    event.registerEntityRenderer(ModEntities.SILKWORM.get(), SilkwormRenderer::new);
    event.registerEntityRenderer(ModEntities.SILVER_FOX.get(), SilverFoxRenderer::new);
    event.registerEntityRenderer(ModEntities.SPROUT.get(), SproutRenderer::new);
  }

  @SubscribeEvent
  public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(ModelHolder.BEETLE, BeetleModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.CLAM, ClamModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.DEER, DeerModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.DUCK, DuckModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.ENDERMINI, EnderminiModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.FROG, FrogModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.HELL_SPROUT, HellSproutModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.LAVA_CAT, LavaCatModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.OWL, OwlModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.SILKWORM, SilkwormModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.SILVER_FOX, SilverFoxModel::createBodyLayer);
    event.registerLayerDefinition(ModelHolder.SPROUT, SproutModel::createBodyLayer);
  }
}
