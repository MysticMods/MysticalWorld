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
  public static RegistryObject<ThatchBlock> THATCH = MysticalWorld.REGISTRY.registerBlock("thatch", MysticalWorld.REGISTRY.block(ThatchBlock::new, () -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)), ModRegistries.SIG);
  public static RegistryObject<AubergineCropBlock> AUBERGINE_CROP = MysticalWorld.REGISTRY.registerBlockWithoutItem("aubergine_crop", MysticalWorld.REGISTRY.block(AubergineCropBlock::new, () -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP).tickRandomly()));

  // Ore Blocks
  public static RegistryObject<BaseOreBlock> AMETHYST_ORE = MysticalWorld.REGISTRY.registerBlock(ModMaterials.AMETHYST.oreName(), MysticalWorld.REGISTRY.ore(BaseOreBlock::new, ModMaterials.AMETHYST), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> COPPER_ORE = MysticalWorld.REGISTRY.registerBlock(ModMaterials.COPPER.oreName(), MysticalWorld.REGISTRY.ore(BaseOreBlock::new, ModMaterials.COPPER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> LEAD_ORE = MysticalWorld.REGISTRY.registerBlock(ModMaterials.LEAD.oreName(), MysticalWorld.REGISTRY.ore(BaseOreBlock::new, ModMaterials.LEAD), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> QUICKSILVER_ORE = MysticalWorld.REGISTRY.registerBlock(ModMaterials.QUICKSILVER.oreName(), MysticalWorld.REGISTRY.ore(BaseOreBlock::new, ModMaterials.QUICKSILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> SILVER_ORE = MysticalWorld.REGISTRY.registerBlock(ModMaterials.SILVER.oreName(), MysticalWorld.REGISTRY.ore(BaseOreBlock::new, ModMaterials.SILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> TIN_ORE = MysticalWorld.REGISTRY.registerBlock(ModMaterials.TIN.oreName(), MysticalWorld.REGISTRY.ore(BaseOreBlock::new, ModMaterials.TIN), ModRegistries.SMG);

  // Blocks
  public static RegistryObject<Block> AMETHYST_BLOCK = MysticalWorld.REGISTRY.registerBlock(ModMaterials.AMETHYST.blockName(), MysticalWorld.REGISTRY.block(Block::new, ModMaterials.AMETHYST.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> COPPER_BLOCK = MysticalWorld.REGISTRY.registerBlock(ModMaterials.COPPER.blockName(), MysticalWorld.REGISTRY.block(Block::new, ModMaterials.COPPER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> LEAD_BLOCK = MysticalWorld.REGISTRY.registerBlock(ModMaterials.LEAD.blockName(), MysticalWorld.REGISTRY.block(Block::new, ModMaterials.LEAD.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> QUICKSILVER_BLOCK = MysticalWorld.REGISTRY.registerBlock(ModMaterials.QUICKSILVER.blockName(), MysticalWorld.REGISTRY.block(Block::new, ModMaterials.QUICKSILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> SILVER_BLOCK = MysticalWorld.REGISTRY.registerBlock(ModMaterials.SILVER.blockName(), MysticalWorld.REGISTRY.block(Block::new, ModMaterials.SILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> TIN_BLOCK = MysticalWorld.REGISTRY.registerBlock(ModMaterials.TIN.blockName(), MysticalWorld.REGISTRY.block(Block::new, ModMaterials.TIN.getBlockProps()), ModRegistries.SMG);

  public static void load() {}
}
