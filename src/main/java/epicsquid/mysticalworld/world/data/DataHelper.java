//package epicsquid.mysticalworld.world.data;
//
//import epicsquid.mysticalworld.world.StructureGenerator;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//import java.util.List;
//
//public class DataHelper {
//  public static StructureSaveData getStructureData(World world) {
//    StructureSaveData saveData = (StructureSaveData) world.getMapStorage().getOrLoadData(StructureSaveData.class, StructureSaveData.ID);
//
//    if (saveData == null) {
//      saveData = new StructureSaveData();
//      world.getMapStorage().setData(StructureSaveData.ID, saveData);
//    }
//
//    return saveData;
//  }
//
//  public static List<BlockPos> getGeneratorMap(StructureGenerator generator, World world) {
//    return getGeneratorMap(generator.getRegistryName(), world);
//  }
//
//  public static List<BlockPos> getGeneratorMap(ResourceLocation location, World world) {
//    StructureSaveData saveData = getStructureData(world);
//
//    return saveData.getGeneratorMap(location);
//  }
//
//  public static int distance(BlockPos pos1, BlockPos pos2) {
//    int d1 = pos1.getX() - pos2.getX();
//    int d2 = pos1.getZ() - pos2.getZ();
//    return Math.abs(d1 * d1 + d2 * d2);
//  }
//
//  public static boolean testBlockPos(ResourceLocation location, BlockPos pos, int maxDistance, World world) {
//    List<BlockPos> map = getGeneratorMap(location, world);
//    for (BlockPos otherPos : map) {
//      int dist = distance(pos, otherPos);
//      if (dist < maxDistance) {
//        return false;
//      }
//    }
//
//    return true;
//  }
//
//  public static void putBlockPos(ResourceLocation location, BlockPos pos, World world) {
//    List<BlockPos> map = getGeneratorMap(location, world);
//    map.add(pos);
//  }
//
//  public static void save(World world) {
//    StructureSaveData saveData = getStructureData(world);
//    saveData.markDirty();
//    world.getMapStorage().saveAllData();
//  }
//}
