package epicsquid.mysticalworld.items.materials;

import epicsquid.mysticallib.block.OreBlockProperties;
import epicsquid.mysticallib.material.IMaterial;
import epicsquid.mysticallib.material.IMaterialFactory;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class DiamondMaterial implements IMaterial {

  private static final String KNIFE = "KNIFE";
  private static final String SPEAR = "SPEAR";

  private int enchantability = 5;

  private Map<String, Float> damage = new HashMap<>();
  private Map<String, Float> speed = new HashMap<>();

  public DiamondMaterial() {
    damage.put(KNIFE, 2.5f);

    speed.put(KNIFE, -1.5f);
  }

  @Override
  public IItemTier getTier() {
    return ItemTier.DIAMOND;
  }

  @Override
  public IArmorMaterial getArmor() {
    return ArmorMaterial.DIAMOND;
  }

  @Override
  public Item.Properties getItemProps() {
    return new Item.Properties().group(MysticalWorld.ITEM_GROUP);
  }

  @Override
  public Block.Properties getBlockProps() {
    return Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1);
  }

  @Override
  public OreBlockProperties getBlockOreProps() {
    return null;
  }

  @Override
  public float getAttackSpeed(String name) {
    return speed.getOrDefault(name, 1.0f);
  }

  @Override
  public float getAttackDamage(String name) {
    return damage.getOrDefault(name, 1.0f);
  }

  @Override
  public String getName() {
    return "diamond";
  }

  @Override
  public int getDurability() {
    return 1561;
  }

  @Override
  public Predicate<IMaterialFactory<?>> matches() {
    return mat -> mat.getName().equals("knife") || mat.getName().equals("spear");
  }
}
