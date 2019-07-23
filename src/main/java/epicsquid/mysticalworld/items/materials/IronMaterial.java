package epicsquid.mysticalworld.items.materials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epicsquid.mysticallib.block.OreBlockProperties;
import epicsquid.mysticallib.material.IMaterial;
import epicsquid.mysticallib.material.IMetalMaterial;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.common.ToolType;

public class IronMaterial implements IMaterial {

	private static final String KNIFE = "KNIFE";
	private static final String SPEAR = "SPEAR";

	private Map<String, Float> damage = new HashMap<>();
	private Map<String, Float> speed = new HashMap<>();

	public IronMaterial() {
		damage.put(KNIFE, 2.5f);

		speed.put(KNIFE, -1.5f);
	}

	@Override
	public IItemTier getTier() {
		return ItemTier.IRON;
	}

	@Override
	public IArmorMaterial getArmor() {
		return ArmorMaterial.IRON;
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
		return "iron";
	}

	@Override
	public int getDurability() {
		return 250;
	}

	@Override
	public List<String> getWhitelist() {
		return Arrays.asList("knife", "dust", "spear");
	}
}
