package epicsquid.mysticalworld.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Supplier;

public class CustomRegistrate extends AbstractRegistrate<CustomRegistrate> {
  protected CustomRegistrate(String modid) {
    super(modid);
  }

  public static CustomRegistrate create(String modid) {
    return new CustomRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
  }

  public <T extends Effect> EffectBuilder<T, CustomRegistrate> effect(Supplier<? extends T> factory) {
    return effect(this, factory);
  }

  public <T extends Effect> EffectBuilder<T, CustomRegistrate> effect(String name, Supplier<? extends T> factory) {
    return effect(this, name, factory);
  }

  public <T extends Effect, P> EffectBuilder<T, P> effect(P parent, Supplier<? extends T> factory) {
    return effect(parent, currentName(), factory);
  }

  public <T extends Effect, P> EffectBuilder<T, P> effect(P parent, String name, Supplier<? extends T> factory) {
    return entry(name, callback -> new EffectBuilder<>(this, parent, name, callback, factory));
  }

  // Serializers

  public <T extends IRecipeSerializer<?>> RecipeSerializerBuilder<T, CustomRegistrate> recipeSerializer(Supplier<? extends T> factory) {
    return recipeSerializer(this, factory);
  }

  public <T extends IRecipeSerializer<?>> RecipeSerializerBuilder<T, CustomRegistrate> recipeSerializer(String name, Supplier<? extends T> factory) {
    return recipeSerializer(this, name, factory);
  }

  public <T extends IRecipeSerializer<?>, P> RecipeSerializerBuilder<T, P> recipeSerializer(P parent, Supplier<? extends T> factory) {
    return recipeSerializer(parent, currentName(), factory);
  }

  public <T extends IRecipeSerializer<?>, P> RecipeSerializerBuilder<T, P> recipeSerializer(P parent, String name, Supplier<? extends T> factory) {
    return entry(name, callback -> new RecipeSerializerBuilder<>(this, parent, name, callback, factory));
  }

  // Containers

  public <T extends Container> ContainerBuilder<T, CustomRegistrate> containerType(String name, ContainerType.IFactory<T> factory) {
    return containerType(this, name, factory);
  }

  public <T extends Container> ContainerBuilder<T, CustomRegistrate> containerType(ContainerType.IFactory<T> factory) {
    return containerType(this, factory);
  }

  public <T extends Container, P> ContainerBuilder<T, P> containerType(P parent, ContainerType.IFactory<T> factory) {
    return containerType(parent, currentName(), factory);
  }

  public <T extends Container, P> ContainerBuilder<T, P> containerType(P parent, String name, ContainerType.IFactory<T> factory) {
    return entry(name, callback -> new ContainerBuilder<>(this, parent, name, callback, factory));
  }

  public SoundEventBuilder<SoundEvent, CustomRegistrate> soundEvent() {
    return soundEvent(currentName());
  }

  public SoundEventBuilder<SoundEvent, CustomRegistrate> soundEvent(String name) {
    ResourceLocation rl = new ResourceLocation(this.getModid(), name);
    Supplier<SoundEvent> factory = () -> new SoundEvent(rl); // TODO ???
    return soundEvent(this, name, factory);
  }

  public SoundEventBuilder<SoundEvent, CustomRegistrate> soundEvent(String name, String fullName) {
    ResourceLocation fullNameRL = new ResourceLocation(this.getModid(), fullName);
    Supplier<SoundEvent> factory = () -> new SoundEvent(fullNameRL);
    return soundEvent(this, name, factory);
  }

  public <P> SoundEventBuilder<SoundEvent, P> soundEvent(P parent, String name, Supplier<SoundEvent> factory) {
    return entry(name, callback -> new SoundEventBuilder<>(this, parent, name, callback, factory));
  }

  public BlockBuilder<LogBlock, CustomRegistrate> log(String name, MaterialColor color) {
    return this.log(this.self(), name, (b) -> new LogBlock(color, b), Material.WOOD);
  }

  public <T extends LogBlock> BlockBuilder<T, CustomRegistrate> log(CustomRegistrate parent, String name, NonNullFunction<Block.Properties, T> factory, Material material) {
    return this.entry(name, (callback) -> BlockBuilder.create(this, parent, name, callback, factory, material));
  }
}
