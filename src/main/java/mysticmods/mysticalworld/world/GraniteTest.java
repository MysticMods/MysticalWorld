package mysticmods.mysticalworld.world;

import com.mojang.serialization.Codec;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModFeatures;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.Random;

public class GraniteTest extends TagMatchTest {
  private static final GraniteTest INSTANCE = new GraniteTest();
  public static final Codec<GraniteTest> CODEC = Codec.unit(() -> INSTANCE);

  public GraniteTest() {
    super(MWTags.Blocks.BASE_STONE_GRANITE);
  }

  @Override
  public boolean test(BlockState pState, Random pRandom) {
    return super.test(pState, pRandom);
  }

  @Override
  protected RuleTestType<?> getType() {
    return ModFeatures.GRANITE_TEST.get();
  }

  public static class Type implements RuleTestType<GraniteTest> {
    @Override
    public Codec<GraniteTest> codec() {
      return CODEC;
    }
  }
}
