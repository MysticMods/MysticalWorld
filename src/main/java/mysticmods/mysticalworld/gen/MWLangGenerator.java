package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.data.BlockData;
import mysticmods.mysticalworld.init.deferred.data.ItemData;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class MWLangGenerator extends LanguageProvider {
  public MWLangGenerator(DataGenerator gen) {
    super(gen, MysticalWorld.MODID, "en_us");
  }

  private final Set<RegistryObject<? extends Block>> doneBlocks = new HashSet<>();
  private final Set<RegistryObject<? extends Item>> doneItems = new HashSet<>();

  protected void genBlock(RegistryObject<? extends Block> block) {
    if (doneBlocks.contains(block)) {
      throw new IllegalStateException("Already generated for " + block.getId());
    }

    doneBlocks.add(block);
  }

  protected void genItem (RegistryObject<? extends Item> item) {
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

  @Override
  protected void addTranslations() {
    add(ModBlocks.BONE_PILE_1.get(), "Bone Pile"); genBlock(ModBlocks.BONE_PILE_1);
    add(ModBlocks.BONE_PILE_2.get(), "Bone Pile"); genBlock(ModBlocks.BONE_PILE_2);
    add(ModBlocks.BONE_PILE_3.get(), "Bone Pile"); genBlock(ModBlocks.BONE_PILE_3);
    add(ModBlocks.BONE_PILE_4.get(), "Bone Pile"); genBlock(ModBlocks.BONE_PILE_4);
    add(ModBlocks.SKELETON_BOTTOM_1.get(), "Skeletal Remains"); genBlock(ModBlocks.SKELETON_BOTTOM_1);
    add(ModBlocks.SKELETON_BOTTOM_2.get(), "Skeletal Remains"); genBlock(ModBlocks.SKELETON_BOTTOM_2);
    add(ModBlocks.SKELETON_BOTTOM_3.get(), "Skeletal Remains"); genBlock(ModBlocks.SKELETON_BOTTOM_3);
    add(ModBlocks.SKELETON_TOP_1.get(), "Skeletal Remains"); genBlock(ModBlocks.SKELETON_TOP_1);
    add(ModBlocks.SKELETON_TOP_2.get(), "Skeletal Remains"); genBlock(ModBlocks.SKELETON_TOP_2);
    add(ModBlocks.SKELETON_TOP_3.get(), "Skeletal Remains"); genBlock(ModBlocks.SKELETON_TOP_3);
    add(ModBlocks.SKELETON_TOP_4.get(), "Skeletal Remains"); genBlock(ModBlocks.SKELETON_TOP_4);

    for (RegistryObject<? extends Block> block : BlockData.getAllBlocks()) {
      if (doneBlocks.contains(block)) {
        continue;
      }

      String[] name = block.getId().getPath().split("_");
      StringJoiner sb = new StringJoiner(" ");
      for (String s : name) {
        sb.add(StringUtils.capitalise(s));
      }
      add(block.get(), sb.toString());
      genBlock(block);
    }

    for (RegistryObject<? extends Item> item : ItemData.getAllItems()) {
      if (doneItems.contains(item)) {
        continue;
      }

      String[] name = item.getId().getPath().split("_");
      StringJoiner sb = new StringJoiner(" ");
      for (String s : name) {
        sb.add(StringUtils.capitalise(s));
      }
      add(item.get(), sb.toString());
      genItem(item);
    }

    validate();
  }
}
