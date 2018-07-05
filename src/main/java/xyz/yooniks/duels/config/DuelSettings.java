package xyz.yooniks.duels.config;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.yooniks.duels.DuelsPlugin;

public class DuelSettings {

  //if I have time, I will use diorite configs or something else

  public static int DUEL$TIME_TO_START;

  public static String MESSAGE$CHAT$DUEL_TIME_TO_START;
  public static String MESSAGE$CHAT$DUEL_CANCELLED_ERROR;
  public static String MESSAGE$CHAT$STARTED;

  public static void setupWith(DuelsPlugin plugin) {
    final FileConfiguration cf = plugin.getConfig();

    DuelSettings.DUEL$TIME_TO_START = cf.getInt("duel.time-to-start");

    DuelSettings.MESSAGE$CHAT$DUEL_TIME_TO_START = cf.getString("duel.counting-message");
    DuelSettings.MESSAGE$CHAT$DUEL_CANCELLED_ERROR = cf.getString("duel.cancelled-error-message");
    DuelSettings.MESSAGE$CHAT$STARTED= cf.getString("duel.start-message");

  }

}
