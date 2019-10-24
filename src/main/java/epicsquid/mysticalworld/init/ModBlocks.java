package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.block.BaseOreBlock;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.blocks.AubergineCropBlock;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import epicsquid.mysticalworld.blocks.XPOreBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("unused")
public class ModBlocks {
  public static RegistryObject<ThatchBlock> THATCH = ModRegistries.registerBlock("thatch", ModRegistries.block(ThatchBlock::new, () -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)), ModRegistries.SIG);
  public static RegistryObject<AubergineCropBlock> AUBERGINE_CROP = ModRegistries.registerBlock("aubergine_crop", ModRegistries.block(AubergineCropBlock::new, () -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP)), ModRegistries.SIG);

  // Ore Blocks
  public static RegistryObject<XPOreBlock> AMETHYST_ORE = ModRegistries.registerBlock(ModMaterials.AMETHYST.oreName(), ModRegistries.ore(XPOreBlock::new, ModMaterials.AMETHYST), ModRegistries.SMG);
  public static RegistryObject<XPOreBlock> COPPER_ORE = ModRegistries.registerBlock(ModMaterials.COPPER.oreName(), ModRegistries.ore(XPOreBlock::new, ModMaterials.COPPER), ModRegistries.SMG);
  public static RegistryObject<XPOreBlock> LEAD_ORE = ModRegistries.registerBlock(ModMaterials.LEAD.oreName(), ModRegistries.ore(XPOreBlock::new, ModMaterials.LEAD), ModRegistries.SMG);
  public static RegistryObject<XPOreBlock> QUICKSILVER_ORE = ModRegistries.registerBlock(ModMaterials.QUICKSILVER.oreName(), ModRegistries.ore(XPOreBlock::new, ModMaterials.QUICKSILVER), ModRegistries.SMG);
  public static RegistryObject<XPOreBlock> SILVER_ORE = ModRegistries.registerBlock(ModMaterials.SILVER.oreName(), ModRegistries.ore(XPOreBlock::new, ModMaterials.SILVER), ModRegistries.SMG);
  public static RegistryObject<XPOreBlock> TIN_ORE = ModRegistries.registerBlock(ModMaterials.TIN.oreName(), ModRegistries.ore(XPOreBlock::new, ModMaterials.TIN), ModRegistries.SMG);

  // Blocks
  public static RegistryObject<Block> AMETHYST_BLOCK = ModRegistries.registerBlock(ModMaterials.AMETHYST.blockName(), ModRegistries.block(Block::new, ModMaterials.AMETHYST.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> COPPER_BLOCK = ModRegistries.registerBlock(ModMaterials.COPPER.blockName(), ModRegistries.block(Block::new, ModMaterials.COPPER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> LEAD_BLOCK = ModRegistries.registerBlock(ModMaterials.LEAD.blockName(), ModRegistries.block(Block::new, ModMaterials.LEAD.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> QUICKSILVER_BLOCK = ModRegistries.registerBlock(ModMaterials.QUICKSILVER.blockName(), ModRegistries.block(Block::new, ModMaterials.QUICKSILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> SILVER_BLOCK = ModRegistries.registerBlock(ModMaterials.SILVER.blockName(), ModRegistries.block(Block::new, ModMaterials.SILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> TIN_BLOCK = ModRegistries.registerBlock(ModMaterials.TIN.blockName(), ModRegistries.block(Block::new, ModMaterials.TIN.getBlockProps()), ModRegistries.SMG);

  public static void init () {}
}
