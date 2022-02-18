package mysticmods.mysticalworld.world.test;

import com.mojang.serialization.Codec;
import mysticmods.mysticalworld.init.ModFeatures;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class OreGenTest extends RuleTest {
  public static final OreGenTest INSTANCE = new OreGenTest();
  public static final Codec<OreGenTest> CODEC = Codec.unit(() -> INSTANCE);

  @Override
  public boolean test(BlockState q, Random rand) {
    if (OreConfiguration.Predicates.NATURAL_STONE.test(q, rand) || OreConfiguration.Predicates.NETHERRACK.test(q, rand)) {
      return true;
    }

    //noinspection ConstantConditions
    if (q == null) {
      return false;
    }

    return Tags.Blocks.END_STONES.contains(q.getBlock());
  }

  @Override
  protected RuleTestType<?> getType() {
    return ModFeatures.ORE_GEN;
  }
}
