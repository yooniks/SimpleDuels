package xyz.yooniks.duels.user;

import java.util.UUID;
import org.bukkit.entity.Player;
import xyz.yooniks.duels.DuelsPlugin;
import xyz.yooniks.duels.util.ChatUtil;

public class User {

  private final String name;
  private final UUID uniqueId;

  private final DuelsPlugin plugin;

  public User(DuelsPlugin plugin, Player player) {
    this.plugin = plugin;
    this.name = player.getName();
    this.uniqueId = player.getUniqueId();
  }

  public void sendMessage(String text) {
    final Player player = this.plugin.getServer().getPlayer(this.uniqueId);
    if (player != null) {
      player.sendMessage(ChatUtil.fixColor(text));
    }
  }

  public void addDuelItems() {
    final Player player = this.plugin.getServer().getPlayer(this.uniqueId);
    this.plugin.addItems(player);
  }

  public void heal() {
    final Player player = this.plugin.getServer().getPlayer(this.uniqueId);
    player.setHealth(20.0D);
    player.setExp(0.0F);
    player.setLevel(0);
    player.setFoodLevel(20);
  }

}
