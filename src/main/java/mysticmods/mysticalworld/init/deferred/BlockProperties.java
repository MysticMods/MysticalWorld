package mysticmods.mysticalworld.init.deferred;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockProperties {
  public static final BlockBehaviour.Properties THATCH = BlockBehaviour.Properties.of(Material.WOOD).strength(1f).sound(SoundType.GRASS);

  public static final BlockBehaviour.Properties MUSHROOM = BlockBehaviour.Properties.of(Material.WOOD).strength(0.0F).sound(SoundType.WOOD);

  public static final BlockBehaviour.Properties CROP = BlockBehaviour.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks();

  public static final BlockBehaviour.Properties STONE = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2f).sound(SoundType.STONE);

  public static final BlockBehaviour.Properties WOOD = BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD);

  public static final BlockBehaviour.Properties PLANKS = BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f, 3.0f);

  public static final BlockBehaviour.Properties IRON_BRICK = BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.2f);

  public static final BlockBehaviour.Properties SOFT_STONE = BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1f);

  public static final BlockBehaviour.Properties SOFT_OBSIDIAN = BlockBehaviour.Properties.copy(Blocks.OBSIDIAN);

  public static final BlockBehaviour.Properties PEARL = BlockBehaviour.Properties.of(Material.STONE).strength(1.2f, 1.2f).sound(SoundType.STONE);

  public static final BlockBehaviour.Properties BONE = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.2f).sound(SoundType.BONE_BLOCK);
}
