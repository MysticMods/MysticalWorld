package mysticmods.mysticalworld.gen;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeRegistryTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeTagGenerator extends ForgeRegistryTagsProvider<Biome> {
  public BiomeTagGenerator(DataGenerator generatorIn, ExistingFileHelper helper) {
    super(generatorIn, ForgeRegistries.BIOMES, MysticalWorld.MODID, helper);
  }

  @Override
  protected void addTags() {
    tag(MWTags.Biomes.HAS_HUT).add(Biomes.PLAINS, Biomes.SAVANNA);
    tag(MWTags.Biomes.HAS_RUINED_HUT).add(Biomes.PLAINS, Biomes.SAVANNA);
    tag(MWTags.Biomes.HAS_BARROW).add(Biomes.PLAINS, Biomes.SAVANNA);
    tag(MWTags.Biomes.HAS_DESERT_HUT).add(Biomes.DESERT);
  }

  @Override
  public String getName() {
    return "Biome Tag Provider";
  }
}
