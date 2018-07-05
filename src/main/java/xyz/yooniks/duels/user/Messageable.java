package xyz.yooniks.duels.user;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public interface Messageable {

  default void sendMessage(CommandSender cs, String text) {
    cs.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
  }

  default void sendMessage(DuelUser user, String text) {
    user.getBukkitPlayer().ifPresent(player ->
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', text)));
  }

}
