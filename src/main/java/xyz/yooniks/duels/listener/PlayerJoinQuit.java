package xyz.yooniks.duels.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.yooniks.duels.DuelsPlugin;
import xyz.yooniks.duels.user.User;

public class PlayerJoinQuit implements Listener {

  private final DuelsPlugin plugin;

  public PlayerJoinQuit(DuelsPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    final User user = this.plugin.getUserManager().getOrCreateUser(event.getPlayer());
    user.heal();
    user.addDuelItems();
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {

  }

}
