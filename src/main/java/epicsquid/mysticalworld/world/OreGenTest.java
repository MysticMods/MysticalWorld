package epicsquid.mysticalworld.world;

import com.mojang.serialization.Codec;
import epicsquid.mysticalworld.init.ModFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class OreGenTest extends RuleTest {
  public static final OreGenTest INSTANCE = new OreGenTest();
  public static final Codec<OreGenTest> CODEC = Codec.unit(() -> INSTANCE);

  @Override
  public boolean test(BlockState q, Random rand) {
    if (OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD.test(q, rand) || OreFeatureConfig.FillerBlockType.NETHERRACK.test(q, rand)) {
      return true;
    }

    //noinspection ConstantConditions
    if (q == null) {
      return false;
    }

    return Tags.Blocks.END_STONES.contains(q.getBlock());
  }

  @Override
  protected IRuleTestType<?> getType() {
    return ModFeatures.ORE_GEN;
  }
}
