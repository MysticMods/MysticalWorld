package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.data.DataGenerator;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionTagGenerator extends ForgeRegistryTagsProvider<Potion> {
  public PotionTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
    super(generatorIn, ForgeRegistries.POTION_TYPES, MysticalWorld.MODID, helper);
  }

  @Override
  protected void addTags() {
    tag(MWTags.Potions.RANDOM_BLACKLIST);
  }

  @Override
  public String getName() {
    return "Potion Tag Provider";
  }
}
