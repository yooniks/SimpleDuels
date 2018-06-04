package xyz.yooniks.duels.util;

import org.bukkit.ChatColor;

public final class ChatUtil {

  private ChatUtil() {
  }

  public static String fixColor(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

}
