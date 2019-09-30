package epicsquid.mysticalworld.items.materials;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import epicsquid.mysticallib.block.OreBlockProperties;
import epicsquid.mysticallib.material.BaseItemTier;
import epicsquid.mysticallib.material.IMaterial;
import epicsquid.mysticallib.material.IMaterialFactory;
import epicsquid.mysticallib.material.factory.FactoryPredicates;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class CactusMaterial implements IMaterial {

	private static final String SWORD = "SWORD";
	private static final String KNIFE = "KNIFE";
	private static final String PICKAXE = "PICKAXE";
	private static final String AXE = "AXE";
	private static final String SHOVEL = "SHOVEL";
	private static final String HOE = "HOE";
	private static final String SPEAR = "SPEAR";

	private Map<String, Float> damage = new HashMap<>();
	private Map<String, Float> speed = new HashMap<>();

	public CactusMaterial() {
		damage.put(SWORD, 1.5f);
		damage.put(SHOVEL, 0.5f);
		damage.put(PICKAXE, 1.0f);
		damage.put(AXE, 3.0f);
		damage.put(KNIFE, 2.0f);

		speed.put(SWORD, -2.4f);
		speed.put(SHOVEL, -3.0f);
		speed.put(PICKAXE, -2.8f);
		speed.put(AXE, -3.1f);
		speed.put(KNIFE, -1.0f);
		speed.put(HOE, -3.0f);
	}

	@Override
	public IItemTier getTier() {
		return new BaseItemTier(getDurability(), 4.0f, 1.5f, 1, 3, () -> Ingredient.fromItems(Blocks.CACTUS));
	}

	@Override
	public IArmorMaterial getArmor() {
		return null;
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
		return "cactus";
	}

	@Override
	public int getDurability() {
		return 76;
	}

	@Override
	public Predicate<IMaterialFactory<?>> matches() {
		return FactoryPredicates.TOOLS;
	}
}
