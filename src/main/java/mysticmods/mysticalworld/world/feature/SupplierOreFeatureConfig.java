package mysticmods.mysticalworld.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.registries.ForgeRegistries;

public class SupplierOreFeatureConfig implements FeatureConfiguration {
  public static final Codec<SupplierOreFeatureConfig> CODEC = RecordCodecBuilder.create((provider) -> provider.group(RuleTest.CODEC.fieldOf("target").forGetter((config) -> config.target), ResourceLocation.CODEC.fieldOf("state").forGetter((config) -> config.state), Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> config.size)).apply(provider, SupplierOreFeatureConfig::new));
  public final RuleTest target;
  public final int size;
  public final ResourceLocation state;

  private BlockState cachedState = null;

  public SupplierOreFeatureConfig(RuleTest p_i241989_1_, ResourceLocation stateKey, int size) {
    this.size = size;
    this.state = stateKey;
    this.target = p_i241989_1_;
  }

  public BlockState getState() {
    if (cachedState == null) {
      Block block = ForgeRegistries.BLOCKS.getValue(state);
      if (block == null) {
        cachedState = Blocks.AIR.defaultBlockState();
      } else {
        cachedState = block.defaultBlockState();
      }
    }

    return cachedState;
  }

}
