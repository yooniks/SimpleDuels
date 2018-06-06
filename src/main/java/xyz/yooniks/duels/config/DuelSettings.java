package xyz.yooniks.duels.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.yooniks.duels.DuelsPlugin;

public class DuelSettings {

  //if I have time, I will use diorite configs or something else

  public static int DUEL$TIME_TO_START;

  public static Location DUEL$LOCATION_A, DUEL$LOCATION_B;

  public static String MESSAGE$CHAT$DUEL_TIME_TO_START;
  public static String MESSAGE$CHAT$DUEL_CANCELLED_ERROR;
  public static String MESSAGE$CHAT$STARTED;

  public static void load(DuelsPlugin plugin) {
    final FileConfiguration cf = plugin.getConfig();

    DuelSettings.DUEL$TIME_TO_START = cf.getInt("duel.time-to-start");

    DuelSettings.MESSAGE$CHAT$DUEL_TIME_TO_START = cf.getString("duel.counting-message");
    DuelSettings.MESSAGE$CHAT$DUEL_CANCELLED_ERROR = cf.getString("duel.cancelled-error-message");
    DuelSettings.MESSAGE$CHAT$STARTED= cf.getString("duel.start-message");

    final ConfigurationSection locations = cf.getConfigurationSection("duel.start-locations");
    DuelSettings.DUEL$LOCATION_A = new Location(
        Bukkit.getWorld(locations.getString("a.world", "world")),
        locations.getInt("a.x", 0),
        locations.getInt("a.y", 80),
        locations.getInt("a.z", 0));
    DuelSettings.DUEL$LOCATION_B = new Location(
        Bukkit.getWorld(locations.getString("b.world", "world")),
        locations.getInt("b.x", 5),
        locations.getInt("b.y", 80),
        locations.getInt("b.z", 0));

  }

}
