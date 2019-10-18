package epicsquid.mysticalworld.world.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureSaveData extends WorldSavedData {
  public static final String ID = "MysticalWorld-StructureSaveData";

  private Map<ResourceLocation, List<BlockPos>> generatorMap = new HashMap<>();

  public StructureSaveData(String name) {
    super(name);
  }

  public StructureSaveData() {
    super(ID);
  }

  public List<BlockPos> getGeneratorMap(ResourceLocation location) {
    return generatorMap.computeIfAbsent(location, k -> new ArrayList<>());
  }

  @Override
  public void read(CompoundNBT nbt) {
    ListNBT keyList = nbt.getList("keyList", Constants.NBT.TAG_STRING);
    for (int i = 0; i < keyList.size(); i++) {
      String key = keyList.getString(i);
      ResourceLocation rl = new ResourceLocation(key);

      ListNBT positions = nbt.getList(key, Constants.NBT.TAG_LONG);
      List<BlockPos> posList = generatorMap.get(rl);
      if (posList == null) {
        posList = new ArrayList<>();
        generatorMap.put(rl, posList);
      } else {
        posList.clear();
      }

      for (int j = 0; j < positions.size(); j++) {
        long l = ((LongNBT) positions.get(j)).getLong();
        posList.add(BlockPos.fromLong(l));
      }
    }
  }

  @Override
  public CompoundNBT write(CompoundNBT compound) {
    ListNBT stringKeyList = new ListNBT();
    for (Map.Entry<ResourceLocation, List<BlockPos>> entry : generatorMap.entrySet()) {
      ResourceLocation rl = entry.getKey();
      String name = rl.toString();
      stringKeyList.add(new StringNBT(name));
      ListNBT positions = new ListNBT();
      for (BlockPos p : entry.getValue()) {
        positions.add(new LongNBT(p.toLong()));
      }
      compound.put(name, positions);
    }
    compound.put("keyList", stringKeyList);
    return compound;
  }
}
