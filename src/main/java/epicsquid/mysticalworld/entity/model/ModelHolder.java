package epicsquid.mysticalworld.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ModelHolder implements IResourceManagerReloadListener {

	public static Map<String, EntityModel> models = new HashMap<>();

	public static void init() {
		models.put("fox", new ModelFox());
		models.put("frog", new FrogModel());
		models.put("beetle", new BeetleModel());
		models.put("deer", new DeerModel());
		models.put("sprout", new SproutModel());
	}

	@Override
	public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {
		models.clear();
		init();
	}
}
