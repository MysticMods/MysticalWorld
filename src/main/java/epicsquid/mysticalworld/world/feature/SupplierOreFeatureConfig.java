package epicsquid.mysticalworld.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.registries.ForgeRegistries;

public class SupplierOreFeatureConfig implements IFeatureConfig {
  public static final Codec<SupplierOreFeatureConfig> CODEC = RecordCodecBuilder.create((provider) -> provider.group(RuleTest.field_237127_c_.fieldOf("target").forGetter((config) -> config.target), ResourceLocation.CODEC.fieldOf("state").forGetter((config) -> config.state), Codec.intRange(0, 64).fieldOf("size").forGetter((config) -> config.size)).apply(provider, SupplierOreFeatureConfig::new));
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
        cachedState = Blocks.AIR.getDefaultState();
      } else {
        cachedState = block.getDefaultState();
      }
    }

    return cachedState;
  }

}
