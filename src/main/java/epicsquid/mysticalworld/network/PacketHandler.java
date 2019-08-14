package epicsquid.mysticalworld.network;

import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

  public static void registerMessages() {

    //Server 2 Client
    epicsquid.mysticallib.network.PacketHandler.registerMessage(MessagePlayerShoulderUpdate.MessageHolder.class, MessagePlayerShoulderUpdate.class, Side.CLIENT);
  }
}
