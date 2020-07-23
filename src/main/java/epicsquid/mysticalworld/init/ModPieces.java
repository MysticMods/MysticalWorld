package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.world.pieces.HutPiece;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class ModPieces {
  public static IStructurePieceType HUT = register("hut", HutPiece::new);

  public static void load () {
  }

  public static IStructurePieceType register(String name, IStructurePieceType type) {
    ResourceLocation key = new ResourceLocation(MysticalWorld.MODID, name);
    return Registry.register(Registry.STRUCTURE_PIECE, key.toString(), type);
  }
}
