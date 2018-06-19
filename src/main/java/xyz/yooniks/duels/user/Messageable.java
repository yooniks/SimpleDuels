package xyz.yooniks.duels.user;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface Messageable {

  default void sendMessage(CommandSender cs, String text) {
    cs.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
  }

  default void sendMessage(DuelUser user, String text) {
    final Player player = Bukkit.getPlayer(user.getUniqueId());
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
  }

}
