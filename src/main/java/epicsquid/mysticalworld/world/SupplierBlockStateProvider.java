package epicsquid.mysticalworld.world;

import com.mojang.serialization.Codec;
import epicsquid.mysticalworld.init.ModFeatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import noobanidus.libs.noobutil.world.gen.provider.AbstractSupplierBockStateProvider;

public class SupplierBlockStateProvider extends AbstractSupplierBockStateProvider {
  public static final Codec<SupplierBlockStateProvider> CODEC = codecBuilder(SupplierBlockStateProvider::new);

  public SupplierBlockStateProvider(String namespace, String path) {
    super(namespace, path);
  }

  public SupplierBlockStateProvider(ResourceLocation key) {
    super(key);
  }

  @Override
  protected BlockStateProviderType<?> getProviderType() {
    return ModFeatures.SUPPLIER_STATE_PROVIDER.get();
  }
}
