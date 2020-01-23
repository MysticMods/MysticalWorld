package epicsquid.mysticalworld.client.data;

import epicsquid.mysticallib.client.data.DeferredBlockStateProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;

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

    simpleBlock(ModBlocks.WET_MUD_BLOCK, new ModelFile.ExistingModelFile(new ResourceLocation(MysticalWorld.MODID, "block/wet_mud_block"), existingFileHelper));
    simpleBlock(ModBlocks.WET_MUD_BRICK);

    simpleBlock(ModBlocks.TERRACOTTA_BRICK);
    stairsBlock(ModBlocks.TERRACOTTA_BRICK_STAIRS, "terracotta_brick");
    slabBlock(ModBlocks.TERRACOTTA_BRICK_SLAB, ModBlocks.TERRACOTTA_BRICK, "terracotta_brick");
    wallBlock(ModBlocks.TERRACOTTA_BRICK_WALL, "terracotta_brick");
    fenceBlock(ModBlocks.TERRACOTTA_BRICK_FENCE, "terracotta_brick");
    fenceGateBlock(ModBlocks.TERRACOTTA_BRICK_FENCE_GATE, "terracotta_brick");

    simpleBlock(ModBlocks.IRON_BRICK);
    stairsBlock(ModBlocks.IRON_BRICK_STAIRS, "iron_brick");
    slabBlock(ModBlocks.IRON_BRICK_SLAB, ModBlocks.IRON_BRICK, "iron_brick");
    wallBlock(ModBlocks.IRON_BRICK_WALL, "iron_brick");

    simpleBlock(ModBlocks.MUD_BLOCK);
    stairsBlock(ModBlocks.MUD_BLOCK_STAIRS, "mud_block");
    slabBlock(ModBlocks.MUD_BLOCK_SLAB, ModBlocks.MUD_BLOCK, "mud_block");
    wallBlock(ModBlocks.MUD_BLOCK_WALL, "mud_block");
    fenceBlock(ModBlocks.MUD_BLOCK_FENCE, "mud_block");
    fenceGateBlock(ModBlocks.MUD_BLOCK_FENCE_GATE, "mud_block");

    simpleBlock(ModBlocks.MUD_BRICK);
    stairsBlock(ModBlocks.MUD_BRICK_STAIRS, "mud_brick");
    slabBlock(ModBlocks.MUD_BRICK_SLAB, ModBlocks.MUD_BRICK, "mud_brick");
    wallBlock(ModBlocks.MUD_BRICK_WALL, "mud_brick");
    fenceBlock(ModBlocks.MUD_BRICK_FENCE, "mud_brick");
    fenceGateBlock(ModBlocks.MUD_BRICK_FENCE_GATE, "mud_brick");

    simpleBlock(ModBlocks.CHARRED_PLANKS);
    logBlock(ModBlocks.CHARRED_LOG);
    stairsBlock(ModBlocks.CHARRED_STAIRS, "charred_planks");
    slabBlock(ModBlocks.CHARRED_SLAB, ModBlocks.CHARRED_PLANKS, "charred_planks");
    wallBlock(ModBlocks.CHARRED_WALL, "charred_planks");
    fenceBlock(ModBlocks.CHARRED_FENCE, "charred_planks");
    fenceGateBlock(ModBlocks.CHARRED_FENCE_GATE, "charred_planks");
  }
}
