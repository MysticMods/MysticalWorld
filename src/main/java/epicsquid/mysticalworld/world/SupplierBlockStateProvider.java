package epicsquid.mysticalworld.world;

import com.mojang.serialization.Codec;
import epicsquid.mysticalworld.init.ModFeatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import noobanidus.libs.noobutil.types.AbstractSBSP;

public class SupplierBlockStateProvider extends AbstractSBSP {
  public static final Codec<SupplierBlockStateProvider> CODEC = ResourceLocation.CODEC.fieldOf("key").xmap(SupplierBlockStateProvider::new, (provider) -> provider.key).codec();

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
