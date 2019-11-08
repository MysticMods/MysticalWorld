package epicsquid.mysticalworld.client.data;

import epicsquid.mysticallib.client.data.DeferredBlockStateProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class MWBlockstateProvider extends DeferredBlockStateProvider {

  public MWBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super("Mystical World Blockstate and Block Model provider", gen, MysticalWorld.MODID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {
    simpleBlock(ModBlocks.AMETHYST_ORE);
    simpleBlock(ModBlocks.COPPER_ORE);
    simpleBlock(ModBlocks.LEAD_ORE);
    simpleBlock(ModBlocks.QUICKSILVER_ORE);
    simpleBlock(ModBlocks.SILVER_ORE);
    simpleBlock(ModBlocks.TIN_ORE);

    simpleBlock(ModBlocks.AMETHYST_BLOCK);
    simpleBlock(ModBlocks.COPPER_BLOCK);
    simpleBlock(ModBlocks.LEAD_BLOCK);
    simpleBlock(ModBlocks.QUICKSILVER_BLOCK);
    simpleBlock(ModBlocks.SILVER_BLOCK);
    simpleBlock(ModBlocks.TIN_BLOCK);

    simpleBlock(ModBlocks.WET_MUD_BLOCK);
    simpleBlock(ModBlocks.WET_MUD_BRICK);
    simpleBlock(ModBlocks.MUD_BLOCK);
    simpleBlock(ModBlocks.MUD_BRICK);
  }
}
