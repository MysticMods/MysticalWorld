package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class MWBlockTagGenerator extends BlockTagsProvider {
  public MWBlockTagGenerator(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
    super(gen, MysticalWorld.MODID, existingFileHelper);
  }

  @Override
  protected void addTags() {
    tag(Tags.Blocks.GRAVEL).add(ModBlocks.UNCANNY_GRAVEL.get());
    tag(Tags.Blocks.SAND).add(ModBlocks.UNCANNY_SAND.get());
  }
}
