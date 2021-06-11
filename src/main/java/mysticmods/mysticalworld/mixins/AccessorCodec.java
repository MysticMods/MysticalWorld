package mysticmods.mysticalworld.mixins;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkGenerator.class)
public interface AccessorCodec {
  @Invoker("func_230347_a_")
  Codec<ChunkGenerator> mw_getCodec();
}
