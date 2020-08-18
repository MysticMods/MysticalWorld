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
    DeferredWorkQueue.runLater(() -> {
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

/*    Map<SkullBlock.ISkullType, GenericHeadModel> skullModels = ObfuscationReflectionHelper.getPrivateValue(SkullTileEntityRenderer.class, null, "field_199358_e");
    Map<SkullBlock.ISkullType, ResourceLocation> skullSkins = ObfuscationReflectionHelper.getPrivateValue(SkullTileEntityRenderer.class, null, "field_199357_d");
    if (skullModels != null) {
      GenericHeadModel genericheadmodel = new GenericHeadModel(0, 0, 64, 64);
      IllagerHeadModel illagerHeadModel = new IllagerHeadModel(0, 64, 64);
      skullModels.put(AdditionalHeads.ZOMBIE_PIGMAN, new GenericHeadwearModel(0, 0, 64, 64));
      skullModels.put(AdditionalHeads.HUSK, genericheadmodel);
      skullModels.put(AdditionalHeads.STRAY, new LayeredHeadModel(new ResourceLocation("minecraft", "textures/entity/skeleton/stray_overlay.png"), 0, 0, 64, 32));
      skullModels.put(AdditionalHeads.DROWNED, new LayeredHeadModel(new ResourceLocation("minecraft", "textures/entity/zombie/drowned_outer_layer.png"), 0, 0, 64, 64));
      //skullModels.put(AdditionalHeads.WITCH, new WitchHeadModel());
      skullModels.put(AdditionalHeads.PILLAGER, illagerHeadModel);
      //skullModels.put(AdditionalHeads.ENDERMAN, new EndermanHeadModel());
      skullModels.put(AdditionalHeads.VILLAGER, new VillagerHeadModel(64, 64, 0));
      skullModels.put(AdditionalHeads.ZOMBIE_VILLAGER, new ZombieVillagerHeadModel());
    } else {
      MysticalWorld.LOG.error("Unable to register additional skull models.");
    }
    if (skullSkins != null) {
      skullSkins.put(AdditionalHeads.ZOMBIE_PIGMAN, new ResourceLocation("minecraft", "textures/entity/zombie_pigman.png"));
      skullSkins.put(AdditionalHeads.HUSK, new ResourceLocation("minecraft", "textures/entity/zombie/husk.png"));
      skullSkins.put(AdditionalHeads.DROWNED, new ResourceLocation("minecraft", "textures/entity/zombie/drowned.png"));
      skullSkins.put(AdditionalHeads.STRAY, new ResourceLocation("minecraft", "textures/entity/skeleton/stray.png"));
      skullSkins.put(AdditionalHeads.WITCH, new ResourceLocation("minecraft",
          "textures/entity/witch.png"));
      skullSkins.put(AdditionalHeads.PILLAGER, new ResourceLocation("minecraft", "textures/entity/illager/pillager.png"));
      skullSkins.put(AdditionalHeads.ENDERMAN, new ResourceLocation("minecraft",
          "textures/entity/enderman/enderman.png"));
      skullSkins.put(AdditionalHeads.VILLAGER, new ResourceLocation("minecraft", "textures/entity/villager/villager.png"));
      skullSkins.put(AdditionalHeads.ZOMBIE_VILLAGER, new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_villager.png"));
    }*/
    });

    // TODO: Cutout, etc, blocks
  }

  public static void registerListeners() {

  }
}
