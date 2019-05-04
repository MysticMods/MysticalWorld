package epicsquid.mysticalworld.world;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class StructureGenerator implements IWorldGenerator {
  private final ResourceLocation structure;
  private final int descent;
  private final double maxDistance;
  private final Supplier<Class<? extends Entity>> entity;
  private static ResourceLocation loot = new ResourceLocation("minecraft", "chests/simple_dungeon");
  private List<BlockPos> structurePositions = new ArrayList<>();

  public StructureGenerator(ResourceLocation structure, int descent, Supplier<Class<? extends Entity>> entity, double maxDistance) {
    this.structure = structure;
    this.descent = descent;
    this.entity = entity;
    this.maxDistance = maxDistance;
  }

  public void clear() {
    System.out.println("Clearing structure information for huts");
    this.structurePositions.clear();
  }

  public void generateChest(World world, BlockPos pos) {
    TileEntity te = world.getTileEntity(pos);
    if (te instanceof TileEntityChest) {
      ((TileEntityChest) te).setLootTable(loot, world.getSeed() * pos.getX() + pos.getY() ^ pos.getZ());
    }
  }

  public double distance(BlockPos pos1, BlockPos pos2) {
    double d1 = (double) (pos1.getX() - pos2.getX());
    double d2 = (double) (pos1.getZ() - pos2.getZ());
    double result = d1 * d2;
    return result < 0 ? Math.abs(result) : result;
  }

  private BlockPos testPlacement(int[] heightmap, BlockPos size, int x, int z, int deviation) {
    int max = 0;
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < size.getX(); i++) {
      for (int j = 0; j < size.getZ(); j++) {
        if (i != 0 && i != (size.getX() - 1) && j != 0 && j != (size.getZ() - 1)) continue;
        int index = (z + j) << 4 | (x + i);
        int height = heightmap[index];
        if (height > max) {
          max = height;
        }
        if (height < min) {
          min = height;
        }
        if (max - min > deviation) {
          return null;
        }
      }
    }

    return new BlockPos(x, max, z);
  }

  private BlockPos testPlacement(int[] heightMap, BlockPos size, int chunkSize, int deviation) {
    int structureX = chunkSize - size.getX() + 1;
    int structureZ = chunkSize - size.getZ() + 1;

    for (int x = 0; x < structureX; x++) {
      for (int z = 0; z < structureZ; z++) {
        BlockPos pos = testPlacement(heightMap, size, x, z, deviation);
        if (pos != null) return pos;
      }
    }

    return null;
  }

  private boolean testForLiquids(World world, BlockPos start, BlockPos size) {
    BlockPos stop = start.add(size.getX(), 0, size.getY());
    for (BlockPos pos : BlockPos.getAllInBoxMutable(start, stop)) {
      BlockPos pos2 = world.getTopSolidOrLiquidBlock(pos);
      IBlockState state = world.getBlockState(pos2);
      if (state.getBlock() instanceof BlockLiquid) return false;
    }

    return true;
  }

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

    if (!(world instanceof WorldServer)) return;
    if (world.provider.getDimension() != 0) return;

    int cx = chunkX * 16;
    int cz = chunkZ * 16;
    BlockPos zxPos = new BlockPos(cx, 0, cz);

    Biome biome = world.getBiome(zxPos);
    if (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS)) return;

    MinecraftServer minecraftserver = world.getMinecraftServer();
    TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
    Template template = templatemanager.getTemplate(minecraftserver, structure);

    Chunk chunk = world.getChunk(zxPos);
    int[] heightMap = chunk.getHeightMap();

    BlockPos size = template.getSize();

    BlockPos pos = testPlacement(heightMap, size, 16, 0);

    if (pos == null) {
      return;
    }

    pos = pos.add(zxPos.getX(), 0, zxPos.getZ());

    for (BlockPos otherPos : structurePositions) {
      double distance = distance(pos, otherPos) / 10;
      if (distance < this.maxDistance) return;
    }

    if (!testForLiquids(world, pos, size)) return;

    PlacementSettings placementsettings = new PlacementSettings();

    ChunkPos chunkpos = new ChunkPos(pos);
    StructureBoundingBox structureboundingbox = new StructureBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
    placementsettings.setBoundingBox(structureboundingbox);

    Rotation rotation = (random.nextBoolean()) ? Rotation.NONE : Rotation.CLOCKWISE_180;
    placementsettings.setRotation(rotation);
    placementsettings.setIntegrity(1);
    placementsettings.setIgnoreEntities(false);
    placementsettings.setChunk(chunkpos);
    placementsettings.setReplacedBlock(null);
    placementsettings.setIgnoreStructureBlock(false);
    placementsettings.setRandom(random);

    pos = Template.transformedBlockPos(placementsettings, pos).down(descent);
    template.addBlocksToWorldChunk(world, pos, placementsettings);

    IBlockState chest = Blocks.CHEST.getDefaultState();
    IBlockState cobble = Blocks.COBBLESTONE.getDefaultState();

    Map<BlockPos, String> data = template.getDataBlocks(pos, placementsettings);
    if (!data.isEmpty()) {
      structurePositions.add(pos);
    }
    data.forEach((blockPos, s) -> {
      if (s.equals("spawner")) {
        if (world.setBlockState(blockPos, Blocks.MOB_SPAWNER.getDefaultState(), 2)) {
          TileEntityMobSpawner ms = (TileEntityMobSpawner) world.getTileEntity(blockPos);
          if (ms != null) {
            ResourceLocation key = EntityList.getKey(entity.get());
            if (key == null) key = EntityList.getKey(EntityZombie.class);
            ms.getSpawnerBaseLogic().setEntityId(key);
            // TODO
          }
        }
        // Two floor chests
      } else if (s.equals("loot_chest1")) {
        if (random.nextBoolean()) {
          if (world.setBlockState(blockPos, chest)) {
            generateChest(world, blockPos);
          }
        } else {
          world.setBlockState(blockPos, cobble);
        }
      } else if (s.equals("loot_chest2")) {
        if (random.nextBoolean()) {
          if (world.setBlockState(blockPos, chest)) {
            generateChest(world, blockPos);
          }
        } else {
          world.setBlockState(blockPos, cobble);
        }
        // Ceiling chest
      } else if (s.equals("loot_chest3")) {
        if (world.setBlockState(blockPos, chest)) {
          generateChest(world, blockPos);
        }
      }
    });
  }
}
