package epicsquid.mysticalworld.world;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Map;
import java.util.Random;

public class BarrowGenerator implements IWorldGenerator {
  private static ResourceLocation BARROW = new ResourceLocation(MysticalWorld.MODID, "barrow");

  public void generateChest(World world, BlockPos pos) {
    TileEntity te = world.getTileEntity(pos);
    if (te instanceof TileEntityChest) {
      ((TileEntityChest) te).setLootTable(new ResourceLocation(MysticalWorld.MODID, "barrow_loot"), world.getSeed() * pos.getX() + pos.getY() ^ pos.getZ());
    }
  }

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if (!(world instanceof WorldServer)) return;

    if (world.provider.getDimension() != 0) return;

    int x = chunkX * 16 + random.nextInt(16);
    int z = chunkZ * 16 + random.nextInt(16);
    BlockPos zxPos = new BlockPos(x, 0, z);

    BlockPos pos = world.getTopSolidOrLiquidBlock(zxPos);
    IBlockState state = world.getBlockState(pos);
    if (pos.getX() == 0 || state.getBlock() instanceof BlockLiquid) {
      return;
    }

    /*while (state.getBlock().isReplaceable(world, pos) && !(state.getBlock() instanceof BlockLiquid)) {
      if (pos.getY() <= 0) {
        return;
      }

      pos = pos.down();
      state = world.getBlockState(pos);
    }*/

    Biome biome = world.getBiome(pos);
    if (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS)) return;

    MinecraftServer minecraftserver = world.getMinecraftServer();
    TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
    Template template = templatemanager.getTemplate(minecraftserver, BARROW);

    BlockPos size = template.getSize();
    int top = pos.getY() + size.getY();
    if (top >= 256) {
      int shift = top - 256;
      pos.add(0, -shift, 0);
    }

    PlacementSettings placementsettings = new PlacementSettings();

    ChunkPos chunkpos = new ChunkPos(pos);
    StructureBoundingBox structureboundingbox = new StructureBoundingBox(chunkpos.getXStart(), 0, chunkpos.getZStart(), chunkpos.getXEnd(), 256, chunkpos.getZEnd());
    placementsettings.setBoundingBox(structureboundingbox);

    Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
    placementsettings.setRotation(rotation);
    placementsettings.setIntegrity(1);
    placementsettings.setIgnoreEntities(false);
    placementsettings.setChunk(chunkpos);
    placementsettings.setReplacedBlock(null);
    placementsettings.setIgnoreStructureBlock(false);
    placementsettings.setRandom(random);

    pos = Template.transformedBlockPos(placementsettings, pos).down().down();
    template.addBlocksToWorldChunk(world, pos, placementsettings);

    IBlockState chest = Blocks.CHEST.getDefaultState();
    IBlockState cobble = Blocks.COBBLESTONE.getDefaultState();

    Map<BlockPos, String> data = template.getDataBlocks(pos, placementsettings);
    data.forEach((blockPos, s) -> {
      if (s.equals("spawner")) {
        if (world.setBlockState(blockPos, Blocks.MOB_SPAWNER.getDefaultState(), 2)) {
          TileEntityMobSpawner ms = (TileEntityMobSpawner) world.getTileEntity(blockPos);
          if (ms != null) {

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
        if (random.nextInt(5) == 0) {
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
