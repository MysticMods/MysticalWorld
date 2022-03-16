package mysticmods.mysticalworld.gen;

import com.google.common.collect.Sets;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.data.BlockData;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class MWBlockStateGenerator extends BlockStateProvider {
  public MWBlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super(gen, MysticalWorld.MODID, exFileHelper);
  }

  private final Set<RegistryObject<? extends Block>> doneBlocks = new HashSet<>();

  protected void gen(RegistryObject<? extends Block> block) {
    if (doneBlocks.contains(block)) {
      throw new IllegalStateException("Already generated for " + block);
    }

    doneBlocks.add(block);
  }

  protected void validate() {
    Set<RegistryObject<? extends Block>> missing = new HashSet<>(BlockData.getAllBlocks());
    missing.removeAll(doneBlocks);

    for (RegistryObject<? extends Block> block : missing) {
      MysticalWorld.LOG.error("Missing block state for " + block.getId());
    }

    if (!missing.isEmpty()) {
      throw new IllegalStateException("Missing block states for " + missing.size() + " blocks");
    }
  }

  @Override
  protected void registerStatesAndModels() {
    getVariantBuilder(ModBlocks.STONEPETAL.get()).partialState().setModels(new ConfiguredModel(models().cross(ModBlocks.STONEPETAL.getId().getPath(), blockTexture(ModBlocks.STONEPETAL.get()))));
    gen(ModBlocks.STONEPETAL);
    simpleBlock(ModBlocks.POTTED_STONEPETAL.get(), models().withExistingParent(ModBlocks.STONEPETAL.getId().getPath(), "minecraft:block/flower_pot_cross").texture("plant", "mysticalworld:block/stonepetal"));
    gen(ModBlocks.POTTED_STONEPETAL);
    getVariantBuilder(ModBlocks.WILD_AUBERGINE_CROP.get()).partialState().addModels(new ConfiguredModel(models().crop(ModBlocks.WILD_AUBERGINE_CROP.getId().getPath(), blockTexture(ModBlocks.WILD_AUBERGINE_CROP.get()))));
    gen(ModBlocks.WILD_AUBERGINE_CROP);
    getVariantBuilder(ModBlocks.WILD_WART.get()).partialState().addModels(new ConfiguredModel(models().crop(ModBlocks.WILD_WART.getId().getPath(), blockTexture(ModBlocks.WILD_WART.get()))));
    gen(ModBlocks.WILD_WART);
    simpleBlock(ModBlocks.RED_MUSHROOM_FULL.get(), models().cubeAll(ModBlocks.RED_MUSHROOM_FULL.getId().getPath(), new ResourceLocation("minecraft", "block/red_mushroom_block")));
    gen(ModBlocks.RED_MUSHROOM_FULL);
    simpleBlock(ModBlocks.BROWN_MUSHROOM_FULL.get(), models().cubeAll(ModBlocks.BROWN_MUSHROOM_FULL.getId().getPath(), new ResourceLocation("minecraft", "block/brown_mushroom_block")));
    gen(ModBlocks.BROWN_MUSHROOM_FULL);
    simpleBlock(ModBlocks.STEM_MUSHROOM_FULL.get(), models().cubeAll(ModBlocks.STEM_MUSHROOM_FULL.getId().getPath(), new ResourceLocation("minecraft", "block/mushroom_stem")));
    gen(ModBlocks.STEM_MUSHROOM_FULL);
    simpleBlock(ModBlocks.MUSHROOM_INSIDE.get(), models().cubeAll(ModBlocks.MUSHROOM_INSIDE.getId().getPath(), new ResourceLocation("minecraft", "block/mushroom_block_inside")));
    gen(ModBlocks.MUSHROOM_INSIDE);
    simpleBlock(ModBlocks.WET_MUD_BLOCK.get(), models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/wet_mud_block")));
    gen(ModBlocks.WET_MUD_BLOCK);
    simpleBlock(ModBlocks.CHARRED_WOOD.get(), models().cubeAll(ModBlocks.CHARRED_WOOD.getId().getPath(), blockTexture(ModBlocks.CHARRED_LOG.get())));
    gen(ModBlocks.CHARRED_WOOD);
    logBlock(ModBlocks.CHARRED_LOG.get());
    gen(ModBlocks.CHARRED_LOG);
    simpleBlock(ModBlocks.STRIPPED_CHARRED_WOOD.get(), models().cubeAll(ModBlocks.STRIPPED_CHARRED_WOOD.getId().getPath(), blockTexture(ModBlocks.STRIPPED_CHARRED_LOG.get())));
    gen(ModBlocks.STRIPPED_CHARRED_WOOD);
    logBlock(ModBlocks.STRIPPED_CHARRED_LOG.get());
    gen(ModBlocks.STRIPPED_CHARRED_LOG);
    getVariantBuilder(ModBlocks.BONE_PILE_1.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.BONE_PILE_1);
    getVariantBuilder(ModBlocks.BONE_PILE_2.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.BONE_PILE_2);
    getVariantBuilder(ModBlocks.BONE_PILE_3.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.BONE_PILE_3);
    getVariantBuilder(ModBlocks.BONE_PILE_4.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile4"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.BONE_PILE_4);
    getVariantBuilder(ModBlocks.SKELETON_BOTTOM_1.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.SKELETON_BOTTOM_1);
    getVariantBuilder(ModBlocks.SKELETON_BOTTOM_2.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.SKELETON_BOTTOM_2);
    getVariantBuilder(ModBlocks.SKELETON_BOTTOM_3.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.SKELETON_BOTTOM_3);
    getVariantBuilder(ModBlocks.SKELETON_TOP_1.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.SKELETON_TOP_1);
    getVariantBuilder(ModBlocks.SKELETON_TOP_2.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.SKELETON_TOP_2);
    getVariantBuilder(ModBlocks.SKELETON_TOP_3.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.SKELETON_TOP_3);
    getVariantBuilder(ModBlocks.SKELETON_TOP_4.get()).forAllStates((state) -> ConfiguredModel.builder().modelFile(models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top4"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build());
    gen(ModBlocks.SKELETON_TOP_4);

    Set<RegistryObject<? extends Block>> simpleBlocks = Sets.newHashSet(ModBlocks.UNCANNY_GRAVEL, ModBlocks.UNCANNY_SAND, ModBlocks.THATCH, ModBlocks.MUD_BLOCK, ModBlocks.WET_MUD_BRICK, ModBlocks.MUD_BRICK, ModBlocks.CHARRED_PLANKS, ModBlocks.TERRACOTTA_BRICK, ModBlocks.IRON_BRICK, ModBlocks.SOFT_STONE, ModBlocks.BLACKENED_STONE, ModBlocks.SOFT_OBSIDIAN, ModBlocks.ORICHALCUM_BLOCK, ModBlocks.PEARL_BLOCK, ModBlocks.GRANITE_QUARTZ_ORE, ModBlocks.SAPPHIRE_ORE, ModBlocks.SAPPHIRE_BLOCK, ModBlocks.LEAD_ORE, ModBlocks.LEAD_BLOCK, ModBlocks.SILVER_ORE, ModBlocks.SILVER_BLOCK, ModBlocks.TIN_ORE, ModBlocks.TIN_BLOCK);

    // ignore these, pre-existing
    gen(ModBlocks.GALL_APPLE);
    gen(ModBlocks.AUBERGINE_CROP);

    for (RegistryObject<? extends Block> block : simpleBlocks){
      simpleBlock(block.get());
      gen(block);
    }

    validate();
  }
}
