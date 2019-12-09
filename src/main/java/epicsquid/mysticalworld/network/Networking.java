package epicsquid.mysticalworld.network;

import epicsquid.mysticallib.network.PacketHandler;

public class Networking {
  public static void init () {
    PacketHandler.registerMessage(ShoulderRide.class, ShoulderRide::encode, ShoulderRide::new, ShoulderRide::handle);
  }
}
