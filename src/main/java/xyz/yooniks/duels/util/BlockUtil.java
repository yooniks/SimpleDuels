package xyz.yooniks.duels.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class BlockUtil {

  private BlockUtil() {
  }

  public static void createIndividualWall(Player player) {

    //TODO: im pretty sure that will not work - so i am opened for pull requests

    final Location location = player.getLocation().clone();

    for (int x = location.getBlockX(); x < x + 5; x++) {
      location.setX(x);
      if (location.getWorld().getBlockAt(location).getType() != Material.AIR) {
        player.sendBlockChange(location, Material.GLASS, (byte) 14);
      }
    }
    for (int z = location.getBlockZ(); z < z + 5; z++) {
      location.setZ(z);
      if (location.getWorld().getBlockAt(location).getType() != Material.AIR) {
        player.sendBlockChange(location, Material.GLASS, (byte) 14);
      }
    }
    for (int y = location.getBlockY(); y < y + 5; y++) {
      location.setY(y);
      if (location.getWorld().getBlockAt(location).getType() != Material.AIR) {
        player.sendBlockChange(location, Material.GLASS, (byte) 14);
      }
    }
  }

}
