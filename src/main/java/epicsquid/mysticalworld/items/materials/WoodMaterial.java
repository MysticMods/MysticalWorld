package epicsquid.mysticalworld.items.materials;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epicsquid.mysticallib.block.OreBlockProperties;
import epicsquid.mysticallib.material.IMaterial;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;

public class WoodMaterial implements IMaterial {

	private static final String KNIFE = "KNIFE";
	private static final String SPEAR = "SPEAR";

	private Map<String, Float> damage = new HashMap<>();
	private Map<String, Float> speed = new HashMap<>();

	public WoodMaterial() {
		damage.put(KNIFE, 1.0f);

		speed.put(KNIFE, -2.0f);
	}

	@Override
	public IItemTier getTier() {
		return ItemTier.WOOD;
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
		return "wood";
	}

	@Override
	public List<String> getWhitelist() {
		return Arrays.asList("knife", "spear");
	}
}
