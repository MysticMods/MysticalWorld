package epicsquid.mysticalworld.world.provider;

import com.mojang.serialization.Codec;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class SupplierBlockStateProvider extends BlockStateProvider {
  public static final Codec<SupplierBlockStateProvider> CODEC = ResourceLocation.CODEC.fieldOf("key").xmap(SupplierBlockStateProvider::new, (provider) -> provider.key).codec();

  private final ResourceLocation key;
  private BlockState state = null;

  public SupplierBlockStateProvider (String namespace, String path) {
    this(new ResourceLocation(namespace, path));
  }

  public SupplierBlockStateProvider(ResourceLocation key) {
    this.key = key;
  }

  @Override
  protected BlockStateProviderType<?> getProviderType() {
    return ModFeatures.SUPPLIER_STATE_PROVIDER.get();
  }

  @Override
  public BlockState getBlockState(Random randomIn, BlockPos blockPosIn) {
    if (state == null) {
      Block block = ForgeRegistries.BLOCKS.getValue(key);
      if (block == null) {
        MysticalWorld.LOG.error("Block couldn't be located for key: " + key);
        state = Blocks.AIR.getDefaultState();
      } else {
        state = block.getDefaultState();
      }
    }

    return state;
  }
}
