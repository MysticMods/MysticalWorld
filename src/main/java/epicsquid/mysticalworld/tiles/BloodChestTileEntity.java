/*package epicsquid.mysticalworld.tiles;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
@OnlyIn(
    value = Dist.CLIENT,
    _interface = IChestLid.class
)
public class BloodChestTileEntity extends TileEntity implements IChestLid, ITickableTileEntity {
  private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);
  *//**
 * The current angle of the lid (between 0 and 1)
 * <p>
 * The angle of the lid last tick
 * <p>
 * The number of players currently using this chest
 * <p>
 * A counter that is incremented once each tick. Used to determine when to recompute ; this is done every 200 ticks
 * (but staggered between different chests). However, the new value isn't actually sent to clients when it is
 * changed.
 * <p>
 * Returns the number of slots in the inventory.
 * <p>
 * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
 * clientside.
 * <p>
 * Returns the stack in the given slot.
 * <p>
 * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
 * <p>
 * Removes a stack from the given slot and returns it.
 * <p>
 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
 * <p>
 * Don't rename this method to canInteractWith due to conflicts with Container
 *//*
  protected float lidAngle;
  *//**
 * The angle of the lid last tick
 *//*
  protected float prevLidAngle;
  *//**
 * The number of players currently using this chest
 *//*
  protected int numPlayersUsing;
  *//**
 * A counter that is incremented once each tick. Used to determine when to recompute ; this is done every 200 ticks
 * (but staggered between different chests). However, the new value isn't actually sent to clients when it is
 * changed.
 *//*
  private int ticksSinceSync;
  private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
  private LockCode code = LockCode.EMPTY_CODE;
  private ITextComponent customName;
  protected ResourceLocation lootTable;
  protected long lootTableSeed;

  protected BloodChestTileEntity(TileEntityType<?> typeIn) {
    super(typeIn);
  }

  public BloodChestTileEntity() {
    this(TileEntityType.CHEST);
  }

  *//**
 * Returns the number of slots in the inventory.
 *//*
  public int getSizeInventory() {
    return 27;
  }

  protected ITextComponent getDefaultName() {
    return new TranslationTextComponent("container.chest");
  }

  public void tick() {
    int i = this.pos.getX();
    int j = this.pos.getY();
    int k = this.pos.getZ();
    ++this.ticksSinceSync;
    this.numPlayersUsing = calculatePlayersUsingSync(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
    this.prevLidAngle = this.lidAngle;
    float f = 0.1F;
    if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
      this.playSound(SoundEvents.BLOCK_CHEST_OPEN);
    }

    if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
      float f1 = this.lidAngle;
      if (this.numPlayersUsing > 0) {
        this.lidAngle += 0.1F;
      } else {
        this.lidAngle -= 0.1F;
      }

      if (this.lidAngle > 1.0F) {
        this.lidAngle = 1.0F;
      }

      float f2 = 0.5F;
      if (this.lidAngle < 0.5F && f1 >= 0.5F) {
        this.playSound(SoundEvents.BLOCK_CHEST_CLOSE);
      }

      if (this.lidAngle < 0.0F) {
        this.lidAngle = 0.0F;
      }
    }

  }

  public static int calculatePlayersUsingSync(World p_213977_0_, BloodChestTileEntity p_213977_1_, int p_213977_2_, int p_213977_3_, int p_213977_4_, int p_213977_5_, int p_213977_6_) {
    if (!p_213977_0_.isRemote && p_213977_6_ != 0 && (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) {
      p_213977_6_ = calculatePlayersUsing(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
    }

    return p_213977_6_;
  }

  public static int calculatePlayersUsing(World p_213976_0_, BloodChestTileEntity p_213976_1_, int p_213976_2_, int p_213976_3_, int p_213976_4_) {
    int i = 0;
    float f = 5.0F;

    for (PlayerEntity playerentity : p_213976_0_.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double) ((float) p_213976_2_ - 5.0F), (double) ((float) p_213976_3_ - 5.0F), (double) ((float) p_213976_4_ - 5.0F), (double) ((float) (p_213976_2_ + 1) + 5.0F), (double) ((float) (p_213976_3_ + 1) + 5.0F), (double) ((float) (p_213976_4_ + 1) + 5.0F)))) {
      if (playerentity.openContainer instanceof ChestContainer) {
        IInventory iinventory = ((ChestContainer) playerentity.openContainer).getLowerChestInventory();
        if (iinventory == p_213976_1_ || iinventory instanceof DoubleSidedInventory && ((DoubleSidedInventory) iinventory).isPartOfLargeChest(p_213976_1_)) {
          ++i;
        }
      }
    }

    return i;
  }

  private void playSound(SoundEvent soundIn) {
    ChestType chesttype = this.getBlockState().get(ChestBlock.TYPE);
    if (chesttype != ChestType.LEFT) {
      double d0 = (double) this.pos.getX() + 0.5D;
      double d1 = (double) this.pos.getY() + 0.5D;
      double d2 = (double) this.pos.getZ() + 0.5D;
      if (chesttype == ChestType.RIGHT) {
        Direction direction = ChestBlock.getDirectionToAttached(this.getBlockState());
        d0 += (double) direction.getXOffset() * 0.5D;
        d2 += (double) direction.getZOffset() * 0.5D;
      }

      this.world.playSound((PlayerEntity) null, d0, d1, d2, soundIn, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    }
  }

  *//**
 * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
 * clientside.
 *//*
  public boolean receiveClientEvent(int id, int type) {
    if (id == 1) {
      this.numPlayersUsing = type;
      return true;
    } else {
      return super.receiveClientEvent(id, type);
    }
  }

  public void openInventory(PlayerEntity player) {
    if (!player.isSpectator()) {
      if (this.numPlayersUsing < 0) {
        this.numPlayersUsing = 0;
      }

      ++this.numPlayersUsing;
      this.onOpenOrClose();
    }

  }

  public void closeInventory(PlayerEntity player) {
    if (!player.isSpectator()) {
      --this.numPlayersUsing;
      this.onOpenOrClose();
    }

  }

  protected void onOpenOrClose() {
    Block block = this.getBlockState().getBlock();
    if (block instanceof ChestBlock) {
      this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
      this.world.notifyNeighborsOfStateChange(this.pos, block);
    }

  }

  protected NonNullList<ItemStack> getItems() {
    return this.chestContents;
  }

  protected void setItems(NonNullList<ItemStack> itemsIn) {
    this.chestContents = itemsIn;
  }

  @OnlyIn(Dist.CLIENT)
  public float getLidAngle(float partialTicks) {
    return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
  }

  public static int getPlayersUsing(IBlockReader reader, BlockPos posIn) {
    BlockState blockstate = reader.getBlockState(posIn);
    if (blockstate.hasTileEntity()) {
      TileEntity tileentity = reader.getTileEntity(posIn);
      if (tileentity instanceof BloodChestTileEntity) {
        return ((BloodChestTileEntity) tileentity).numPlayersUsing;
      }
    }

    return 0;
  }

  public static void swapContents(BloodChestTileEntity chest, BloodChestTileEntity otherChest) {
    NonNullList<ItemStack> nonnulllist = chest.getItems();
    chest.setItems(otherChest.getItems());
    otherChest.setItems(nonnulllist);
  }

  protected Container createMenu(int id, PlayerInventory player) {
    return ChestContainer.createGeneric9X3(id, player, this);
  }

  @Override
  public void updateContainingBlockInfo() {
    super.updateContainingBlockInfo();
    if (this.chestHandler != null) {
      this.chestHandler.invalidate();
      this.chestHandler = null;
    }
  }

  @Override
  public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
    if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      if (this.chestHandler == null)
        this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
      return this.chestHandler.cast();
    }
    if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      return itemHandler.cast();
    return super.getCapability(cap, side);
  }

  private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
    BlockState state = this.getBlockState();
    if (!(state.getBlock() instanceof ChestBlock)) {
      return new net.minecraftforge.items.wrapper.InvWrapper(this);
    }
    IInventory inv = ChestBlock.getChestInventory((ChestBlock) state.getBlock(), state, getWorld(), getPos(), true);
    return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
  }

  public static void setLootTable(IBlockReader reader, Random rand, BlockPos p_195479_2_, ResourceLocation lootTableIn) {
    TileEntity tileentity = reader.getTileEntity(p_195479_2_);
    if (tileentity instanceof LockableLootTileEntity) {
      ((LockableLootTileEntity) tileentity).setLootTable(lootTableIn, rand.nextLong());
    }

  }

  protected boolean checkLootAndRead(CompoundNBT compound) {
    if (compound.contains("LootTable", 8)) {
      this.lootTable = new ResourceLocation(compound.getString("LootTable"));
      this.lootTableSeed = compound.getLong("LootTableSeed");
      return true;
    } else {
      return false;
    }
  }

  protected boolean checkLootAndWrite(CompoundNBT compound) {
    if (this.lootTable == null) {
      return false;
    } else {
      compound.putString("LootTable", this.lootTable.toString());
      if (this.lootTableSeed != 0L) {
        compound.putLong("LootTableSeed", this.lootTableSeed);
      }

      return true;
    }
  }

  public void fillWithLoot(@Nullable PlayerEntity player) {
    if (this.lootTable != null && this.world.getServer() != null) {
      LootTable loottable = this.world.getServer().getLootTableManager().getLootTableFromLocation(this.lootTable);
      if (player instanceof ServerPlayerEntity) {
        CriteriaTriggers.PLAYER_GENERATES_CONTAINER_LOOT.test((ServerPlayerEntity) player, this.lootTable);
      }

      this.lootTable = null;
      LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.world)).withParameter(LootParameters.field_237457_g_, Vector3d.copyCentered(this.pos)).withSeed(this.lootTableSeed);
      if (player != null) {
        lootcontext$builder.withLuck(player.getLuck()).withParameter(LootParameters.THIS_ENTITY, player);
      }

      loottable.fillInventory(this, lootcontext$builder.build(LootParameterSets.CHEST));
    }

  }

  public void setLootTable(ResourceLocation lootTableIn, long seedIn) {
    this.lootTable = lootTableIn;
    this.lootTableSeed = seedIn;
  }

  public boolean isEmpty() {
    this.fillWithLoot((PlayerEntity) null);
    return this.getItems().stream().allMatch(ItemStack::isEmpty);
  }

  *//**
 * Returns the stack in the given slot.
 *//*
  public ItemStack getStackInSlot(int index) {
    this.fillWithLoot((PlayerEntity) null);
    return this.getItems().get(index);
  }

  *//**
 * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
 *//*
  public ItemStack decrStackSize(int index, int count) {
    this.fillWithLoot((PlayerEntity) null);
    ItemStack itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
    if (!itemstack.isEmpty()) {
      this.markDirty();
    }

    return itemstack;
  }

  *//**
 * Removes a stack from the given slot and returns it.
 *//*
  public ItemStack removeStackFromSlot(int index) {
    this.fillWithLoot((PlayerEntity) null);
    return ItemStackHelper.getAndRemove(this.getItems(), index);
  }

  *//**
 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
 *//*
  public void setInventorySlotContents(int index, ItemStack stack) {
    this.fillWithLoot((PlayerEntity) null);
    this.getItems().set(index, stack);
    if (stack.getCount() > this.getInventoryStackLimit()) {
      stack.setCount(this.getInventoryStackLimit());
    }

    this.markDirty();
  }

  *//**
 * Don't rename this method to canInteractWith due to conflicts with Container
 *//*
  public boolean isUsableByPlayer(PlayerEntity player) {
    if (this.world.getTileEntity(this.pos) != this) {
      return false;
    } else {
      return !(player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) > 64.0D);
    }
  }

  public void clear() {
    this.getItems().clear();
  }

  public boolean canOpen(PlayerEntity p_213904_1_) {
    return canUnlock(p_213904_1_, this.code, this.getDisplayName()) && (this.lootTable == null || !p_213904_1_.isSpectator());
  }

  @Nullable
  public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
    if (this.canOpen(p_createMenu_3_)) {
      this.fillWithLoot(p_createMenu_2_.player);
      return this.createMenu(p_createMenu_1_, p_createMenu_2_);
    } else {
      return null;
    }
  }

  public void read(BlockState state, CompoundNBT nbt) {
    super.read(state, nbt);
    this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
    if (!this.checkLootAndRead(nbt)) {
      ItemStackHelper.loadAllItems(nbt, this.chestContents);
    }
    this.code = LockCode.read(nbt);
    if (nbt.contains("CustomName", 8)) {
      this.customName = ITextComponent.Serializer.getComponentFromJson(nbt.getString("CustomName"));
    }
  }

  public CompoundNBT write(CompoundNBT compound) {
    super.write(compound);
    this.code.write(compound);
    if (this.customName != null) {
      compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
    }
    if (!this.checkLootAndWrite(compound)) {
      ItemStackHelper.saveAllItems(compound, this.chestContents);
    }


    return compound;
  }

  public void setCustomName(ITextComponent name) {
    this.customName = name;
  }

  public ITextComponent getName() {
    return this.customName != null ? this.customName : this.getDefaultName();
  }

  public ITextComponent getDisplayName() {
    return this.getName();
  }

  @Nullable
  public ITextComponent getCustomName() {
    return this.customName;
  }

  public static boolean canUnlock(PlayerEntity p_213905_0_, LockCode p_213905_1_, ITextComponent p_213905_2_) {
    if (!p_213905_0_.isSpectator() && !p_213905_1_.func_219964_a(p_213905_0_.getHeldItemMainhand())) {
      p_213905_0_.sendStatusMessage(new TranslationTextComponent("container.isLocked", p_213905_2_), true);
      p_213905_0_.playSound(SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.BLOCKS, 1.0F, 1.0F);
      return false;
    } else {
      return true;
    }
  }

  @Nullable
  public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
    return this.canOpen(p_createMenu_3_) ? this.createMenu(p_createMenu_1_, p_createMenu_2_) : null;
  }

  @Override
  protected void invalidateCaps() {
    super.invalidateCaps();
    if (chestHandler != null)
      chestHandler.invalidate();
  }
}*/
