package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MWItemModelGenerator extends ItemModelProvider {
  public MWItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, MysticalWorld.MODID, existingFileHelper);
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
    return generated(block::asItem, texture);
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

  @Override
  protected void registerModels() {
    blockItem(ModBlocks.UNCANNY_GRAVEL.get());
    blockItem(ModBlocks.UNCANNY_SAND.get());
  }
}
