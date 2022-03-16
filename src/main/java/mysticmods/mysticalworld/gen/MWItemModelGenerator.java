package mysticmods.mysticalworld.gen;

import com.google.common.collect.Sets;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.ModItems;
import mysticmods.mysticalworld.init.deferred.data.BlockData;
import mysticmods.mysticalworld.init.deferred.data.ItemData;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class MWItemModelGenerator extends ItemModelProvider {
  public MWItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, MysticalWorld.MODID, existingFileHelper);
  }

  private final Set<RegistryObject<? extends Block>> doneBlocks = new HashSet<>();
  private final Set<RegistryObject<? extends Item>> doneItems = new HashSet<>();

  protected void genBlock(RegistryObject<? extends Block> block) {
    if (doneBlocks.contains(block)) {
      throw new IllegalStateException("Already generated for " + block.getId());
    }

    doneBlocks.add(block);
  }

  protected void genItem(RegistryObject<? extends Item> item) {
    if (doneItems.contains(item)) {
      throw new IllegalStateException("Already generated language for " + item.getId());
    }

    doneItems.add(item);
  }

  protected void validate() {
    Set<RegistryObject<? extends Block>> missing = new HashSet<>(BlockData.getAllBlocks());
    missing.removeAll(doneBlocks);

    for (RegistryObject<? extends Block> block : missing) {
      MysticalWorld.LOG.error("Missing translation for block: " + block.getId());
    }

    Set<RegistryObject<? extends Item>> missingItems = new HashSet<>(ItemData.getAllItems());
    missingItems.removeAll(doneItems);

    for (RegistryObject<? extends Item> item : missingItems) {
      MysticalWorld.LOG.error("Missing translation for item: " + item.getId());
    }

    if (!missingItems.isEmpty() || !missing.isEmpty()) {
      throw new IllegalStateException("Missing translations for " + (missingItems.size() + missing.size()) + " entries");
    }
  }

  public String modid(ItemLike item) {
    return item.asItem().getRegistryName().getNamespace();
  }

  public String name(ItemLike item) {
    return item.asItem().getRegistryName().getPath();
  }

  public ResourceLocation itemTexture(ItemLike item) {
    return modLoc("item/" + name(item));
  }

  public ItemModelBuilder blockItem(ItemLike block) {
    return blockItem(block, "");
  }

  public ItemModelBuilder blockItem(ItemLike block, String suffix) {
    return withExistingParent(name(block), new ResourceLocation(modid(block), "block/" + name(block) + suffix));
  }

  public ItemModelBuilder blockWithInventoryModel(ItemLike block) {
    return withExistingParent(name(block), new ResourceLocation(modid(block), "block/" + name(block) + "_inventory"));
  }

  public ItemModelBuilder blockSprite(ItemLike block) {
    return blockSprite(block, modLoc("block/" + name(block)));
  }

  public ItemModelBuilder blockSprite(ItemLike block, ResourceLocation texture) {
    return generated(block, texture);
  }

  public ItemModelBuilder generated(ItemLike item) {
    return generated(item, itemTexture(item));
  }

  public ItemModelBuilder generated(ItemLike item, ResourceLocation... layers) {
    ItemModelBuilder ret = getBuilder(name(item)).parent(new ModelFile.UncheckedModelFile("item/generated"));
    for (int i = 0; i < layers.length; i++) {
      ret = ret.texture("layer" + i, layers[i]);
    }
    return ret;
  }

  public ItemModelBuilder handheld(ItemLike item) {
    return handheld(item, itemTexture(item));
  }

  public ItemModelBuilder handheld(ItemLike item, ResourceLocation texture) {
    return withExistingParent(name(item), "item/handheld").texture("layer0", texture);
  }

  private void boneModel(RegistryObject<? extends Block> block) {
    String[] init = block.getId().getPath().split("_");
    withExistingParent(block.getId().getPath(), new ResourceLocation(MysticalWorld.MODID, "block/" + init[0] + "_" + init[1] + init[2]));
  }

  @Override
  protected void registerModels() {
    blockSprite(ModBlocks.STONEPETAL.get());
    genBlock(ModBlocks.STONEPETAL);
    cubeAll(ModBlocks.RED_MUSHROOM_FULL.getId().getPath(), new ResourceLocation("minecraft", "block/red_mushroom_block"));
    genBlock(ModBlocks.RED_MUSHROOM_FULL);
    cubeAll(ModBlocks.BROWN_MUSHROOM_FULL.getId().getPath(), new ResourceLocation("minecraft", "block/brown_mushroom_block"));
    genBlock(ModBlocks.BROWN_MUSHROOM_FULL);
    cubeAll(ModBlocks.STEM_MUSHROOM_FULL.getId().getPath(), new ResourceLocation("minecraft", "block/mushroom_stem"));
    genBlock(ModBlocks.STEM_MUSHROOM_FULL);
    boneModel(ModBlocks.BONE_PILE_1);
    genBlock(ModBlocks.BONE_PILE_1);
    boneModel(ModBlocks.BONE_PILE_2);
    genBlock(ModBlocks.BONE_PILE_2);
    boneModel(ModBlocks.BONE_PILE_3);
    genBlock(ModBlocks.BONE_PILE_3);
    boneModel(ModBlocks.BONE_PILE_4);
    genBlock(ModBlocks.BONE_PILE_4);
    boneModel(ModBlocks.SKELETON_BOTTOM_1);
    genBlock(ModBlocks.SKELETON_BOTTOM_1);
    boneModel(ModBlocks.SKELETON_BOTTOM_2);
    genBlock(ModBlocks.SKELETON_BOTTOM_2);
    boneModel(ModBlocks.SKELETON_BOTTOM_3);
    genBlock(ModBlocks.SKELETON_BOTTOM_3);
    boneModel(ModBlocks.SKELETON_TOP_1);
    genBlock(ModBlocks.SKELETON_TOP_1);
    boneModel(ModBlocks.SKELETON_TOP_2);
    genBlock(ModBlocks.SKELETON_TOP_2);
    boneModel(ModBlocks.SKELETON_TOP_3);
    genBlock(ModBlocks.SKELETON_TOP_3);
    boneModel(ModBlocks.SKELETON_TOP_4);
    genBlock(ModBlocks.SKELETON_TOP_4);

    genBlock(ModBlocks.GALL_APPLE);
    genBlock(ModBlocks.AUBERGINE_CROP);
    genBlock(ModBlocks.WILD_AUBERGINE_CROP);
    genBlock(ModBlocks.WILD_WART);

    for (RegistryObject<? extends Block> block : BlockData.getAllBlocks()) {
      if (doneBlocks.contains(block)) {
        continue;
      }
      blockItem(block.get());
      genBlock(block);
    }

    // Items
    Set<RegistryObject<? extends Item>> handheld = Sets.newHashSet(ModItems.NAUTILUS_HORN, ModItems.PEARLEPORTER, ModItems.SAPPHIRE_AXE, ModItems.SAPPHIRE_HOE, ModItems.SAPPHIRE_PICKAXE, ModItems.SAPPHIRE_SHOVEL, ModItems.SAPPHIRE_SWORD, ModItems.SAPPHIRE_KNIFE, ModItems.COPPER_AXE, ModItems.COPPER_HOE, ModItems.COPPER_PICKAXE, ModItems.COPPER_SHOVEL, ModItems.COPPER_SWORD, ModItems.COPPER_KNIFE, ModItems.CACTUS_AXE, ModItems.CACTUS_HOE, ModItems.CACTUS_PICKAXE, ModItems.CACTUS_SHOVEL, ModItems.CACTUS_SWORD, ModItems.CACTUS_KNIFE, ModItems.LEAD_AXE, ModItems.LEAD_HOE, ModItems.LEAD_PICKAXE, ModItems.LEAD_SHOVEL, ModItems.LEAD_SWORD, ModItems.LEAD_KNIFE, ModItems.TIN_AXE, ModItems.TIN_HOE, ModItems.TIN_PICKAXE, ModItems.TIN_SHOVEL, ModItems.TIN_SWORD, ModItems.TIN_KNIFE, ModItems.SILVER_AXE, ModItems.SILVER_HOE, ModItems.SILVER_PICKAXE, ModItems.SILVER_SHOVEL, ModItems.SILVER_SWORD, ModItems.SILVER_KNIFE, ModItems.ORICHALCUM_AXE, ModItems.ORICHALCUM_HOE, ModItems.ORICHALCUM_PICKAXE, ModItems.ORICHALCUM_SHOVEL, ModItems.ORICHALCUM_SWORD, ModItems.ORICHALCUM_KNIFE, ModItems.STONE_KNIFE, ModItems.WOODEN_KNIFE, ModItems.IRON_KNIFE, ModItems.GOLD_KNIFE, ModItems.DIAMOND_KNIFE, ModItems.NETHERITE_KNIFE);

    for (RegistryObject<? extends Item> item : handheld) {
      handheld(item.get());
      genItem(item);
    }

    withExistingParent(name(ModItems.GLISTERING_HORN.get()), "item/handheld").texture("layer0", itemTexture(ModItems.NAUTILUS_HORN.get())); genItem(ModItems.GLISTERING_HORN);

    for (RegistryObject<? extends Item> item : ItemData.getAllItems()) {
      if (doneItems.contains(item)) {
        continue;
      }

      generated(item.get());
      genItem(item);
    }

    validate();
  }
}
