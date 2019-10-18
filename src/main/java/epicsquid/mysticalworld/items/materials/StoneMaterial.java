package epicsquid.mysticalworld.items.materials;

import epicsquid.mysticallib.block.OreBlockProperties;
import epicsquid.mysticallib.material.IMaterial;
import epicsquid.mysticallib.material.IMaterialFactory;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.item.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StoneMaterial implements IMaterial {

  private static final String KNIFE = "KNIFE";
  private static final String SPEAR = "SPEAR";

  private Map<String, Float> damage = new HashMap<>();
  private Map<String, Float> speed = new HashMap<>();

  public StoneMaterial() {
    damage.put(KNIFE, 1.0f);

    speed.put(KNIFE, -1.0f);
  }

  @Override
  public IItemTier getTier() {
    return ItemTier.STONE;
  }

  @Override
  public IArmorMaterial getArmor() {
    return ArmorMaterial.LEATHER;
  }

  @Override
  public Item.Properties getItemProps() {
    return new Item.Properties().group(MysticalWorld.ITEM_GROUP);
  }

  @Override
  public Block.Properties getBlockProps() {
    return null;
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
    return "stone";
  }

  @Override
  public Predicate<IMaterialFactory<?>> matches() {
    return mat -> mat.getName().equals("knife") || mat.getName().equals("spear");
  }
}
