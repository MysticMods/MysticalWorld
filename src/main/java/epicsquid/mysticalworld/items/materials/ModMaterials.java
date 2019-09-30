package epicsquid.mysticalworld.items.materials;

import epicsquid.mysticallib.material.IMaterial;
import net.minecraft.block.Block;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ModMaterials {

	private static List<IMaterial> materials = new ArrayList<>();

	public static void addMaterial(IMaterial mat) {
		materials.add(mat);
	}

	public static List<IMaterial> getMaterials() {
		return materials;
	}

	@Nullable
	public static Block getOre (String name) {
		for (IMaterial mat : materials) {
			if (mat.getName().equalsIgnoreCase(name)) {
				return mat.getOre();
			}
		}
		return null;
	}
}
