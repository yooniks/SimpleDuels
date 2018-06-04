package xyz.yooniks.duels.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.yooniks.duels.DuelsPlugin;

public class DuelSettings {

  //if I have time, I will use diorite configs or something else

  public static int DUEL$TIME_TO_START;

  public static final Location DUEL$LOCATION = new Location(
      Bukkit.getWorlds().get(0), 0.0D, 80.0D,0.0D);

  public static String MESSAGE$CHAT$DUEL_TIME_TO_START =
      "&7Your duel will start in: &6{SECONDS}&7 seconds!";
  public static String MESSAGE$CHAT$DUEL_CANCELLED_ERROR =
      "&7Your duel will start in: &6{SECONDS}&7 seconds!";

  public static void load(DuelsPlugin plugin) {
    final FileConfiguration cf = plugin.getConfig();
    DuelSettings.DUEL$TIME_TO_START = cf.getInt("duel.time-to-start");
    DuelSettings.MESSAGE$CHAT$DUEL_TIME_TO_START = cf.getString("duel.counting-message");
    DuelSettings.MESSAGE$CHAT$DUEL_CANCELLED_ERROR = cf.getString("duel.cancelled-error-message");
  }

}
