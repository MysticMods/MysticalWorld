package epicsquid.mysticalworld.items.materials;

import epicsquid.mysticallib.material.IMaterial;

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
}
