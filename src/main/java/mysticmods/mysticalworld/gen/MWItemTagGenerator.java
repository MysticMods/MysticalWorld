package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.data.BlockData;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class MWItemTagGenerator extends ItemTagsProvider {
  public MWItemTagGenerator(DataGenerator gen, BlockTagsProvider blockTags, @Nullable ExistingFileHelper existingFileHelper) {
    super(gen, blockTags, MysticalWorld.MODID, existingFileHelper);
  }

  @Override
  protected void addTags() {
    tag(ItemTags.SAND).add(BlockData.item(ModBlocks.UNCANNY_GRAVEL));
    tag(Tags.Items.GRAVEL).add(BlockData.item(ModBlocks.UNCANNY_SAND));
  }
}
