package epicsquid.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class XPOreBlock extends OreBlock {
  private int minXP;
  private int maxXP;

  public XPOreBlock(Properties properties, int minXP, int maxXP) {
    super(properties);
    this.minXP = minXP;
    this.maxXP = maxXP;
  }

  @Override
  protected int getExperience(Random rand) {
    return MathHelper.nextInt(rand, minXP, maxXP);
  }
}
