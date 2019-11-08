package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.block.BaseOreBlock;
import epicsquid.mysticalworld.blocks.AubergineCropBlock;
import epicsquid.mysticalworld.blocks.MudBlock;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRY;

@SuppressWarnings("unused")
public class ModBlocks {
  public static RegistryObject<ThatchBlock> THATCH = REGISTRY.registerBlock("thatch", REGISTRY.block(ThatchBlock::new, () -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)), ModRegistries.SIG);
  public static RegistryObject<AubergineCropBlock> AUBERGINE_CROP = REGISTRY.registerBlockWithoutItem("aubergine_crop", REGISTRY.block(AubergineCropBlock::new, () -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP).tickRandomly()));
  public static RegistryObject<MudBlock> WET_MUD_BLOCK = REGISTRY.registerBlock("wet_mud_block", REGISTRY.block(MudBlock::new, () -> Block.Properties.create(Material.EARTH).sound(SoundType.SLIME)), ModRegistries.SIG);
  public static RegistryObject<MudBlock> WET_MUD_BRICK = REGISTRY.registerBlock("wet_mud_brick", REGISTRY.block(MudBlock::new, () -> Block.Properties.create(Material.EARTH).sound(SoundType.SLIME)), ModRegistries.SIG);
  public static RegistryObject<Block> MUD_BLOCK = REGISTRY.registerBlock("mud_block", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE)), ModRegistries.SIG);
  public static RegistryObject<Block> MUD_BRICK = REGISTRY.registerBlock("mud_brick", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE)), ModRegistries.SIG);

  // Ore Blocks
  public static RegistryObject<BaseOreBlock> AMETHYST_ORE = REGISTRY.registerBlock(ModMaterials.AMETHYST.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.AMETHYST), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> COPPER_ORE = REGISTRY.registerBlock(ModMaterials.COPPER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.COPPER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> LEAD_ORE = REGISTRY.registerBlock(ModMaterials.LEAD.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.LEAD), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> QUICKSILVER_ORE = REGISTRY.registerBlock(ModMaterials.QUICKSILVER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.QUICKSILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> SILVER_ORE = REGISTRY.registerBlock(ModMaterials.SILVER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.SILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> TIN_ORE = REGISTRY.registerBlock(ModMaterials.TIN.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.TIN), ModRegistries.SMG);

  // Blocks
  public static RegistryObject<Block> AMETHYST_BLOCK = REGISTRY.registerBlock(ModMaterials.AMETHYST.blockName(), REGISTRY.block(Block::new, ModMaterials.AMETHYST.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> COPPER_BLOCK = REGISTRY.registerBlock(ModMaterials.COPPER.blockName(), REGISTRY.block(Block::new, ModMaterials.COPPER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> LEAD_BLOCK = REGISTRY.registerBlock(ModMaterials.LEAD.blockName(), REGISTRY.block(Block::new, ModMaterials.LEAD.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> QUICKSILVER_BLOCK = REGISTRY.registerBlock(ModMaterials.QUICKSILVER.blockName(), REGISTRY.block(Block::new, ModMaterials.QUICKSILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> SILVER_BLOCK = REGISTRY.registerBlock(ModMaterials.SILVER.blockName(), REGISTRY.block(Block::new, ModMaterials.SILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> TIN_BLOCK = REGISTRY.registerBlock(ModMaterials.TIN.blockName(), REGISTRY.block(Block::new, ModMaterials.TIN.getBlockProps()), ModRegistries.SMG);

  public static void load() {
  }
}
