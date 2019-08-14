package epicsquid.mysticalworld.network;

import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class MessagePlayerShoulderUpdate implements IMessage {
  private NBTTagCompound tag = new NBTTagCompound();
  private UUID id;

  public MessagePlayerShoulderUpdate() {
  }

  public MessagePlayerShoulderUpdate(EntityPlayer player, PlayerShoulderCapability cap) {
    this.tag = cap.writeNBT();
    this.id = player.getUniqueID();
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    long uuid1 = buf.readLong();
    long uuid2 = buf.readLong();
    id = new UUID(uuid1, uuid2);
    tag = ByteBufUtils.readTag(buf);
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeLong(id.getLeastSignificantBits());
    buf.writeLong(id.getMostSignificantBits());
    ByteBufUtils.writeTag(buf, tag);
  }

  public static class MessageHolder implements IMessageHandler<MessagePlayerShoulderUpdate, IMessage> {
    @SideOnly(Side.CLIENT)
    @Override
    public IMessage onMessage(final MessagePlayerShoulderUpdate message, final MessageContext ctx) {
      if (message != null) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player != null) {
          EntityPlayer target;
          if (player.getUniqueID().equals(message.id)) {
            target = player;
          } else {
            EntityPlayer otherPlayer = player.world.getPlayerEntityByUUID(message.id);
            if (otherPlayer != null) {
              target = otherPlayer;
            } else {
              return null;
            }
          }
          PlayerShoulderCapability cap = target.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
          if (cap != null) {
            cap.readNBT(message.tag);
          }
        }
      }

      return null;
    }
  }
}
